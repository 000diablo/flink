package eu.stratosphere.sopremo.expressions;

import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.type.IJsonNode;

public class TraceExpression extends EvaluationExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3935412444889830869L;

	private final EvaluationExpression expression;

	public TraceExpression(final EvaluationExpression expression) {
		this.expression = expression;
	}

	public TraceExpression() {
		this(VALUE);
	}

	@Override
	public IJsonNode evaluate(final IJsonNode node, IJsonNode target, final EvaluationContext context) {
		SopremoUtil.LOG.info(this.expression.evaluate(node, target, context));
		return node;
	}

}
