package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BucketSortTest {

    @Test
    public void sortSingleIntegerArray() {
        int[] inputArray = {4};
        int[] expectedOutput = {4};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortEmptyArray() {
        int[] inputArray = {};
        int[] expectedOutput = {};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortAlreadySortedArray() {
        int[] inputArray = {1, 2, 3, 4, 5, 6, 7};
        int[] expectedOutput = {1, 2, 3, 4, 5, 6, 7};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortNonDuplicateIntegerArray() {
        int[] inputArray = {6, 1, 99, 27, 15, 23, 36};
        int[] expectedOutput = {1, 6, 15, 23, 27, 36, 99};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortDuplicateIntegerArray() {
        int[] inputArray = {6, 1, 27, 15, 23, 27, 36, 23};
        int[] expectedOutput = {1, 6, 15, 23, 23, 27, 27, 36};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortNonDuplicateIntegerArrayWithNegativeNum() {
        int[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        int[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortDuplicateIntegerArrayWithNegativeNum() {
        int[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        int[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortLargeArray() {
        int[] inputArray = {100, 50, -100, 200, 0, 150, -50};
        int[] expectedOutput = {-100, -50, 0, 50, 100, 150, 200};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortArrayWithAllIdenticalElements() {
        int[] inputArray = {5, 5, 5, 5, 5};
        int[] expectedOutput = {5, 5, 5, 5, 5};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    public void sortArrayWithMixedPositiveAndNegativeNumbers() {
        int[] inputArray = {-3, 0, 2, -2, 3, 1, -1};
        int[] expectedOutput = {-3, -2, -1, 0, 1, 2, 3};
        int[] outputArray = BucketSort.bucketSort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
