package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonSubsequenceTest {

    @Test
    public void testLCSBasic() {
        String str1 = "ABCBDAB";
        String str2 = "BDCAB";
        String expected = "BDAB"; // The longest common subsequence
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSIdenticalStrings() {
        String str1 = "AGGTAB";
        String str2 = "AGGTAB";
        String expected = "AGGTAB"; // LCS is the same as the strings
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSNoCommonCharacters() {
        String str1 = "ABC";
        String str2 = "XYZ";
        String expected = ""; // No common subsequence
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSWithEmptyString() {
        String str1 = "";
        String str2 = "XYZ";
        String expected = ""; // LCS with an empty string should be empty
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSWithBothEmptyStrings() {
        String str1 = "";
        String str2 = "";
        String expected = ""; // LCS with both strings empty should be empty
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSWithNullFirstString() {
        String str1 = null;
        String str2 = "XYZ";
        String expected = null; // Should return null if first string is null
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSWithNullSecondString() {
        String str1 = "ABC";
        String str2 = null;
        String expected = null; // Should return null if second string is null
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSWithNullBothStrings() {
        String str1 = null;
        String str2 = null;
        String expected = null; // Should return null if both strings are null
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }

    @Test
    public void testLCSWithLongerStringContainingCommonSubsequence() {
        String str1 = "ABCDEF";
        String str2 = "AEBDF";
        String expected = "ABDF"; // Common subsequence is "ABDF"
        String result = LongestCommonSubsequence.getLCS(str1, str2);
        assertEquals(expected, result);
    }
}
