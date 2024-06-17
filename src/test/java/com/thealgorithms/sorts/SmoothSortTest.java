package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SmoothSortTest {

    @Test
    public void smoothSortSingleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = SmoothSort.smoothSort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void smoothSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, 1, 99, 27, 15, 23, 36};
        Integer[] outputArray = SmoothSort.smoothSort(inputArray);
        Integer[] expectedOutput = {1, 6, 15, 23, 27, 36, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void smoothSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, 1, 27, 15, 23, 27, 36, 23};
        Integer[] outputArray = SmoothSort.smoothSort(inputArray);
        Integer[] expectedOutput = {1, 6, 15, 23, 23, 27, 27, 36};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void smoothSortNonDuplicateIntegerArrayWithNegativeNum() {
        Integer[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        Integer[] outputArray = SmoothSort.smoothSort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void smoothSortDuplicateIntegerArrayWithNegativeNum() {
        Integer[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        Integer[] outputArray = SmoothSort.smoothSort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }
}
