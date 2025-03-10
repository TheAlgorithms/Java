package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckAnagramsTest {
    private static final String MESSAGE = "Strings must contain only lowercase English letters!";

    // CHECK METHOD isAnagrams()
    @Test
    public void testCheckAnagrams() {
        String testString1 = "STUDY";
        String testString2 = "DUSTY";
        Assertions.assertTrue(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    @Test
    public void testCheckFalseAnagrams() {
        String testString1 = "STUDY";
        String testString2 = "random";
        Assertions.assertFalse(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    @Test
    public void testCheckSameWordAnagrams() {
        String testString1 = "STUDY";
        Assertions.assertTrue(CheckAnagrams.isAnagrams(testString1, testString1));
    }

    @Test
    public void testCheckDifferentCasesAnagram() {
        String testString1 = "STUDY";
        String testString2 = "dusty";
        Assertions.assertTrue(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    // CHECK METHOD isAnagramsUnicode()
    // Below tests work with strings which consist of Unicode symbols & the algorithm is case-sensitive.
    @Test
    public void testStringAreValidAnagramsCaseSensitive() {
        Assertions.assertTrue(CheckAnagrams.isAnagramsUnicode("Silent", "liSten"));
        Assertions.assertTrue(CheckAnagrams.isAnagramsUnicode("This is a string", "is This a string"));
    }

    @Test
    public void testStringAreNotAnagramsCaseSensitive() {
        Assertions.assertFalse(CheckAnagrams.isAnagramsUnicode("Silent", "Listen"));
        Assertions.assertFalse(CheckAnagrams.isAnagramsUnicode("This is a string", "Is this a string"));
    }

    // CHECK METHOD isAnagramsOptimised()
    // Below tests work with strings which consist of only lowercase English letters
    @Test
    public void testOptimisedAlgorithmStringsAreValidAnagrams() {
        Assertions.assertTrue(CheckAnagrams.isAnagramsOptimised("silent", "listen"));
        Assertions.assertTrue(CheckAnagrams.isAnagramsOptimised("mam", "amm"));
    }

    @Test
    public void testOptimisedAlgorithmShouldThrowExceptionWhenStringsContainUppercaseLetters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> CheckAnagrams.isAnagramsOptimised("Silent", "Listen"));
        Assertions.assertEquals(exception.getMessage(), MESSAGE);

        exception = assertThrows(IllegalArgumentException.class, () -> Assertions.assertFalse(CheckAnagrams.isAnagramsOptimised("This is a string", "Is this a string")));
        Assertions.assertEquals(exception.getMessage(), MESSAGE);
    }
}
