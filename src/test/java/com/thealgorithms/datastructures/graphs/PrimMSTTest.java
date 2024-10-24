package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class PrimMSTTest {

    private final PrimMST primMST = new PrimMST();

    @Test
    public void testSimpleGraph() {
        // Test graph with 5 nodes and weighted edges
        int[][] graph = {{0, 2, 0, 6, 0}, {2, 0, 3, 8, 5}, {0, 3, 0, 0, 7}, {6, 8, 0, 0, 9}, {0, 5, 7, 9, 0}};

        int[] expectedParent = {-1, 0, 1, 0, 1};
        int[] actualParent = primMST.primMST(graph);

        assertArrayEquals(expectedParent, actualParent);
    }

    @Test
    public void testDisconnectedGraph() {
        // Test case with a disconnected graph (no valid MST)
        int[][] graph = {{0, 1, 0, 0, 0}, {1, 0, 2, 0, 0}, {0, 2, 0, 3, 0}, {0, 0, 3, 0, 4}, {0, 0, 0, 4, 0}};

        int[] expectedParent = {-1, 0, 1, 2, 3}; // Expected MST parent array
        int[] actualParent = primMST.primMST(graph);

        assertArrayEquals(expectedParent, actualParent);
    }

    @Test
    public void testAllEqualWeightsGraph() {
        // Test case where all edges have equal weight
        int[][] graph = {{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}};

        int[] expectedParent = {-1, 0, 0, 0, 0}; // Expected MST parent array (any valid spanning tree)
        int[] actualParent = primMST.primMST(graph);

        assertArrayEquals(expectedParent, actualParent);
    }

    @Test
    public void testSparseGraph() {
        // Test case with a sparse graph (few edges)
        int[][] graph = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}};

        int[] expectedParent = {-1, 0, 1, 2, 3}; // Expected MST parent array
        int[] actualParent = primMST.primMST(graph);

        assertArrayEquals(expectedParent, actualParent);
    }
}
