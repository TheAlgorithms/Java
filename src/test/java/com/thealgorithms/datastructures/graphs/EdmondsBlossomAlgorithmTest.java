package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the EdmondsBlossomAlgorithm class.
 *
 * These tests ensure that the Edmonds' Blossom Algorithm implementation
 * works as expected for various graph structures, returning the correct
 * maximum matching.
 */
public class EdmondsBlossomAlgorithmTest {

    /**
     * Helper method to convert a list of matching pairs into a sorted 2D array.
     * Sorting ensures consistent ordering of pairs and vertices for easier comparison in tests.
     *
     * @param matching List of matched pairs returned by the algorithm.
     * @return A sorted 2D array of matching pairs.
     */
    private int[][] convertMatchingToArray(Collection<int[]> matching) {
        // Convert the list of pairs into an array
        int[][] result = matching.toArray(new int[0][]);

        // Sort each individual pair for consistency
        for (int[] pair : result) {
            Arrays.sort(pair);
        }

        // Sort the array of pairs to ensure consistent order
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
        return result;
    }

    /**
     * Test Case 1: A triangle graph where vertices 0, 1, and 2 form a cycle.
     * The expected maximum matching is a single pair (0, 1) or any equivalent pair from the cycle.
     */
    @Test
    public void testCase1() {
        List<int[]> edges = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 0});
        List<int[]> matching = EdmondsBlossomAlgorithm.maximumMatching(edges, 3);

        int[][] expected = new int[][] {{0, 1}};
        assertArrayEquals(expected, convertMatchingToArray(matching));
    }

    /**
     * Test Case 2: A disconnected graph where vertices 0, 1, 2 form one component,
     * and vertices 3, 4 form another. The expected maximum matching is two pairs:
     * (0, 1) and (3, 4).
     */
    @Test
    public void testCase2() {
        List<int[]> edges = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {3, 4});
        List<int[]> matching = EdmondsBlossomAlgorithm.maximumMatching(edges, 5);

        int[][] expected = new int[][] {{0, 1}, {3, 4}};
        assertArrayEquals(expected, convertMatchingToArray(matching));
    }

    /**
     * Test Case 3: A cycle graph involving vertices 0, 1, 2, 3 forming a cycle,
     * with an additional edge (4, 5) outside the cycle.
     * The expected maximum matching is (0, 1) and (4, 5).
     */
    @Test
    public void testCase3() {
        List<int[]> edges = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 3}, new int[] {3, 0}, new int[] {4, 5});
        List<int[]> matching = EdmondsBlossomAlgorithm.maximumMatching(edges, 6);

        // Updated expected output to include the maximum matching pairs
        int[][] expected = new int[][] {{0, 1}, {2, 3}, {4, 5}};
        assertArrayEquals(expected, convertMatchingToArray(matching));
    }

    /**
     * Test Case 4: A graph with no edges.
     * Since there are no edges, the expected matching is an empty set.
     */
    @Test
    public void testCaseNoMatching() {
        List<int[]> edges = Collections.emptyList(); // No edges
        List<int[]> matching = EdmondsBlossomAlgorithm.maximumMatching(edges, 3);

        int[][] expected = new int[][] {}; // No pairs expected
        assertArrayEquals(expected, convertMatchingToArray(matching));
    }

    /**
     * Test Case 5: A more complex graph with multiple cycles and extra edges.
     * This tests the algorithm's ability to handle larger, more intricate graphs.
     * The expected matching is {{0, 1}, {2, 5}, {3, 4}}.
     */
    @Test
    public void testCaseLargeGraph() {
        List<int[]> edges = Arrays.asList(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 3}, new int[] {3, 4}, new int[] {4, 5}, new int[] {5, 0}, new int[] {1, 4}, new int[] {2, 5});
        List<int[]> matching = EdmondsBlossomAlgorithm.maximumMatching(edges, 6);

        // Check if the size of the matching is correct (i.e., 3 pairs)
        assertEquals(3, matching.size());

        // Check that the result contains valid pairs (any order is fine)
        // Valid maximum matchings could be {{0, 1}, {2, 5}, {3, 4}} or {{0, 1}, {2, 3}, {4, 5}}, etc.
        int[][] possibleMatching1 = new int[][] {{0, 1}, {2, 5}, {3, 4}};
        int[][] possibleMatching2 = new int[][] {{0, 1}, {2, 3}, {4, 5}};
        int[][] result = convertMatchingToArray(matching);

        // Assert that the result is one of the valid maximum matchings
        assertTrue(Arrays.deepEquals(result, possibleMatching1) || Arrays.deepEquals(result, possibleMatching2));
    }
}
