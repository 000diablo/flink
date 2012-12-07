/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.pact.compiler.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.pact.common.contract.CompilerHints;
import eu.stratosphere.pact.common.plan.Visitor;
import eu.stratosphere.pact.common.stubs.StubAnnotation.ConstantFieldsFirst;
import eu.stratosphere.pact.common.stubs.StubAnnotation.ConstantFieldsFirstExcept;
import eu.stratosphere.pact.common.stubs.StubAnnotation.ConstantFieldsSecond;
import eu.stratosphere.pact.common.stubs.StubAnnotation.ConstantFieldsSecondExcept;
import eu.stratosphere.pact.common.util.FieldList;
import eu.stratosphere.pact.common.util.FieldSet;
import eu.stratosphere.pact.compiler.CompilerException;
import eu.stratosphere.pact.compiler.PactCompiler;
import eu.stratosphere.pact.compiler.costs.CostEstimator;
import eu.stratosphere.pact.compiler.dataproperties.InterestingProperties;
import eu.stratosphere.pact.compiler.plan.candidate.Channel;
import eu.stratosphere.pact.compiler.plan.candidate.PlanNode;
import eu.stratosphere.pact.generic.contract.Contract;
import eu.stratosphere.pact.generic.contract.DualInputContract;
import eu.stratosphere.pact.runtime.shipping.ShipStrategyType;
import eu.stratosphere.pact.runtime.task.util.LocalStrategy;

/**
 * A node in the optimizer plan that represents a PACT with a two different inputs, such as MATCH or CROSS.
 * The two inputs are not substitutable in their sides.
 * 
 * @author Stephan Ewen
 */
public abstract class TwoInputNode extends OptimizerNode
{
	private List<PlanNode> cachedPlans; // a cache for the computed alternative plans

	protected PactConnection input1; // The first input edge

	protected PactConnection input2; // The second input edge

	protected final FieldList keySet1; // The set of key fields for the first input
	
	protected final FieldList keySet2; // The set of key fields for the second input
	
	// ------------- Stub Annotations
	
	protected FieldSet constant1; // set of fields that are left unchanged by the stub
	
	protected FieldSet constant2; // set of fields that are left unchanged by the stub
	
	protected FieldSet notConstant1; // set of fields that are changed by the stub
	
	protected FieldSet notConstant2; // set of fields that are changed by the stub
	
	// --------------------------------------------------------------------------------------------
	
	/**
	 * Creates a new node with a single input for the optimizer plan.
	 * 
	 * @param pactContract
	 *        The PACT that the node represents.
	 */
	public TwoInputNode(DualInputContract<?> pactContract) {
		super(pactContract);

		this.keySet1 = new FieldList(pactContract.getKeyColumns(0));
		this.keySet2 = new FieldList(pactContract.getKeyColumns(1));
		
		if (this.keySet1.size() != this.keySet2.size()) {
			throw new CompilerException("Unequal number of key fields on the two inputs.");
		}
	}

	// ------------------------------------------------------------------------
	

	/**
	 * Gets the <tt>PactConnection</tt> through which this node receives its <i>first</i> input.
	 * 
	 * @return The first input connection.
	 */
	public PactConnection getFirstIncomingConnection() {
		return this.input1;
	}

	/**
	 * Gets the <tt>PactConnection</tt> through which this node receives its <i>second</i> input.
	 * 
	 * @return The second input connection.
	 */
	public PactConnection getSecondIncomingConnection() {
		return this.input2;
	}
	
	public OptimizerNode getFirstPredecessorNode() {
		if(this.input1 != null)
			return this.input1.getSourcePact();
		else
			return null;
	}

	public OptimizerNode getSecondPredecessorNode() {
		if(this.input2 != null)
			return this.input2.getSourcePact();
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getIncomingConnections()
	 */
	@Override
	public List<PactConnection> getIncomingConnections() {
		ArrayList<PactConnection> inputs = new ArrayList<PactConnection>(2);
		inputs.add(input1);
		inputs.add(input2);
		return inputs;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#setInputs(java.util.Map)
	 */
	@Override
	public void setInputs(Map<Contract, OptimizerNode> contractToNode) {
		// get the predecessors
		DualInputContract<?> contr = (DualInputContract<?>) getPactContract();
		
		List<Contract> leftPreds = contr.getFirstInputs();
		List<Contract> rightPreds = contr.getSecondInputs();
		
		OptimizerNode pred1;
		if (leftPreds.size() == 1) {
			pred1 = contractToNode.get(leftPreds.get(0));
		} else {
			pred1 = new UnionNode(getPactContract(), leftPreds, contractToNode);
			pred1.setDegreeOfParallelism(getDegreeOfParallelism());
			//push id down to newly created union node
			pred1.SetId(this.id);
			pred1.setSubtasksPerInstance(getSubtasksPerInstance());
			this.id++;
		}
		// create the connection and add it
		PactConnection conn1 = new PactConnection(pred1, this);
		this.input1 = conn1;
		pred1.addOutgoingConnection(conn1);
		
		OptimizerNode pred2;
		if (rightPreds.size() == 1) {
			pred2 = contractToNode.get(rightPreds.get(0));
		} else {
			pred2 = new UnionNode(getPactContract(), rightPreds, contractToNode);
			pred2.setDegreeOfParallelism(this.getDegreeOfParallelism());
			//push id down to newly created union node
			pred2.SetId(this.id);
			pred2.setSubtasksPerInstance(getSubtasksPerInstance());
			this.id++;
		}
		// create the connection and add it
		PactConnection conn2 = new PactConnection(pred2, this);
		this.input2 = conn2;
		pred2.addOutgoingConnection(conn2);

		// see if there is a hint that dictates which shipping strategy to use for BOTH inputs
		Configuration conf = getPactContract().getParameters();
		String shipStrategy = conf.getString(PactCompiler.HINT_SHIP_STRATEGY, null);
		if (shipStrategy != null) {
			if (PactCompiler.HINT_SHIP_STRATEGY_FORWARD.equals(shipStrategy)) {
				this.input1.setShipStrategy(ShipStrategyType.FORWARD);
				this.input2.setShipStrategy(ShipStrategyType.FORWARD);
			} else if (PactCompiler.HINT_SHIP_STRATEGY_BROADCAST.equals(shipStrategy)) {
				this.input1.setShipStrategy(ShipStrategyType.BROADCAST);
				this.input2.setShipStrategy(ShipStrategyType.BROADCAST);
			} else if (PactCompiler.HINT_SHIP_STRATEGY_REPARTITION.equals(shipStrategy)) {
				this.input1.setShipStrategy(ShipStrategyType.PARTITION_HASH);
				this.input2.setShipStrategy(ShipStrategyType.PARTITION_HASH);
			} else {
				throw new CompilerException("Unknown hint for shipping strategy: " + shipStrategy);
			}
		}

		// see if there is a hint that dictates which shipping strategy to use for the FIRST input
		shipStrategy = conf.getString(PactCompiler.HINT_SHIP_STRATEGY_FIRST_INPUT, null);
		if (shipStrategy != null) {
			if (PactCompiler.HINT_SHIP_STRATEGY_FORWARD.equals(shipStrategy)) {
				this.input1.setShipStrategy(ShipStrategyType.FORWARD);
			} else if (PactCompiler.HINT_SHIP_STRATEGY_BROADCAST.equals(shipStrategy)) {
				this.input1.setShipStrategy(ShipStrategyType.BROADCAST);
			} else if (PactCompiler.HINT_SHIP_STRATEGY_REPARTITION.equals(shipStrategy)) {
				this.input1.setShipStrategy(ShipStrategyType.PARTITION_HASH);
			} else {
				throw new CompilerException("Unknown hint for shipping strategy of input one: " + shipStrategy);
			}
		}

		// see if there is a hint that dictates which shipping strategy to use for the SECOND input
		shipStrategy = conf.getString(PactCompiler.HINT_SHIP_STRATEGY_SECOND_INPUT, null);
		if (shipStrategy != null) {
			if (PactCompiler.HINT_SHIP_STRATEGY_FORWARD.equals(shipStrategy)) {
				this.input2.setShipStrategy(ShipStrategyType.FORWARD);
			} else if (PactCompiler.HINT_SHIP_STRATEGY_BROADCAST.equals(shipStrategy)) {
				this.input2.setShipStrategy(ShipStrategyType.BROADCAST);
			} else if (PactCompiler.HINT_SHIP_STRATEGY_REPARTITION.equals(shipStrategy)) {
				this.input2.setShipStrategy(ShipStrategyType.PARTITION_HASH);
			} else {
				throw new CompilerException("Unknown hint for shipping strategy of input two: " + shipStrategy);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getAlternativePlans()
	 */
	@Override
	final public List<PlanNode> getAlternativePlans(CostEstimator estimator) {
		// check if we have a cached version
		if (this.cachedPlans != null) {
			return this.cachedPlans;
		}

		// step down to all producer nodes and calculate alternative plans
		final List<? extends PlanNode> subPlans1 = getFirstPredecessorNode().getAlternativePlans(estimator);
		final List<? extends PlanNode> subPlans2 = getSecondPredecessorNode().getAlternativePlans(estimator);
		final List<Channel> candidates1 = new ArrayList<Channel>(subPlans1.size());
		final List<Channel> candidates2 = new ArrayList<Channel>(subPlans2.size());
		
		List<InterestingProperties> ips = this.input1.getInterestingProperties();
		for (PlanNode p : subPlans1) {
			if (ips.isEmpty()) {
				// create a simple forwarding channel
				Channel c = new Channel(p);
				c.setShipStrategy(ShipStrategyType.FORWARD);
				c.setLocalStrategy(LocalStrategy.NONE);
				candidates1.add(c);
			} else {
				for (InterestingProperties ip : ips) {
					// create a channel that realizes the properties
					candidates1.add(ip.createChannelRealizingProperties(p));
				}
			}
		}
		
		ips = this.input2.getInterestingProperties();
		for (PlanNode p : subPlans2) {
			if (ips.isEmpty()) {
				// create a simple forwarding channel
				Channel c = new Channel(p);
				c.setShipStrategy(ShipStrategyType.FORWARD);
				c.setLocalStrategy(LocalStrategy.NONE);
				candidates2.add(c);
			} else {
				for (InterestingProperties ip : ips) {
					// create a channel that realizes the properties
					candidates2.add(ip.createChannelRealizingProperties(p));
				}
			}
		}
		
		
		final List<PlanNode> outputPlans = new ArrayList<PlanNode>(subPlans1.size() + subPlans2.size());
		for (Channel first : candidates1) {
			for (Channel second : candidates2) {
				if (areBranchCompatible(first, second)) {
					createPlanAlternative(first, second, outputPlans);
				}
			}
		}

		// prune the plans
		prunePlanAlternatives(outputPlans);

		this.cachedPlans = outputPlans;
		return outputPlans;
	}
	

	protected abstract void createPlanAlternative(Channel candidate1, Channel candidate2, List<PlanNode> outputPlans);
		
	/**
	 * Checks if the subPlan has a valid outputSize estimation.
	 * 
	 * @param subPlan		the subPlan to check
	 * 
	 * @return	{@code true} if all values are valid, {@code false} otherwise
	 */
	protected boolean haveValidOutputEstimates(OptimizerNode subPlan) {
		return subPlan.getEstimatedOutputSize() != -1;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#computeUnclosedBranchStack()
	 */
	@Override
	public void computeUnclosedBranchStack() {
		if (this.openBranches != null) {
			return;
		}

		addClosedBranches(this.getFirstPredecessorNode().closedBranchingNodes);
		addClosedBranches(this.getSecondPredecessorNode().closedBranchingNodes);
		
		List<UnclosedBranchDescriptor> result1 = new ArrayList<UnclosedBranchDescriptor>();
		// TODO: check if merge is really necessary
		result1 = mergeLists(result1, this.getFirstPredecessorNode().getBranchesForParent(this));
		
		List<UnclosedBranchDescriptor> result2 = new ArrayList<UnclosedBranchDescriptor>();
		// TODO: check if merge is really necessary
		result2 = mergeLists(result2, this.getSecondPredecessorNode().getBranchesForParent(this));

		this.openBranches = mergeLists(result1, result2);
	}

	// --------------------------------------------------------------------------------------------
	//                                 Stub Annotation Handling
	// --------------------------------------------------------------------------------------------
	
	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#readReadsAnnotation()
	 */
	@Override
	protected void readConstantAnnotation() {
		DualInputContract<?> c = (DualInputContract<?>)super.getPactContract();
		
		// get readSet annotation from stub
		ConstantFieldsFirst constantSet1Annotation = c.getUserCodeClass().getAnnotation(ConstantFieldsFirst.class);
		ConstantFieldsSecond constantSet2Annotation = c.getUserCodeClass().getAnnotation(ConstantFieldsSecond.class);
		
		// extract readSets from annotations
		if(constantSet1Annotation == null) {
			this.constant1 = null;
		} else {
			this.constant1 = new FieldSet(constantSet1Annotation.fields());
		}
		
		if(constantSet2Annotation == null) {
			this.constant2 = null;
		} else {
			this.constant2 = new FieldSet(constantSet2Annotation.fields());
		}
		
		
		// get readSet annotation from stub
		ConstantFieldsFirstExcept notConstantSet1Annotation = c.getUserCodeClass().getAnnotation(ConstantFieldsFirstExcept.class);
		ConstantFieldsSecondExcept notConstantSet2Annotation = c.getUserCodeClass().getAnnotation(ConstantFieldsSecondExcept.class);
		
		// extract readSets from annotations
		if(notConstantSet1Annotation == null) {
			this.notConstant1 = null;
		} else {
			this.notConstant1 = new FieldSet(notConstantSet1Annotation.fields());
		}
		
		if(notConstantSet2Annotation == null) {
			this.notConstant2 = null;
		} else {
			this.notConstant2 = new FieldSet(notConstantSet2Annotation.fields());
		}
		
		
		if (this.notConstant1 != null && this.constant1 != null) {
			throw new CompilerException("Either ConstantFieldsFirst or ConstantFieldsFirstExcept can be specified, not both.");
		}
		
		if (this.notConstant2 != null && this.constant2 != null) {
			throw new CompilerException("Either ConstantFieldsSecond or ConstantFieldsSecondExcept can be specified, not both.");
		}
	}
	
	/**
	 * Computes the width of output records
	 * 
	 * @return width of output records
	 */
	protected double computeAverageRecordWidth() {
		CompilerHints hints = getPactContract().getCompilerHints();

		if(hints.getAvgBytesPerRecord() != -1) {
			// use hint if available
			return hints.getAvgBytesPerRecord();
		}
	
		double avgRecordWidth = -1;
		
		if(this.getFirstPredecessorNode() != null && 
				this.getFirstPredecessorNode().estimatedOutputSize != -1 &&
				this.getFirstPredecessorNode().estimatedNumRecords != -1) {
			avgRecordWidth = (this.getFirstPredecessorNode().estimatedOutputSize / this.getFirstPredecessorNode().estimatedNumRecords);
			
		} else {
			return -1;
		}
		
		if(this.getSecondPredecessorNode() != null && 
				this.getSecondPredecessorNode().estimatedOutputSize != -1 &&
				this.getSecondPredecessorNode().estimatedNumRecords != -1) {
			
			avgRecordWidth += (this.getSecondPredecessorNode().estimatedOutputSize / this.getSecondPredecessorNode().estimatedNumRecords);
			
		} else {
			return -1;
		}

		return (avgRecordWidth < 1) ? 1 : avgRecordWidth;
	}

	/**
	 * Returns the key fields of the given input.
	 * 
	 * @param input The input for which key fields must be returned.
	 * @return the key fields of the given input.
	 */
	public FieldList getInputKeySet(int input) {
		switch(input) {
			case 0: return keySet1;
			case 1: return keySet2;
			default: throw new IndexOutOfBoundsException();
		}
	}
	
	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#isFieldConstant(int, int)
	 */
	@Override
	public boolean isFieldConstant(int input, int fieldNumber) {
		switch(input) {
		case 0:
			if (this.constant1 == null) {
				if (this.notConstant1 == null) {
					return false;
				} else {
					return !this.notConstant1.contains(fieldNumber);
				}
			} else {
				return this.constant1.contains(fieldNumber);
			}
		case 1:
			if (this.constant2 == null) {
				if (this.notConstant2 == null) {
					return false;
				} else {
					return !this.notConstant2.contains(fieldNumber);
				}
			} else {
				return this.constant2.contains(fieldNumber);
			}
		default:
			throw new IndexOutOfBoundsException();
		}
	}
	
	// --------------------------------------------------------------------------------------------
	//                                     Miscellaneous
	// --------------------------------------------------------------------------------------------
	
	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.pact.compiler.plan.OptimizerNode#accept(eu.stratosphere.pact.common.plan.Visitor
	 * )
	 */
	@Override
	public void accept(Visitor<OptimizerNode> visitor) {
		if (visitor.preVisit(this)) {
			if (this.input1 == null || this.input2 == null) {
				throw new CompilerException();
			}
			this.getFirstPredecessorNode().accept(visitor);
			this.getSecondPredecessorNode().accept(visitor);
			visitor.postVisit(this);
		}
	}
}
