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

import eu.stratosphere.pact.common.contract.CompilerHints;
import eu.stratosphere.pact.common.contract.Contract;
import eu.stratosphere.pact.common.contract.MapContract;
import eu.stratosphere.pact.compiler.DataStatistics;
import eu.stratosphere.pact.compiler.GlobalProperties;
import eu.stratosphere.pact.compiler.LocalProperties;
import eu.stratosphere.pact.compiler.OutputContract;
import eu.stratosphere.pact.compiler.costs.CostEstimator;
import eu.stratosphere.pact.runtime.task.util.OutputEmitter.ShipStrategy;
import eu.stratosphere.pact.runtime.task.util.TaskConfig.LocalStrategy;

/**
 * The Optimizer representation of a <i>Map</i> contract node.
 * 
 * @author Stephan Ewen (stephan.ewen@tu-berlin.de)
 */
public class MapNode extends SingleInputNode {
	private List<MapNode> cachedPlans; // a cache for the computed alternative plans

	/**
	 * Creates a new MapNode for the given contract.
	 * 
	 * @param pactContract
	 *        The map contract object.
	 */
	public MapNode(MapContract<?, ?, ?, ?> pactContract) {
		super(pactContract);
		setLocalStrategy(LocalStrategy.NONE);
	}

	/**
	 * Copy constructor to create a copy a MapNode with a different predecessor. The predecessor
	 * is assumed to be of the same type and merely a copy with different strategies, as they
	 * are created in the process of the plan enumeration.
	 * 
	 * @param template
	 *        The node to create a copy of.
	 * @param pred
	 *        The new predecessor.
	 * @param conn
	 *        The old connection to copy properties from.
	 * @param globalProps
	 *        The global properties of this copy.
	 * @param localProps
	 *        The local properties of this copy.
	 */
	protected MapNode(MapNode template, List<OptimizerNode> pred, List<PactConnection> conn, GlobalProperties globalProps,
			LocalProperties localProps) {
		super(template, pred, conn, globalProps, localProps);
		setLocalStrategy(LocalStrategy.NONE);
	}

	/**
	 * Gets the contract object for this map node.
	 * 
	 * @return The contract.
	 */
	@Override
	public MapContract<?, ?, ?, ?> getPactContract() {
		return (MapContract<?, ?, ?, ?>) super.getPactContract();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getName()
	 */
	@Override
	public String getName() {
		return "Map";
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#isMemoryConsumer()
	 */
	@Override
	public int getMemoryConsumerCount() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#setInputs(java.util.Map)
	 */
	@Override
	public void setInputs(Map<Contract, OptimizerNode> contractToNode) {
		super.setInputs(contractToNode);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#computeInterestingProperties()
	 */
	@Override
	public void computeInterestingPropertiesForInputs(CostEstimator estimator) {
		// the map itself has no interesting properties.
		// check, if there is an output contract that tells us that certain properties are preserved.
		// if so, propagate to the child.

		OutputContract oc = getOutputContract();
		if (oc == OutputContract.None) {
			for(PactConnection c : this.input)
				c.setNoInterestingProperties();
		} else if (oc == OutputContract.SameKey || oc == OutputContract.SuperKey) {
			List<InterestingProperties> thisNodesIntProps = getInterestingProperties();
			List<InterestingProperties> props = InterestingProperties.filterByOutputContract(thisNodesIntProps,
				getOutputContract());
			if (!props.isEmpty()) {
				for(PactConnection c : this.input)
					c.addAllInterestingProperties(props);
			} else {
				for(PactConnection c : this.input)
					c.setNoInterestingProperties();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#getAlternativePlans()
	 */
	@Override
	public List<MapNode> getAlternativePlans(CostEstimator estimator) {
		// check if we have a cached version
		if (this.cachedPlans != null) {
			return this.cachedPlans;
		}

		// when generating the different alternative plans for a map,
		// we need to take all alternative plans for input and
		// filter their properties by the output contract.
		// the remaining list is pruned.

		// the map itself also adds no cost for local strategies!

		// TODO: mjsax
		
		// set shipping strategies for all incoming connections
		List<MapNode> outputPlans = new ArrayList<MapNode>();
		getAlternativePlansRecursively(new ArrayList<OptimizerNode>(0), estimator, outputPlans);
		// right now we do not enumerate all plans
		// -> because of union we have to do a recursive enumeration, what is missing right now
//		List<OptimizerNode> allPreds = new ArrayList<OptimizerNode>(this.input.size());
//		for(PactConnection c : this.input) {
//			allPreds.add(c.getSourcePact());
//		}
//
//		final int inputSize = this.input.size();
//		List<List<OptimizerNode>> allPlanCombinations = new ArrayList<List<OptimizerNode>>(inputSize);
//		
//		for(int i = 0; i < inputSize; ++i) {
//			List<? extends OptimizerNode> inPlans = this.input.get(i).getSourcePact().getAlternativePlans(estimator);
//
//			final int numberOfAlternatives = inPlans.size();
//			List<OptimizerNode> alternativeList = new ArrayList<OptimizerNode>(numberOfAlternatives);
//			for(int j = 0; j < numberOfAlternatives; ++j) {
//				alternativeList.add(inPlans.get(j));
//			}
//			
//			allPlanCombinations.add(alternativeList);
//		}
//		
//		List<MapNode> outputPlans = new ArrayList<MapNode>();
//		for(PactConnection c : this.input) {
//			List<? extends OptimizerNode> inPlans = c.getSourcePact().getAlternativePlans(estimator);
//	
//			for (OptimizerNode pred : inPlans) {
//	
//				ShipStrategy ss = c.getShipStrategy() == ShipStrategy.NONE ? ShipStrategy.FORWARD : c.getShipStrategy();
//				GlobalProperties gp = PactConnection.getGlobalPropertiesAfterConnection(pred, this, ss);
//				LocalProperties lp = PactConnection.getLocalPropertiesAfterConnection(pred, this, ss);
//	
//				// we take each input and add a mapper to it
//				// the properties of the inputs are copied
//				MapNode nMap = new MapNode(this, allPreds, this.input, gp, lp);
//				for(PactConnection cc : nMap.getInputConnections()) {
//					cc.setShipStrategy(ss);
//				}
//	
//				// now, the properties (copied from the inputs) are filtered by the
//				// output contracts
//				nMap.getGlobalProperties().filterByOutputContract(getOutputContract());
//				nMap.getLocalProperties().filterByOutputContract(getOutputContract());
//	
//				// copy the cumulative costs and set the costs of the map itself to zero
//				estimator.costOperator(nMap);
//	
//				outputPlans.add(nMap);
//			}
//		}
		
		// prune the plans
		prunePlanAlternatives(outputPlans);

		// cache the result only if we have multiple outputs --> this function gets invoked multiple times
		if (this.getOutgoingConnections() != null && this.getOutgoingConnections().size() > 1) {
			this.cachedPlans = outputPlans;
		}

		return outputPlans;
	}
	
	private void getAlternativePlansRecursively(List<OptimizerNode> allPreds, CostEstimator estimator, List<MapNode> outputPlans) {
		// what is out recursive depth
		final int allPredsSize = allPreds.size();
		// pick the connection this recursive step has to process
		PactConnection connToProcess = this.input.get(allPredsSize);
		// get all alternatives for current recursion level
		List<? extends OptimizerNode> inPlans = connToProcess.getSourcePact().getAlternativePlans(estimator);
		
		// now enumerate all alternative of this recursion level
		for (OptimizerNode pred : inPlans) {
			// add an alternative plan node
			allPreds.add(pred);
			
			ShipStrategy ss = connToProcess.getShipStrategy() == ShipStrategy.NONE ? ShipStrategy.FORWARD : connToProcess.getShipStrategy();
			GlobalProperties gp = PactConnection.getGlobalPropertiesAfterConnection(pred, this, ss);
			LocalProperties lp = PactConnection.getLocalPropertiesAfterConnection(pred, this, ss);
			
			// check if the hit the last recursion level
			if(allPredsSize + 1 == this.input.size()) {
				// last recursion level: create a new alternative now
				
				MapNode nMap = new MapNode(this, allPreds, this.input, gp, lp);
				for(PactConnection cc : nMap.getInputConnections()) {
					cc.setShipStrategy(ss);
				}
	
				// now, the properties (copied from the inputs) are filtered by the
				// output contracts
				nMap.getGlobalProperties().filterByOutputContract(getOutputContract());
				nMap.getLocalProperties().filterByOutputContract(getOutputContract());
	
				// copy the cumulative costs and set the costs of the map itself to zero
				estimator.costOperator(nMap);
	
				outputPlans.add(nMap);
			} else {
				getAlternativePlansRecursively(allPreds, estimator, outputPlans);
			}
			
			// remove the added alternative plan node, in order to replace it with the next alternative at the beginning of the loop
			allPreds.remove(allPredsSize);
		}
	}
	
	/**
	 * Computes the number of keys that are processed by the PACT.
	 * 
	 * @return the number of keys processed by the PACT.
	 */
	private long computeNumberOfProcessedKeys() {
		long keySum = 0;
		
		for(PactConnection c : this.input) {
			OptimizerNode pred = c.getSourcePact();
		
			if(pred != null) {
				// if one input (all of them are unioned) does not know
				// its record count, we a pessimistic and return "unknown" as well
				if(pred.estimatedKeyCardinality == -1)
					return -1;
				
				// Each key is processed by Map
				// all inputs are union -> we sum up the keyCounts
				keySum += pred.estimatedKeyCardinality;
			} 
		}
		
		return keySum;
	}
	
	/**
	 * Computes the number of stub calls for one processed key. 
	 * 
	 * @return the number of stub calls for one processed key.
	 */
	private double computeStubCallsPerProcessedKey() {
		// we are pessimistic -> if one value is unknown we return "unknown" as well
		
		long numRecords = computeNumberOfStubCalls();
		if(numRecords == -1)
			return -1;
		
		long keyCardinality = computeNumberOfProcessedKeys();
		if(keyCardinality == -1)
			return -1;

		return numRecords / (double)keyCardinality;
	}
	
	/**
	 * Computes the number of stub calls.
	 * 
	 * @return the number of stub calls.
	 */
	private long computeNumberOfStubCalls() {
		long sumStubCalls = 0;
		
		for(PactConnection c : this.input) {
			OptimizerNode pred = c.getSourcePact();

			if(pred != null) {
				// if one input (all of them are unioned) does not know
				// its stub call count, we a pessimistic and return "unknown" as well
				if(pred.estimatedNumRecords == -1)
					return -1;
				
				// Map is called once per record
				// all inputs are union -> we sum up the stubCallCount
				sumStubCalls += pred.estimatedNumRecords; 
			}
			
		}
		
		return sumStubCalls;
	}
	
	/**
	 * Computes the width of output records
	 * 
	 * @return width of output records
	 */
	private double computeAverageRecordWidth() {
		CompilerHints hints = getPactContract().getCompilerHints();
		
		// use hint if available
		if(hints != null && hints.getAvgBytesPerRecord() != -1) {
			return hints.getAvgBytesPerRecord();
		}

		long numRecords = computeNumberOfStubCalls();
		// if unioned number of records is unknown,
		// we are pessimistic and return "unknown" as well
		if(numRecords == -1)
			return -1;
		
		long outputSize = 0;
		for(PactConnection c : this.input) {
			OptimizerNode pred = c.getSourcePact();
			
			if(pred != null) {
				// if one input (all of them are unioned) does not know
				// its output size, we a pessimistic and return "unknown" as well
				if(pred.estimatedOutputSize == -1)
					return -1;
				
				outputSize += pred.estimatedOutputSize;
			}
		}
		
		double result = outputSize / (double)numRecords;
		// a record must have at least one byte...
		if(result < 1)
			return 1;
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.compiler.plan.OptimizerNode#computeOutputEstimates(eu.stratosphere.pact.compiler.DataStatistics)
	 */
	@Override
	public void computeOutputEstimates(DataStatistics statistics) {
		// TODO: mjsax
		//OptimizerNode pred = input == null ? null : input.getSourcePact();
		OptimizerNode pred = null;
		CompilerHints hints = getPactContract().getCompilerHints();

		// check if preceding node is available
		if (pred == null) {
			// Preceding node is not available, we take hints as given
			this.estimatedKeyCardinality = hints.getKeyCardinality();
			
			if(hints.getKeyCardinality() != -1 && hints.getAvgNumValuesPerKey() != -1) {
				this.estimatedNumRecords = (hints.getKeyCardinality() * hints.getAvgNumValuesPerKey()) >= 1 ? 
						(long) (hints.getKeyCardinality() * hints.getAvgNumValuesPerKey()) : 1;
			}
			
			if(this.estimatedNumRecords != -1 && hints.getAvgBytesPerRecord() != -1) {
				this.estimatedOutputSize = (this.estimatedNumRecords * hints.getAvgBytesPerRecord() >= 1) ? 
						(long) (this.estimatedNumRecords * hints.getAvgBytesPerRecord()) : 1;
			}
			
		} else {
			// We have a preceding node
		
			// ############# set default estimates
			
			// default output cardinality is equal to number of stub calls
			this.estimatedNumRecords = this.computeNumberOfStubCalls();
			// default key cardinality is -1
			this.estimatedKeyCardinality = -1;
			// default output size is equal to output size of previous node
			this.estimatedOutputSize = pred.estimatedOutputSize;
						
			
			// ############# output cardinality estimation ##############
			
			boolean outputCardEstimated = true;
				
			if(hints.getKeyCardinality() != -1 && hints.getAvgNumValuesPerKey() != -1) {
				// we have precise hints
				this.estimatedNumRecords = (hints.getKeyCardinality() * hints.getAvgNumValuesPerKey() >= 1) ?
						(long) (hints.getKeyCardinality() * hints.getAvgNumValuesPerKey()) : 1;
			} else if(hints.getAvgRecordsEmittedPerStubCall() != 1.0) {
				// we know how many records are in average emitted per stub call
				this.estimatedNumRecords = (this.computeNumberOfStubCalls() * hints.getAvgRecordsEmittedPerStubCall() >= 1) ?
						(long) (this.computeNumberOfStubCalls() * hints.getAvgRecordsEmittedPerStubCall()) : 1;
			} else {
				outputCardEstimated = false;
			}
						
			// ############# output key cardinality estimation ##########

			if(hints.getKeyCardinality() != -1) {
				// number of keys is explicitly given by user hint
				this.estimatedKeyCardinality = hints.getKeyCardinality();
				
			} else if(!this.getOutputContract().equals(OutputContract.None)) {
				// we have an output contract which might help to estimate the number of output keys
				
				if(this.getOutputContract().equals(OutputContract.UniqueKey)) {
					// each output key is unique. Every record has a unique key.
					this.estimatedKeyCardinality = this.estimatedNumRecords;
					
				} else if(this.getOutputContract().equals(OutputContract.SameKey) || 
						this.getOutputContract().equals(OutputContract.SameKeyFirst) || 
						this.getOutputContract().equals(OutputContract.SameKeySecond)) {
					// we have a samekey output contract
					
					if(hints.getAvgRecordsEmittedPerStubCall() < 1.0) {
						// in average less than one record is emitted per stub call
						
						// compute the probability that at least one stub call emits a record for a given key 
						double probToKeepKey = 1.0 - Math.pow((1.0 - hints.getAvgRecordsEmittedPerStubCall()), this.computeStubCallsPerProcessedKey());

						this.estimatedKeyCardinality = (this.computeNumberOfProcessedKeys() * probToKeepKey >= 1) ?
								(long) (this.computeNumberOfProcessedKeys() * probToKeepKey) : 1;
					} else {
						// in average more than one record is emitted per stub call. We assume all keys are kept.
						this.estimatedKeyCardinality = this.computeNumberOfProcessedKeys();
					}
				}
			} else if(hints.getAvgNumValuesPerKey() != -1 && this.estimatedNumRecords != -1) {
				// we have a hint for the average number of records per key
				this.estimatedKeyCardinality = (this.estimatedNumRecords / hints.getAvgNumValuesPerKey() >= 1) ? 
						(long) (this.estimatedNumRecords / hints.getAvgNumValuesPerKey()) : 1;
			}
			 
			// try to reversely estimate output cardinality from key cardinality
			if(this.estimatedKeyCardinality != -1 && !outputCardEstimated) {
				// we could derive an estimate for key cardinality but could not derive an estimate for the output cardinality
				if(hints.getAvgNumValuesPerKey() != -1) {
					// we have a hint for average values per key
					this.estimatedNumRecords = (this.estimatedKeyCardinality * hints.getAvgNumValuesPerKey() >= 1) ?
							(long) (this.estimatedKeyCardinality * hints.getAvgNumValuesPerKey()) : 1;
				}
			}
			
				
			// ############# output size estimation #####################

			double estAvgRecordWidth = this.computeAverageRecordWidth();
			
			if(this.estimatedNumRecords != -1 && estAvgRecordWidth != -1) {
				// we have a cardinality estimate and width estimate

				this.estimatedOutputSize = (this.estimatedNumRecords * estAvgRecordWidth) >= 1 ? 
						(long)(this.estimatedNumRecords * estAvgRecordWidth) : 1;
			}
			
			// check that the key-card is maximally as large as the number of rows
			if (this.estimatedKeyCardinality > this.estimatedNumRecords) {
				this.estimatedKeyCardinality = this.estimatedNumRecords;
			}
		}
	}
}