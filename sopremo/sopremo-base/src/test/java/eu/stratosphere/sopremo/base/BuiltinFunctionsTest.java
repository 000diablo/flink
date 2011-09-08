package eu.stratosphere.sopremo.base;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import eu.stratosphere.sopremo.BuiltinFunctions;
import eu.stratosphere.sopremo.SopremoTest;
import static eu.stratosphere.sopremo.JsonUtil.*;

/**
 * Tests {@link BuiltinFunctions}
 */
public class BuiltinFunctionsTest {

	/**
	 * 
	 */
	@Test
	public void shouldCoerceDataWhenSumming() {
		Assert.assertEquals(
			6.4,
			BuiltinFunctions.sum(createArrayNode(1.1, 2, new BigDecimal("3.3"))).getDoubleValue(), 0.01);
	}

	/**
	 * 
	 */
	@Test
	public void shouldConcatenateObjects() {
		Assert.assertEquals(
			createValueNode("bla1blu2"),
			BuiltinFunctions.concat(new JsonNode[] {
				createValueNode("bla"),
				createValueNode(1),
				createValueNode("blu"),
				createValueNode(2), }));
	}

	/**
	 * 
	 */
	@Test
	public void shouldConcatenateStrings() {
		Assert.assertEquals(
			createValueNode("blabliblu"),
			BuiltinFunctions.concat(new JsonNode[] {
				createValueNode("bla"),
				createValueNode("bli"),
				createValueNode("blu") }));
	}

	/**
	 * 
	 */
	@Test
	public void shouldCountCompactArray() {
		Assert.assertEquals(
			createValueNode(3),
			BuiltinFunctions.count(createCompactArray(1, 2, 3)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldCountNormalArray() {
		Assert.assertEquals(
			createValueNode(3),
			BuiltinFunctions.count(createArrayNode(1, 2, 3)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldCountStreamArray() {
		Assert.assertEquals(
			createValueNode(3),
			BuiltinFunctions.count(SopremoTest.createStreamArray(1, 2, 3)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldCountZeroForEmptyArray() {
		Assert.assertEquals(
			createValueNode(0),
			BuiltinFunctions.count(createArrayNode()));
	}

//	/**
//	 * 
//	 */
//	@Test(expected = ClassCastException.class)
//	public void shouldFailIfIncompatible() {
//		BuiltinFunctions.sort(createArrayNode(3.14, 4, 1.2, 2));
//	}

	/**
	 * 
	 */
	@Test(expected = ClassCastException.class)
	public void shouldFailToSumIfNonNumbers() {
		BuiltinFunctions.sum(createArrayNode("test"));
	}

	/**
	 * 
	 */
	@Test
	public void shouldReturnEmptyStringWhenConcatenatingEmptyArray() {
		Assert.assertEquals(
			createValueNode(""), BuiltinFunctions.concat(new JsonNode[0]));
	}

	/**
	 * 
	 */
	@Test
	public void shouldSortArrays() {
		Assert.assertEquals(
			createArrayNode(new Number[] { 1, 2.4 }, new Number[] { 1, 3.4 }, new Number[] { 2, 2.4 },
				new Number[] { 2, 2.4, 3 }),
			BuiltinFunctions.sort(createArrayNode(new Number[] { 1, 3.4 }, new Number[] { 2, 2.4 },
				new Number[] { 1, 2.4 }, new Number[] { 2, 2.4, 3 })));
	}

	/**
	 * 
	 */
	@Test
	public void shouldSortDoubles() {
		Assert.assertEquals(
			createArrayNode(1.2, 2.0, 3.14, 4.5),
			BuiltinFunctions.sort(createArrayNode(3.14, 4.5, 1.2, 2.0)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldSortEmptyArray() {
		Assert.assertEquals(
			createArrayNode(),
			BuiltinFunctions.sort(createArrayNode()));
	}

	/**
	 * 
	 */
	@Test
	public void shouldSortIntegers() {
		Assert.assertEquals(
			createArrayNode(1, 2, 3, 4),
			BuiltinFunctions.sort(createArrayNode(3, 4, 1, 2)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldSumDoubles() {
		Assert.assertEquals(
			6.6,
			BuiltinFunctions.sum(createArrayNode(1.1, 2.2, 3.3)).getDoubleValue(), 0.01);
	}

	/**
	 * 
	 */
	@Test
	public void shouldSumEmptyArrayToZero() {
		Assert.assertEquals(
			createValueNode(0),
			BuiltinFunctions.sum(createArrayNode()));
	}

	/**
	 * 
	 */
	@Test
	public void shouldSumIntegers() {
		Assert.assertEquals(
			createValueNode(6),
			BuiltinFunctions.sum(createArrayNode(1, 2, 3)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldUnionAllCompactArrays() {
		Assert.assertEquals(
			createArrayNode(1, 2, 3, 4, 5, 6),
			BuiltinFunctions.unionAll(createCompactArray(1, 2, 3), createCompactArray(4, 5),
				createCompactArray(6)));
	}

	/**
	 * Very rare case...
	 */
	@Test
	public void shouldUnionAllMixedArrayTypes() {
		Assert.assertEquals(
			SopremoTest.createStreamArray(1, 2, 3, 4, 5, 6),
			BuiltinFunctions.unionAll(createArrayNode(1, 2, 3), createCompactArray(4, 5),
				SopremoTest.createStreamArray(6)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldUnionAllNormalArrays() {
		Assert.assertEquals(
			createArrayNode(1, 2, 3, 4, 5, 6),
			BuiltinFunctions.unionAll(createArrayNode(1, 2, 3), createArrayNode(4, 5),
				createArrayNode(6)));
	}

	/**
	 * 
	 */
	@Test
	public void shouldUnionAllStreamArrays() {
		Assert.assertEquals(
			SopremoTest.createStreamArray(1, 2, 3, 4, 5, 6),
			BuiltinFunctions.unionAll(SopremoTest.createStreamArray(1, 2, 3), SopremoTest.createStreamArray(4, 5),
				SopremoTest.createStreamArray(6)));
	}
}
