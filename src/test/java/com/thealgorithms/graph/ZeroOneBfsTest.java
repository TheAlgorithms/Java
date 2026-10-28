package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ZeroOneBfsTest {

    // Helper to build adjacency list with capacity n
    private static List<List<int[]>> makeAdj(int n) {
        List<List<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        return adj;
    }

    @Test
    void simpleLineGraph() {
        int n = 4;
        List<List<int[]>> adj = makeAdj(n);
        // 0 --0--> 1 --1--> 2 --0--> 3
        adj.get(0).add(new int[] {1, 0});
        adj.get(1).add(new int[] {2, 1});
        adj.get(2).add(new int[] {3, 0});

        int[] dist = ZeroOneBfs.shortestPaths(n, adj, 0);
        assertArrayEquals(new int[] {0, 0, 1, 1}, dist);
    }

    @Test
    void parallelEdgesPreferZero() {
        int n = 3;
        List<List<int[]>> adj = makeAdj(n);
        // Two edges 0->1: weight 1 and weight 0. Algorithm should choose 0.
        adj.get(0).add(new int[] {1, 1});
        adj.get(0).add(new int[] {1, 0});
        adj.get(1).add(new int[] {2, 1});

        int[] dist = ZeroOneBfs.shortestPaths(n, adj, 0);
        assertArrayEquals(new int[] {0, 0, 1}, dist);
    }

    @Test
    void unreachableNodes() {
        int n = 3;
        List<List<int[]>> adj = makeAdj(n);
        adj.get(0).add(new int[] {1, 0});
        int[] dist = ZeroOneBfs.shortestPaths(n, adj, 0);
        // node 2 unreachable -> Integer.MAX_VALUE
        assertArrayEquals(new int[] {0, 0, Integer.MAX_VALUE}, dist);
    }

    @Test
    void invalidArgs() {
        int n = 2;
        List<List<int[]>> adj = makeAdj(n);
        // invalid weight
        adj.get(0).add(new int[] {1, 2});
        assertThrows(IllegalArgumentException.class, () -> ZeroOneBfs.shortestPaths(n, adj, 0));
        // invalid src
        assertThrows(IllegalArgumentException.class, () -> ZeroOneBfs.shortestPaths(n, adj, -1));
        assertThrows(IllegalArgumentException.class, () -> ZeroOneBfs.shortestPaths(n, adj, 2));
    }
}
