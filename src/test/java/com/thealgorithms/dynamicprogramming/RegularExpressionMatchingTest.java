package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test class for RegularExpressionMatching.
 */
public class RegularExpressionMatchingTest {

    @Test
    public void testBasicCases() {
        final RegularExpressionMatching matcher = new RegularExpressionMatching();
        
        // Test cases from the problem
        assertFalse(matcher.isMatch("aa", "a"));
        assertTrue(matcher.isMatch("aa", "a*"));
        assertTrue(matcher.isMatch("ab", ".*"));
        assertFalse(matcher.isMatch("mississippi", "mis*is*p*."));
        assertFalse(matcher.isMatch("ab", ".*c"));
        assertTrue(matcher.isMatch("aaa", "a*a"));
        assertTrue(matcher.isMatch("aab", "c*a*b"));
        assertTrue(matcher.isMatch("", ".*"));
        assertTrue(matcher.isMatch("", ""));
        assertFalse(matcher.isMatch("abcd", "d*"));
    }
}