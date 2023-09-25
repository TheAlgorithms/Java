package com.thealgorithms.dynamicprogramming;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WildcardMatchingTest {

    @Test
    public void testMatchingPattern() {
        assertTrue(WildcardMatching.isMatch("aa", "a*"));
        assertTrue(WildcardMatching.isMatch("acdcb", "a*c?b"));
        assertTrue(WildcardMatching.isMatch("mississippi", "m*issi*iss?*i"));
    }

    @Test
    public void testNonMatchingPattern() {
        assertFalse(WildcardMatching.isMatch("cb", "?a"));
        assertFalse(WildcardMatching.isMatch("adceb", "*a*b"));
    }

    @Test
    public void testEmptyPattern() {
        assertTrue(WildcardMatching.isMatch("", ""));
        assertFalse(WildcardMatching.isMatch("abc", ""));
    }
}