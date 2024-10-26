package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the MinSumKSizeSubarray class.
 *
 * @author Rashi Dashore (https://github.com/rashi07dashore)
 */
class MinSumKSizeSubarrayTest {

    /**
     * Test for the basic case of finding the minimum sum.
     */
    @Test
    void testMinSumKSizeSubarray() {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        int expectedMinSum = 6; // Corrected: Minimum sum of a subarray of size 3
        assertEquals(expectedMinSum, MinSumKSizeSubarray.minSumKSizeSubarray(arr, k));
    }

    /**
     * Test for a different array and subarray size.
     */
    @Test
    void testMinSumKSizeSubarrayWithDifferentValues() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        int expectedMinSum = 3; // 1 + 2
        assertEquals(expectedMinSum, MinSumKSizeSubarray.minSumKSizeSubarray(arr, k));
    }

    /**
     * Test for edge case with insufficient elements.
     */
    @Test
    void testMinSumKSizeSubarrayWithInsufficientElements() {
        int[] arr = {1, 2};
        int k = 3; // Not enough elements
        int expectedMinSum = -1; // Edge case
        assertEquals(expectedMinSum, MinSumKSizeSubarray.minSumKSizeSubarray(arr, k));
    }

    /**
     * Test for large array.
     */
    @Test
    void testMinSumKSizeSubarrayWithLargeArray() {
        int[] arr = {5, 4, 3, 2, 1, 0, -1, -2, -3, -4};
        int k = 5;
        int expectedMinSum = -10; // -1 + -2 + -3 + -4 + 0
        assertEquals(expectedMinSum, MinSumKSizeSubarray.minSumKSizeSubarray(arr, k));
    }

    /**
     * Test for array with negative numbers.
     */
    @Test
    void testMinSumKSizeSubarrayWithNegativeNumbers() {
        int[] arr = {-1, -2, -3, -4, -5};
        int k = 2;
        int expectedMinSum = -9; // -4 + -5
        assertEquals(expectedMinSum, MinSumKSizeSubarray.minSumKSizeSubarray(arr, k));
    }

    /**
     * Test for the case where k equals the array length.
     */
    @Test
    void testMinSumKSizeSubarrayWithKEqualToArrayLength() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 5;
        int expectedMinSum = 15; // 1 + 2 + 3 + 4 + 5
        assertEquals(expectedMinSum, MinSumKSizeSubarray.minSumKSizeSubarray(arr, k));
    }
}
