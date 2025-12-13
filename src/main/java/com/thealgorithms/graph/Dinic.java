package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Implementation of Dinic's Algorithm to compute the maximum flow
 * in a directed graph.
 *
 * <p><b>Algorithm idea:</b>
 * The algorithm works by repeatedly building a level graph using BFS
 * and then finding blocking flows using DFS until no more augmenting
 * paths exist.</p>
 *
 * <p><b>Time Complexity:</b>
 * Worst case: O(E × V²) <br>
 * Practical performance: Much faster, often close to O(E × √V)</p>
 *
 * <p>The graph is represented using a capacity matrix where
 * {@code capacity[u][v]} denotes the capacity of the directed edge
 * from vertex {@code u} to vertex {@code v}.</p>
 *
 * <p>This implementation follows the same validation style as
 * {@link EdmondsKarp#maxFlow(int[][], int, int)} for consistency.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Dinic%27s_algorithm">
 *      Wikipedia: Dinic's Algorithm</a>
 */
public final class Dinic {

    // Private constructor to prevent instantiation
    private Dinic() {
    }

    /**
     * Computes the maximum flow from a source vertex to a sink vertex
     * using Dinic's Algorithm.
     *
     * @param capacity square matrix representing edge capacities
     * @param source index of the source vertex
     * @param sink index of the sink vertex
     * @return maximum possible flow from source to sink
     * @throws IllegalArgumentException if the input matrix is invalid
     */
    public static int maxFlow(int[][] capacity, int source, int sink) {

        // Validate capacity matrix
        if (capacity == null || capacity.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }

        final int n = capacity.length;

        // Ensure matrix is square and capacities are non-negative
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

        // Validate source and sink
        if (source < 0 || sink < 0 || source >= n || sink >= n) {
            throw new IllegalArgumentException("Source and sink must be valid vertex indices");
        }

        if (source == sink) {
            return 0;
        }

        // Create residual graph
        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }

        int[] level = new int[n];
        int maxFlow = 0;

        // Repeatedly build level graph and find blocking flows
        while (bfsBuildLevelGraph(residual, source, sink, level)) {
            int[] next = new int[n]; // current-edge optimization
            int flow;

            // Push flow while augmenting paths exist
            while ((flow = dfsBlocking(residual, level, next, source, sink, Integer.MAX_VALUE)) > 0) {
                maxFlow += flow;
            }
        }
        return maxFlow;
    }

    /**
     * Builds the level graph using BFS.
     */
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

    /**
     * DFS to find blocking flow in the level graph.
     */
    private static int dfsBlocking(int[][] residual, int[] level, int[] next,
                                   int u, int sink, int flow) {

        if (u == sink) {
            return flow;
        }

        final int n = residual.length;

        for (int v = next[u]; v < n; v++, next[u] = v) {
            if (residual[u][v] > 0 && level[v] == level[u] + 1) {
                int pushed = dfsBlocking(
                        residual, level, next, v, sink,
                        Math.min(flow, residual[u][v])
                );

                if (pushed > 0) {
                    residual[u][v] -= pushed;
                    residual[v][u] += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }
}
