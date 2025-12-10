package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class provides a method to check if a given undirected graph is bipartite using Depth-First Search (DFS).
 * A bipartite graph is a graph whose vertices can be divided into two disjoint sets such that no two vertices
 * within the same set are adjacent. In other words, all edges must go between the two sets.
 *
 * The implementation leverages DFS to attempt to color the graph using two colors. If we can color the graph such
 * that no two adjacent vertices have the same color, the graph is bipartite.
 *
 * Example:
 * Input (Adjacency Matrix):
 * {{0, 1, 0, 1},
 *  {1, 0, 1, 0},
 *  {0, 1, 0, 1},
 *  {1, 0, 1, 0}}
 *
 * Output: YES (This graph is bipartite)
 *
 * Input (Adjacency Matrix):
 * {{0, 1, 1, 0},
 *  {1, 0, 1, 0},
 *  {1, 1, 0, 1},
 *  {0, 0, 1, 0}}
 *
 * Output: NO (This graph is not bipartite)
 */
public final class BipartiteGraphDFS {
    private BipartiteGraphDFS() {
    }

    /**
     * Helper method to perform DFS and check if the graph is bipartite.
     *
     * During DFS traversal, this method attempts to color each vertex in such a way
     * that no two adjacent vertices share the same color.
     *
     * @param v     Number of vertices in the graph
     * @param adj   Adjacency list of the graph where each index i contains a list of adjacent vertices
     * @param color Array to store the color assigned to each vertex (-1 indicates uncolored)
     * @param node  Current vertex being processed
     * @return      True if the graph (or component of the graph) is bipartite, otherwise false
     */
    private static boolean bipartite(int v, ArrayList<ArrayList<Integer>> adj, int[] color, int node) {
        if (color[node] == -1) {
            color[node] = 1;
        }
        for (Integer it : adj.get(node)) {
            if (color[it] == -1) {
                color[it] = 1 - color[node];
                if (!bipartite(v, adj, color, it)) {
                    return false;
                }
            } else if (color[it] == color[node]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if the graph is bipartite.
     *
     * @param v   Number of vertices in the graph
     * @param adj Adjacency list of the graph
     * @return    True if the graph is bipartite, otherwise false
     */
    public static boolean isBipartite(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[v + 1];
        Arrays.fill(color, -1);
        for (int i = 0; i < v; i++) {
            if (color[i] == -1) {
                if (!bipartite(v, adj, color, i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
