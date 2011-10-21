package eu.stratosphere.sopremo.cleansing.scrubbing;

import java.util.Arrays;
import java.util.List;

import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.SopremoExpression;
import eu.stratosphere.sopremo.type.JsonNode;

public abstract class CleansingRule<ContextType extends EvaluationContext> extends EvaluationExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1801909303463739160L;

	private final List<EvaluationExpression> targetPath;

	public CleansingRule(final EvaluationExpression... targetPath) {
		this.targetPath = Arrays.asList(targetPath);
	}

	public List<EvaluationExpression> getTargetPath() {
		return this.targetPath;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JsonNode evaluate(JsonNode node, EvaluationContext context) {
		return evaluateRule(node, (ContextType) context);
	}

	public abstract JsonNode evaluateRule(JsonNode node, ContextType context);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.targetPath.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		CleansingRule<?> other = (CleansingRule<?>) obj;
		return this.targetPath.equals(other.targetPath);
	}

}