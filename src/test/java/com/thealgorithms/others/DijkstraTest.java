package com.thealgorithms.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for Dijkstra negative-weight detection.
 */
public class DijkstraTest {

    @Test
    void testNegativeWeightThrows() {
        Graph.Edge[] edges = { new Graph.Edge("a", "b", -1) };
        Graph g = new Graph(edges);
        assertThrows(IllegalArgumentException.class, () -> g.dijkstra("a"));
    }
}
