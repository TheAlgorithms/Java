package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class RecursiveInsertionSortTest {

    private final RecursiveInsertionSort sorter = new RecursiveInsertionSort();

    @Test
    void testEmptyArray() {
        Integer[] input = {};
        Integer[] expected = {};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    void testSingleElement() {
        Integer[] input = {1};
        Integer[] expected = {1};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    void testAlreadySorted() {
        Integer[] input = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    void testReverseSorted() {
        Integer[] input = {5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    void testRandomOrder() {
        Integer[] input = {3, 1, 4, 5, 2};
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    void testDuplicates() {
        Integer[] input = {4, 2, 5, 2, 3};
        Integer[] expected = {2, 2, 3, 4, 5};
        assertArrayEquals(expected, sorter.sort(input));
    }

    @Test
    void testStrings() {
        String[] input = {"banana", "apple", "cherry"};
        String[] expected = {"apple", "banana", "cherry"};
        assertArrayEquals(expected, sorter.sort(input));
    }
}