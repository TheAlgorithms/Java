package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Dinic's algorithm for computing maximum flow in a directed graph.
 *
 * <p>Time complexity: O(E * V^2) in the worst case, but typically faster in practice
 * and near O(E * sqrt(V)) for unit networks.</p>
 *
 * <p>The graph is represented using a capacity matrix where capacity[u][v] is the
 * capacity of the directed edge u -> v. Capacities must be non-negative.
 * The algorithm builds level graphs using BFS and finds blocking flows using DFS
 * with current-edge optimization.</p>
 *
 * <p>This implementation mirrors the API and validation style of
 * {@link EdmondsKarp#maxFlow(int[][], int, int)} for consistency.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Dinic%27s_algorithm">Wikipedia: Dinic's algorithm</a>
 */
public final class Dinic {
    private Dinic() {
    }

    /**
     * Computes the maximum flow from source to sink using Dinic's algorithm.
     *
     * @param capacity square capacity matrix (n x n); entries must be >= 0
     * @param source source vertex index in [0, n)
     * @param sink sink vertex index in [0, n)
     * @return the maximum flow value
     * @throws IllegalArgumentException if the input matrix is null/non-square/has negatives or
     *     indices invalid
     */
    public static int maxFlow(int[][] capacity, int source, int sink) {
        if (capacity == null || capacity.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }
        final int n = capacity.length;
        for (int i = 0; i < n; i++) {
            if (capacity[i] == null || capacity[i].length != n) {
                throw new IllegalArgumentException("Capacity matrix must be square");
            }
            for (int j = 0; j < n; j++) {
                if (capacity[i][j] < 0) {
                    throw new IllegalArgumentException("Capacities must be non-negative");
                }
            }
        }
        if (source < 0 || sink < 0 || source >= n || sink >= n) {
            throw new IllegalArgumentException("Source and sink must be valid vertex indices");
        }
        if (source == sink) {
            return 0;
        }

        // residual capacities
        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }

        int[] level = new int[n];
        int flow = 0;
        while (bfsBuildLevelGraph(residual, source, sink, level)) {
            int[] next = new int[n]; // current-edge optimization
            int pushed;
            do {
                pushed = dfsBlocking(residual, level, next, source, sink, Integer.MAX_VALUE);
                flow += pushed;
            } while (pushed > 0);
        }
        return flow;
    }

    private static boolean bfsBuildLevelGraph(int[][] residual, int source, int sink, int[] level) {
        Arrays.fill(level, -1);
        level[source] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < residual.length; v++) {
                if (residual[u][v] > 0 && level[v] == -1) {
                    level[v] = level[u] + 1;
                    if (v == sink) {
                        return true;
                    }
                    q.add(v);
                }
            }
        }
        return level[sink] != -1;
    }

    private static int dfsBlocking(int[][] residual, int[] level, int[] next, int u, int sink, int f) {
        if (u == sink) {
            return f;
        }
        final int n = residual.length;
        for (int v = next[u]; v < n; v++, next[u] = v) {
            if (residual[u][v] <= 0) {
                continue;
            }
            if (level[v] != level[u] + 1) {
                continue;
            }
            int pushed = dfsBlocking(residual, level, next, v, sink, Math.min(f, residual[u][v]));
            if (pushed > 0) {
                residual[u][v] -= pushed;
                residual[v][u] += pushed;
                return pushed;
            }
        }
        return 0;
    }
}
