package eu.stratosphere.simple.jaql;

import java.lang.reflect.Field;
import java.util.List;

import com.ibm.jaql.json.type.JsonBool;
import com.ibm.jaql.json.type.JsonDecimal;
import com.ibm.jaql.json.type.JsonDouble;
import com.ibm.jaql.json.type.JsonLong;
import com.ibm.jaql.json.type.JsonValue;
import com.ibm.jaql.lang.expr.agg.Aggregate;
import com.ibm.jaql.lang.expr.core.ArrayExpr;
import com.ibm.jaql.lang.expr.core.ConstExpr;
import com.ibm.jaql.lang.expr.core.Expr;
import com.ibm.jaql.lang.expr.core.FixedRecordExpr;
import com.ibm.jaql.lang.expr.core.MathExpr;
import com.ibm.jaql.lang.expr.core.VarExpr;
import com.ibm.jaql.lang.expr.function.BuiltInFunction;
import com.ibm.jaql.lang.expr.function.BuiltInFunctionDescriptor;
import com.ibm.jaql.lang.expr.path.PathArrayAll;
import com.ibm.jaql.lang.expr.path.PathExpr;
import com.ibm.jaql.lang.expr.path.PathFieldValue;
import com.ibm.jaql.lang.expr.path.PathIndex;

import eu.stratosphere.simple.jaql.QueryParser.Binding;
import eu.stratosphere.sopremo.Operator;
import eu.stratosphere.sopremo.base.Aggregation;
import eu.stratosphere.sopremo.expressions.ArithmeticExpression;
import eu.stratosphere.sopremo.expressions.ArithmeticExpression.ArithmeticOperator;
import eu.stratosphere.sopremo.expressions.ArrayAccess;
import eu.stratosphere.sopremo.expressions.ArrayCreation;
import eu.stratosphere.sopremo.expressions.ConstantExpression;
import eu.stratosphere.sopremo.expressions.EvaluableExpression;
import eu.stratosphere.sopremo.expressions.ObjectAccess;
import eu.stratosphere.sopremo.expressions.FunctionCall;
import eu.stratosphere.sopremo.expressions.IdentifierAccess;
import eu.stratosphere.sopremo.expressions.InputSelection;
import eu.stratosphere.sopremo.expressions.PathExpression;
import eu.stratosphere.util.dag.converter.AppendChildren;
import eu.stratosphere.util.dag.converter.GraphConverter;
import eu.stratosphere.util.dag.converter.NodeConverter;

class EvaluableExpressionParser implements JaqlToSopremoParser<EvaluableExpression> {

	private final class AggregationFunctionConverter implements ExpressionConverter<Aggregate> {
		@Override
		public EvaluableExpression convertNode(Aggregate expr, List<EvaluableExpression> childPaths) {
			BuiltInFunctionDescriptor d = BuiltInFunction.getDescriptor(expr.getClass());
			return new FunctionCall(d.getName(), childPaths.toArray(new EvaluableExpression[childPaths.size()]));
		}
	}

	@AppendChildren(fromIndex = 1)
	private final class ArrayAccessConverter implements ExpressionConverter<PathIndex> {
		@Override
		public EvaluableExpression convertNode(PathIndex expr, List<EvaluableExpression> childPaths) {
			return new ArrayAccess(((ConstantExpression) childPaths.get(0)).asInt());
		}
	}

	private final class ArrayCreationConverter implements ExpressionConverter<ArrayExpr> {
		@Override
		public EvaluableExpression convertNode(ArrayExpr expr, List<EvaluableExpression> childPaths) {
			return new ArrayCreation(childPaths);
		}
	}

	private final class ConstantConverter implements ExpressionConverter<ConstExpr> {
		@Override
		public EvaluableExpression convertNode(ConstExpr expr, List<EvaluableExpression> childPaths) {
			if (expr.value == null)
				return null;
			// TODO: adjust to json model
			return new ConstantExpression(this.toJavaValue(expr.value));
		}

		private Object toJavaValue(JsonValue value) {
			if (value instanceof JsonLong)
				return ((JsonLong) value).longValue();
			if (value instanceof JsonDouble)
				return ((JsonDouble) value).doubleValue();
			if (value instanceof JsonDecimal)
				return ((JsonDecimal) value).decimalValue();
			if (value instanceof JsonBool)
				return ((JsonBool) value).get();
			return value.toString();
		}
	}

	private static interface ExpressionConverter<I extends Expr> extends NodeConverter<I, EvaluableExpression> {
	}

	@AppendChildren(fromIndex = 1)
	private final class FieldAccessConverter implements ExpressionConverter<PathFieldValue> {
		@Override
		public EvaluableExpression convertNode(PathFieldValue expr, List<EvaluableExpression> childPaths) {
			ObjectAccess fieldAccess = new ObjectAccess(((ConstExpr) expr.nameExpr()).value.toString());
			// if (childPaths.size() > 1)
			// spreadFields.put(fieldAccess, childPaths.subList(1, childPaths.size()));
			return fieldAccess;
		}
	}

	private final class FunctionConverter implements ExpressionConverter<Expr> {
		// function fall-back
		@Override
		public EvaluableExpression convertNode(Expr expr, List<EvaluableExpression> childPaths) {
			if (expr.getClass().getSimpleName().endsWith("Fn")) {
				BuiltInFunctionDescriptor d = BuiltInFunction.getDescriptor(expr.getClass());
				return new FunctionCall(d.getName(), childPaths.toArray(new EvaluableExpression[childPaths.size()]));
			}
			Operator operator = EvaluableExpressionParser.this.queryParser.parseOperator(expr);
			if (operator instanceof Aggregation && operator.getKeyTransformation() instanceof PathExpression)
				return new FunctionCall("distinct", operator.getKeyTransformation());
			// if (queryParser.bindings.get("$").getTransformed().equals(new Fragment.Input(0))
			// && queryParser.parsePath(expr.collectExpr()).equals(
			// new Fragment.ArrayCreation(new Fragment.Input(0))))
			// return new Fragment.Function("distinct", childPaths.get(childPaths.size() - 1));
			return null;
		}
	}

	private final class MathExprConverter implements ExpressionConverter<MathExpr> {
		private Field OpField;

		private ArithmeticOperator[] OperatorMapping = { ArithmeticOperator.ADDITION, ArithmeticOperator.SUBTRACTION,
			ArithmeticOperator.MULTIPLICATION, ArithmeticOperator.DIVISION };

		MathExprConverter() {
			try {
				this.OpField = MathExpr.class.getDeclaredField("op");
				this.OpField.setAccessible(true);
			} catch (Exception e) {
				throw new IllegalStateException("Cannot find op field", e);
			}
		}

		@Override
		public EvaluableExpression convertNode(MathExpr expr, List<EvaluableExpression> childPaths) {
			try {
				int op = (Integer) this.OpField.get(expr);
				return new ArithmeticExpression(childPaths.get(0), this.OperatorMapping[op], childPaths.get(1));
			} catch (Exception e) {
				throw new IllegalArgumentException("Cannot parse " + expr, e);
			}
		}
	}

	private final class ObjectCreationConverter implements ExpressionConverter<FixedRecordExpr> {
		@Override
		public EvaluableExpression convertNode(FixedRecordExpr expr, List<EvaluableExpression> childPaths) {
			return EvaluableExpressionParser.this.queryParser.parseObjectCreation(expr);
		}
	}

	private final class PathConverter implements ExpressionConverter<PathExpr> {
		@Override
		public EvaluableExpression convertNode(PathExpr expr, List<EvaluableExpression> childPaths) {
			for (int index = 0; index < childPaths.size(); index++) {
				if (childPaths.get(index) instanceof PathExpression) {
					PathExpression path = (PathExpression) childPaths.get(index);
					childPaths.set(index, path.getFragment(0));
					childPaths.addAll(index + 1, path.getFragments().subList(1, path.getFragments().size()));
					index += path.getFragments().size() - 1;
				}
			}
			return new PathExpression(childPaths);
		}
	}

	@AppendChildren
	private final class SpreadOperatorConverter implements ExpressionConverter<PathArrayAll> {
		@Override
		public EvaluableExpression convertNode(PathArrayAll expr, List<EvaluableExpression> childPaths) {
			return new ArrayAccess();
		}
	}

	private final class VariableLookup implements ExpressionConverter<VarExpr> {
		@Override
		public EvaluableExpression convertNode(VarExpr expr, List<EvaluableExpression> childPaths) {
			// if (!expr.var().taggedName().equals("$"))
			Binding binding = EvaluableExpressionParser.this.queryParser.bindings.get(expr.var().taggedName());
			if (binding == null)
				return EvaluableExpression.UNKNOWN;
			Object var = binding.getTransformed();
			if (EvaluableExpressionParser.this.queryParser.expressionToOperators.containsKey(binding.getExpr()))
				var = EvaluableExpressionParser.this.queryParser.expressionToOperators.get(binding.getExpr());
			if (var instanceof Operator) {
				int index = EvaluableExpressionParser.this.queryParser.findInputIndex((Operator) var);
				if (index != -1)
					return new InputSelection(index);
			}

			if (var instanceof EvaluableExpression)
				return (EvaluableExpression) var;

			return new IdentifierAccess(expr.var().taggedName());
		}
	}

	private GraphConverter<Expr, EvaluableExpression> converter = new GraphConverter<Expr, EvaluableExpression>();

	private QueryParser queryParser;

	@SuppressWarnings("unchecked")
	public EvaluableExpressionParser(QueryParser queryParser) {
		this.queryParser = queryParser;
		this.converter.registerAll(new PathConverter(), new VariableLookup(), new FieldAccessConverter(),
			new ConstantConverter(), new ArrayAccessConverter(), new ObjectCreationConverter(),
			new SpreadOperatorConverter(), new FunctionConverter(), new ArrayCreationConverter(),
			new AggregationFunctionConverter(), new MathExprConverter());
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.simple.jaql.JaqlToSopremoParser#parse(com.ibm.jaql.lang.expr.core.Expr)
	 */
	@Override
	public EvaluableExpression parse(Expr expr) {
		return this.converter.convertGraph(expr, ExprNavigator.INSTANCE);
	}
}
