package com.thealgorithms.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This class provides an implementation to check whether a given graph is
 * bipartite using Depth First Search (DFS).
 * 
 * A bipartite graph is one in which the set of vertices can be divided into two
 * disjoint subsets
 * such that no two vertices within the same subset are adjacent.
 * 
 * For more information, see
 * <a href="https://en.wikipedia.org/wiki/Bipartite_graph">Wikipedia: Bipartite
 * Graph</a>.
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * Input:
 * 4 4
 * 0 1
 * 1 2
 * 2 3
 * 3 0
 * 
 * Output:
 * Graph is Bipartite: true
 * </pre>
 * 
 * Author: Ilma Akram Ansari
 */
public final class BipartiteGraphDFS {

    // Private constructor to prevent instantiation
    private BipartiteGraphDFS() {
    }

    /**
     * Checks whether the given undirected graph is bipartite using Depth First
     * Search (DFS).
     *
     * @param graph The adjacency list representation of the graph,
     *              where graph[i] contains an array of all vertices adjacent to
     *              vertex i.
     * @return {@code true} if the graph is bipartite, otherwise {@code false}.
     */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1); // -1 = uncolored

        // Handle disconnected graphs
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfs(graph, i, 0, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Recursive DFS helper method to assign colors and check bipartiteness.
     *
     * @param graph     The adjacency list of the graph.
     * @param node      The current vertex being explored.
     * @param currColor The color to assign to this vertex (0 or 1).
     * @param color     Array storing colors of all vertices.
     * @return {@code true} if no conflict is found, otherwise {@code false}.
     */
    private static boolean dfs(int[][] graph, int node, int currColor, int[] color) {
        color[node] = currColor;

        for (int neighbor : graph[node]) {
            if (color[neighbor] == -1) {
                // Assign opposite color to neighbor
                if (!dfs(graph, neighbor, 1 - currColor, color)) {
                    return false;
                }
            } else if (color[neighbor] == currColor) {
                // Found same color on adjacent nodes -> not bipartite
                return false;
            }
        }
        return true;
    }

    /**
     * Main method to take input and check if the graph is bipartite.
     * Input format:
     * n m
     * u1 v1
     * u2 v2
     * ...
     * um vm
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices and edges:");
        int n = sc.nextInt(); // number of vertices
        int m = sc.nextInt(); // number of edges

        // Using adjacency list representation
        int[][] temp = new int[n][n]; // temporary adjacency matrix for building adjacency list
        int[] degree = new int[n]; // degree of each vertex

        System.out.println("Enter each edge (u v):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            temp[u][degree[u]++] = v;
            temp[v][degree[v]++] = u; // since graph is undirected
        }

        // Convert to adjacency list (trim extra zeros)
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.copyOf(temp[i], degree[i]);
        }

        boolean result = isBipartite(graph);
        System.out.println("Graph is Bipartite: " + result);

        sc.close();
    }
}
