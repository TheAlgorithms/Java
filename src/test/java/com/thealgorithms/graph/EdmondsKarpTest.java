package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EdmondsKarpTest {

    @Test
    @DisplayName("Classic CLRS network yields max flow 23")
    void clrsExample() {
        int[][] capacity = {{0, 16, 13, 0, 0, 0}, {0, 0, 10, 12, 0, 0}, {0, 4, 0, 0, 14, 0}, {0, 0, 9, 0, 0, 20}, {0, 0, 0, 7, 0, 4}, {0, 0, 0, 0, 0, 0}};
        int maxFlow = EdmondsKarp.maxFlow(capacity, 0, 5);
        assertEquals(23, maxFlow);
    }

    @Test
    @DisplayName("Disconnected network has zero flow")
    void disconnectedGraph() {
        int[][] capacity = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int maxFlow = EdmondsKarp.maxFlow(capacity, 0, 2);
        assertEquals(0, maxFlow);
    }

    @Test
    @DisplayName("Source equals sink returns zero")
    void sourceEqualsSink() {
        int[][] capacity = {{0, 5}, {0, 0}};
        int maxFlow = EdmondsKarp.maxFlow(capacity, 0, 0);
        assertEquals(0, maxFlow);
    }

    @Test
    @DisplayName("Invalid matrix throws exception")
    void invalidMatrix() {
        int[][] capacity = {{0, 1}, {1}};
        assertThrows(IllegalArgumentException.class, () -> EdmondsKarp.maxFlow(capacity, 0, 1));
    }

    @Test
    @DisplayName("Negative capacity is rejected")
    void negativeCapacity() {
        int[][] capacity = {{0, -1}, {0, 0}};
        assertThrows(IllegalArgumentException.class, () -> EdmondsKarp.maxFlow(capacity, 0, 1));
    }
}
