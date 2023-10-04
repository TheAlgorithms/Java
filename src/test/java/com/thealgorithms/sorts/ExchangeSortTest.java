package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ExchangeSortTest {

    private final ExchangeSort exchangeSort = new ExchangeSort();

    @Test
    public void exchangeSortEmptyArray() {
        int[] inputArray = {};
        int[] outputArray = exchangeSort.sort(inputArray);
        int[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void exchangeSortNegativeIntegerArray() {
        int[] inputArray = {-1, -10, -5, -2, -17, -99, -25, -50};
        int[] outputArray = exchangeSort.sort(inputArray);
        int[] expectedArray = {-99, -50, -25, -17, -10, -5, -2, -1};
        assertArrayEquals(outputArray, expectedArray);
    }

    @Test
    public void exchangeSortIntegerArray() {
        int[] inputArray = {22, 45, 78, 70, 15, 1, 0, 99};
        int[] outputArray = exchangeSort.sort(inputArray);
        int[] expectedArray = {0, 1, 15, 22, 45, 70, 78, 99};
        assertArrayEquals(outputArray, expectedArray);
    }
}
