package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BogoSortTest {

    private BogoSort bogoSort = new BogoSort();

    @Test
    public void bogoSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortSingleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortSingleStringArray() {
        String[] inputArray = {"s"};
        String[] outputArray = bogoSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortNonDuplicateStringArray() {
        String[] inputArray = {"s", "b", "k", "a", "d", "c", "h"};
        String[] outputArray = bogoSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "h", "k", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortDuplicateStringArray() {
        String[] inputArray = {"s", "b", "d", "a", "d", "c", "h", "b"};
        String[] outputArray = bogoSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "b", "c", "d", "d", "h", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }
}
