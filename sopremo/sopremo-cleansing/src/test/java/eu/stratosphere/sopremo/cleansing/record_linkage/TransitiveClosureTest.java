package eu.stratosphere.sopremo.cleansing.record_linkage;
import static eu.stratosphere.sopremo.JsonUtil.createObjectNode;
import junit.framework.Assert;

import org.junit.Test;

import eu.stratosphere.sopremo.SopremoTest;
import eu.stratosphere.sopremo.jsondatamodel.IntNode;
import eu.stratosphere.sopremo.jsondatamodel.JsonNode;
import eu.stratosphere.sopremo.testing.SopremoTestPlan;

public class TransitiveClosureTest extends SopremoTest<TransitiveClosure> {
	@Override
	protected TransitiveClosure createDefaultInstance(int index) {
		final TransitiveClosure transitiveClosure = new TransitiveClosure();
		transitiveClosure.setClosureMode(ClosureMode.values()[index]);
		// transitiveClosure.setIdProjection(new ObjectAccess(String.valueOf(index)));
		return transitiveClosure;
	}

	/**
	 * Tests the algorithm only
	 */
	@Test
	public void testWarshall() {
		final BinarySparseMatrix<JsonNode> matrix = new BinarySparseMatrix<JsonNode>();
		final JsonNode[] nodes = new JsonNode[6];
		for (int index = 0; index < nodes.length; index++)
			nodes[index] = IntNode.valueOf(index);

		matrix.set(nodes[0], nodes[1]);
		matrix.set(nodes[1], nodes[2]);
		matrix.set(nodes[2], nodes[3]);

		matrix.set(nodes[4], nodes[5]);
		matrix.makeSymmetric();

		TransitiveClosure.warshall(matrix);

		final BinarySparseMatrix<JsonNode> expected = new BinarySparseMatrix<JsonNode>();
		expected.set(nodes[0], nodes[1]);
		expected.set(nodes[1], nodes[2]);
		expected.set(nodes[2], nodes[3]);
		// newly found pairs
		expected.set(nodes[0], nodes[2]);
		expected.set(nodes[0], nodes[3]);
		expected.set(nodes[1], nodes[3]);

		expected.set(nodes[4], nodes[5]);
		expected.makeSymmetric();

		Assert.assertEquals(expected, matrix);
	}

	@Test
	public void testTransitiveClosureWithIdAndPairMode() {
		final TransitiveClosure transitiveClosure = new TransitiveClosure();
		transitiveClosure.setClosureMode(ClosureMode.LINKS);
		// transitiveClosure.setIdProjection(new ObjectAccess("id"));
		final SopremoTestPlan sopremoTestPlan = new SopremoTestPlan(transitiveClosure);
		sopremoTestPlan.getInput(0).
			addArray(createObjectNode("id", 11, "name", "a"), createObjectNode("id", 22, "name", "b")).
			addArray(createObjectNode("id", 11, "name", "a"), createObjectNode("id", 23, "name", "c")).
			addArray(createObjectNode("id", 14, "name", "e"), createObjectNode("id", 25, "name", "d")).
			addArray(createObjectNode("id", 16, "name", "a"), createObjectNode("id", 25, "name", "d"));
		sopremoTestPlan.getExpectedOutput(0).
			addArray(createObjectNode("id", 11, "name", "a"), createObjectNode("id", 22, "name", "b")).
			addArray(createObjectNode("id", 11, "name", "a"), createObjectNode("id", 23, "name", "c")).
			addArray(createObjectNode("id", 22, "name", "b"), createObjectNode("id", 23, "name", "c")).
			addArray(createObjectNode("id", 14, "name", "e"), createObjectNode("id", 25, "name", "d")).
			addArray(createObjectNode("id", 16, "name", "a"), createObjectNode("id", 25, "name", "d")).
			addArray(createObjectNode("id", 14, "name", "e"), createObjectNode("id", 16, "name", "a"));
		sopremoTestPlan.run();
	}
}
