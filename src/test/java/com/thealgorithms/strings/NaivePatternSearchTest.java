package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class NaivePatternSearchTest {

    @Test
    void testPatternFound() {
        List<Integer> result = NaivePatternSearch.search("abcdxabchjikabc", "abc");
        assertEquals(List.of(0, 5, 12), result);
    }

    @Test
    void testPatternNotFound() {
        List<Integer> result = NaivePatternSearch.search("abcdefg", "xyz");
        assertTrue(result.isEmpty());
    }

    @Test
    void testPatternAtEnd() {
        List<Integer> result = NaivePatternSearch.search("helloworld", "world");
        assertEquals(List.of(5), result);
    }

    @Test
    void testPatternEqualsText() {
        List<Integer> result = NaivePatternSearch.search("abc", "abc");
        assertEquals(List.of(0), result);
    }

    @Test
    void testEmptyPattern() {
        List<Integer> result = NaivePatternSearch.search("abcdef", "");
        assertTrue(result.isEmpty());
    }

    @Test
    void testPatternLongerThanText() {
        List<Integer> result = NaivePatternSearch.search("abc", "abcd");
        assertTrue(result.isEmpty());
    }
}
