package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Implementation of the Edmonds–Karp algorithm for computing the maximum flow of a directed graph.
 * <p>
 * The algorithm runs in O(V * E^2) time and is a specific implementation of the Ford–Fulkerson
 * method where the augmenting paths are found using breadth-first search (BFS) to ensure the
 * shortest augmenting paths (in terms of the number of edges) are used.
 * </p>
 *
 * <p>The graph is represented with a capacity matrix where {@code capacity[u][v]} denotes the
 * capacity of the edge from {@code u} to {@code v}. Negative capacities are not allowed.</p>
 *
 * @author <a href="https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm">Wikipedia: EdmondsKarp algorithm</a>
 */
public final class EdmondsKarp {

    private EdmondsKarp() {
    }

    /**
     * Computes the maximum flow from {@code source} to {@code sink} in the provided capacity matrix.
     *
     * @param capacity the capacity matrix representing the directed graph; must be square and non-null
     * @param source the source vertex index
     * @param sink the sink vertex index
     * @return the value of the maximum flow between {@code source} and {@code sink}
     * @throws IllegalArgumentException if the matrix is {@code null}, not square, contains negative
     *         capacities, or if {@code source} / {@code sink} indices are invalid
     */
    public static int maxFlow(int[][] capacity, int source, int sink) {
        if (capacity == null || capacity.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }

        final int n = capacity.length;
        for (int row = 0; row < n; row++) {
            if (capacity[row] == null || capacity[row].length != n) {
                throw new IllegalArgumentException("Capacity matrix must be square");
            }
            for (int col = 0; col < n; col++) {
                if (capacity[row][col] < 0) {
                    throw new IllegalArgumentException("Capacities must be non-negative");
                }
            }
        }

        if (source < 0 || source >= n || sink < 0 || sink >= n) {
            throw new IllegalArgumentException("Source and sink must be valid vertex indices");
        }
        if (source == sink) {
            return 0;
        }

        final int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }

        final int[] parent = new int[n];
        int maxFlow = 0;

        while (bfs(residual, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private static boolean bfs(int[][] residual, int source, int sink, int[] parent) {
        Arrays.fill(parent, -1);
        parent[source] = source;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < residual.length; v++) {
                if (residual[u][v] > 0 && parent[v] == -1) {
                    parent[v] = u;
                    if (v == sink) {
                        return true;
                    }
                    queue.add(v);
                }
            }
        }
        return false;
    }
}
