package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RandomizedQuickSort class.
 */
public class RandomizedQuickSortTest {

    /**
     * Tests sorting of an array with multiple elements, including duplicates.
     */
    @Test
    public void testRandomizedQuickSortMultipleElements() {
        int[] arr = {3, 6, 8, 10, 1, 2, 1};
        int[] expected = {1, 1, 2, 3, 6, 8, 10};
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

    /**
     * Tests sorting of an empty array.
     */
    @Test
    public void testRandomizedQuickSortEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

    /**
     * Tests sorting of an array with a single element.
     */
    @Test
    public void testRandomizedQuickSortSingleElement() {
        int[] arr = {5};
        int[] expected = {5};
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }
}
