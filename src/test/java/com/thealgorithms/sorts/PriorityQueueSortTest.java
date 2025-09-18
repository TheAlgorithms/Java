package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class PriorityQueueSortTest {

    @Test
    void testNullArray() {
        int[] input = null;
        assertArrayEquals(null, PriorityQueueSort.sort(input));
    }

    @Test
    void testSingleElementArray() {
        int[] input = {5};
        int[] expected = {5};
        assertArrayEquals(expected, PriorityQueueSort.sort(input));
    }

    @Test
    void testSortNormalArray() {
        int[] input = {7, 2, 9, 4, 1};
        int[] expected = {1, 2, 4, 7, 9};
        assertArrayEquals(expected, PriorityQueueSort.sort(input));
    }

    @Test
    void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        assertArrayEquals(expected, PriorityQueueSort.sort(input));
    }

    @Test
    void testNegativeNumbers() {
        int[] input = {3, -1, 2, -5, 0};
        int[] expected = {-5, -1, 0, 2, 3};
        assertArrayEquals(expected, PriorityQueueSort.sort(input));
    }

    @Test
    void testAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, PriorityQueueSort.sort(input));
    }

    @Test
    void testArrayWithDuplicates() {
        int[] input = {5, 1, 3, 3, 2, 5};
        int[] expected = {1, 2, 3, 3, 5, 5};
        assertArrayEquals(expected, PriorityQueueSort.sort(input));
    }
}
