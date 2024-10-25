package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the MaxSumKSizeSubarray class.
 *
 * @author Your Name (https://github.com/Chiefpatwal)
 */
class MaxSumKSizeSubarrayTest {

    /**
     * Test for the basic case of finding the maximum sum.
     */
    @Test
    void testMaxSumKSizeSubarray() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        int expectedMaxSum = 9; // 4 + 5
        assertEquals(expectedMaxSum, MaxSumKSizeSubarray.maxSumKSizeSubarray(arr, k));
    }

    /**
     * Test for a different array and subarray size.
     */
    @Test
    void testMaxSumKSizeSubarrayWithDifferentValues() {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        int expectedMaxSum = 9; // 5 + 1 + 3
        assertEquals(expectedMaxSum, MaxSumKSizeSubarray.maxSumKSizeSubarray(arr, k));
    }

    /**
     * Test for edge case with insufficient elements.
     */
    @Test
    void testMaxSumKSizeSubarrayWithInsufficientElements() {
        int[] arr = {1, 2};
        int k = 3; // Not enough elements
        int expectedMaxSum = -1; // Edge case
        assertEquals(expectedMaxSum, MaxSumKSizeSubarray.maxSumKSizeSubarray(arr, k));
    }

    /**
     * Test for large array.
     */
    @Test
    void testMaxSumKSizeSubarrayWithLargeArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 5;
        int expectedMaxSum = 40; // 6 + 7 + 8 + 9 + 10
        assertEquals(expectedMaxSum, MaxSumKSizeSubarray.maxSumKSizeSubarray(arr, k));
    }

    /**
     * Test for array with negative numbers.
     */
    @Test
    void testMaxSumKSizeSubarrayWithNegativeNumbers() {
        int[] arr = {-1, -2, -3, -4, -5};
        int k = 2;
        int expectedMaxSum = -3; // -1 + -2
        assertEquals(expectedMaxSum, MaxSumKSizeSubarray.maxSumKSizeSubarray(arr, k));
    }

    /**
     * Test for the case where k equals the array length.
     */
    @Test
    void testMaxSumKSizeSubarrayWithKEqualToArrayLength() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 5;
        int expectedMaxSum = 15; // 1 + 2 + 3 + 4 + 5
        assertEquals(expectedMaxSum, MaxSumKSizeSubarray.maxSumKSizeSubarray(arr, k));
    }
}
