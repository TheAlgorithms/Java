/*
 * Tests For Aho-Corasick String Matching Algorithm
 *
 *            Author: Prabhat-Kumar-42
 *    GitHub: https://github.com/Prabhat-Kumar-42
 */

package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains test cases for the Aho-Corasick String Matching Algorithm.
 * The Aho-Corasick algorithm is used to efficiently find all occurrences of multiple
 * patterns in a given text.
 */
class AhoCorasickTest {
    private String[] patterns; // The array of patterns to search for
    private String text; // The input text to search within

    /**
     * This method sets up the test environment before each test case.
     * It initializes the patterns and text to be used for testing.
     */
    @BeforeEach
    void setUp() {
        patterns = new String[] {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        text = "GCATCG";
    }

    /**
     * Test searching for multiple patterns in the input text.
     * The expected results are defined for each pattern.
     */
    @Test
    void testSearch() {
        // Define the expected results for each pattern
        final var expected = Map.of("ACC", new ArrayList<>(List.of()), "ATC", new ArrayList<>(List.of(2)), "CAT", new ArrayList<>(List.of(1)), "GCG", new ArrayList<>(List.of()), "C", new ArrayList<>(List.of(1, 4)), "T", new ArrayList<>(List.of(3)));
        assertEquals(expected, AhoCorasick.search(text, patterns));
    }

    /**
     * Test searching with an empty pattern array.
     * The result should be an empty map.
     */
    @Test
    void testEmptyPatterns() {
        // Define an empty pattern array
        final var emptyPatterns = new String[] {};
        assertTrue(AhoCorasick.search(text, emptyPatterns).isEmpty());
    }

    /**
     * Test searching for patterns that are not present in the input text.
     * The result should be an empty list for each pattern.
     */
    @Test
    void testPatternNotFound() {
        // Define patterns that are not present in the text
        final var searchPatterns = new String[] {"XYZ", "123"};
        final var expected = Map.of("XYZ", new ArrayList<Integer>(), "123", new ArrayList<Integer>());
        assertEquals(expected, AhoCorasick.search(text, searchPatterns));
    }

    /**
     * Test searching for patterns that start at the beginning of the input text.
     * The expected position for each pattern is 0.
     */
    @Test
    void testPatternAtBeginning() {
        // Define patterns that start at the beginning of the text
        final var searchPatterns = new String[] {"GC", "GCA", "GCAT"};
        final var expected = Map.of("GC", new ArrayList<>(List.of(0)), "GCA", new ArrayList<>(List.of(0)), "GCAT", new ArrayList<>(List.of(0)));
        assertEquals(expected, AhoCorasick.search(text, searchPatterns));
    }

    /**
     * Test searching for patterns that end at the end of the input text.
     * The expected positions are 4, 3, and 2 for the patterns.
     */
    @Test
    void testPatternAtEnd() {
        // Define patterns that end at the end of the text
        final var searchPatterns = new String[] {"CG", "TCG", "ATCG"};
        final var expected = Map.of("CG", new ArrayList<>(List.of(4)), "TCG", new ArrayList<>(List.of(3)), "ATCG", new ArrayList<>(List.of(2)));
        assertEquals(expected, AhoCorasick.search(text, searchPatterns));
    }

    /**
     * Test searching for patterns with multiple occurrences in the input text.
     * The expected sizes are 1 and 1, and the expected positions are 2 and 3
     * for the patterns "AT" and "T" respectively.
     */
    @Test
    void testMultipleOccurrencesOfPattern() {
        // Define patterns with multiple occurrences in the text
        final var searchPatterns = new String[] {"AT", "T"};
        final var expected = Map.of("AT", new ArrayList<>(List.of(2)), "T", new ArrayList<>(List.of(3)));
        assertEquals(expected, AhoCorasick.search(text, searchPatterns));
    }

    /**
     * Test searching for patterns in a case-insensitive manner.
     * The search should consider patterns regardless of their case.
     */
    @Test
    void testCaseInsensitiveSearch() {
        // Define patterns with different cases
        final var searchPatterns = new String[] {"gca", "aTc", "C"};
        final var expected = Map.of("gca", new ArrayList<Integer>(), "aTc", new ArrayList<Integer>(), "C", new ArrayList<>(Arrays.asList(1, 4)));
        assertEquals(expected, AhoCorasick.search(text, searchPatterns));
    }
}
