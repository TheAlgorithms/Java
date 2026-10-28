package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LongestPalindromicSubsequenceTest {

    @ParameterizedTest
    @CsvSource({"BBABCBCAB, BACBCAB", "BABCBAB, BABCBAB", "A, A", "AA, AA", "AB, B"})
    void testLpsKnownCases(String input, String expectedLps) {
        assertEquals(expectedLps, LongestPalindromicSubsequence.lps(input));
    }

    @Test
    void testLpsEmptyString() {
        assertEquals("", LongestPalindromicSubsequence.lps(""));
    }

    @Test
    void testLpsSingleCharacter() {
        assertEquals("Z", LongestPalindromicSubsequence.lps("Z"));
    }

    @Test
    void testLpsAllSameCharacters() {
        assertEquals("AAAA", LongestPalindromicSubsequence.lps("AAAA"));
    }

    @Test
    void testLpsAlreadyPalindrome() {
        assertEquals("RACECAR", LongestPalindromicSubsequence.lps("RACECAR"));
    }

    @Test
    void testLpsNoRepeatingCharacters() {
        assertEquals(1, LongestPalindromicSubsequence.lps("ABCDE").length());
    }

    @Test
    void testLpsNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> { LongestPalindromicSubsequence.lps(null); });
    }

    @Test
    void testLpsResultIsActuallyPalindrome() {
        String result = LongestPalindromicSubsequence.lps("BBABCBCAB");
        String reversed = new StringBuilder(result).reverse().toString();
        assertEquals(result, reversed);
    }
}
