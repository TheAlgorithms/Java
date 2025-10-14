package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the SmoothSort class.
 */
final class SmoothSortTest {

    @Test
    @DisplayName("Test with an empty array")
    void testSortEmptyArray() {
        Integer[] array = {};
        SmoothSort.sort(array);
        assertArrayEquals(new Integer[] {}, array);
    }

    @Test
    @DisplayName("Test with a single-element array")
    void testSortSingleElementArray() {
        Integer[] array = {42};
        SmoothSort.sort(array);
        assertArrayEquals(new Integer[] {42}, array);
    }

    @Test
    @DisplayName("Test with a simple unsorted array of integers")
    void testSortSimpleIntegerArray() {
        Integer[] array = {5, 8, 3, 1, 9, 4, 7, 2, 6};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SmoothSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("Test with an already sorted array (best case)")
    void testSortAlreadySortedArray() {
        Integer[] array = {10, 20, 30, 40, 50};
        Integer[] expected = {10, 20, 30, 40, 50};
        SmoothSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("Test with a reverse sorted array (worst case)")
    void testSortReverseSortedArray() {
        Integer[] array = {55, 44, 33, 22, 11};
        Integer[] expected = {11, 22, 33, 44, 55};
        SmoothSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("Test with an array containing duplicate elements")
    void testSortArrayWithDuplicates() {
        Integer[] array = {7, 2, 9, 2, 5, 7, 9, 1, 5};
        Integer[] expected = {1, 2, 2, 5, 5, 7, 7, 9, 9};
        SmoothSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("Test with an array of strings")
    void testSortStringArray() {
        String[] array = {"banana", "apple", "cherry", "date", "fig"};
        String[] expected = {"apple", "banana", "cherry", "date", "fig"};
        SmoothSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("Test with an array containing negative numbers")
    void testSortArrayWithNegativeNumbers() {
        Integer[] array = {-5, 8, -3, 0, 9, -4, 7, 2, -6};
        Integer[] expected = {-6, -5, -4, -3, 0, 2, 7, 8, 9};
        SmoothSort.sort(array);
        assertArrayEquals(expected, array);
    }
}
