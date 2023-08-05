package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Aitor Fidalgo (https://github.com/aitorfi)
 * @see BubbleSort
 */
public class BubbleSortTest {

    private BubbleSort bubbleSort = new BubbleSort();

    @Test
    public void bubbleSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortSingleIntegerElementArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortSingleStringElementArray() {
        String[] inputArray = {"s"};
        String[] outputArray = bubbleSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortIntegerArray() {
        Integer[] inputArray = {4, 23, -6, 78, 1, 54, 23, -6, -231, 9, 12};
        Integer[] outputArray = bubbleSort.sort(inputArray);
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
    public void bubbleSortStringArray() {
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
        String[] outputArray = bubbleSort.sort(inputArray);
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
