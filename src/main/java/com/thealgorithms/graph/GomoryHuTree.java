package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Gomory–Hu tree construction for undirected graphs via n−1 max-flow computations.
 *
 * <p>API: {@code buildTree(int[][])} returns {@code {parent, weight}} arrays for the tree.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Gomory%E2%80%93Hu_tree">Wikipedia: Gomory–Hu tree</a>
 */

public final class GomoryHuTree {
    private GomoryHuTree() {
    }

    public static int[][] buildTree(int[][] cap) {
        validateCapacityMatrix(cap);
        final int n = cap.length;
        if (n == 1) {
            return new int[][] {new int[] {-1}, new int[] {0}};
        }

        int[] parent = new int[n];
        int[] weight = new int[n];
        Arrays.fill(parent, 0);
        parent[0] = -1;
        weight[0] = 0;

        for (int s = 1; s < n; s++) {
            int t = parent[s];
            MaxFlowResult res = edmondsKarpWithMinCut(cap, s, t);
            int f = res.flow;
            weight[s] = f;

            for (int v = 0; v < n; v++) {
                if (v != s && parent[v] == t && res.reachable[v]) {
                    parent[v] = s;
                }
            }

            if (t != 0 && res.reachable[parent[t]]) {
                parent[s] = parent[t];
                parent[t] = s;
                weight[s] = weight[t];
                weight[t] = f;
            }
        }
        return new int[][] {parent, weight};
    }

    private static void validateCapacityMatrix(int[][] cap) {
        if (cap == null || cap.length == 0) {
            throw new IllegalArgumentException("Capacity matrix must not be null or empty");
        }
        final int n = cap.length;
        for (int i = 0; i < n; i++) {
            if (cap[i] == null || cap[i].length != n) {
                throw new IllegalArgumentException("Capacity matrix must be square");
            }
            for (int j = 0; j < n; j++) {
                if (cap[i][j] < 0) {
                    throw new IllegalArgumentException("Capacities must be non-negative");
                }
            }
        }
    }

    private static final class MaxFlowResult {
        final int flow;
        final boolean[] reachable;
        MaxFlowResult(int flow, boolean[] reachable) {
            this.flow = flow;
            this.reachable = reachable;
        }
    }

    private static MaxFlowResult edmondsKarpWithMinCut(int[][] capacity, int source, int sink) {
        final int n = capacity.length;
        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) {
            residual[i] = Arrays.copyOf(capacity[i], n);
        }

        int[] parent = new int[n];
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

        boolean[] reachable = new boolean[n];
        markReachable(residual, source, reachable);
        return new MaxFlowResult(maxFlow, reachable);
    }

    private static boolean bfs(int[][] residual, int source, int sink, int[] parent) {
        Arrays.fill(parent, -1);
        parent[source] = source;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < residual.length; v++) {
                if (residual[u][v] > 0 && parent[v] == -1) {
                    parent[v] = u;
                    if (v == sink) {
                        return true;
                    }
                    q.add(v);
                }
            }
        }
        return false;
    }

    private static void markReachable(int[][] residual, int source, boolean[] vis) {
        Arrays.fill(vis, false);
        Queue<Integer> q = new ArrayDeque<>();
        vis[source] = true;
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < residual.length; v++) {
                if (!vis[v] && residual[u][v] > 0) {
                    vis[v] = true;
                    q.add(v);
                }
            }
        }
    }
}
