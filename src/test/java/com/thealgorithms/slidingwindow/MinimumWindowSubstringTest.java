package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LongestSubstringWithoutRepeatingCharacters class.
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public class MinimumWindowSubstringTest {

    /**
     * Tests for the longest subarray with a sum less than or equal to k.
     */
    @Test
    public void testMinimumWindowSubstring() {
        assertEquals(3, MinimumWindowSubstring.minWindow("ADOBECODEBANC","ABC"); // "BANC"
        assertEquals(4, MinimumWindowSubstring.minWindow("a","a"); // "a"
        assertEquals(2, MinimumWindowSubstring.minWindow("a","aa" // ""
    }
}
