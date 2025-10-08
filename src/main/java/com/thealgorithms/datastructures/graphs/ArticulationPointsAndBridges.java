package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Java program that implements an algorithm to find Articulation Points (Cut Vertices) and Bridges (Cut Edges)
 * in an undirected graph using Depth-First Search (DFS).
 *
 * <p>
 * <b>Articulation Point (Cut Vertex):</b> A vertex in a graph whose removal increases the number of connected components.
 * In other words, removing an articulation point disconnects the graph or increases the number of separate subgraphs.
 *
 * <p>
 * <b>Bridge (Cut Edge):</b> An edge in a graph whose removal increases the number of connected components.
 * Bridges are critical connections in the graph.
 *
 * <h3>Algorithm Overview:</h3>
 * <ul>
 * <li><b>DFS Search:</b> A depth-first search is performed on the graph to build a DFS tree.</li>
 * <li><b>Discovery Time:</b> The time at which a vertex is first visited during DFS.</li>
 * <li><b>Low-Link Value:</b> The minimum discovery time reachable from the vertex's DFS subtree.</li>
 * <li><b>Articulation Point Detection:</b>
 *     <ul>
 *     <li>For the root: It's an articulation point if it has more than one child in the DFS tree.</li>
 *     <li>For non-root vertices: A vertex u is an articulation point if it has a child v such that
 *         no vertex in the subtree rooted at v has a back edge to an ancestor of u (i.e., low[v] >= discovery[u]).</li>
 *     </ul>
 * </li>
 * <li><b>Bridge Detection:</b> An edge (u, v) is a bridge if there is no back edge from the subtree rooted at v
 *     that goes to an ancestor of u (i.e., low[v] > discovery[u]).</li>
 * </ul>
 *
 * <p>
 * <b>Time Complexity:</b> O(V + E), where V is the number of vertices and E is the number of edges.
 * <br>
 * <b>Space Complexity:</b> O(V) for storing discovery times, low-link values, and visited status.
 *
 * <p>
 * Example of an undirected graph:
 * <pre>
 *       0 -------- 1 -------- 2
 *       |          |
 *       |          |
 *       3 -------- 4
 * </pre>
 *
 * <p>
 * For the above graph:
 * <ul>
 * <li><b>Articulation Points:</b> 1 (removing it disconnects the graph)</li>
 * <li><b>Bridges:</b> (0,1), (1,2), (1,4)</li>
 * </ul>
 *
 * <h3>Applications:</h3>
 * <ul>
 * <li>Network reliability analysis (finding critical routers/connections)</li>
 * <li>Social network analysis (finding influential people)</li>
 * <li>Circuit design (identifying critical components)</li>
 * <li>Transportation networks (finding critical roads/bridges)</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Biconnected_component">Biconnected Component on Wikipedia</a>
 * @see <a href="https://cp-algorithms.com/graph/bridge-searching.html">Bridge Finding Algorithm</a>
 */
public final class ArticulationPointsAndBridges {

    // Represents an edge in the graph
    public static class Edge {
        private final int from;
        private final int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return (from == edge.from && to == edge.to) || (from == edge.to && to == edge.from);
        }

        @Override
        public int hashCode() {
            // Ensure edges (u,v) and (v,u) have the same hash code
            return Math.min(from, to) * 31 + Math.max(from, to);
        }

        @Override
        public String toString() {
            return "(" + from + ", " + to + ")";
        }
    }

    // Result class to hold both articulation points and bridges
    public static class Result {
        private final Set<Integer> articulationPoints;
        private final Set<Edge> bridges;

        public Result(Set<Integer> articulationPoints, Set<Edge> bridges) {
            this.articulationPoints = articulationPoints;
            this.bridges = bridges;
        }

        public Set<Integer> getArticulationPoints() {
            return articulationPoints;
        }

        public Set<Edge> getBridges() {
            return bridges;
        }

        @Override
        public String toString() {
            return "Articulation Points: " + articulationPoints + ", Bridges: " + bridges;
        }
    }

    private ArticulationPointsAndBridges() {
    }

    // Timer for tracking discovery time
    private static int time;

    /**
     * Finds articulation points and bridges in an undirected graph.
     *
     * @param vertices the number of vertices in the graph
     * @param graph the adjacency list representation of the graph
     * @return a Result object containing sets of articulation points and bridges
     * @throws IllegalArgumentException if vertices is negative or if graph is null
     */
    public static Result findArticulationPointsAndBridges(int vertices, List<List<Integer>> graph) {
        if (vertices < 0) {
            throw new IllegalArgumentException("Number of vertices cannot be negative");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }

        // Initialize data structures
        int[] discoveryTime = new int[vertices];
        int[] lowLink = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];

        Set<Integer> articulationPoints = new HashSet<>();
        Set<Edge> bridges = new HashSet<>();

        // Initialize arrays
        for (int i = 0; i < vertices; i++) {
            discoveryTime[i] = -1;
            lowLink[i] = -1;
            parent[i] = -1;
        }

        time = 0;

        // Perform DFS from each unvisited vertex (handles disconnected graphs)
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, discoveryTime, lowLink, parent, articulationPoints, bridges, graph);
            }
        }

        return new Result(articulationPoints, bridges);
    }

    /**
     * Depth-First Search utility function to find articulation points and bridges.
     *
     * @param u the current vertex being visited
     * @param visited array tracking visited vertices
     * @param discoveryTime array storing discovery time of each vertex
     * @param lowLink array storing low-link values
     * @param parent array storing parent of each vertex in DFS tree
     * @param articulationPoints set to store articulation points
     * @param bridges set to store bridges
     * @param graph the adjacency list representation of the graph
     */
    private static void dfs(int u, boolean[] visited, int[] discoveryTime, int[] lowLink, int[] parent, Set<Integer> articulationPoints, Set<Edge> bridges, List<List<Integer>> graph) {
        // Count children in DFS tree (used for root articulation point check)
        int children = 0;

        // Mark the current vertex as visited
        visited[u] = true;

        // Set discovery time and low-link value
        discoveryTime[u] = time;
        lowLink[u] = time;
        time++;

        // Explore all adjacent vertices
        for (Integer v : graph.get(u)) {
            // If v is not visited, make it a child of u in DFS tree and recur
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfs(v, visited, discoveryTime, lowLink, parent, articulationPoints, bridges, graph);

                // Check if the subtree rooted at v has a connection back to an ancestor of u
                lowLink[u] = Math.min(lowLink[u], lowLink[v]);

                // Articulation point check for non-root vertex
                // If u is not root and low-link value of v is greater than or equal to discovery time of u
                if (parent[u] != -1 && lowLink[v] >= discoveryTime[u]) {
                    articulationPoints.add(u);
                }

                // Bridge check
                // If low-link value of v is greater than discovery time of u, then (u,v) is a bridge
                if (lowLink[v] > discoveryTime[u]) {
                    bridges.add(new Edge(u, v));
                }
            } else if (v != parent[u]) {
                // Update low-link value of u for back edge (v is visited and not parent of u)
                lowLink[u] = Math.min(lowLink[u], discoveryTime[v]);
            }
        }

        // Articulation point check for root vertex
        // If u is root of DFS tree and has more than one child, it's an articulation point
        if (parent[u] == -1 && children > 1) {
            articulationPoints.add(u);
        }
    }

    /**
     * Convenience method to find only articulation points.
     *
     * @param vertices the number of vertices in the graph
     * @param graph the adjacency list representation of the graph
     * @return a set of articulation points
     * @throws IllegalArgumentException if vertices is negative or if graph is null
     */
    public static Set<Integer> findArticulationPoints(int vertices, List<List<Integer>> graph) {
        return findArticulationPointsAndBridges(vertices, graph).getArticulationPoints();
    }

    /**
     * Convenience method to find only bridges.
     *
     * @param vertices the number of vertices in the graph
     * @param graph the adjacency list representation of the graph
     * @return a set of bridges
     * @throws IllegalArgumentException if vertices is negative or if graph is null
     */
    public static Set<Edge> findBridges(int vertices, List<List<Integer>> graph) {
        return findArticulationPointsAndBridges(vertices, graph).getBridges();
    }
}
