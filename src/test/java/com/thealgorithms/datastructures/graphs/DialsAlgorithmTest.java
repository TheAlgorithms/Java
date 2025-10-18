package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class DialsAlgorithmTest {

    private List<List<DialsAlgorithm.Edge>> graph;
    private static final int NUM_VERTICES = 6;
    private static final int MAX_EDGE_WEIGHT = 10;

    @BeforeEach
    void setUp() {
        graph = new ArrayList<>();
        for (int i = 0; i < NUM_VERTICES; i++) {
            graph.add(new ArrayList<>());
        }
    }

    private void addEdge(int u, int v, int weight) {
        graph.get(u).add(new DialsAlgorithm.Edge(v, weight));
    }

    @Test
    @DisplayName("Test with a simple connected graph")
    void testSimpleGraph() {
        // Build graph from a standard example
        addEdge(0, 1, 2);
        addEdge(0, 2, 4);
        addEdge(1, 2, 1);
        addEdge(1, 3, 7);
        addEdge(2, 4, 3);
        addEdge(3, 5, 1);
        addEdge(4, 3, 2);
        addEdge(4, 5, 5);

        int[] expectedDistances = {0, 2, 3, 8, 6, 9};
        int[] actualDistances = DialsAlgorithm.run(graph, 0, MAX_EDGE_WEIGHT);
        assertArrayEquals(expectedDistances, actualDistances);
    }

    @Test
    @DisplayName("Test with a disconnected node")
    void testDisconnectedNode() {
        addEdge(0, 1, 5);
        addEdge(1, 2, 5);
        // Node 3, 4, 5 are disconnected

        int[] expectedDistances = {0, 5, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] actualDistances = DialsAlgorithm.run(graph, 0, MAX_EDGE_WEIGHT);
        assertArrayEquals(expectedDistances, actualDistances);
    }

    @Test
    @DisplayName("Test with source as destination")
    void testSourceIsDestination() {
        addEdge(0, 1, 10);
        int[] expectedDistances = {0, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        // Run with source 0
        int[] actualDistances = DialsAlgorithm.run(graph, 0, MAX_EDGE_WEIGHT);
        assertArrayEquals(expectedDistances, actualDistances);
    }

    @Test
    @DisplayName("Test graph with multiple paths to a node")
    void testMultiplePaths() {
        addEdge(0, 1, 10);
        addEdge(0, 2, 3);
        addEdge(2, 1, 2); // Shorter path to 1 is via 2 (3+2=5)

        int[] expectedDistances = {0, 5, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] actualDistances = DialsAlgorithm.run(graph, 0, MAX_EDGE_WEIGHT);
        assertArrayEquals(expectedDistances, actualDistances);
    }

    @Test
    @DisplayName("Test with an invalid source vertex")
    void testInvalidSource() {
        assertThrows(IllegalArgumentException.class, () -> DialsAlgorithm.run(graph, -1, MAX_EDGE_WEIGHT));
        assertThrows(IllegalArgumentException.class, () -> DialsAlgorithm.run(graph, NUM_VERTICES, MAX_EDGE_WEIGHT));
    }
}
