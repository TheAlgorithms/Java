package com.thealgorithms.graph;

import java.util.*;

/**
 * @author Panteleimon Tzecheridis
 *
 * Implementation of the Hopcroft–Karp algorithm for finding the maximum matching in a bipartite graph.
 *
 * The bipartite graph is assumed to have:
 * - Left part: vertices [0..nLeft-1]
 * - Right part: vertices [0..nRight-1]
 *
 * Adjacency list format: For each left vertex, list the right vertices it is connected to.
 * Example:
 *   adj[0] = [0, 1]  // left vertex 0 connects to right vertices 0 and 1
 *
 * Time complexity: O(E * sqrt(V))
 *
 * @see <a href="https://en.wikipedia.org/wiki/Hopcroft%E2%80%93Karp_algorithm">Wikipedia: Hopcroft–Karp algorithm</a>
 */
public class HopcroftKarp {

    private int nLeft, nRight;
    private List<List<Integer>> adj;
    private int[] pairU, pairV, dist;

    public HopcroftKarp(int nLeft, int nRight, List<List<Integer>> adj) {
        this.nLeft = nLeft;
        this.nRight = nRight;
        this.adj = adj;
        this.pairU = new int[nLeft];
        this.pairV = new int[nRight];
        this.dist = new int[nLeft];
        Arrays.fill(pairU, -1);
        Arrays.fill(pairV, -1);
    }

    /**
     * Returns the size of the maximum matching.
     */
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
                int u2 = pairV[v];
                if (u2 == -1) {
                    foundAugPath = true;
                } else if (dist[u2] == -1) {
                    dist[u2] = dist[u] + 1;
                    queue.add(u2);
                }
            }
        }
        return foundAugPath;
    }

    // DFS to find augmenting paths
    private boolean dfs(int u) {
        for (int v : adj.get(u)) {
            int u2 = pairV[v];
            if (u2 == -1 || (dist[u2] == dist[u] + 1 && dfs(u2))) {
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