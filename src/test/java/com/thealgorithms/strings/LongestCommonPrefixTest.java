package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonPrefixTest {

    private final LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();

    @Test
    public void testCommonPrefix() {
        String[] input = {"flower", "flow", "flight"};
        String expected = "fl";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testNoCommonPrefix() {
        String[] input = {"dog", "racecar", "car"};
        String expected = "";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testEmptyArray() {
        String[] input = {};
        String expected = "";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testNullArray() {
        String[] input = null;
        String expected = "";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testSingleString() {
        String[] input = {"single"};
        String expected = "single";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testCommonPrefixWithDifferentLengths() {
        String[] input = {"ab", "a"};
        String expected = "a";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testAllSameStrings() {
        String[] input = {"test", "test", "test"};
        String expected = "test";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testPrefixAtEnd() {
        String[] input = {"abcde", "abcfgh", "abcmnop"};
        String expected = "abc";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }

    @Test
    public void testMixedCase() {
        String[] input = {"Flower", "flow", "flight"};
        String expected = "";
        assertEquals(expected, longestCommonPrefix.longestCommonPrefix(input));
    }
}
