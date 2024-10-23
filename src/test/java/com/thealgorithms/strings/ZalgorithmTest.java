package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ZalgorithmTest {

    /**
     * Test 1:
     * Pattern: "a", Text: "aabaaabaa"
     * Expected: [0, 1, 4, 5, 8]
     */
    @Test
    public void testFindPatternOccurrencesSingleCharacter() {
        String text = "aabaaabaa";
        String pattern = "a";
        List<Integer> expected = Arrays.asList(0, 1, 4, 5, 8);
        assertEquals(expected, Zalgorithm.findPatternOccurrences(text, pattern));
    }

    /**
     * Test 2:
     * Pattern: "abc", Text: "ababcabcabc"
     * Expected: [2, 5, 8]
     */
    @Test
    public void testFindPatternOccurrences() {
        String text = "ababcabcabc";
        String pattern = "abc";
        List<Integer> expected = Arrays.asList(2, 5, 8);
        assertEquals(expected, Zalgorithm.findPatternOccurrences(text, pattern));
    }
    /**
     * Test 3:
     * Pattern: "aa", Text: "aaaaaa"
     * Expected: [0, 1, 2, 3, 4]
     */
    @Test
    public void testFindPatternOccurrencesRepeated() {
        String text = "aaaaaa";
        String pattern = "aa";
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4);
        assertEquals(expected, Zalgorithm.findPatternOccurrences(text, pattern));
    }
}
