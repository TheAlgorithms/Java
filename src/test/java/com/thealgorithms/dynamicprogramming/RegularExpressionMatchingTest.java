package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for RegularExpressionMatching algorithm
 * 
 * Covers various test cases including:
 * - Basic matching scenarios
 * - '.' wildcard functionality  
 * - '*' quantifier functionality
 * - Edge cases and boundary conditions
 * - Invalid input validation
 * 
 * @author Your Name (replace with your GitHub username)
 */
public class RegularExpressionMatchingTest {

    @Test
    void testBasicMatching() {
        // Exact matches
        assertTrue(RegularExpressionMatching.isMatch("abc", "abc"));
        assertFalse(RegularExpressionMatching.isMatch("abc", "abcd"));
        assertFalse(RegularExpressionMatching.isMatch("abcd", "abc"));
    }

    @Test
    void testDotWildcard() {
        // '.' should match any single character
        assertTrue(RegularExpressionMatching.isMatch("abc", "a.c"));
        assertTrue(RegularExpressionMatching.isMatch("axc", "a.c"));
        assertFalse(RegularExpressionMatching.isMatch("abc", "a.."));
        assertTrue(RegularExpressionMatching.isMatch("abc", "..."));
        assertFalse(RegularExpressionMatching.isMatch("ab", "..."));
    }

    @Test
    void testStarQuantifier() {
        // '*' means zero or more of preceding element
        assertTrue(RegularExpressionMatching.isMatch("aa", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("aaa", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("", "a*"));
        assertFalse(RegularExpressionMatching.isMatch("b", "a*"));
        
        // Complex star patterns
        assertTrue(RegularExpressionMatching.isMatch("aab", "c*a*b"));
        assertTrue(RegularExpressionMatching.isMatch("b", "c*a*b"));
    }

    @Test
    void testDotStarCombination() {
        // ".*" should match any sequence of characters
        assertTrue(RegularExpressionMatching.isMatch("abc", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("xyz", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("", ".*"));
        assertTrue(RegularExpressionMatching.isMatch("abc123", ".*"));
        
        // More complex combinations
        assertTrue(RegularExpressionMatching.isMatch("abc", "a.*c"));
        assertTrue(RegularExpressionMatching.isMatch("axxxc", "a.*c"));
        assertFalse(RegularExpressionMatching.isMatch("abc", "a.*d"));
    }

    @Test
    void testComplexPatterns() {
        // Mixed patterns
        assertTrue(RegularExpressionMatching.isMatch("mississippi", "mis*is*ip*."));
        assertTrue(RegularExpressionMatching.isMatch("mississippi", "mis*is*p*."));
        assertFalse(RegularExpressionMatching.isMatch("mississippi", "mis*is*ip*.."));
        
        // Multiple star operators
        assertTrue(RegularExpressionMatching.isMatch("a", "a*a*a*"));
        assertTrue(RegularExpressionMatching.isMatch("aaa", "a*a*a*"));
        assertTrue(RegularExpressionMatching.isMatch("", "a*b*c*"));
    }

    @Test
    void testEdgeCases() {
        // Empty strings
        assertTrue(RegularExpressionMatching.isMatch("", ""));
        assertTrue(RegularExpressionMatching.isMatch("", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("", ".*"));
        assertFalse(RegularExpressionMatching.isMatch("", "a"));
        assertFalse(RegularExpressionMatching.isMatch("", "."));
        
        // Single character patterns
        assertTrue(RegularExpressionMatching.isMatch("a", "a"));
        assertTrue(RegularExpressionMatching.isMatch("a", "."));
        assertFalse(RegularExpressionMatching.isMatch("a", "b"));
        assertFalse(RegularExpressionMatching.isMatch("a", "aa"));
    }

    @Test
    void testInvalidInputs() {
        // Null inputs
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch(null, "pattern"));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("string", null));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch(null, null));
        
        // Invalid patterns
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("test", "*abc"));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("test", "a**b"));
        assertThrows(IllegalArgumentException.class, 
            () -> RegularExpressionMatching.isMatch("test", "abc@"));
    }

    @Test
    void testIterativeImplementation() {
        // Test that iterative implementation produces same results as recursive
        assertTrue(RegularExpressionMatching.isMatchIterative("aa", "a*"));
        assertTrue(RegularExpressionMatching.isMatchIterative("ab", ".*"));
        assertFalse(RegularExpressionMatching.isMatchIterative("aa", "a"));
        assertTrue(RegularExpressionMatching.isMatchIterative("aab", "c*a*b"));
        
        // Test with same inputs for both implementations
        String[] testStrings = {"", "a", "aa", "ab", "aaa", "aab", "mississippi"};
        String[] testPatterns = {"", "a", "a*", ".*", "a.b", "c*a*b", "mis*is*p*."};
        
        for (String s : testStrings) {
            for (String p : testPatterns) {
                if (!p.isEmpty() && p.charAt(0) != '*') { // Skip invalid patterns
                    boolean recursiveResult = RegularExpressionMatching.isMatch(s, p);
                    boolean iterativeResult = RegularExpressionMatching.isMatchIterative(s, p);
                    assertTrue(recursiveResult == iterativeResult, 
                        String.format("Mismatch for s='%s', p='%s'", s, p));
                }
            }
        }
    }

    @Test
    void testLeetCodeExamples() {
        // Examples from LeetCode problem description
        assertFalse(RegularExpressionMatching.isMatch("aa", "a"));
        assertTrue(RegularExpressionMatching.isMatch("aa", "a*"));
        assertTrue(RegularExpressionMatching.isMatch("ab", ".*"));
        
        // Additional LeetCode test cases
        assertTrue(RegularExpressionMatching.isMatch("aab", "c*a*b"));
        assertFalse(RegularExpressionMatching.isMatch("mississippi", "mis*is*p*."));
    }
}
