package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 0-1 BFS for shortest paths on graphs with edges weighted 0 or 1.
 *
 * <p>Time Complexity: O(V + E). Space Complexity: O(V).
 *
 * <p>References:
 * <ul>
 *   <li>https://cp-algorithms.com/graph/01_bfs.html</li>
 * </ul>
 */
public final class ZeroOneBfs {

    private ZeroOneBfs() {
        // Utility class; do not instantiate.
    }

    /**
     * Computes shortest distances from {@code src} in a graph whose edges have weight 0 or 1.
     *
     * @param n the number of vertices, labeled {@code 0..n-1}
     * @param adj adjacency list; for each vertex u, {@code adj.get(u)} is a list of pairs
     *            {@code (v, w)} where {@code v} is a neighbor and {@code w} is 0 or 1
     * @param src the source vertex
     * @return an array of distances; {@code Integer.MAX_VALUE} denotes unreachable
     * @throws IllegalArgumentException if {@code n < 0}, {@code src} is out of range,
     *                                  or any edge has weight other than 0 or 1
     */
    public static int[] shortestPaths(int n, List<List<int[]>> adj, int src) {
        if (n < 0 || src < 0 || src >= n) {
            throw new IllegalArgumentException("Invalid n or src");
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<Integer> dq = new ArrayDeque<>();

        dist[src] = 0;
        dq.addFirst(src);

        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            List<int[]> edges = adj.get(u);
            if (edges == null) {
                continue;
            }
            for (int[] e : edges) {
                if (e == null || e.length < 2) {
                    continue;
                }
                int v = e[0];
                int w = e[1];
                if (v < 0 || v >= n) {
                    continue; // ignore bad edges
                }
                if (w != 0 && w != 1) {
                    throw new IllegalArgumentException("Edge weight must be 0 or 1");
                }
                int nd = dist[u] + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    if (w == 0) {
                        dq.addFirst(v);
                    } else {
                        dq.addLast(v);
                    }
                }
            }
        }
        return dist;
    }
}
