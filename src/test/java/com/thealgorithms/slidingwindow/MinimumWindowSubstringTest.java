package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Finds the minimum window substring in {@code s} that contains all characters of {@code t}.
 *
 * @param s The input string to search within
 * @param t The string with required characters
 * @return The minimum window substring, or empty string if not found
 * @author  (https://github.com/Chiefpatwal)
 */
public class MinimumWindowSubstringTest {

    /**
     * Tests for MinimumWindowSubstring.minWindow.
     */
    @Test
    public void testMinimumWindowSubstring() {
        assertEquals("BANC", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("a", MinimumWindowSubstring.minWindow("a", "a"));
        assertEquals("", MinimumWindowSubstring.minWindow("a", "aa"));
        assertEquals("", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "XYZ"));
        assertEquals("BC", MinimumWindowSubstring.minWindow("ABCDEF", "BC"));
        assertEquals("q", MinimumWindowSubstring.minWindow("abcdefghijklmnopqrstuvwxyz", "q"));
        assertEquals("", MinimumWindowSubstring.minWindow("zzzzzzzzz", "zzzzzzzzzz"));
        assertEquals("abbbbbcdd", MinimumWindowSubstring.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        assertEquals("ABCDEFG", MinimumWindowSubstring.minWindow("ABCDEFG", "ABCDEFG"));
        assertEquals("", MinimumWindowSubstring.minWindow("abc", "A"));
        assertEquals("A", MinimumWindowSubstring.minWindow("aAbBcC", "A"));
        assertEquals("AABBC", MinimumWindowSubstring.minWindow("AAABBC", "AABC"));
    }
}
