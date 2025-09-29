package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the MinimumWindowSubstring class.
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public class MinimumWindowSubstringTest {

    /**
     * Tests for MinimumWindowSubstring.minWindow.
     */
    @Test
    public void testMinimumWindowSubstring() {
        assertEquals("BANC", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        assertEquals("a", MinimumWindowSubstring.minWindow("a", "a")); // "a"
        assertEquals("", MinimumWindowSubstring.minWindow("a", "aa")); // ""
    }
}
