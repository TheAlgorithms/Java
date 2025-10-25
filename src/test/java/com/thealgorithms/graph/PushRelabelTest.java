package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PushRelabelTest {

    @Test
    @DisplayName("Classic CLRS network yields max flow 23 (PushRelabel)")
    void clrsExample() {
        int[][] capacity = {{0, 16, 13, 0, 0, 0}, {0, 0, 10, 12, 0, 0}, {0, 4, 0, 0, 14, 0}, {0, 0, 9, 0, 0, 20}, {0, 0, 0, 7, 0, 4}, {0, 0, 0, 0, 0, 0}};
        int maxFlow = PushRelabel.maxFlow(capacity, 0, 5);
        assertEquals(23, maxFlow);
    }

    @Test
    @DisplayName("Disconnected network has zero flow (PushRelabel)")
    void disconnectedGraph() {
        int[][] capacity = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int maxFlow = PushRelabel.maxFlow(capacity, 0, 2);
        assertEquals(0, maxFlow);
    }

    @Test
    @DisplayName("Source equals sink returns zero (PushRelabel)")
    void sourceEqualsSink() {
        int[][] capacity = {{0, 5}, {0, 0}};
        int maxFlow = PushRelabel.maxFlow(capacity, 0, 0);
        assertEquals(0, maxFlow);
    }

    @Test
    @DisplayName("PushRelabel matches Dinic and EdmondsKarp on random small graphs")
    void parityWithOtherMaxFlow() {
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
                int fPushRelabel = PushRelabel.maxFlow(copyMatrix(cap), s, t);
                int fDinic = Dinic.maxFlow(copyMatrix(cap), s, t);
                int fEdmondsKarp = EdmondsKarp.maxFlow(cap, s, t);
                assertEquals(fDinic, fPushRelabel);
                assertEquals(fEdmondsKarp, fPushRelabel);
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
