package com.thealgorithms.datastructures.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class provides a method to check if a given graph is bipartite using Depth-First Search (DFS).
 * A bipartite graph is a graph whose vertices can be divided into two disjoint sets such that no two graph vertices
 * within the same set are adjacent.
 *
 * Example:
 * Input : {{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}}
 * Output : YES
 */
public final class BipartiteGraphDFS {
    private BipartiteGraphDFS() {
    }

    /**
     * Helper method to perform DFS and check if the graph is bipartite.
     *
     * @param v     Number of vertices in the graph
     * @param adj   Adjacency list of the graph
     * @param color Array to store colors assigned to vertices
     * @param node  Current node being processed
     * @return      True if the graph is bipartite, otherwise false
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
        // Code here
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

    /**
     * Main method to read input and check if the graph is bipartite.
     *
     * @param args Command line arguments
     * @throws IOException If an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine().trim());
        while (t-- > 0) {
            String[] str1 = read.readLine().trim().split(" ");
            int numVertices = Integer.parseInt(str1[0]);
            int numEdges = Integer.parseInt(str1[1]);

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < numEdges; i++) {
                String[] str2 = read.readLine().trim().split(" ");
                int vertexU = Integer.parseInt(str2[0]);
                int vertexV = Integer.parseInt(str2[1]);
                adj.get(vertexU).add(vertexV);
                adj.get(vertexV).add(vertexU);
            }

            boolean ans = isBipartite(numVertices, adj);
            if (ans) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
