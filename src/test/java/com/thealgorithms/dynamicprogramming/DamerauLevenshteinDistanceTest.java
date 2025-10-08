package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code DamerauLevenshteinDistance} class.
 * Tests cover edge cases, basic operations, and complex transposition scenarios.
 */
class DamerauLevenshteinDistanceTest {

    @Test
    @DisplayName("Should throw exception for null first string")
    void testNullFirstString() {
        assertThrows(IllegalArgumentException.class, () -> { DamerauLevenshteinDistance.distance(null, "test"); });
    }

    @Test
    @DisplayName("Should throw exception for null second string")
    void testNullSecondString() {
        assertThrows(IllegalArgumentException.class, () -> { DamerauLevenshteinDistance.distance("test", null); });
    }

    @Test
    @DisplayName("Should throw exception for both null strings")
    void testBothNullStrings() {
        assertThrows(IllegalArgumentException.class, () -> { DamerauLevenshteinDistance.distance(null, null); });
    }

    @Test
    @DisplayName("Should return 0 for identical strings")
    void testIdenticalStrings() {
        assertEquals(0, DamerauLevenshteinDistance.distance("", ""));
        assertEquals(0, DamerauLevenshteinDistance.distance("a", "a"));
        assertEquals(0, DamerauLevenshteinDistance.distance("abc", "abc"));
        assertEquals(0, DamerauLevenshteinDistance.distance("hello", "hello"));
    }

    @Test
    @DisplayName("Should return length when one string is empty")
    void testEmptyStrings() {
        assertEquals(3, DamerauLevenshteinDistance.distance("", "abc"));
        assertEquals(5, DamerauLevenshteinDistance.distance("hello", ""));
        assertEquals(0, DamerauLevenshteinDistance.distance("", ""));
    }

    @Test
    @DisplayName("Should handle single character insertions")
    void testSingleInsertion() {
        assertEquals(1, DamerauLevenshteinDistance.distance("cat", "cats"));
        assertEquals(1, DamerauLevenshteinDistance.distance("ab", "abc"));
        assertEquals(1, DamerauLevenshteinDistance.distance("", "a"));
    }

    @Test
    @DisplayName("Should handle single character deletions")
    void testSingleDeletion() {
        assertEquals(1, DamerauLevenshteinDistance.distance("cats", "cat"));
        assertEquals(1, DamerauLevenshteinDistance.distance("abc", "ab"));
        assertEquals(1, DamerauLevenshteinDistance.distance("a", ""));
    }

    @Test
    @DisplayName("Should handle single character substitutions")
    void testSingleSubstitution() {
        assertEquals(1, DamerauLevenshteinDistance.distance("cat", "bat"));
        assertEquals(1, DamerauLevenshteinDistance.distance("abc", "adc"));
        assertEquals(1, DamerauLevenshteinDistance.distance("x", "y"));
    }

    @Test
    @DisplayName("Should handle adjacent character transpositions")
    void testAdjacentTransposition() {
        assertEquals(1, DamerauLevenshteinDistance.distance("ab", "ba"));
        assertEquals(1, DamerauLevenshteinDistance.distance("abc", "bac"));
        assertEquals(1, DamerauLevenshteinDistance.distance("hello", "ehllo"));
    }

    @Test
    @DisplayName("Should correctly compute distance for CA to ABC")
    void testCAtoABC() {
        // This is the critical test case that differentiates full DL from OSA
        // Full DL: 2 (insert A at start, insert B in middle)
        // OSA would give: 3
        assertEquals(2, DamerauLevenshteinDistance.distance("CA", "ABC"));
    }

    @Test
    @DisplayName("Should handle non-adjacent transpositions")
    void testNonAdjacentTransposition() {
        assertEquals(2, DamerauLevenshteinDistance.distance("abc", "cba"));
        assertEquals(3, DamerauLevenshteinDistance.distance("abcd", "dcba"));
    }

    @Test
    @DisplayName("Should handle multiple operations")
    void testMultipleOperations() {
        assertEquals(3, DamerauLevenshteinDistance.distance("kitten", "sitting"));
        assertEquals(3, DamerauLevenshteinDistance.distance("saturday", "sunday"));
        assertEquals(5, DamerauLevenshteinDistance.distance("intention", "execution"));
    }

    @Test
    @DisplayName("Should handle completely different strings")
    void testCompletelyDifferentStrings() {
        assertEquals(3, DamerauLevenshteinDistance.distance("abc", "xyz"));
        assertEquals(4, DamerauLevenshteinDistance.distance("hello", "world"));
    }

    @Test
    @DisplayName("Should handle strings with repeated characters")
    void testRepeatedCharacters() {
        assertEquals(0, DamerauLevenshteinDistance.distance("aaa", "aaa"));
        assertEquals(1, DamerauLevenshteinDistance.distance("aaa", "aab"));
        assertEquals(1, DamerauLevenshteinDistance.distance("aaa", "aba"));
    }

    @Test
    @DisplayName("Should be symmetric")
    void testSymmetry() {
        assertEquals(DamerauLevenshteinDistance.distance("abc", "def"), DamerauLevenshteinDistance.distance("def", "abc"));
        assertEquals(DamerauLevenshteinDistance.distance("hello", "world"), DamerauLevenshteinDistance.distance("world", "hello"));
    }

    @Test
    @DisplayName("Should handle case sensitivity")
    void testCaseSensitivity() {
        assertEquals(1, DamerauLevenshteinDistance.distance("Hello", "hello"));
        assertEquals(5, DamerauLevenshteinDistance.distance("HELLO", "hello"));
    }

    @Test
    @DisplayName("Should handle single character strings")
    void testSingleCharacterStrings() {
        assertEquals(1, DamerauLevenshteinDistance.distance("a", "b"));
        assertEquals(0, DamerauLevenshteinDistance.distance("a", "a"));
        assertEquals(2, DamerauLevenshteinDistance.distance("a", "abc"));
    }

    @Test
    @DisplayName("Should handle long strings efficiently")
    void testLongStrings() {
        String s1 = "abcdefghijklmnopqrstuvwxyz";
        String s2 = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(0, DamerauLevenshteinDistance.distance(s1, s2));

        String s3 = "abcdefghijklmnopqrstuvwxyz";
        String s4 = "zyxwvutsrqponmlkjihgfedcba";
        assertEquals(25, DamerauLevenshteinDistance.distance(s3, s4));
    }

    @Test
    @DisplayName("Should satisfy triangle inequality")
    void testTriangleInequality() {
        // d(a,c) <= d(a,b) + d(b,c)
        String a = "cat";
        String b = "hat";
        String c = "rat";

        int ab = DamerauLevenshteinDistance.distance(a, b);
        int bc = DamerauLevenshteinDistance.distance(b, c);
        int ac = DamerauLevenshteinDistance.distance(a, c);

        assertTrue(ac <= ab + bc);
    }

    @Test
    @DisplayName("Should handle special characters")
    void testSpecialCharacters() {
        assertEquals(0, DamerauLevenshteinDistance.distance("hello!", "hello!"));
        assertEquals(1, DamerauLevenshteinDistance.distance("hello!", "hello?"));
        assertEquals(1, DamerauLevenshteinDistance.distance("a@b", "a#b"));
    }

    @Test
    @DisplayName("Should handle numeric strings")
    void testNumericStrings() {
        assertEquals(1, DamerauLevenshteinDistance.distance("123", "124"));
        assertEquals(1, DamerauLevenshteinDistance.distance("123", "213"));
        assertEquals(0, DamerauLevenshteinDistance.distance("999", "999"));
    }

    @Test
    @DisplayName("Should handle unicode characters")
    void testUnicodeCharacters() {
        assertEquals(0, DamerauLevenshteinDistance.distance("café", "café"));
        assertEquals(1, DamerauLevenshteinDistance.distance("café", "cafe"));
        assertEquals(0, DamerauLevenshteinDistance.distance("你好", "你好"));
    }
}
