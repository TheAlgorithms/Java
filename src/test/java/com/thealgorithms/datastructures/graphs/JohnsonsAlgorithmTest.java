package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link JohnsonsAlgorithm} class. This class
 * contains test cases to verify the correct implementation of
 * various methods used in Johnson's Algorithm such as shortest path
 * calculations, graph reweighting, and more.
 */
class JohnsonsAlgorithmTest {

    // Constant representing infinity
    private static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Tests the Johnson's Algorithm with a simple graph without negative edges.
     * Verifies that the algorithm returns the correct shortest path distances.
     */
    @Test
    void testSimpleGraph() {
        // Test case for a simple graph without negative edges
        double[][] graph = {{0, 4, INF, INF}, {INF, 0, 1, INF}, {INF, INF, 0, 2}, {INF, INF, INF, 0}};

        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);

        double[][] expected = {{0, 4, 5, 7}, {INF, 0, 1, 3}, {INF, INF, 0, 2}, {INF, INF, INF, 0}};

        assertArrayEquals(expected, result);
    }

    /**
     * Tests Johnson's Algorithm on a graph with negative edges but no
     * negative weight cycles. Verifies the algorithm handles negative
     * edge weights correctly.
     */
    @Test
    void testGraphWithNegativeEdges() {
        // Graph with negative edges but no negative weight cycles
        double[][] graph = {{0, -1, 4}, {INF, 0, 3}, {INF, INF, 0}};

        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);

        double[][] expected = {{0, INF, 4}, {INF, 0, 3}, {INF, INF, 0}};

        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behavior of Johnson's Algorithm on a graph with a negative
     * weight cycle. Expects an IllegalArgumentException to be thrown
     * due to the presence of the cycle.
     */
    @Test
    void testNegativeWeightCycle() {
        // Graph with a negative weight cycle
        double[][] graph = {{0, 1, INF}, {INF, 0, -1}, {-1, INF, 0}};

        // Johnson's algorithm should throw an exception when a negative cycle is detected
        assertThrows(IllegalArgumentException.class, () -> { JohnsonsAlgorithm.johnsonAlgorithm(graph); });
    }

    /**
     * Tests Dijkstra's algorithm as a part of Johnson's algorithm implementation
     * on a small graph. Verifies that the shortest path is correctly calculated.
     */
    @Test
    void testDijkstra() {
        // Testing Dijkstra's algorithm with a small graph
        double[][] graph = {{0, 1, 2}, {INF, 0, 3}, {INF, INF, 0}};

        double[] modifiedWeights = {0, 0, 0}; // No reweighting in this simple case

        double[] result = JohnsonsAlgorithm.dijkstra(graph, 0, modifiedWeights);
        double[] expected = {0, 1, 2};

        assertArrayEquals(expected, result);
    }

    /**
     * Tests the conversion of an adjacency matrix to an edge list.
     * Verifies that the conversion process generates the correct edge list.
     */
    @Test
    void testEdgeListConversion() {
        // Test the conversion of adjacency matrix to edge list
        double[][] graph = {{0, 5, INF}, {INF, 0, 2}, {INF, INF, 0}};

        // Running convertToEdgeList
        double[][] edges = JohnsonsAlgorithm.convertToEdgeList(graph);

        // Expected edge list: (0 -> 1, weight 5), (1 -> 2, weight 2)
        double[][] expected = {{0, 1, 5}, {1, 2, 2}};

        // Verify the edge list matches the expected values
        assertArrayEquals(expected, edges);
    }

    /**
     * Tests the reweighting of a graph as a part of Johnson's Algorithm.
     * Verifies that the reweighted graph produces correct results.
     */
    @Test
    void testReweightGraph() {
        // Test reweighting of the graph
        double[][] graph = {{0, 2, 9}, {INF, 0, 1}, {INF, INF, 0}};
        double[] modifiedWeights = {1, 2, 3}; // Arbitrary weight function

        double[][] reweightedGraph = JohnsonsAlgorithm.reweightGraph(graph, modifiedWeights);

        // Expected reweighted graph:
        double[][] expected = {{0, 1, 7}, {INF, 0, 0}, {INF, INF, 0}};

        assertArrayEquals(expected, reweightedGraph);
    }

    /**
     * Tests the minDistance method used in Dijkstra's algorithm to find
     * the vertex with the minimum distance that has not yet been visited.
     */
    @Test
    void testMinDistance() {
        // Test minDistance method
        double[] dist = {INF, 3, 1, INF};
        boolean[] visited = {false, false, false, false};

        int minIndex = JohnsonsAlgorithm.minDistance(dist, visited);

        // The vertex with minimum distance is vertex 2 with a distance of 1
        assertEquals(2, minIndex);
    }
}
