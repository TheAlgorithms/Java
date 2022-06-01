package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinTest {
	@Test
	public void testFindMinValue(){
		assertEquals(9, FindMin.findMin(new int[] {1,2,3,4,5,6,7,8,9}));
	}
	
	@Test
	public void testFindMinValue(){
		assertEquals(5, FindMin.findMin(new int[] {10,11,12,13,14}));
	}
}
