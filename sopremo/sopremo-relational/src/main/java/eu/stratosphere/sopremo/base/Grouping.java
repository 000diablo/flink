package eu.stratosphere.sopremo.base;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.NullNode;

import eu.stratosphere.sopremo.ElementaryOperator;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.JsonStream;
import eu.stratosphere.sopremo.JsonUtil;
import eu.stratosphere.sopremo.Operator;
import eu.stratosphere.sopremo.StreamArrayNode;
import eu.stratosphere.sopremo.aggregation.TransitiveAggregationFunction;
import eu.stratosphere.sopremo.expressions.AggregationExpression;
import eu.stratosphere.sopremo.expressions.ArrayCreation;
import eu.stratosphere.sopremo.expressions.ConstantExpression;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.PathExpression;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.PactJsonObject;
import eu.stratosphere.sopremo.pact.SopremoReduce;

public class Grouping extends MultiSourceOperator<Grouping> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1452280003631381562L;

	private final static EvaluationExpression NO_GROUPING = new ConstantExpression(NullNode.getInstance());

	private final EvaluationExpression projection;

	public Grouping(final EvaluationExpression projection, final JsonStream... inputs) {
		super(inputs);
		this.projection = projection;

		this.setDefaultKeyProjection(NO_GROUPING);
	}

	public Grouping(final EvaluationExpression projection, final List<? extends JsonStream> inputs) {
		super(inputs);
		this.projection = projection;

		this.setDefaultKeyProjection(NO_GROUPING);
	}

	@Override
	protected Operator createElementaryOperations(final List<Operator> inputs) {
		if (inputs.size() <= 1)
			return new GroupProjection(this.projection, inputs.get(0));

		final UnionAll union = new UnionAll(inputs);
		return new GroupProjection(new PathExpression(new AggregationExpression(new ArrayUnion()), this.projection),
			union);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Grouping other = (Grouping) obj;
		return this.projection.equals(other.projection);
	}

	@Override
	protected EvaluationExpression getDefaultValueProjection(final Output source) {
		if(super.getDefaultValueProjection(source) != EvaluationExpression.SAME_VALUE)
			return super.getDefaultValueProjection(source);
		if (this.getInputs().size() <= 1)
			return EvaluationExpression.SAME_VALUE;
		final EvaluationExpression[] elements = new EvaluationExpression[this.getInputs().size()];
		Arrays.fill(elements, EvaluationExpression.NULL);
		elements[this.getInputs().indexOf(source)] = EvaluationExpression.SAME_VALUE;
		return new ArrayCreation(elements);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.projection.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return String.format("%s to %s", super.toString(), this.projection);
	}

	private static final class ArrayUnion extends TransitiveAggregationFunction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5358556436487835033L;

		public ArrayUnion() {
			super("U<values>", new ArrayNode(null));
		}

		@Override
		protected JsonNode aggregate(final JsonNode mergedArray, final JsonNode array, final EvaluationContext context) {
			for (int index = 0; index < array.size(); index++) {
				if (mergedArray.size() <= index)
					((ArrayNode) mergedArray).add(JsonUtil.NODE_FACTORY.arrayNode());
				if (!array.get(index).isNull())
					((ArrayNode) mergedArray.get(index)).add(array.get(index));
			}
			return mergedArray;
		}
	}

	public static class GroupProjection extends ElementaryOperator {
		/**
		 * 
		 */
		private static final long serialVersionUID = 561729616462154707L;

		@SuppressWarnings("unused")
		private final EvaluationExpression projection;

		public GroupProjection(final EvaluationExpression projection, final JsonStream input) {
			super(input);
			this.projection = projection;
		}

		public static class Implementation extends
				SopremoReduce<PactJsonObject.Key, PactJsonObject, PactJsonObject.Key, PactJsonObject> {
			private EvaluationExpression projection;

			@Override
			protected void reduce(final JsonNode key1, final StreamArrayNode values, final JsonCollector out) {
				out.collect(key1, this.projection.evaluate(values, this.getContext()));
			}
		}
	}
}
