package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ShellSortTest {

    private ShellSort shellSort = new ShellSort();

    @Test
    public void shellSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = shellSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void shellSortSingleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = shellSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void shellSortSingleStringArray() {
        String[] inputArray = {"s"};
        String[] outputArray = shellSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void shellSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        Integer[] outputArray = shellSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void shellSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        Integer[] outputArray = shellSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void shellSortNonDuplicateStringArray() {
        String[] inputArray = {"s", "b", "k", "a", "d", "c", "h"};
        String[] outputArray = shellSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "h", "k", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void shellSortDuplicateStringArray() {
        String[] inputArray = {"s", "b", "d", "a", "d", "c", "h", "b"};
        String[] outputArray = shellSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "b", "c", "d", "d", "h", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }
}
