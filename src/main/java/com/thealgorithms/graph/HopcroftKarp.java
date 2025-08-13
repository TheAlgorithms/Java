package com.thealgorithms.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Hopcroft–Karp algorithm for maximum bipartite matching.
 *
 * Left part: vertices [0,nLeft-1], Right part: [0,nRight-1].
 * Adjacency list: for each left vertex u, list right vertices v it connects to.
 *
 * Time complexity: O(E * sqrt(V)).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Hopcroft%E2%80%93Karp_algorithm">
 *      Wikipedia: Hopcroft–Karp algorithm</a>
 * @author ptzecher
 */
public class HopcroftKarp {

    private final int nLeft;
    private final List<List<Integer>> adj;

    private final int[] pairU;
    private final int[] pairV;
    private final int[] dist;

    public HopcroftKarp(int nLeft, int nRight, List<List<Integer>> adj) {
        this.nLeft = nLeft;
        this.adj = adj;

        this.pairU = new int[nLeft];
        this.pairV = new int[nRight];
        this.dist = new int[nLeft];

        Arrays.fill(pairU, -1);
        Arrays.fill(pairV, -1);
    }

    /** Returns the size of the maximum matching. */
    public int maxMatching() {
        int matching = 0;
        while (bfs()) {
            for (int u = 0; u < nLeft; u++) {
                if (pairU[u] == -1 && dfs(u)) {
                    matching++;
                }
            }
        }
        return matching;
    }

    // BFS to build layers
    private boolean bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        Arrays.fill(dist, -1);

        for (int u = 0; u < nLeft; u++) {
            if (pairU[u] == -1) {
                dist[u] = 0;
                queue.add(u);
            }
        }

        boolean foundAugPath = false;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                int matchedLeft = pairV[v];
                if (matchedLeft == -1) {
                    foundAugPath = true;
                } else if (dist[matchedLeft] == -1) {
                    dist[matchedLeft] = dist[u] + 1;
                    queue.add(matchedLeft);
                }
            }
        }
        return foundAugPath;
    }

    // DFS to find augmenting paths within the BFS layering
    private boolean dfs(int u) {
        for (int v : adj.get(u)) {
            int matchedLeft = pairV[v];
            if (matchedLeft == -1 || (dist[matchedLeft] == dist[u] + 1 && dfs(matchedLeft))) {
                pairU[u] = v;
                pairV[v] = u;
                return true;
            }
        }
        dist[u] = -1;
        return false;
    }

    public int[] getLeftMatches() {
        return pairU.clone();
    }

    public int[] getRightMatches() {
        return pairV.clone();
    }
}
