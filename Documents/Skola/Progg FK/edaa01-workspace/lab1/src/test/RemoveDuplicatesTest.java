package test;

import static org.junit.Assert.*;

import org.junit.Test;

import set.RemoveDuplicates;

public class RemoveDuplicatesTest {

	@Test
	public final void testExample() {
		int[] test = { 9, 7, 7, -2, 50, 50, 9 };
		int[] result = { -2, 7, 9, 50 };
		for (int i = 0; i < result.length; i++) {
			assertEquals("Result not correct", result[i], RemoveDuplicates.uniqueElements(test)[i]);
		}

	}

	@Test
	public final void testEmptyArray() {
		int[] i = null;
		assertNull("Result not null", RemoveDuplicates.uniqueElements(i));
	}

	@Test
	public final void testNegativeArray() {
		int[] test = { -1, -7, -7, -2, -50 };
		int[] result = { -50, -7, -2, -1 };
		for (int i = 0; i < result.length; i++) {
			assertEquals("Result not correct", result[i], RemoveDuplicates.uniqueElements(test)[i]);
		}
	}

}
