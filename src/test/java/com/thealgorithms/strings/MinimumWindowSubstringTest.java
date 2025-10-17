package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MinimumWindowSubstringTest {

    @Test
    public void testExample1() {
        assertEquals("BANC", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    public void testExample2() {
        assertEquals("", MinimumWindowSubstring.minWindow("A", "AA"));
    }

    @Test
    public void testExample3() {
        assertEquals("a", MinimumWindowSubstring.minWindow("a", "a"));
    }

    @Test
    public void testExample4() {
        assertEquals("t stri", MinimumWindowSubstring.minWindow("test string", "tist"));
    }
}
