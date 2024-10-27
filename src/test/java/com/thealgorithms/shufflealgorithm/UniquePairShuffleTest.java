package com.thealgorithms.shufflealgorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.shufflealogrithm.UniquePairShuffle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class UniquePairShuffleTest {

    // Test case for standard even-length array
    @Test
    void testPairShuffleEvenArray() {
        int[] array = {1, 2, 3, 4};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // There should be 2 pairs in the result
        assertEquals(2, pairs.size());

        // Check that all pairs are unique
        Set<String> uniquePairs = new HashSet<>();
        for (int[] pair : pairs) {
            String pairString =
                    Math.min(pair[0], pair[1]) + "-" + Math.max(pair[0], pair[1]);
            uniquePairs.add(pairString);
        }

        assertEquals(2, uniquePairs.size()); // Should have 2 unique pairs
    }

    // Test case for standard odd-length array
    @Test
    void testPairShuffleOddArray() {
        int[] array = {1, 2, 3};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // Result should be empty for odd-length array
        assertTrue(pairs.isEmpty());
    }

    // Test case for empty array
    @Test
    void testPairShuffleEmptyArray() {
        int[] array = {};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // Result should be empty
        assertTrue(pairs.isEmpty());
    }

    // Test case for single element array
    @Test
    void testPairShuffleSingleElementArray() {
        int[] array = {1};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // Result should be empty for single-element array
        assertTrue(pairs.isEmpty());
    }

    // Test case for two elements
    @Test
    void testPairShuffleTwoElements() {
        int[] array = {1, 2};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // There should be 1 pair in the result
        assertEquals(1, pairs.size());
        int[] pair = pairs.get(0);

        // Check that the pair contains both elements, regardless of order
        assertTrue((pair[0] == 1 && pair[1] == 2) || (pair[0] == 2 && pair[1] == 1));
    }

    // Test case for larger even-length array
    @Test
    void testPairShuffleLargeEvenArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // There should be 4 pairs in the result
        assertEquals(4, pairs.size());

        // Check that all pairs are unique
        Set<String> uniquePairs = new HashSet<>();
        for (int[] pair : pairs) {
            String pairString =
                    Math.min(pair[0], pair[1]) + "-" + Math.max(pair[0], pair[1]);
            uniquePairs.add(pairString);
        }

        assertEquals(4, uniquePairs.size()); // Should have 4 unique pairs
    }

    // Test case for larger odd-length array
    @Test
    void testPairShuffleLargeOddArray() {
        int[] array = {1, 2, 3, 4, 5};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // Result should be empty for odd-length array
        assertTrue(pairs.isEmpty());
    }

    // Test case for negative numbers in array
    @Test
    void testPairShuffleNegativeNumbers() {
        int[] array = {-1, -2, -3, -4};
        List<int[]> pairs = UniquePairShuffle.pairShuffle(array);

        // There should be 2 pairs in the result
        assertEquals(2, pairs.size());

        // Check that all pairs are unique
        Set<String> uniquePairs = new HashSet<>();
        for (int[] pair : pairs) {
            String pairString =
                    Math.min(pair[0], pair[1]) + "-" + Math.max(pair[0], pair[1]);
            uniquePairs.add(pairString);
        }

        assertEquals(2, uniquePairs.size()); // Should have 2 unique pairs
    }
}
