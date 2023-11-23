package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Boruvka's algorithm to find Minimum Spanning Tree
 * (https://en.wikipedia.org/wiki/Bor%C5%AFvka%27s_algorithm)
 *
 * @author itakurah (https://github.com/itakurah)
 */

final class BoruvkaAlgorithm {
    private BoruvkaAlgorithm() {
    }
    /**
     * Represents an edge in the graph
     */
    static class Edge {
        final int src;
        final int dest;
        final int weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    /**
     * Represents the graph
     */
    static class Graph {
        final int vertex;
        final List<Edge> edges;

        /**
         * Constructor for the graph
         *
         * @param vertex number of vertices
         * @param edges list of edges
         */
        Graph(int vertex, List<Edge> edges) {
            this.vertex = vertex;
            this.edges = edges;
        }
    }

    /**
     * Represents a subset for Union-Find operations
     */
    static class Subset {
        int parent;
        int rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    /**
     * Finds the parent of the subset using path compression
     *
     * @param subsets array of subsets
     * @param i       index of the subset
     * @return the parent of the subset
     */
    static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    /**
     * Performs the Union operation for Union-Find
     *
     * @param subsets array of subsets
     * @param x       index of the first subset
     * @param y       index of the second subset
     */
    static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    /**
     * Boruvka's algorithm to find the Minimum Spanning Tree
     *
     * @param graph the graph
     * @return list of edges in the Minimum Spanning Tree
     */
    static List<Edge> boruvkaMST(Graph graph) {
        List<Edge> result = new ArrayList<>();

        // Initialize subsets for Union-Find
        Subset[] subsets = new Subset[graph.vertex];
        for (int v = 0; v < graph.vertex; ++v) {
            subsets[v] = new Subset(v, 0);
        }

        // Continue until the number of edges in the MST is V-1
        while (result.size() < graph.vertex - 1) {
            // Array to store the cheapest edge for each subset
            Edge[] cheapest = new Edge[graph.vertex];

            // Iterate through all edges and update the cheapest edge for each
            // subset
            for (Edge edge : graph.edges) {
                int set1 = find(subsets, edge.src);
                int set2 = find(subsets, edge.dest);

                if (set1 != set2) {
                    if (cheapest[set1] == null || edge.weight < cheapest[set1].weight) {
                        cheapest[set1] = edge;
                    }
                    if (cheapest[set2] == null || edge.weight < cheapest[set2].weight) {
                        cheapest[set2] = edge;
                    }
                }
            }

            // Add the cheapest edges to the result and perform Union operation
            for (int i = 0; i < graph.vertex; ++i) {
                if (cheapest[i] != null) {
                    int set1 = find(subsets, cheapest[i].src);
                    int set2 = find(subsets, cheapest[i].dest);

                    if (set1 != set2) {
                        result.add(cheapest[i]);
                        union(subsets, set1, set2);
                    }
                }
            }
        }

        return result;
    }
}
