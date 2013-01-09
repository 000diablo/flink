/***********************************************************************************************************************
 *
 * Copyright (C) 2012 by the Stratosphere project (http://stratosphere.eu)
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

package eu.stratosphere.pact.compiler.operators;

import java.util.Collections;
import java.util.List;

import eu.stratosphere.pact.common.util.FieldSet;
import eu.stratosphere.pact.compiler.costs.Costs;
import eu.stratosphere.pact.compiler.dataproperties.GlobalProperties;
import eu.stratosphere.pact.compiler.dataproperties.LocalProperties;
import eu.stratosphere.pact.compiler.dataproperties.RequestedGlobalProperties;
import eu.stratosphere.pact.compiler.dataproperties.RequestedLocalProperties;
import eu.stratosphere.pact.compiler.plan.SingleInputNode;
import eu.stratosphere.pact.compiler.plan.candidate.Channel;
import eu.stratosphere.pact.compiler.plan.candidate.SingleInputPlanNode;
import eu.stratosphere.pact.runtime.shipping.ShipStrategyType;
import eu.stratosphere.pact.runtime.task.DriverStrategy;
import eu.stratosphere.pact.runtime.task.util.LocalStrategy;

/**
 * 
 */
public class OperatorPropertiesFactory
{
	/**
	 * Prevent external instantiation.
	 */
	private OperatorPropertiesFactory() {}
	
	
	// ============================================================================================
	// Implementations for Built-In Strategies
	// ============================================================================================

	public static final class GroupProperties extends OperatorDescriptorSingle
	{
		
		public GroupProperties(FieldSet keys) {
			super(keys);
		}
		
		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesHandler#getStrategy()
		 */
		@Override
		public DriverStrategy getStrategy() {
			return DriverStrategy.GROUP_OVER_ORDERED;
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesHandlerSingle#instantiate(eu.stratosphere.pact.compiler.plan.candidate.Channel, eu.stratosphere.pact.compiler.plan.SingleInputNode, eu.stratosphere.pact.common.util.FieldList)
		 */
		@Override
		public SingleInputPlanNode instantiate(Channel in, SingleInputNode node) {
			return new SingleInputPlanNode(node, in, DriverStrategy.GROUP_OVER_ORDERED, this.keyList);
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#createPossibleGlobalProperties()
		 */
		@Override
		protected List<RequestedGlobalProperties> createPossibleGlobalProperties() {
			RequestedGlobalProperties props = new RequestedGlobalProperties();
			props.setAnyPartitioning(this.keys);
			return Collections.singletonList(props);
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#createPossibleLocalProperties()
		 */
		@Override
		protected List<RequestedLocalProperties> createPossibleLocalProperties() {
			RequestedLocalProperties props = new RequestedLocalProperties();
			props.setGroupedFields(this.keys);
			return Collections.singletonList(props);
		}
		
		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#processPropertiesByStrategy(eu.stratosphere.pact.compiler.dataproperties.GlobalProperties, eu.stratosphere.pact.compiler.dataproperties.LocalProperties)
		 */
		@Override
		public void processPropertiesByStrategy(GlobalProperties gProps, LocalProperties lProps) {
			gProps.clearUniqueFieldSets();
			lProps.clearUniqueFieldSets();
		}
	}
	
	public static final class PartialGroupProperties extends OperatorDescriptorSingle
	{
		public PartialGroupProperties(FieldSet keys) {
			super(keys);
		}
		
		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesHandler#getStrategy()
		 */
		@Override
		public DriverStrategy getStrategy() {
			return DriverStrategy.PARTIAL_GROUP;
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesHandlerSingle#instantiate(eu.stratosphere.pact.compiler.plan.candidate.Channel, eu.stratosphere.pact.compiler.plan.SingleInputNode, eu.stratosphere.pact.common.util.FieldList)
		 */
		@Override
		public SingleInputPlanNode instantiate(Channel in, SingleInputNode node) {
			return new SingleInputPlanNode(node, in, DriverStrategy.PARTIAL_GROUP, this.keyList);
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#createPossibleGlobalProperties()
		 */
		@Override
		protected List<RequestedGlobalProperties> createPossibleGlobalProperties() {
			return Collections.singletonList(new RequestedGlobalProperties());
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#createPossibleLocalProperties()
		 */
		@Override
		protected List<RequestedLocalProperties> createPossibleLocalProperties() {
			RequestedLocalProperties props = new RequestedLocalProperties();
			props.setGroupedFields(this.keys);
			return Collections.singletonList(props);
		}
		
		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#processPropertiesByStrategy(eu.stratosphere.pact.compiler.dataproperties.GlobalProperties, eu.stratosphere.pact.compiler.dataproperties.LocalProperties)
		 */
		@Override
		public void processPropertiesByStrategy(GlobalProperties gProps, LocalProperties lProps) {
			gProps.clearUniqueFieldSets();
			lProps.clearUniqueFieldSets();
		}
	}
	
	public static final class GroupWithPartialPreGroupProperties extends OperatorDescriptorSingle
	{
		public GroupWithPartialPreGroupProperties(FieldSet keys) {
			super(keys);
		}
		
		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesHandler#getStrategy()
		 */
		@Override
		public DriverStrategy getStrategy() {
			return DriverStrategy.GROUP_OVER_ORDERED;
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesHandlerSingle#instantiate(eu.stratosphere.pact.compiler.plan.candidate.Channel, eu.stratosphere.pact.compiler.plan.SingleInputNode, eu.stratosphere.pact.common.util.FieldList)
		 */
		@Override
		public SingleInputPlanNode instantiate(Channel in, SingleInputNode node) {
			if (in.getShipStrategy() == ShipStrategyType.FORWARD) {
				// adjust a sort (changes grouping, so it must be for this driver to combining sort
				if (in.getLocalStrategy() == LocalStrategy.SORT) {
					if (!in.getLocalStrategyKeys().isValidUnorderedPrefix(this.keys)) {
						throw new RuntimeException("Bug: Inconsistent sort for group strategy.");
					}
					in.setLocalStrategy(LocalStrategy.COMBININGSORT, in.getLocalStrategyKeys(), in.getLocalStrategySortOrder());
				}
				return new SingleInputPlanNode(node, in, DriverStrategy.GROUP_OVER_ORDERED, this.keyList);
			} else {
				// non forward case. all local properties are killed anyways, so we can safely plug in a combiner
				Channel toCombiner = new Channel(in.getSource());
				toCombiner.setShipStrategy(ShipStrategyType.FORWARD);
				SingleInputPlanNode combiner = new SingleInputPlanNode(node, toCombiner, DriverStrategy.PARTIAL_GROUP, this.keyList);
				combiner.setCosts(new Costs(0, 0));
				
				Channel toReducer = new Channel(combiner);
				toReducer.setShipStrategy(in.getShipStrategy(), in.getShipStrategyKeys(), in.getShipStrategySortOrder());
				toReducer.setLocalStrategy(LocalStrategy.COMBININGSORT, in.getLocalStrategyKeys(), in.getLocalStrategySortOrder());
				return new SingleInputPlanNode(node, toReducer, DriverStrategy.GROUP_OVER_ORDERED, this.keyList);
			}
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#createPossibleGlobalProperties()
		 */
		@Override
		protected List<RequestedGlobalProperties> createPossibleGlobalProperties() {
			RequestedGlobalProperties props = new RequestedGlobalProperties();
			props.setAnyPartitioning(this.keys);
			return Collections.singletonList(props);
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#createPossibleLocalProperties()
		 */
		@Override
		protected List<RequestedLocalProperties> createPossibleLocalProperties() {
			RequestedLocalProperties props = new RequestedLocalProperties();
			props.setGroupedFields(this.keys);
			return Collections.singletonList(props);
		}
		
		/* (non-Javadoc)
		 * @see eu.stratosphere.pact.compiler.dataproperties.DriverPropertiesSingle#processPropertiesByStrategy(eu.stratosphere.pact.compiler.dataproperties.GlobalProperties, eu.stratosphere.pact.compiler.dataproperties.LocalProperties)
		 */
		@Override
		public void processPropertiesByStrategy(GlobalProperties gProps, LocalProperties lProps) {
			gProps.clearUniqueFieldSets();
			lProps.clearUniqueFieldSets();
		}
	}
}
