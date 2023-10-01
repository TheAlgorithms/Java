package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Objects;

/*
 *
 * @aurthor - Prabhat-Kumar-42
 * @github - https://github.com/Prabhat-Kumar-42
 *
 * Problem :
 *
 * Given an adjacency list of a graph with vertices = v, having
 * 0 based indexing. Find all Articulation Points and Articulation
 * Bridge Present in Graph
 *
 * Articulation Point : vertex whcih when removed increases the number of
 * connected component in graph.
 *
 * Articulation Bridge : edge which when removed increases the number of
 * connected component in graph.
 *
 * eg : Given graph with vertices = 5 and
 *     adj list is :  vertex -> neighbours (Assuming all edges are bidirectional)
 *                    0 -> 1, 2
 *                    1 -> 2
 *                    2 -> 3
 *                    3 -> 4
 *
 *      Graph is :
 *
 *                0-------1
 *                |      /
 *                |     /
 *                |    /
 *                |   /
 *                |  /       4
 *                | /        |
 *                |/         |
 *                2----------3
 *
 *     current connected component = 1
 *
 *  -> Articulation Points : 2 , 3
 *     Explanation :
 *
 *             -> When removing 2, graph changes to below and connected components increases
 *                from 1 to 2.
 *
 *                0-------1
 *                |      /
 *                |     /
 *                |    /
 *                |   /
 *                |  /       4
 *                | /        |
 *                |/         |
 *                           3
 *
 *
 *              -> When removing 3, graph changes to below and connected components increases
 *                from 1 to 2.
 *
 *                0-------1
 *                |      /
 *                |     /
 *                |    /
 *                |   /
 *                |  /       4
 *                | /
 *                |/
 *                2
 *
 *
 *  -> Articulation Bridges: edge : 2-3, edge: 3-4
 *     Explanation :
 *
 *             -> When removing edge : 2-3, graph changes to below and connected components increases
 *                from 1 to 2.
 *
 *                0-------1
 *                |      /
 *                |     /
 *                |    /
 *                |   /
 *                |  /       4
 *                | /        |
 *                |/         |
 *                2          3
 *
 *
 *              -> When removing edge : 3-4, graph changes to below and connected components increases
 *                from 1 to 2.
 *
 *                0-------1
 *                |      /
 *                |     /
 *                |    /
 *                |   /
 *                |  /       4
 *                | /
 *                |/
 *                2----------3
 *
 */

class ArticulationPointsAndBridges {

    // Edge class used to store bridges
    public static class Edge {
        private int u;
        private int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        // Override equals method to compare the values of u and v
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Edge other = (Edge) obj;
            return u == other.u && v == other.v;
        }

        // Override hashCode method for consistency when using in collections
        @Override
        public int hashCode() {
            return Objects.hash(u, v);
        }
    }

    private int V; // Number of vertices
    private ArrayList<ArrayList<Integer>> adj; // Adjacency list

    private int time = 0;
    private boolean[] visited; // To track visited vertices during traversal
    private int[] disc; // Discovery time of vertices
    private int[] low; // Lowest discovery time reachable from the vertex
    private int[] parent; // Parent vertex in DFS traversal, initialized to -1
    private boolean[] articulationPoints; // To store articulation points
    private ArrayList<Edge> bridges; // ArrayList to store bridges

    /**
     * Constructor
     * @param v number of vertex in graph
     *
     */
    public ArticulationPointsAndBridges(int v) {
        V = v;
        adj = new ArrayList<>(v); // Initialize with initial capacity

        visited = new boolean[V];
        disc = new int[V];
        low = new int[V];
        parent = new int[V];
        articulationPoints = new boolean[V];
        bridges = new ArrayList<>();

        // Initialize parent to -1 and adjacency lists
        for (int i = 0; i < v; ++i) {
            parent[i] = -1;
            adj.add(new ArrayList<Integer>());
        }
    }

    /**
     * function addEdge - Add an edge between vertices u and v
     * @param u - source vertex of graph that needs to be conneted to v
     * @param v - end vertex f graph that needs to be connected to u
     */
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Find articulation points and bridges in the graph
    public void findArticulationPointsAndBridges() {
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                findArticulationPointsAndBridges(i);
            }
        }
    }

    /* Helper function to perform DFS and find articulation points and bridges
     *
     * @param u source vertex
     */
    private void findArticulationPointsAndBridges(int u) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                findArticulationPointsAndBridges(v);
                low[u] = Math.min(low[u], low[v]);

                // Check if u is an articulation point
                if (parent[u] == -1 && children > 1) {
                    articulationPoints[u] = true;
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    articulationPoints[u] = true;
                }

                // Check if the edge u-v is a bridge
                if (low[v] > disc[u]) {
                    bridges.add(new Edge(u, v));
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    /* Check if a vertex is an articulation point
     *
     * @param u source vertex
     */
    public boolean isArticulationPoint(int u) {
        return articulationPoints[u];
    }

    // Get an ArrayList of bridges in the graph
    public ArrayList<Edge> getBridges() {
        return bridges;
    }

    // Get an ArrayList of articulation points in the graph
    public ArrayList<Integer> getArticulationPoints() {
        ArrayList<Integer> articulationPointsList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (articulationPoints[i]) {
                articulationPointsList.add(i);
            }
        }
        return articulationPointsList;
    }
}
