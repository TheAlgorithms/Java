package com.thealgorithms.sorts;
// added by @555vedant//


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ExchangeSortTest {
    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        ExchangeSort.exchangeSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        ExchangeSort.exchangeSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        ExchangeSort.exchangeSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testRandomOrderArray() {
        int[] arr = {3, 1, 4, 2, 5};
        int[] expected = {1, 2, 3, 4, 5};
        ExchangeSort.exchangeSort(arr);
        assertArrayEquals(expected, arr);
    }
}
