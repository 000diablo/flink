package eu.stratosphere.sopremo;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.pact.common.stub.CoGroupStub;
import eu.stratosphere.pact.common.type.Key;
import eu.stratosphere.pact.common.type.base.PactJsonObject;

public abstract class SopremoCoGroup<IK extends Key, IV1 extends PactJsonObject, IV2 extends PactJsonObject, OK extends Key, OV extends PactJsonObject>
		extends CoGroupStub<IK, IV1, IV2, OK, OV> {
	private Evaluable transformation;

	private EvaluationContext context;

	@Override
	public void configure(Configuration parameters) {
		this.transformation = SopremoUtil.getObject(parameters, "transformation", Evaluable.class);
		this.context = SopremoUtil.getObject(parameters, "context", EvaluationContext.class);
	}

	protected EvaluationContext getContext() {
		return this.context;
	}

	protected Evaluable getTransformation() {
		return this.transformation;
	}
}
