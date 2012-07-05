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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import eu.stratosphere.pact.common.contract.Contract;
import eu.stratosphere.pact.common.plan.Visitor;
import eu.stratosphere.pact.common.util.FieldSet;
import eu.stratosphere.pact.compiler.GlobalProperties;
import eu.stratosphere.pact.compiler.LocalProperties;
import eu.stratosphere.pact.compiler.costs.CostEstimator;
import eu.stratosphere.pact.runtime.shipping.ShipStrategy;

/**
 * @author ringwald
 *
 */
public class UnionNode extends OptimizerNode {

	protected List<PactConnection> inConns;
	
	public UnionNode(Contract descendant, List<Contract> children, Map<Contract, OptimizerNode> contractToNode) {
		super(descendant);
		this.inConns = new LinkedList<PactConnection>();
		
		for (Contract child : children) {
			OptimizerNode pred = contractToNode.get(child);
			// create the connection and add it
			PactConnection conn = new PactConnection(pred, this);
			this.inConns.add(conn);
			pred.addOutConn(conn);
			conn.setShipStrategy(ShipStrategy.FORWARD);
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public UnionNode(UnionNode template, Stack<OptimizerNode> preds) {
		super(template, new GlobalProperties(), new LocalProperties());
		this.inConns = new LinkedList<PactConnection>();
		for (int i = 0; i < preds.size(); i++){
			OptimizerNode pred = preds.get(i);
			inConns.add(new PactConnection(template.inConns.get(i), pred, this));
		}
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#setInputs(java.util.Map)
	 */
	@Override
	public void setInputs(Map<Contract, OptimizerNode> contractToNode) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();

	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getIncomingConnections()
	 */
	@Override
	public List<PactConnection> getIncomingConnections() {
		// TODO Auto-generated method stub
		return inConns;
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#computeInterestingPropertiesForInputs(eu.stratosphere.pact.compiler.costs.CostEstimator)
	 */
	@Override
	public void computeInterestingPropertiesForInputs(CostEstimator estimator) {
		for (PactConnection inConn : inConns) {
			inConn.addAllInterestingProperties(getInterestingProperties());
		}
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#computeUnclosedBranchStack()
	 */
	@Override
	public void computeUnclosedBranchStack() {
		// TODO Auto-generated method stub
		if (this.openBranches != null) {
			return;
		}

		List<UnclosedBranchDescriptor> result = new ArrayList<UnclosedBranchDescriptor>();
		// TODO: check if merge is really necessary
		
		for (PactConnection inConn : inConns) {
			result = mergeLists(result, inConn.getSourcePact().getBranchesForParent(this));
		}
		
		this.openBranches = result;
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getAlternativePlans(eu.stratosphere.pact.compiler.costs.CostEstimator)
	 */
	@Override
	public List<? extends OptimizerNode> getAlternativePlans(
			CostEstimator estimator) {

		List<UnionNode> target = new LinkedList<UnionNode>();
		Stack<OptimizerNode> newInputs = new Stack<OptimizerNode>();
		List<List<? extends OptimizerNode>> inputs = new LinkedList<List<? extends OptimizerNode>>();
		for (PactConnection inConn : inConns) {
			inputs.add(inConn.getSourcePact().getAlternativePlans(estimator));
		}
		
		calcAlternatives(target, newInputs, 0, inputs);
		return target;
		
//		throw new UnsupportedOperationException();
	}
	
	public void calcAlternatives(List<UnionNode> target, Stack<OptimizerNode> newInputs, int index, List<List<? extends OptimizerNode>> inputs) {
		List<? extends OptimizerNode> alternativesAtLevel = inputs.get(index);
		
		for (OptimizerNode alternative : alternativesAtLevel) {
			newInputs.push(alternative);
			
			if (index < inputs.size() - 1) {
				calcAlternatives(target, newInputs, index + 1, inputs);
			}
			else {
				target.add(new UnionNode(this, newInputs));
			}
			
			newInputs.pop();
		}
		
	}
	
	@Override
	public PactType getPactType() {
		return PactType.Union;
	}


	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#accept(eu.stratosphere.pact.common.plan.Visitor)
	 */
	@Override
	public void accept(Visitor<OptimizerNode> visitor) {
		// TODO Auto-generated method stub
		
		if (visitor.preVisit(this)) {
			for (PactConnection inConn : this.inConns) {
				inConn.getSourcePact().accept(visitor);
			}
			visitor.postVisit(this);
		}
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getMemoryConsumerCount()
	 */
	@Override
	public int getMemoryConsumerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#readConstantAnnotation()
	 */
	@Override
	protected void readConstantAnnotation() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getConstantSet(int)
	 */
	@Override
	public FieldSet getConstantSet(int input) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#isFieldKept(int, int)
	 */
	@Override
	public boolean isFieldKept(int input, int fieldNumber) {
		return true;
	}

	public List<PactConnection> getUnionedIncommingConnections() {
		return inConns;
	}

}
