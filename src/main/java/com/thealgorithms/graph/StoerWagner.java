package com.thealgorithms.graph;

/**
 * An implementation of the Stoer-Wagner algorithm to find the global minimum cut of an undirected, weighted graph.
 * A minimum cut is a partition of the graph's vertices into two disjoint sets with the minimum possible edge weight
 * sum connecting the two sets.
 *
 * Wikipedia: https://en.wikipedia.org/wiki/Stoer%E2%80%93Wagner_algorithm
 * Time Complexity: O(V^3) where V is the number of vertices.
 */
public class StoerWagner {

    /**
     * Finds the minimum cut in the given undirected, weighted graph.
     *
     * @param graph An adjacency matrix representing the graph. graph[i][j] is the weight of the edge between i and j.
     * @return The weight of the minimum cut.
     */
    public int findMinCut(int[][] graph) {
        int n = graph.length;
        if (n < 2) {
            return 0;
        }

        int[][] currentGraph = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, currentGraph[i], 0, n);
        }

        int minCut = Integer.MAX_VALUE;
        boolean[] merged = new boolean[n];

        for (int phase = 0; phase < n - 1; phase++) {
            boolean[] inSetA = new boolean[n];
            int[] weights = new int[n];
            int prev = -1;
            int last = -1;

            for (int i = 0; i < n - phase; i++) {
                int maxWeight = -1;
                int currentVertex = -1;

                for (int j = 0; j < n; j++) {
                    if (!merged[j] && !inSetA[j] && weights[j] > maxWeight) {
                        maxWeight = weights[j];
                        currentVertex = j;
                    }
                }

                if (currentVertex == -1) {
                    // This can happen if the graph is disconnected.
                    return 0;
                }

                prev = last;
                last = currentVertex;
                inSetA[last] = true;

                for (int j = 0; j < n; j++) {
                    if (!merged[j] && !inSetA[j]) {
                        weights[j] += currentGraph[last][j];
                    }
                }
            }

            minCut = Math.min(minCut, weights[last]);

            // Merge 'last' vertex into 'prev' vertex
            for (int i = 0; i < n; i++) {
                currentGraph[prev][i] += currentGraph[last][i];
                currentGraph[i][prev] = currentGraph[prev][i];
            }
            merged[last] = true;
        }

        return minCut;
    }
}
