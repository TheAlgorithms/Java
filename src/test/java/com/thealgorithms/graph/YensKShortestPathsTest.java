package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YensKShortestPathsTest {

    @Test
    @DisplayName("Basic K-shortest paths on small directed graph")
    void basicKPaths() {
        // Graph (directed) with non-negative weights, -1 = no edge
        // 0 -> 1 (1), 0 -> 2 (2), 1 -> 3 (1), 2 -> 3 (1), 0 -> 3 (5), 1 -> 2 (1)
        int n = 4;
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = -1;
            }
        }
        w[0][1] = 1;
        w[0][2] = 2;
        w[1][3] = 1;
        w[2][3] = 1;
        w[0][3] = 5;
        w[1][2] = 1;

        List<List<Integer>> paths = YensKShortestPaths.kShortestPaths(w, 0, 3, 3);
        // Expected K=3 loopless shortest paths from 0 to 3, ordered by total cost:
        // 1) 0-1-3 (cost 2)
        // 2) 0-2-3 (cost 3)
        // 3) 0-1-2-3 (cost 3) -> tie with 0-2-3; tie-broken lexicographically by nodes
        assertEquals(3, paths.size());
        assertEquals(List.of(0, 1, 3), paths.get(0));
        assertEquals(List.of(0, 1, 2, 3), paths.get(1)); // lexicographically before [0,2,3]
        assertEquals(List.of(0, 2, 3), paths.get(2));
    }

    @Test
    @DisplayName("K larger than available paths returns only existing ones")
    void kLargerThanAvailable() {
        int[][] w = {{-1, 1, -1}, {-1, -1, 1}, {-1, -1, -1}};
        // Only one simple path 0->1->2
        List<List<Integer>> paths = YensKShortestPaths.kShortestPaths(w, 0, 2, 5);
        assertEquals(1, paths.size());
        assertEquals(List.of(0, 1, 2), paths.get(0));
    }

    @Test
    @DisplayName("No path returns empty list")
    void noPath() {
        int[][] w = {{-1, -1}, {-1, -1}};
        List<List<Integer>> paths = YensKShortestPaths.kShortestPaths(w, 0, 1, 3);
        assertEquals(0, paths.size());
    }

    @Test
    @DisplayName("Source equals destination returns trivial path")
    void sourceEqualsDestination() {
        int[][] w = {{-1, 1}, {-1, -1}};
        List<List<Integer>> paths = YensKShortestPaths.kShortestPaths(w, 0, 0, 2);
        // First path is [0]
        assertEquals(1, paths.size());
        assertEquals(List.of(0), paths.get(0));
    }

    @Test
    @DisplayName("Negative weight entries (other than -1) are rejected")
    void negativeWeightsRejected() {
        int[][] w = {{-1, -2}, {-1, -1}};
        assertThrows(IllegalArgumentException.class, () -> YensKShortestPaths.kShortestPaths(w, 0, 1, 2));
    }
}
