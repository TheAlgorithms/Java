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

        // Make a working copy of the adjacency matrix so we can merge vertices
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, g[i], 0, n);
        }

        // vertices contains the list of active vertex indices (initially 0..n-1)
        int[] vertices = new int[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = i;
        }

        int bestCut = Integer.MAX_VALUE;

        // Repeat n-1 phases; in each phase we reduce number of active vertices by 1
        for (int m = n; m > 1; m--) {
            int[] weights = new int[n];       // accumulated weights for selection
            boolean[] added = new boolean[n]; // which original vertices have been added in this phase
            int prev = -1;                    // previously added vertex id in the growing set

            for (int i = 0; i < m; i++) {
                // Select the not-yet-added vertex (among the first m active vertices) with maximum weight
                int sel = -1;
                for (int j = 0; j < m; j++) {
                    int v = vertices[j];
                    if (!added[v] && (sel == -1 || weights[v] > weights[sel])) {
                        sel = v;
                    }
                }

                // If sel is -1 it means the graph is disconnected in the remaining part;
                // the minimum cut value is 0 in that case.
                if (sel == -1) {
                    return 0;
                }

                added[sel] = true;

                // If this is the last vertex added in this phase, weights[sel] is the cut weight between sel and the rest.
                if (i == m - 1) {
                    // Update best cut
                    if (weights[sel] < bestCut) {
                        bestCut = weights[sel];
                    }

                    // Merge 'sel' into 'prev' (combine their edges) to reduce vertex count
                    if (prev != -1) {
                        for (int k = 0; k < n; k++) {
                            // accumulate edges from sel into prev
                            g[prev][k] += g[sel][k];
                            g[k][prev] = g[prev][k];
                        }

                        // Remove 'sel' from vertices[] by replacing it with last active vertex
                        int selIndex = -1;
                        for (int j = 0; j < m; j++) {
                            if (vertices[j] == sel) {
                                selIndex = j;
                                break;
                            }
                        }
                        // replace position selIndex with last active vertex (m-1)
                        vertices[selIndex] = vertices[m - 1];
                    }
                    // Phase done
                } else {
                    // not last: update weights of remaining active vertices
                    for (int j = 0; j < m; j++) {
                        int v = vertices[j];
                        if (!added[v]) {
                            weights[v] += g[sel][v];
                        }
                    }
                    prev = sel;
                }
            }
        }

        return bestCut == Integer.MAX_VALUE ? 0 : bestCut;
    }
}

