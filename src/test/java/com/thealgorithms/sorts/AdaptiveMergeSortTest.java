package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AdaptiveMergeSortTest {

    @Test
    public void testSortIntegers() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] expected = {1, 4, 6, 9, 12, 23, 54, 78, 231};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortStrings() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        String[] input = {"c", "a", "e", "b", "d"};
        String[] expected = {"a", "b", "c", "d", "e"};
        String[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortWithDuplicates() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {1, 3, 2, 2, 5, 4};
        Integer[] expected = {1, 2, 2, 3, 4, 5};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortEmptyArray() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {};
        Integer[] expected = {};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortSingleElement() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {42};
        Integer[] expected = {42};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }
}
