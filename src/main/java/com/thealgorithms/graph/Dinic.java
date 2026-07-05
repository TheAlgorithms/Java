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
        validateInput(capacity, source, sink);

        if (source == sink) {
            return 0;
        }

        int[][] residual = buildResidualGraph(capacity);
        return computeMaxFlow(residual, source, sink);
    }

    private static void validateInput(int[][] capacity, int source, int sink) {
        validateCapacityMatrix(capacity);
        validateSourceAndSink(capacity.length, source, sink);
    }

    private static void validateCapacityMatrix(int[][] capacity) {
        if (capacity == null || capacity.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }

        int n = capacity.length;
        for (int[] ints : capacity) {
            if (ints == null || ints.length != n) {
                throw new IllegalArgumentException("Capacity matrix must be square");
            }
            validateNonNegativeRow(ints);
        }
    }

    private static void validateNonNegativeRow(int[] row) {
        for (int value : row) {
            if (value < 0) {
                throw new IllegalArgumentException("Capacities must be non-negative");
            }
        }
    }

    private static void validateSourceAndSink(int vertexCount, int source, int sink) {
        if (source < 0 || sink < 0 || source >= vertexCount || sink >= vertexCount) {
            throw new IllegalArgumentException("Source and sink must be valid vertex indices");
        }
    }

    private static int[][] buildResidualGraph(int[][] capacity) {
        int n = capacity.length;
        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }
        return residual;
    }

    private static int computeMaxFlow(int[][] residual, int source, int sink) {
        int n = residual.length;
        int[] level = new int[n];
        int flow = 0;

        while (bfsBuildLevelGraph(residual, source, sink, level)) {
            int[] next = new int[n];
            flow += sendBlockingFlow(residual, level, next, source, sink);
        }

        return flow;
    }

    private static int sendBlockingFlow(int[][] residual, int[] level, int[] next, int source, int sink) {
        int totalPushed = 0;
        int pushed;

        do {
            pushed = dfsBlocking(residual, level, next, source, sink, Integer.MAX_VALUE);
            totalPushed += pushed;
        } while (pushed > 0);

        return totalPushed;
    }

    private static boolean bfsBuildLevelGraph(int[][] residual, int source, int sink, int[] level) {
        Arrays.fill(level, -1);
        level[source] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < residual.length; v++) {
                if (residual[u][v] > 0 && level[v] == -1) {
                    level[v] = level[u] + 1;
                    if (v == sink) {
                        return true;
                    }
                    queue.add(v);
                }
            }
        }

        return level[sink] != -1;
    }

    private static int dfsBlocking(int[][] residual, int[] level, int[] next, int u, int sink, int flow) {
        if (u == sink) {
            return flow;
        }

        int n = residual.length;
        for (int v = next[u]; v < n; v++, next[u] = v) {
            if (residual[u][v] <= 0 || level[v] != level[u] + 1) {
                continue;
            }

            int pushed = dfsBlocking(residual, level, next, v, sink, Math.min(flow, residual[u][v]));
            if (pushed > 0) {
                residual[u][v] -= pushed;
                residual[v][u] += pushed;
                return pushed;
            }
        }

        return 0;
    }
}