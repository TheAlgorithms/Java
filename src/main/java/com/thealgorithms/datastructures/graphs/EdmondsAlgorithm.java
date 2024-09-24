package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EdmondsAlgorithm class for finding maximum weight matching in a graph.
 * The graph is represented as an adjacency matrix, where graph[i][j]
 * indicates the weight of the edge between vertices i and j.
 * A weight of 0 means no edge exists.
 */
public final class EdmondsAlgorithm {
    // Private constructor to prevent instantiation
    private EdmondsAlgorithm() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // private static final int INF = Integer.MAX_VALUE;

    /**
     * Method to find the maximum weight matching in a graph.
     *
     * @param graph the adjacency matrix of the graph
     * @return a list of vertex pairs representing the maximum weight matching
     */
    public static List<int[]> maximumWeightMatching(int[][] graph) {
        int n = graph.length;
        boolean[] matched = new boolean[n];
        int[] match = new int[n];
        Arrays.fill(match, -1);
        List<int[]> result = new ArrayList<>();

        // Iterate through each vertex to find matches
        for (int u = 0; u < n; u++) {
            if (!matched[u]) {
                boolean[] visited = new boolean[n];
                findMatch(u, graph, matched, match, visited);
            }
        }

        // Collect the matches into the result list
        for (int v = 0; v < n; v++) {
            if (match[v] != -1) {
                result.add(new int[] {match[v], v});
            }
        }
        return result;
    }

    /**
     * Helper method to find matches using depth-first search (DFS).
     *
     * @param u       the vertex to start the search from
     * @param graph   the adjacency matrix of the graph
     * @param matched array indicating whether each vertex is matched
     * @param match   the current matching
     * @param visited array indicating visited vertices during DFS
     * @return true if a match was found, false otherwise
     */
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

    /**
     * Test cases to validate the EdmondsAlgorithm.
     */
    public static void runTests() {
        // Test case 1
        int[][] graph1 = {{0, 2, 0, 3}, {2, 0, 1, 0}, {0, 1, 0, 4}, {3, 0, 4, 0}};
        List<int[]> result1 = maximumWeightMatching(graph1);
        System.out.println("Test Case 1: ");
        printMatching(result1);

        // Test case 2: Simple bipartite graph
        int[][] graph2 = {{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}};
        List<int[]> result2 = maximumWeightMatching(graph2);
        System.out.println("Test Case 2: ");
        printMatching(result2);

        // Test case 3: No edges
        int[][] graph3 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        List<int[]> result3 = maximumWeightMatching(graph3);
        System.out.println("Test Case 3: ");
        printMatching(result3);
    }

    /**
     * Helper method to print the matching results.
     *
     * @param matching the list of vertex pairs representing the matching
     */
    private static void printMatching(List<int[]> matching) {
        if (matching.isEmpty()) {
            System.out.println("No matching found.");
        } else {
            for (int[] pair : matching) {
                System.out.println("Vertex " + pair[0] + " is matched with Vertex " + pair[1]);
            }
        }
        System.out.println(); // Blank line for better readability
    }
}
