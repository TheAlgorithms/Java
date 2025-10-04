package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for RegularExpressionMatching algorithm.
 * 
 * <p>
 * For more information about regular expression matching:
 * @see <a href="https://en.wikipedia.org/wiki/Regular_expression">Regular Expression</a>
 * @see <a href="https://leetcode.com/problems/regular-expression-matching/">LeetCode Problem 10</a>
 */
class RegularExpressionMatchingTest {

    @Test
    void testBasicMatching() {
        assertTrue(RegularExpressionMatching.isMatch("abc", "abc"));
        assertFalse(RegularExpressionMatching.isMatch("abc", "abcd"));
        assertFalse(RegularExpressionMatching.isMatch("abcd", "abc"));
    }

    @Test
    void testDotWildcard() {
        assertTrue(RegularExpressionMatching.isMatch("abc", "a.c"));
        assertTrue(RegularExpressionMatching.isMatch("axc", "a.c"));
        assertFalse(RegularExpressionMatching.isMatch("abc", "a.."));
        assertTrue(RegularExpressionMatching.isMatch("abc", "..."));
        assertFalse(RegularExpressionMatching.isMatch("ab", "..."));
    }

    @Test
    void testStarQuantifier() {
        assertTrue(RegularExpressionMatching.isMatch("aa", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("aaa", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("", "a*"));
        assertFalse(RegularExpressionMatching.isMatch("b", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("aab", "c*a*b"));
        assertTrue(RegularExpressionMatching.isMatch("b", "c*a*b"));
    }

    @Test
    void testDotStarCombination() {
        assertTrue(RegularExpressionMatching.isMatch("abc", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("xyz", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("abc123", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("abc", "a.*c"));
        assertTrue(RegularExpressionMatching.isMatch("axxxc", "a.*c"));
        assertFalse(RegularExpressionMatching.isMatch("abc", "a.*d"));
    }

    @Test
    void testComplexPatterns() {
        assertTrue(RegularExpressionMatching.isMatch("mississippi", "mis*is*ip*."));
        assertTrue(RegularExpressionMatching.isMatch("mississippi", "mis*is*p*."));
        assertFalse(RegularExpressionMatching.isMatch("mississippi", "mis*is*ip*.."));
        assertTrue(RegularExpressionMatching.isMatch("a", "a*a*a*"));
        assertTrue(RegularExpressionMatching.isMatch("aaa", "a*a*a*"));
        assertTrue(RegularExpressionMatching.isMatch("", "a*b*c*"));
    }

    @Test
    void testEdgeCases() {
        assertTrue(RegularExpressionMatching.isMatch("", ""));
        assertTrue(RegularExpressionMatching.isMatch("", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("", ".*"));
        assertFalse(RegularExpressionMatching.isMatch("", "a"));
        assertFalse(RegularExpressionMatching.isMatch("", "."));
        assertTrue(RegularExpressionMatching.isMatch("a", "a"));
        assertTrue(RegularExpressionMatching.isMatch("a", "."));
        assertFalse(RegularExpressionMatching.isMatch("a", "b"));
        assertFalse(RegularExpressionMatching.isMatch("a", "aa"));
    }

    @Test
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch(null, "pattern"));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("string", null));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch(null, null));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("test", "*abc"));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("test", "a**b"));
    }

    @Test
    void testIterativeImplementation() {
        assertTrue(RegularExpressionMatching.isMatchIterative("aa", "a*"));
        assertTrue(RegularExpressionMatching.isMatchIterative("ab", ".*"));
        assertFalse(RegularExpressionMatching.isMatchIterative("aa", "a"));
        assertTrue(RegularExpressionMatching.isMatchIterative("aab", "c*a*b"));
        
        String[] testStrings = {"", "a", "aa", "ab", "aaa", "aab"};
        String[] testPatterns = {"", "a", "a*", ".*", "a.b", "c*a*b"};
        
        for (String string : testStrings) {
            for (String patternString : testPatterns) {
                if (!patternString.isEmpty() && patternString.charAt(0) != '*') {
                    boolean recursiveResult = RegularExpressionMatching.isMatch(string, patternString);
                    boolean iterativeResult = RegularExpressionMatching.isMatchIterative(string, patternString);
                    assertTrue(recursiveResult == iterativeResult);
                }
            }
        }
    }

    @Test
    void testLeetCodeExamples() {
        assertFalse(RegularExpressionMatching.isMatch("aa", "a"));
        assertTrue(RegularExpressionMatching.isMatch("aa", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("ab", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("aab", "c*a*b"));
        assertFalse(RegularExpressionMatching.isMatch("mississippi", "mis*is*p*."));
    }
}
