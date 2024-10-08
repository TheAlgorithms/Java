package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KadaneAlgorithmTest {

    @Test
    void testMaxSumWithPositiveValues() {
        // Test with all positive numbers
        int[] input = {89, 56, 98, 123, 26, 75, 12, 40, 39, 68, 91};
        int expectedMaxSum = 89 + 56 + 98 + 123 + 26 + 75 + 12 + 40 + 39 + 68 + 91; // sum of all elements
        assertTrue(KadaneAlgorithm.maxSum(input, expectedMaxSum));
    }

    @Test
    void testMaxSumWithMixedValues() {
        // Test with mixed positive and negative numbers
        int[] input = {1, -2, 3, 4, -1, 2, 1, -5, 4};
        int expectedMaxSum = 3 + 4 + -1 + 2 + 1; // max subarray is [3, 4, -1, 2, 1]
        assertTrue(KadaneAlgorithm.maxSum(input, expectedMaxSum));
    }

    @Test
    void testMaxSumWithAllNegativeValues() {
        // Test with all negative numbers
        int[] input = {-2, -3, -1, -4};
        int expectedMaxSum = -1; // max subarray is the least negative number
        assertTrue(KadaneAlgorithm.maxSum(input, expectedMaxSum));
    }

    @Test
    void testMaxSumWithSingleElement() {
        // Test with a single positive element
        int[] input = {10};
        int expectedMaxSum = 10; // max subarray is the single element
        assertTrue(KadaneAlgorithm.maxSum(input, expectedMaxSum));

        // Test with a single negative element
        input = new int[] {-10};
        expectedMaxSum = -10; // max subarray is the single element
        assertTrue(KadaneAlgorithm.maxSum(input, expectedMaxSum));
    }

    @Test
    void testMaxSumWithZero() {
        // Test with zeros in the array
        int[] input = {0, -1, 2, -2, 0, 3};
        int expectedMaxSum = 3; // max subarray is [2, -2, 0, 3]
        assertTrue(KadaneAlgorithm.maxSum(input, expectedMaxSum));
    }

    @Test
    void testMaxSumWithEmptyArray() {
        // Test with an empty array; should ideally throw an exception or return false
        int[] input = {};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> { KadaneAlgorithm.maxSum(input, 0); });
    }
}
