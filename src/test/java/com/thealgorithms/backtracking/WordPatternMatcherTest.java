package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordPatternMatcherTest {

    @Test
    public void testPatternMatchingSuccess() {
        assertTrue(WordPatternMatcher.matchWordPattern("aba", "GraphTreesGraph"));
        assertTrue(WordPatternMatcher.matchWordPattern("xyx", "PythonRubyPython"));
    }

    @Test
    public void testPatternMatchingFailure() {
        assertFalse(WordPatternMatcher.matchWordPattern("GG", "PythonJavaPython"));
    }

    @Test
    public void testEmptyPatternAndString() {
        assertTrue(WordPatternMatcher.matchWordPattern("", ""));
    }

    @Test
    public void testEmptyPattern() {
        assertFalse(WordPatternMatcher.matchWordPattern("", "nonempty"));
    }

    @Test
    public void testEmptyString() {
        assertFalse(WordPatternMatcher.matchWordPattern("abc", ""));
    }

    @Test
    public void testLongerPatternThanString() {
        assertFalse(WordPatternMatcher.matchWordPattern("abcd", "abc"));
    }
}
