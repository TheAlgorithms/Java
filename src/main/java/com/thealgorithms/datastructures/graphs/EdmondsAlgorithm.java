package com.thealgorithms.datastructures.graphs;

import java.util.*;

/*
Graph Representation: 
  
The graph is represented as an adjacency matrix, where graph[i][j] indicates the weight of the edge between vertices i and j. A weight of 0 means no edge exists.

Main Method:

A sample graph is defined as an adjacency matrix.
The maximumWeightMatching method is called to find the maximum weight matching.
The results are printed, showing which vertices are matched.

Algorithm Functionality:

The algorithm iterates through each vertex, applying a depth-first search (DFS) to find matches.
It keeps track of matched vertices and updates the matching as it finds new pairs.
 */

public class EdmondsAlgorithm {
    private static final int INF = Integer.MAX_VALUE;

    // Method to find maximum weight matching
    public static List<int[]> maximumWeightMatching(int[][] graph) {
        int n = graph.length;
        boolean[] matched = new boolean[n];
        int[] match = new int[n];
        Arrays.fill(match, -1);
        List<int[]> result = new ArrayList<>();

        for (int u = 0; u < n; u++) {
            if (!matched[u]) {
                boolean[] visited = new boolean[n];
                findMatch(u, graph, matched, match, visited);
            }
        }

        for (int v = 0; v < n; v++) {
            if (match[v] != -1) {
                result.add(new int[]{match[v], v});
            }
        }
        return result;
    }

    // Helper method to find match using DFS
    private static boolean findMatch(int u, int[][] graph, boolean[] matched, int[] match, boolean[] visited) {
        visited[u] = true;

        for (int v = 0; v < graph.length; v++) {
            if (graph[u][v] > 0 && !visited[v]) {
                visited[v] = true;

                if (match[v] == -1 || findMatch(match[v], graph, matched, match, visited)) {
                    matched[v] = true;
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        int[][] graph = {
            {0, 2, 0, 3},
            {2, 0, 1, 0},
            {0, 1, 0, 4},
            {3, 0, 4, 0}
        };

        List<int[]> matching = maximumWeightMatching(graph);

        System.out.println("Maximum Weight Matching:");
        for (int[] pair : matching) {
            System.out.println("Vertex " + pair[0] + " is matched with Vertex " + pair[1]);
        }
    }
}
