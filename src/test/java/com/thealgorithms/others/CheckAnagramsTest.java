package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.thealgorithms.strings.CheckAnagrams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 06/07/2023
 */
public class CheckAnagramsTest {
    private static final String MESSAGE = "Strings must contain only lowercase English letters!";

    // CHECK METHOD isAnagrams()
    @Test
    public void testStringsAreValidAnagrams() {
        Assertions.assertTrue(CheckAnagrams.isAnagrams("Silent", "Listen"));
        Assertions.assertTrue(CheckAnagrams.isAnagrams("This is a string", "Is this a string"));
    }

    @Test
    public void testStringsAreNotAnagrams() {
        Assertions.assertFalse(CheckAnagrams.isAnagrams("There", "Their"));
        Assertions.assertFalse(CheckAnagrams.isAnagrams("Mam", "Ama"));
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
