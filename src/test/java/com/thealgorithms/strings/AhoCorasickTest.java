/****************************************************
 * Tests For Aho-Corasick String Matching Algorithm *
 *                                                  *
 *            Author: Prabhat-Kumar-42              *
 *    GitHub: https://github.com/Prabhat-Kumar-42   *
 ****************************************************/

package com.thealgorithms.strings;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AhoCorasickTest {
    private String[] patterns;
    private String text;

    @BeforeEach
    void setUp() {
        patterns = new String[] {"ACC", "ATC", "CAT", "GCG", "C", "T"};
        text = "GCATCG";
    }

    @Test
    void testSearch() {
        // Test searching for multiple patterns in the input text.
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, patterns);

        // Define expected results for each pattern
        int[][] expected = {
            {}, // "ACC" should not be found
            {2}, // "ATC" should be found at index 2
            {1}, // "CAT" should be found at index 1
            {}, // "GCG" should not be found
            {1, 4}, // "C" should be found at indices 1 and 4
            {3} // "T" should be found at index 3
        };

        // Check specific pattern occurrences
        for (int i = 0; i < patterns.length; i++) {
            assertIterableEquals(toList(expected[i]), positions.get(patterns[i]));
        }
    }

    @Test
    void testEmptyPatterns() {
        // Test searching with an empty pattern array.
        String[] emptyPatterns = {};
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, emptyPatterns);

        // Verify that no patterns are found
        for (String pattern : emptyPatterns) {
            assertTrue(positions.get(pattern).isEmpty());
        }
    }

    @Test
    void testPatternNotFound() {
        // Test searching for patterns that are not present in the input text.
        String[] patterns = {"XYZ", "123"};
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, patterns);

        // Verify that empty lists are found for patterns that are not in the text.
        for (String pattern : patterns) {
            assertEquals(positions.get(pattern).size(), 0);
        }
    }

    @Test
    void testPatternAtBeginning() {
        // Test searching for patterns that start at the beginning of the input text.
        String[] patterns = {"GC", "GCA", "GCAT"};
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, patterns);

        // Define the expected position (0) for each pattern.
        int expected = 0;

        // Check that the first occurrence of each pattern is at the beginning.
        for (String pattern : patterns) {
            assertEquals(expected, positions.get(pattern).get(0));
        }
    }

    @Test
    void testPatternAtEnd() {
        // Test searching for patterns that end at the end of the input text.
        String[] patterns = {"CG", "TCG", "ATCG"};
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, patterns);

        // Define the expected positions (4, 3, 2) for the patterns.
        int[] expected = {4, 3, 2};

        // Check that the first occurrence of each pattern is at the expected position.
        for (int i = 0; i < patterns.length; i++) {
            assertEquals(expected[i], positions.get(patterns[i]).get(0));
        }
    }

    @Test
    void testMultipleOccurrencesOfPattern() {
        // Test searching for patterns with multiple occurrences in the input text.
        String[] patterns = {"AT", "T"};
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, patterns);

        // Define the expected sizes (1, 1) and positions (2, 3) for the patterns.
        int[] expectedSize = {1, 1};
        int[] expectedPositions = {2, 3};

        // Check that the size and positions of each pattern match the expected values.
        for (int i = 0; i < patterns.length; i++) {
            assertEquals(expectedSize[i], positions.get(patterns[i]).size());
            assertEquals(expectedPositions[i], positions.get(patterns[i]).get(0));
        }
    }

    @Test
    void testCaseInsensitiveSearch() {
        // Test searching for patterns in a case-insensitive manner.
        String[] patterns = {"gca", "aTc", "C"};
        Map<String, ArrayList<Integer>> positions = AhoCorasick.search(text, patterns);

        // Check that the positions contain all the provided patterns in a case-insensitive way.
        for (String pattern : patterns) {
            assertTrue(positions.containsKey(pattern));
        }
    }

    // Helper method to convert an array to an ArrayList
    private ArrayList<Integer> toList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : array) {
            list.add(value);
        }
        return list;
    }
}
