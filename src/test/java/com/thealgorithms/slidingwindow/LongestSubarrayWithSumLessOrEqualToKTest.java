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
        assertEquals(3, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[]{1, 2, 3, 4, 5}, 11));
        assertEquals(2, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[]{2, 1, 1, 1, 1}, 3));
        assertEquals(5, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[]{1, 2, 3, 4, 5}, 15));
        assertEquals(0, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[]{10, 20, 30}, 5));
        assertEquals(4, LongestSubarrayWithSumLessOrEqualToK.longestSubarrayWithSumLEK(new int[]{3, 1, 2, 1, 1}, 5));
    }
}
