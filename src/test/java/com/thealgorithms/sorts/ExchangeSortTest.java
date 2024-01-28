package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author vedant kasar (https://github.com/555vedant)
 * @see ExchangeSort
 */
public class ExchangeSortTest {

    private ExchangeSort exchangeSort = new ExchangeSort();

    @Test
    public void exchangeSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = exchangeSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void exchangeSortSingleIntegerElementArray() {
        Integer[] inputArray = {5};
        Integer[] outputArray = exchangeSort.sort(inputArray);
        Integer[] expectedOutput = {5};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void exchangeSortSingleStringElementArray() {
        String[] inputArray = {"v"};
        String[] outputArray = exchangeSort.sort(inputArray);
        String[] expectedOutput = {"v"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void exchangeSortIntegerArray() {
        Integer[] inputArray = {4, 23, -6, 78, 1, 54, 23, -6, -231, 9, 12};
        Integer[] outputArray = exchangeSort.sort(inputArray);
        Integer[] expectedOutput = {
            -231,
            -6,
            -6,
            1,
            4,
            9,
            12,
            23,
            23,
            54,
            78,
        };
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void exchangeSortStringArray() {
        String[] inputArray = {
            "cbf",
            "auk",
            "ó",
            "(b",
            "a",
            ")",
            "au",
            "á",
            "cba",
            "auk",
            "(a",
            "bhy",
            "cba",
        };
        String[] outputArray = exchangeSort.sort(inputArray);
        String[] expectedOutput = {
            "(a",
            "(b",
            ")",
            "a",
            "au",
            "auk",
            "auk",
            "bhy",
            "cba",
            "cba",
            "cbf",
            "á",
            "ó",
        };
        assertArrayEquals(outputArray, expectedOutput);
    }
}
