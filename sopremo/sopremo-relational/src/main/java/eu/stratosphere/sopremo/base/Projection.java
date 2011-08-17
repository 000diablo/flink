package eu.stratosphere.sopremo.base;

import org.codehaus.jackson.JsonNode;

import eu.stratosphere.pact.common.plan.PactModule;
import eu.stratosphere.sopremo.ElementaryOperator;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.JsonStream;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.PactJsonObject;
import eu.stratosphere.sopremo.pact.SopremoMap;

public class Projection extends ElementaryOperator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2170992457478875950L;

	private final EvaluationExpression keyTransformation, valueTransformation;

	public Projection(final EvaluationExpression keyTransformation, final EvaluationExpression valueTransformation,
			final JsonStream input) {
		super(input);
		if (valueTransformation == null || keyTransformation == null)
			throw new NullPointerException();
		this.keyTransformation = keyTransformation;
		this.valueTransformation = valueTransformation;
	}

	public Projection(final EvaluationExpression valueTransformation, final JsonStream input) {
		this(EvaluationExpression.SAME_KEY, valueTransformation, input);
	}

	@Override
	public PactModule asPactModule(EvaluationContext context) {
		return super.asPactModule(context);
	}
	//
	// @Override
	// public PactModule asPactModule(EvaluationContext context) {
	// PactModule module = new PactModule(this.toString(), 1, 1);
	// MapContract<PactJsonObject.Key, PactJsonObject, PactJsonObject.Key, PactJsonObject> projectionMap =
	// new MapContract<PactJsonObject.Key, PactJsonObject, PactJsonObject.Key, PactJsonObject>(
	// ProjectionStub.class);
	// module.getOutput(0).setInput(projectionMap);
	// projectionMap.setInput(module.getInput(0));
	// SopremoUtil.setContext(projectionMap.getStubParameters(), context);
	// SopremoUtil.serialize(projectionMap.getStubParameters(), "keyTransformation", this.getKeyTransformation());
	// SopremoUtil.serialize(projectionMap.getStubParameters(), "valueTransformation", this.getValueTransformation());
	// return module;
	// }

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Projection other = (Projection) obj;
		return this.keyTransformation.equals(other.keyTransformation)
			&& this.valueTransformation.equals(other.valueTransformation);
	}

	/**
	 * Returns the transformation of this operation that is applied to an input tuple to generate the output key.
	 * 
	 * @return the key transformation of this operation
	 */
	public EvaluationExpression getKeyTransformation() {
		return this.keyTransformation;
	}

	/**
	 * Returns the transformation of this operation that is applied to an input tuple to generate the output value.
	 * 
	 * @return the value transformation of this operation
	 */
	public EvaluationExpression getValueTransformation() {
		return this.valueTransformation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.keyTransformation.hashCode();
		result = prime * result + this.valueTransformation.hashCode();
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder(this.getName());
		builder.append(" to (").append(this.getKeyTransformation()).append(", ")
			.append(this.getValueTransformation()).append(")");
		return builder.toString();
	}

	public static class ProjectionStub extends
			SopremoMap<PactJsonObject.Key, PactJsonObject, PactJsonObject.Key, PactJsonObject> {

		private EvaluationExpression keyTransformation, valueTransformation;

		@Override
		protected void map(JsonNode key, JsonNode value, final JsonCollector out) {
			if (this.keyTransformation != EvaluationExpression.SAME_KEY)
				key = this.keyTransformation.evaluate(value, this.getContext());
			if (this.valueTransformation != EvaluationExpression.SAME_VALUE)
				value = this.valueTransformation.evaluate(value, this.getContext());
			out.collect(key, value);
		}
	}

}
