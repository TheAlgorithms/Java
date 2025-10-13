package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DinicTest {
    @Test
    @DisplayName("Classic CLRS network yields max flow 23 (Dinic)")
    void clrsExample() {
        int[][] capacity = {{0, 16, 13, 0, 0, 0}, {0, 0, 10, 12, 0, 0}, {0, 4, 0, 0, 14, 0}, {0, 0, 9, 0, 0, 20}, {0, 0, 0, 7, 0, 4}, {0, 0, 0, 0, 0, 0}};
        int maxFlow = Dinic.maxFlow(capacity, 0, 5);
        assertEquals(23, maxFlow);
    }

    @Test
    @DisplayName("Disconnected network has zero flow (Dinic)")
    void disconnectedGraph() {
        int[][] capacity = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int maxFlow = Dinic.maxFlow(capacity, 0, 2);
        assertEquals(0, maxFlow);
    }

    @Test
    @DisplayName("Source equals sink returns zero (Dinic)")
    void sourceEqualsSink() {
        int[][] capacity = {{0, 5}, {0, 0}};
        int maxFlow = Dinic.maxFlow(capacity, 0, 0);
        assertEquals(0, maxFlow);
    }

    @Test
    @DisplayName("Invalid matrix throws exception (Dinic)")
    void invalidMatrix() {
        int[][] capacity = {{0, 1}, {1}};
        assertThrows(IllegalArgumentException.class, () -> Dinic.maxFlow(capacity, 0, 1));
    }

    @Test
    @DisplayName("Dinic matches Edmonds-Karp on random small graphs")
    void parityWithEdmondsKarp() {
        java.util.Random rnd = new java.util.Random(42);
        for (int n = 3; n <= 7; n++) {
            for (int it = 0; it < 25; it++) {
                int[][] cap = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i != j && rnd.nextDouble() < 0.35) {
                            cap[i][j] = rnd.nextInt(10); // capacities 0..9
                        }
                    }
                }
                int s = 0;
                int t = n - 1;
                int f1 = Dinic.maxFlow(copyMatrix(cap), s, t);
                int f2 = EdmondsKarp.maxFlow(cap, s, t);
                assertEquals(f2, f1);
            }
        }
    }

    private static int[][] copyMatrix(int[][] a) {
        int[][] b = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = java.util.Arrays.copyOf(a[i], a[i].length);
        }
        return b;
    }
}
