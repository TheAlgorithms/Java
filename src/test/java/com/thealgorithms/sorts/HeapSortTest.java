package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class HeapSortTest {
	
	private HeapSort heapSort = new HeapSort();
	
	@Test
    void testHeapSortCase1() {
        Integer[] array = { 49, 4, 36, 9, 144, 1 };
        Integer[] sorted = heapSort.sort(array);
        Integer[] expected = { 1, 4, 9, 36, 49, 144 };
        assertArrayEquals(expected, sorted);
    }
	
	@Test
    void testHeapSortCase2() {
        Integer[] array = { };
        Integer[] sorted = heapSort.sort(array);
        Integer[] expected = { };
        assertArrayEquals(expected, sorted);
    }
	
	@Test
    void testHeapSortCase3 () {
        Integer[] array = { -3, 5, 3, 4, 3, 7, 40, -20, 30, 0 };
        Integer[] sorted = heapSort.sort(array);
        Integer[] expected = { -20, -3, 0, 3, 3, 4, 5, 7, 30, 40 };
        assertArrayEquals(expected, sorted);
    }

}
