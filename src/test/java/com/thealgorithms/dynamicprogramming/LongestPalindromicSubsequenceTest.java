package com.thealgorithms.dynamicprogramming;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
 
public class LongestPalindromicSubsequenceTest {
 
    @ParameterizedTest
    @CsvSource({
        "BBABCBCAB, BABCBAB",
        "BABCBAB,   BABCBAB",
        "A,         A",
        "AA,        AA",
        "AB,        A",
    })
    void testLpsKnownCases(String input, String expectedLps) {
        assertEquals(expectedLps.strip(), LongestPalindromicSubsequence.lps(input.strip()));
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
        // Only one character can form a palindrome when all chars are unique
        assertEquals(1, LongestPalindromicSubsequence.lps("ABCDE").length());
    }
 
    @Test
    void testLpsNullThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> LongestPalindromicSubsequence.lps(null));
    }
 
    @Test
    void testLpsResultIsActuallyPalindrome() {
        String result = LongestPalindromicSubsequence.lps("BBABCBCAB");
        String reversed = new StringBuilder(result).reverse().toString();
        assertEquals(result, reversed);
    }
 
    @Test
    void testLpsResultIsSubsequenceOfInput() {
        String input = "BBABCBCAB";
        String result = LongestPalindromicSubsequence.lps(input);
        int idx = 0;
        for (char c : result.toCharArray()) {
            idx = input.indexOf(c, idx);
            assert idx != -1 : "Result character not found in input at expected position";
            idx++;
        }
    }
}