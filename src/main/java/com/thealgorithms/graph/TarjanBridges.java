package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Tarjan's Bridge-Finding Algorithm for undirected graphs.
 *
 * <p>A <b>bridge</b> (also called a cut-edge) is an edge in an undirected graph whose removal
 * increases the number of connected components. Bridges represent critical links
 * in a network — if any bridge is removed, part of the network becomes unreachable.</p>
 *
 * <p>The algorithm performs a single Depth-First Search (DFS) traversal, tracking two
 * values for each vertex:</p>
 * <ul>
 *   <li><b>discoveryTime</b> — the time step at which the vertex was first visited.</li>
 *   <li><b>lowLink</b> — the smallest discovery time reachable from the subtree rooted
 *       at that vertex (via back edges).</li>
 * </ul>
 *
 * <p>An edge (u, v) is a bridge if and only if {@code lowLink[v] > discoveryTime[u]},
 * meaning there is no back edge from the subtree of v that can reach u or any ancestor of u.</p>
 *
 * <p>Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.</p>
 * <p>Space Complexity: O(V + E) for the adjacency list, discovery/low arrays, and recursion stack.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bridge_(graph_theory)">Wikipedia: Bridge (graph theory)</a>
 */
public final class TarjanBridges {

    private TarjanBridges() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Finds all bridge edges in an undirected graph.
     *
     * <p>The graph is represented as an adjacency list where each vertex is identified by
     * an integer in the range {@code [0, vertexCount)}. For each undirected edge (u, v),
     * v must appear in {@code adjacencyList.get(u)} and u must appear in
     * {@code adjacencyList.get(v)}.</p>
     *
     * @param vertexCount   the total number of vertices in the graph (must be non-negative)
     * @param adjacencyList the adjacency list representation of the graph; must contain
     *                      exactly {@code vertexCount} entries (one per vertex)
     * @return a list of bridge edges, where each bridge is represented as an {@code int[]}
     *         of length 2 with {@code edge[0] < edge[1]}; returns an empty list if no bridges exist
     * @throws IllegalArgumentException if {@code vertexCount} is negative, or if
     *                                  {@code adjacencyList} is null or its size does not match
     *                                  {@code vertexCount}
     */
    public static List<int[]> findBridges(int vertexCount, List<List<Integer>> adjacencyList) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("vertexCount must be non-negative");
        }
        if (adjacencyList == null || adjacencyList.size() != vertexCount) {
            throw new IllegalArgumentException("adjacencyList size must equal vertexCount");
        }

        List<int[]> bridges = new ArrayList<>();

        if (vertexCount == 0) {
            return bridges;
        }

        BridgeFinder finder = new BridgeFinder(vertexCount, adjacencyList, bridges);

        // Run DFS from every unvisited vertex to handle disconnected graphs
        for (int i = 0; i < vertexCount; i++) {
            if (!finder.visited[i]) {
                finder.dfs(i, -1);
            }
        }

        return bridges;
    }

    private static class BridgeFinder {
        private final List<List<Integer>> adjacencyList;
        private final List<int[]> bridges;
        private final int[] discoveryTime;
        private final int[] lowLink;
        boolean[] visited;
        private int timer;

        BridgeFinder(int vertexCount, List<List<Integer>> adjacencyList, List<int[]> bridges) {
            this.adjacencyList = adjacencyList;
            this.bridges = bridges;
            this.discoveryTime = new int[vertexCount];
            this.lowLink = new int[vertexCount];
            this.visited = new boolean[vertexCount];
            this.timer = 0;
        }

        /**
         * Performs DFS from the given vertex, computing discovery times and low-link values,
         * and collects any bridge edges found.
         *
         * @param u      the current vertex being explored
         * @param parent the parent of u in the DFS tree (-1 if u is a root)
         */
        void dfs(int u, int parent) {
            visited[u] = true;
            discoveryTime[u] = timer;
            lowLink[u] = timer;
            timer++;

            for (int v : adjacencyList.get(u)) {
                if (!visited[v]) {
                    dfs(v, u);
                    lowLink[u] = Math.min(lowLink[u], lowLink[v]);

                    if (lowLink[v] > discoveryTime[u]) {
                        bridges.add(new int[] {Math.min(u, v), Math.max(u, v)});
                    }
                } else if (v != parent) {
                    lowLink[u] = Math.min(lowLink[u], discoveryTime[v]);
                }
            }
        }
    }
}
