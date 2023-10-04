package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SimpleSortTest {

    private SimpleSort simpleSort = new SimpleSort();

    @Test
    public void simpleSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = simpleSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void simpleSortSingleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = simpleSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void simpleSortSingleStringArray() {
        String[] inputArray = {"s"};
        String[] outputArray = simpleSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void simpleSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        Integer[] outputArray = simpleSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void simpleSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        Integer[] outputArray = simpleSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void simpleSortNonDuplicateStringArray() {
        String[] inputArray = {"s", "b", "k", "a", "d", "c", "h"};
        String[] outputArray = simpleSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "h", "k", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void simpleSortDuplicateStringArray() {
        String[] inputArray = {"s", "b", "d", "a", "d", "c", "h", "b"};
        String[] outputArray = simpleSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "b", "c", "d", "d", "h", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }
}
