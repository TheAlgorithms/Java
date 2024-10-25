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
        double[][] graph = {{0, 4, INF, INF}, {INF, 0, 1, INF}, {INF, INF, 0, 2}, {INF, INF, INF, 0}};
        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);
        double[][] expected = {{0, 4, 5, 7}, {INF, 0, 1, 3}, {INF, INF, 0, 2}, {INF, INF, INF, 0}};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests Johnson's Algorithm on a graph with negative edges but no negative weight cycles.
     */
    @Test
    void testGraphWithNegativeEdges() {
        double[][] graph = {{0, -1, 4}, {INF, 0, 3}, {INF, INF, 0}};
        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);
        double[][] expected = {{0, INF, 4}, {INF, 0, 3}, {INF, INF, 0}};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests Johnson's Algorithm on a graph with a negative weight cycle.
     */
    @Test
    void testNegativeWeightCycle() {
        double[][] graph = {{0, 1, INF}, {INF, 0, -1}, {-1, INF, 0}};
        assertThrows(IllegalArgumentException.class, () -> JohnsonsAlgorithm.johnsonAlgorithm(graph));
    }

    /**
     * Tests Dijkstra's algorithm on a small graph as part of Johnson's Algorithm.
     */
    @Test
    void testDijkstra() {
        double[][] graph = {{0, 1, 2}, {INF, 0, 3}, {INF, INF, 0}};
        double[] modifiedWeights = {0, 0, 0};
        double[] result = JohnsonsAlgorithm.dijkstra(graph, 0, modifiedWeights);
        double[] expected = {0, 1, 2};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the conversion of an adjacency matrix to an edge list.
     */
    @Test
    void testEdgeListConversion() {
        double[][] graph = {{0, 5, INF}, {INF, 0, 2}, {INF, INF, 0}};
        double[][] edges = JohnsonsAlgorithm.convertToEdgeList(graph);
        double[][] expected = {{0, 1, 5}, {1, 2, 2}};
        assertArrayEquals(expected, edges);
    }

    /**
     * Tests the reweighting of a graph.
     */
    @Test
    void testReweightGraph() {
        double[][] graph = {{0, 2, 9}, {INF, 0, 1}, {INF, INF, 0}};
        double[] modifiedWeights = {1, 2, 3};
        double[][] reweightedGraph = JohnsonsAlgorithm.reweightGraph(graph, modifiedWeights);
        double[][] expected = {{0, 1, 7}, {INF, 0, 0}, {INF, INF, 0}};
        assertArrayEquals(expected, reweightedGraph);
    }

    /**
     * Tests the minDistance method used in Dijkstra's algorithm.
     */
    @Test
    void testMinDistance() {
        double[] dist = {INF, 3, 1, INF};
        boolean[] visited = {false, false, false, false};
        int minIndex = JohnsonsAlgorithm.minDistance(dist, visited);
        assertEquals(2, minIndex);
    }

    /**
     * Tests Johnson's Algorithm on a graph where all vertices are disconnected.
     */
    @Test
    void testDisconnectedGraph() {
        double[][] graph = {{0, INF, INF}, {INF, 0, INF}, {INF, INF, 0}};
        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);
        double[][] expected = {{0, INF, INF}, {INF, 0, INF}, {INF, INF, 0}};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests Johnson's Algorithm on a fully connected graph.
     */
    @Test
    void testFullyConnectedGraph() {
        double[][] graph = {{0, 1, 2}, {1, 0, 1}, {2, 1, 0}};
        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);
        double[][] expected = {{0, 1, 2}, {1, 0, 1}, {2, 1, 0}};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests Dijkstra's algorithm on a graph with multiple shortest paths.
     */
    @Test
    void testDijkstraMultipleShortestPaths() {
        double[][] graph = {{0, 1, 2, INF}, {INF, 0, INF, 1}, {INF, INF, 0, 1}, {INF, INF, INF, 0}};
        double[] modifiedWeights = {0, 0, 0, 0};
        double[] result = JohnsonsAlgorithm.dijkstra(graph, 0, modifiedWeights);
        double[] expected = {0, 1, 2, 2};
        assertArrayEquals(expected, result);
    }

    /**
     * Tests Johnson's Algorithm with a graph where all edge weights are zero.
     */
    @Test
    void testGraphWithZeroWeights() {
        double[][] graph = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] result = JohnsonsAlgorithm.johnsonAlgorithm(graph);
        double[][] expected = {{0, INF, INF}, {INF, 0, INF}, {INF, INF, 0}};
        assertArrayEquals(expected, result);
    }
}
