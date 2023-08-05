package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BeadSortTest {
    // BeadSort can't sort negative number, Character, String. It can sort positive number only
    private BeadSort beadSort = new BeadSort();

    @Test
    public void beadSortEmptyArray() {
        int[] inputArray = {};
        int[] outputArray = beadSort.sort(inputArray);
        int[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void beadSortSingleIntegerArray() {
        int[] inputArray = {4};
        int[] outputArray = beadSort.sort(inputArray);
        int[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortNonDuplicateIntegerArray() {
        int[] inputArray = {6, 1, 99, 27, 15, 23, 36};
        int[] outputArray = beadSort.sort(inputArray);
        int[] expectedOutput = {1, 6, 15, 23, 27, 36, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortDuplicateIntegerArray() {
        int[] inputArray = {6, 1, 27, 15, 23, 27, 36, 23};
        int[] outputArray = beadSort.sort(inputArray);
        int[] expectedOutput = {1, 6, 15, 23, 23, 27, 27, 36};
        assertArrayEquals(outputArray, expectedOutput);
    }
}