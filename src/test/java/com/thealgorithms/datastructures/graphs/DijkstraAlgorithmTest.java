package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DijkstraAlgorithmTest {

    private DijkstraAlgorithm dijkstraAlgorithm;
    private int[][] graph;

    @BeforeEach
    void setUp() {
        graph = new int[][] {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0},
        };

        dijkstraAlgorithm = new DijkstraAlgorithm(graph.length);
    }

    @Test
    void testRunAlgorithm() {
        int[] expectedDistances = {0, 4, 12, 19, 21, 11, 9, 8, 14};
        assertArrayEquals(expectedDistances, dijkstraAlgorithm.run(graph, 0));
    }

    @Test
    void testGraphWithDisconnectedNodes() {
        int[][] disconnectedGraph = {
            {0, 3, 0, 0}, {3, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0} // Node 3 is disconnected
        };

        DijkstraAlgorithm dijkstraDisconnected = new DijkstraAlgorithm(disconnectedGraph.length);

        // Testing from vertex 0
        int[] expectedDistances = {0, 3, 4, Integer.MAX_VALUE}; // Node 3 is unreachable
        assertArrayEquals(expectedDistances, dijkstraDisconnected.run(disconnectedGraph, 0));
    }

    @Test
    void testSingleVertexGraph() {
        int[][] singleVertexGraph = {{0}};
        DijkstraAlgorithm dijkstraSingleVertex = new DijkstraAlgorithm(1);

        int[] expectedDistances = {0}; // The only vertex's distance to itself is 0
        assertArrayEquals(expectedDistances, dijkstraSingleVertex.run(singleVertexGraph, 0));
    }

    @Test
    void testInvalidSourceVertex() {
        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.run(graph, -1));
        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.run(graph, graph.length));
    }
}
