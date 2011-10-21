package eu.stratosphere.sopremo.pact;

import java.util.ArrayList;
import java.util.Iterator;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.nephele.template.AbstractTask;
import eu.stratosphere.pact.common.stub.CoGroupStub;
import eu.stratosphere.pact.common.stub.Collector;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.JsonUtil;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.JsonNode;

public abstract class SopremoCoGroup<IK extends JsonNode, IV1 extends JsonNode, IV2 extends JsonNode, OK extends JsonNode, OV extends JsonNode>
		extends CoGroupStub<JsonNode, JsonNode, JsonNode, JsonNode, JsonNode> {
	private EvaluationContext context;

	protected abstract void coGroup(JsonNode key, ArrayNode values1, ArrayNode values2, JsonCollector out);

	@Override
	public Class<JsonNode> getFirstInKeyType() {
		return SopremoUtil.WRAPPER_TYPE;
	}

	@Override
	public Class<JsonNode> getSecondInKeyType() {
		return SopremoUtil.WRAPPER_TYPE;
	}

	@Override
	public Class<JsonNode> getFirstInValueType() {
		return SopremoUtil.WRAPPER_TYPE;
	}

	@Override
	public Class<JsonNode> getSecondInValueType() {
		return SopremoUtil.WRAPPER_TYPE;
	}

	@Override
	public Class<JsonNode> getOutKeyType() {
		return SopremoUtil.WRAPPER_TYPE;
	}

	@Override
	public Class<JsonNode> getOutValueType() {
		return SopremoUtil.WRAPPER_TYPE;
	}

	@Override
	public void coGroup(final JsonNode key, Iterator<JsonNode> values1,
			Iterator<JsonNode> values2,
			final Collector<JsonNode, JsonNode> out) {
		this.context.increaseInputCounter();
		if (SopremoUtil.LOG.isTraceEnabled()) {
			final ArrayList<JsonNode> cached1 = new ArrayList<JsonNode>(), cached2 = new ArrayList<JsonNode>();
			while (values1.hasNext())
				cached1.add(values1.next());
			while (values2.hasNext())
				cached2.add(values2.next());
			SopremoUtil.LOG.trace(String
				.format("%s %s/%s/%s", this.getContext().operatorTrace(), key, cached1, cached2));
			values1 = cached1.iterator();
			values2 = cached2.iterator();
		}
		try {
			this.coGroup(SopremoUtil.unwrap(key),
				JsonUtil.wrapWithNode(this.needsResettableIterator(0, key, values1), new WrapperIterator(values1)),
				JsonUtil.wrapWithNode(this.needsResettableIterator(0, key, values2), new WrapperIterator(values2)),
				new JsonCollector(out));
		} catch (final RuntimeException e) {
			SopremoUtil.LOG.error(String.format("Error occurred @ %s with k/v/v %s/%s/%s: %s", this.getContext()
				.operatorTrace(), key, values1, values2, e));
			throw e;
		}
	}

	@Override
	public void configure(final Configuration parameters) {
		this.context = SopremoUtil.deserialize(parameters, "context", EvaluationContext.class);
		this.context.setTaskId(parameters.getInteger(AbstractTask.TASK_ID, 0));
		SopremoUtil.configureStub(this, parameters);
	}

	protected EvaluationContext getContext() {
		return this.context;
	}

	protected boolean needsResettableIterator(final int input, final JsonNode key,
			final Iterator<JsonNode> values) {
		return false;
	}
}
