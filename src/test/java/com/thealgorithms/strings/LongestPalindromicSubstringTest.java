package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestPalindromicSubstringTest {
  
    Solution solution = new Solution();

    @Test
    void shouldReturnEmptyStringWhenLengthIsZero() {
        String string = "";

        String longestPalindromeSubstring = solution.longestPalindrome(string);

        assertEquals("",longestPalindromeSubstring);
    }

    @Test
    void shouldReturnStringWhenLengthIsOne() {
        String string = "a";

        String longestPalindromeSubstring = solution.longestPalindrome(string);

        assertEquals("a",longestPalindromeSubstring);
    }

    @Test
    void shouldReturnSubstringOfLengthOneWhenInputIsabc() {
        String string = "abc";

        String longestPalindromeSubstring = solution.longestPalindrome(string);

        assertEquals("a",longestPalindromeSubstring);
    }

    @Test
    void shouldReturnPalindromeSubStringWhenStringIsGiven() {
        String string = "babac";

        String longestPalindromeSubstring = solution.longestPalindrome(string);

        assertEquals("bab",longestPalindromeSubstring);
    }

    @Test
    void shouldReturnLongestPalindromeSubStringWhenStringIsGiven() {
        String string = "aaaabbaa";

        String longestPalindromeSubstring = solution.longestPalindrome(string);

        assertEquals("aabbaa",longestPalindromeSubstring);
    }

}
