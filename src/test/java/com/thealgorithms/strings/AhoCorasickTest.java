/*
 * Tests For Aho-Corasick String Matching Algorithm
 *
 *            Author: Prabhat-Kumar-42
 *    GitHub: https://github.com/Prabhat-Kumar-42
 */

package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
        final var expected = Map.of("ACC", new ArrayList<>(Arrays.asList()), "ATC", new ArrayList<>(Arrays.asList(2)), "CAT", new ArrayList<>(Arrays.asList(1)), "GCG", new ArrayList<>(Arrays.asList()), "C", new ArrayList<>(Arrays.asList(1, 4)), "T", new ArrayList<>(Arrays.asList(3)));

        // Perform the search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, patterns);

        // Compare the actual results with the expected results
        assertEquals(expected, positions);
    }

    /**
     * Test searching with an empty pattern array.
     * The result should be an empty map.
     */
    @Test
    void testEmptyPatterns() {
        // Define an empty pattern array
        final var emptyPatterns = new String[] {};

        // Perform the search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, emptyPatterns);

        // Verify that no patterns are found (empty map)
        assertTrue(positions.isEmpty());
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

        // Perform the search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, searchPatterns);

        // Verify that empty lists are found for patterns that are not in the text
        assertEquals(expected, positions);
    }

    /**
     * Test searching for patterns that start at the beginning of the input text.
     * The expected position for each pattern is 0.
     */
    @Test
    void testPatternAtBeginning() {
        // Define patterns that start at the beginning of the text
        final var searchPatterns = new String[] {"GC", "GCA", "GCAT"};

        // Define the expected positions (0) for each pattern
        final var expected = Map.of("GC", new ArrayList<Integer>(Arrays.asList(0)), "GCA", new ArrayList<Integer>(Arrays.asList(0)), "GCAT", new ArrayList<Integer>(Arrays.asList(0)));
        // Perform the search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, searchPatterns);

        // Check that the first occurrence of each pattern is at the beginning (position 0)
        assertEquals(expected, positions);
    }

    /**
     * Test searching for patterns that end at the end of the input text.
     * The expected positions are 4, 3, and 2 for the patterns.
     */
    @Test
    void testPatternAtEnd() {
        // Define patterns that end at the end of the text
        final var searchPatterns = new String[] {"CG", "TCG", "ATCG"};

        // Define the expected positions (4, 3, 2) for the patterns
        final var expected = new int[] {4, 3, 2};

        // Perform the search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, searchPatterns);

        // Check that the first occurrence of each pattern is at the expected position
        for (int i = 0; i < searchPatterns.length; i++) {
            assertEquals(expected[i], positions.get(searchPatterns[i]).get(0));
        }
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

        // Define expected with two ArrayList of sizes (1, 1) and positions (2, 3) for the patterns
        final var expected = Map.of("AT", new ArrayList<Integer>(Arrays.asList(2)), "T", new ArrayList<Integer>(Arrays.asList(3)));

        // Perform the search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, searchPatterns);

        // Check that the size and positions of each pattern match the expected values
        assertEquals(expected, positions);
    }

    /**
     * Test searching for patterns in a case-insensitive manner.
     * The search should consider patterns regardless of their case.
     */
    @Test
    void testCaseInsensitiveSearch() {
        // Define patterns with different cases
        final var searchPatterns = new String[] {"gca", "aTc", "C"};

        // Perform the case-insensitive search using the Aho-Corasick algorithm
        final var positions = AhoCorasick.search(text, searchPatterns);

        // Check that the positions contain all the provided patterns in a case-insensitive way
        for (String pattern : searchPatterns) {
            assertTrue(positions.containsKey(pattern));
        }
    }
}
