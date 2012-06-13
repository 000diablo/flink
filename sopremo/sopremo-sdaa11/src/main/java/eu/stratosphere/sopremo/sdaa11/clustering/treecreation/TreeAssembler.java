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
package eu.stratosphere.sopremo.sdaa11.clustering.treecreation;

import java.util.Arrays;
import java.util.List;

import eu.stratosphere.sopremo.ElementaryOperator;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.ObjectAccess;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.SopremoReduce;
import eu.stratosphere.sopremo.sdaa11.clustering.Point;
import eu.stratosphere.sopremo.sdaa11.clustering.json.RepresentationNodes;
import eu.stratosphere.sopremo.sdaa11.clustering.tree.ClusterTree;
import eu.stratosphere.sopremo.sdaa11.json.AnnotatorNodes;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.ObjectNode;

/**
 * @author skruse
 * 
 */
public class TreeAssembler extends ElementaryOperator<TreeAssembler> {

	private static final long serialVersionUID = -1439186245691893155L;

	public static final int DEFAULT_TREE_WIDTH = 10;

	/**
	 * Specifies the degree of the created tree.
	 */
	private int treeWidth = DEFAULT_TREE_WIDTH;

	/**
	 * Returns the treeWidth.
	 * 
	 * @return the treeWidth
	 */
	public int getTreeWidth() {
		return this.treeWidth;
	}

	/**
	 * Sets the treeWidth to the specified value.
	 * 
	 * @param treeWidth
	 *            the treeWidth to set
	 */
	public void setTreeWidth(final int treeWidth) {

		this.treeWidth = treeWidth;
	}

	@Override
	public List<? extends EvaluationExpression> getKeyExpressions(
			final int inputIndex) {
		if (inputIndex != 0)
			throw new IllegalArgumentException("Illegal input index: "
					+ inputIndex);
		return Arrays.asList(new ObjectAccess(AnnotatorNodes.FLAT_ANNOTATION));
	}

	public static class Implementation extends SopremoReduce {

		/**
		 * @see TreeAssembler#treeWidth
		 */
		private int treeWidth;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * eu.stratosphere.sopremo.pact.SopremoReduce#reduce(eu.stratosphere
		 * .sopremo.type.IArrayNode, eu.stratosphere.sopremo.pact.JsonCollector)
		 */
		@Override
		protected void reduce(final IArrayNode values, final JsonCollector out) {

			System.out.println("Assembling tree from: " + values);

			final ClusterTree tree = new ClusterTree(this.treeWidth);

			for (final IJsonNode value : values) {
				final ObjectNode representationNode = (ObjectNode) value;
				final String clusterName = RepresentationNodes.getId(
						representationNode).getJavaValue();
				final Point clustroid = new Point();
				clustroid.read(RepresentationNodes
						.getClustroid(representationNode));
				tree.add(clustroid, clusterName);
			}

			out.collect(tree.write(null));
		}

	}

}
