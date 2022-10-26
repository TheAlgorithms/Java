package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LongestPalindromicSubstringTest {

    @Test
    public void isLongestPalindromicSubstring() {
        assertEquals("ee", LongestPalindromicSubstring.longestPalindrome("Geeks"));
        assertEquals("anana", LongestPalindromicSubstring.longestPalindrome("bananas"));
        assertEquals("aca", LongestPalindromicSubstring.longestPalindrome("abracadabra"));
     }
}
