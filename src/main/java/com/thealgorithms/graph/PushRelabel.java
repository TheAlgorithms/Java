package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Push–Relabel (Relabel-to-Front variant simplified to array scanning) for maximum flow.
 *
 * <p>Input graph is a capacity matrix where {@code capacity[u][v]} is the capacity of the edge
 * {@code u -> v}. Capacities must be non-negative. Vertices are indexed in {@code [0, n)}.
 *
 * <p>Time complexity: O(V^3) in the worst case for the array-based variant; typically fast in
 * practice. This implementation uses a residual network over an adjacency-matrix representation.
 *
 * <p>The API mirrors {@link EdmondsKarp#maxFlow(int[][], int, int)} and {@link Dinic#maxFlow(int[][], int, int)}.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Push%E2%80%93relabel_maximum_flow_algorithm">Wikipedia: Push–Relabel maximum flow algorithm</a>
 */
public final class PushRelabel {

    private PushRelabel() {
    }

    /**
     * Computes the maximum flow from {@code source} to {@code sink} using Push–Relabel.
     *
     * @param capacity square capacity matrix (n x n); entries must be >= 0
     * @param source source vertex index in [0, n)
     * @param sink sink vertex index in [0, n)
     * @return the maximum flow value
     * @throws IllegalArgumentException if inputs are invalid
     */
    public static int maxFlow(int[][] capacity, int source, int sink) {
        validate(capacity, source, sink);
        final int n = capacity.length;
        if (source == sink) {
            return 0;
        }

        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }

        int[] height = new int[n];
        int[] excess = new int[n];
        int[] nextNeighbor = new int[n];

        // Preflow initialization
        height[source] = n;
        for (int v = 0; v < n; v++) {
            int cap = residual[source][v];
            if (cap > 0) {
                residual[source][v] -= cap;
                residual[v][source] += cap;
                excess[v] += cap;
                excess[source] -= cap;
            }
        }

        // Active queue contains vertices (except source/sink) with positive excess
        Queue<Integer> active = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (v != source && v != sink && excess[v] > 0) {
                active.add(v);
            }
        }

        State state = new State(residual, height, excess, nextNeighbor, source, sink, active);

        while (!active.isEmpty()) {
            int u = active.poll();
            discharge(u, state);
            if (excess[u] > 0) {
                // still active after discharge; push to back
                active.add(u);
            }
        }

        // Total flow equals excess at sink
        return excess[sink];
    }

    private static void discharge(int u, State s) {
        final int n = s.residual.length;
        while (s.excess[u] > 0) {
            if (s.nextNeighbor[u] >= n) {
                relabel(u, s.residual, s.height);
                s.nextNeighbor[u] = 0;
                continue;
            }
            int v = s.nextNeighbor[u];
            if (s.residual[u][v] > 0 && s.height[u] == s.height[v] + 1) {
                int delta = Math.min(s.excess[u], s.residual[u][v]);
                s.residual[u][v] -= delta;
                s.residual[v][u] += delta;
                s.excess[u] -= delta;
                int prevExcessV = s.excess[v];
                s.excess[v] += delta;
                if (v != s.source && v != s.sink && prevExcessV == 0) {
                    s.active.add(v);
                }
            } else {
                s.nextNeighbor[u]++;
            }
        }
    }

    private static final class State {
        final int[][] residual;
        final int[] height;
        final int[] excess;
        final int[] nextNeighbor;
        final int source;
        final int sink;
        final Queue<Integer> active;

        State(int[][] residual, int[] height, int[] excess, int[] nextNeighbor, int source, int sink, Queue<Integer> active) {
            this.residual = residual;
            this.height = height;
            this.excess = excess;
            this.nextNeighbor = nextNeighbor;
            this.source = source;
            this.sink = sink;
            this.active = active;
        }
    }

    private static void relabel(int u, int[][] residual, int[] height) {
        final int n = residual.length;
        int minHeight = Integer.MAX_VALUE;
        for (int v = 0; v < n; v++) {
            if (residual[u][v] > 0) {
                minHeight = Math.min(minHeight, height[v]);
            }
        }
        if (minHeight < Integer.MAX_VALUE) {
            height[u] = minHeight + 1;
        }
    }

    private static void validate(int[][] capacity, int source, int sink) {
        if (capacity == null || capacity.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }
        int n = capacity.length;
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
    }
}
