package eu.stratosphere.sopremo.base;

import eu.stratosphere.pact.common.contract.MapContract;
import eu.stratosphere.pact.common.plan.PactModule;
import eu.stratosphere.pact.common.stub.Collector;
import eu.stratosphere.pact.common.type.Key;
import eu.stratosphere.pact.common.type.base.PactNull;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.JsonStream;
import eu.stratosphere.sopremo.Operator;
import eu.stratosphere.sopremo.expressions.EvaluableExpression;
import eu.stratosphere.sopremo.pact.PactJsonObject;
import eu.stratosphere.sopremo.pact.SopremoMap;
import eu.stratosphere.sopremo.pact.SopremoUtil;

public class Projection extends Operator {

	public Projection(EvaluableExpression transformation, JsonStream input) {
		super(transformation, input);
	}

	@Override
	public PactModule asPactModule(EvaluationContext context) {
		PactModule module = new PactModule(1, 1);
		MapContract<PactNull, PactJsonObject, Key, PactJsonObject> projectionMap = new MapContract<PactNull, PactJsonObject, Key, PactJsonObject>(
			ProjectionStub.class);
		module.getOutput(0).setInput(projectionMap);
		projectionMap.setInput(module.getInput(0));
		SopremoUtil.setTransformationAndContext(projectionMap.getStubParameters(), this.getTransformation(), context);
		return module;
	}

	public static class ProjectionStub extends SopremoMap<PactNull, PactJsonObject, Key, PactJsonObject> {

		@Override
		public void map(PactNull key, PactJsonObject value, Collector<Key, PactJsonObject> out) {
			out.collect(key,
				new PactJsonObject(this.getTransformation().evaluate(value.getValue(), this.getContext())));
		}
	}

}
