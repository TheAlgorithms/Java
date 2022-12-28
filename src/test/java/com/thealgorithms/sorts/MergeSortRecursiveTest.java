package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MergeSortRecursiveTest {
	
//	private MergeSortRecursive mergeSortRecursive = new MergeSortRecursive();
	
	@Test
	void testMergeSortRecursiveCase1 () {
		MergeSortRecursive mergeSortRecursive = new MergeSortRecursive(Arrays.asList(5, 12, 9, 3, 15, 88));
		
		List<Integer> expected = Arrays.asList(3, 5, 9, 12, 15, 88);
		List<Integer> sorted = mergeSortRecursive.mergeSort();
		
		assertEquals(expected, sorted);
	}
	
	@Test
	void testMergeSortRecursiveCase2 () {
		MergeSortRecursive mergeSortRecursive = new MergeSortRecursive(Arrays.asList(-3, 5, 3, 4, 3, 7, 40, -20, 30, 0));
		
		List<Integer> expected = Arrays.asList(-20, -3, 0, 3, 3, 4, 5, 7, 30, 40);
		List<Integer> sorted = mergeSortRecursive.mergeSort();
		
		assertEquals(expected, sorted);
	}

}
