package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class RandomizedQuickSortTest {
    @Test
    public void testRandomizedQuickSort() {
        int[] arr = {3, 6, 8, 10, 1, 2, 1};
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        int[] expected = {1, 1, 2, 3, 6, 8, 10};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testRandomizedQuickSortEmptyArray() {
        int[] arr = {};
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        int[] expected = {};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testRandomizedQuickSortSingleElementArray() {
        int[] arr = {5};
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        int[] expected = {5};
        assertArrayEquals(expected, arr);
    }

}
