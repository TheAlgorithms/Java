package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BucketSortTest {

    @Test
    public void bucketSortSingleIntegerArray() {
        int[] inputArray = {4};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        int[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bucketSortNonDuplicateIntegerArray() {
        int[] inputArray = {6, 1, 99, 27, 15, 23, 36};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        int[] expectedOutput = {1, 6, 15, 23, 27, 36, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bucketSortDuplicateIntegerArray() {
        int[] inputArray = {6, 1, 27, 15, 23, 27, 36, 23};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        int[] expectedOutput = {1, 6, 15, 23, 23, 27, 27, 36};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bucketSortNonDuplicateIntegerArrayWithNegativeNum() {
        int[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        int[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bucketSortDuplicateIntegerArrayWithNegativeNum() {
        int[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        int[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }
}