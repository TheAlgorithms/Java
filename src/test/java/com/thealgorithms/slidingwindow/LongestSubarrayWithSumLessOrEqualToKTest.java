package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LongestSubarrayWithSumLessOrEqualToK algorithm.
 */
public class LongestSubarrayWithSumLessOrEqualToKTest {

    /**
     * Tests for the longest subarray with a sum less than or equal to k.
     */
    @Test
    public void testLongestSubarrayWithSumLEK() {
        assertEquals(3, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {1, 2, 3, 4}, 6)); // {1, 2, 3}
        assertEquals(4, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {1, 2, 3, 4}, 10)); // {1, 2, 3, 4}
        assertEquals(2, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {5, 1, 2, 3}, 5)); // {5}
        assertEquals(0, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {1, 2, 3}, 0)); // No valid subarray
    }
}
