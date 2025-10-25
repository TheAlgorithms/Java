package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class GenerateSubsetsTest {

    @Test
    @DisplayName("Subsets of 'abc'")
    void testSubsetsOfABC() {
        assertSubsets("abc", Arrays.asList("abc", "ab", "ac", "a", "bc", "b", "c", ""));
    }

    @Test
    @DisplayName("Subsets of 'cbf'")
    void testSubsetsOfCBF() {
        assertSubsets("cbf", Arrays.asList("cbf", "cb", "cf", "c", "bf", "b", "f", ""));
    }

    @Test
    @DisplayName("Subsets of 'aba' with duplicates")
    void testSubsetsWithDuplicateChars() {
        assertSubsets("aba", Arrays.asList("aba", "ab", "aa", "a", "ba", "b", "a", ""));
    }

    @Test
    @DisplayName("Subsets of empty string")
    void testEmptyInput() {
        assertSubsets("", List.of(""));
    }

    private void assertSubsets(String input, Iterable<String> expected) {
        List<String> actual = GenerateSubsets.subsetRecursion(input);
        assertIterableEquals(expected, actual, "Subsets do not match for input: " + input);
    }
}
