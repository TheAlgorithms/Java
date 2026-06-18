package com.thealgorithms.strings;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonSubstringTest {

    @Test
    public void testNullOrEmptyInputs() {
        assertEquals("", LongestCommonSubstring.longestCommonSubstring(null, "abc"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", null));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("", "abc"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", ""));
    }

    @Test
    public void testNormalSubstrings() {
        assertEquals("cde", LongestCommonSubstring.longestCommonSubstring("abcdef", "zcdemf"));
        assertEquals("abc", LongestCommonSubstring.longestCommonSubstring("abc", "abc"));
        assertEquals("cdef", LongestCommonSubstring.longestCommonSubstring("abcdef", "cdefgh"));
    }

    @Test
    public void testSingleCharacterAndNoMatch() {
        assertEquals("a", LongestCommonSubstring.longestCommonSubstring("a", "a"));
        assertEquals("", LongestCommonSubstring.longestCommonSubstring("abc", "xyz"));
    }

    @Test
    public void testMultipleMatchesFirstLongest() {
        // Keeps the first matched longest substring when lengths are tied
        assertEquals("abc", LongestCommonSubstring.longestCommonSubstring("abcXdef", "abcYdef"));
    }
}
