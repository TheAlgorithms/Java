package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SortCharacterByFrequencyTest {

    private final SortCharacterByFrequency solution = new SortCharacterByFrequency();

    @Test
    void testBasicCase() {
        String result = solution.frequencySort("tree");

        // Possible outputs: "eert" or "eetr"
        assertTrue(result.equals("eert") || result.equals("eetr"));
    }

    @Test
    void testSingleCharacter() {
        assertEquals("a", solution.frequencySort("a"));
    }

    @Test
    void testEmptyString() {
        assertEquals("", solution.frequencySort(""));
    }

    @Test
    void testAllSameCharacters() {
        assertEquals("aaaa", solution.frequencySort("aaaa"));
    }

    @Test
    void testMixedCharacters() {
        String result = solution.frequencySort("cccaaa");

        // Possible outputs: "cccaaa" or "aaaccc"
        assertTrue(result.equals("cccaaa") || result.equals("aaaccc"));
    }
}
