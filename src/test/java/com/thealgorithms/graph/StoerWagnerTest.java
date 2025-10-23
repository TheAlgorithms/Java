package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the StoerWagner global minimum cut algorithm.
 *
 * These tests verify correctness of the implementation across
 * several graph configurations: simple, complete, disconnected,
 * and small edge cases.
 */
public class StoerWagnerTest {

    @Test
    public void testSimpleGraph() {
        int[][] graph = {{0, 3, 2, 0}, {3, 0, 1, 4}, {2, 1, 0, 5}, {0, 4, 5, 0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(5, algo.findMinCut(graph)); // Correct minimum cut = 5
    }

    @Test
    public void testTriangleGraph() {
        int[][] graph = {{0, 2, 3}, {2, 0, 4}, {3, 4, 0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(5, algo.findMinCut(graph)); // min cut = 5
    }

    @Test
    public void testDisconnectedGraph() {
        int[][] graph = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(0, algo.findMinCut(graph)); // Disconnected graph => cut = 0
    }

    @Test
    public void testCompleteGraph() {
        int[][] graph = {{0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(3, algo.findMinCut(graph)); // Each vertex connected to all others
    }

    @Test
    public void testSingleVertex() {
        int[][] graph = {{0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(0, algo.findMinCut(graph)); // Only one vertex
    }

    @Test
    public void testTwoVertices() {
        int[][] graph = {{0, 7}, {7, 0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(7, algo.findMinCut(graph)); // Only one edge, cut weight = 7
    }

    @Test
    public void testSquareGraphWithDiagonal() {
        int[][] graph = {{0, 2, 0, 2}, {2, 0, 3, 0}, {0, 3, 0, 4}, {2, 0, 4, 0}};
        StoerWagner algo = new StoerWagner();
        assertEquals(4, algo.findMinCut(graph)); // verified manually
    }
}
