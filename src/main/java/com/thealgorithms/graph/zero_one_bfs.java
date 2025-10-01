package com.thealgorithms.graphs;

import java.util.*;

/**
 * 0-1 BFS for shortest paths on graphs with 0/1 edge weights.
 *
 * <p>Time: O(V + E). Space: O(V).
 *
 * <p>References:
 * <ul>
 *   <li>https://cp-algorithms.com/graph/01_bfs.html</li>
 * </ul>
 */
public final class ZeroOneBfs {

    private ZeroOneBfs() {
        // utility class
    }

    /**
     * Computes shortest distances from {@code src} in a graph with edges weighted 0 or 1.
     *
     * @param n number of vertices labeled [0..n-1]
     * @param edges adjacency list where each entry is (to, weight) with weight ∈ {0,1}
     * @param src source vertex
     * @return distances array; {@code Integer.MAX_VALUE} if unreachable
     */
    public static int[] shortestPaths(int n, List<List<int[]>> edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<Integer> dq = new ArrayDeque<>();
        dist[src] = 0;
        dq.addFirst(src);

        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            for (int[] e : edges.get(u)) {
                int v = e[0], w = e[1];
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
