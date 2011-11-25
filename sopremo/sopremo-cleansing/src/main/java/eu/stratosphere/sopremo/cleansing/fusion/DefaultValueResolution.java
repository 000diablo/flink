package eu.stratosphere.sopremo.cleansing.fusion;

import eu.stratosphere.sopremo.type.JsonNode;

public class DefaultValueResolution extends ConflictResolution {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3038287806909149202L;

	private final JsonNode defaultValue;

	public DefaultValueResolution(final JsonNode defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public JsonNode fuse(final JsonNode[] values, final double[] weights, final FusionContext context) {
		return this.defaultValue;
	}
}
