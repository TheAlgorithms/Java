package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LongestSubarrayWithSumLessOrEqualToK class.
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public class LongestSubarrayWithSumLessOrEqualToKTest {

    @Test
    public void testLongestSubarrayWithSumLEK() {
        // Test cases for the longestSubarrayWithSumLEK method
        assertEquals(5, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {1, 2, 3, 4, 5}, 11));
        assertEquals(3, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {1, 2, 3, 4, 5}, 7));
        assertEquals(2, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {1, 2, 3, 4, 5}, 3));
        assertEquals(0, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {}, 0));
        assertEquals(4, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[] {2, 1, 5, 2, 3, 2}, 7));
    }
}
