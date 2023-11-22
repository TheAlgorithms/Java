package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Boruvka's algorithm to find Minimum Spanning Tree
 * (https://en.wikipedia.org/wiki/Bor%C5%AFvka%27s_algorithm)
 *
 * @author itakurah (https://github.com/itakurah)
 */

class BoruvkaAlgorithm {
    /**
     * Represents an edge in the graph
     */
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * Represents the graph
     */
    static class Graph {
        final int V;
        final int E;
        List<Edge> edges;

        /**
         * Constructor for the graph
         *
         * @param V number of vertices
         * @param E number of edges
         */
        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            this.edges = new ArrayList<>();
        }

        /**
         * Add an edge to the graph
         *
         * @param src    source vertex
         * @param dest   destination vertex
         * @param weight weight of the edge
         */
        void addEdge(int src, int dest, int weight) {
            edges.add(new Edge(src, dest, weight));
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
        Subset[] subsets = new Subset[graph.V];
        for (int v = 0; v < graph.V; ++v) {
            subsets[v] = new Subset(v, 0);
        }

        // Continue until the number of edges in the MST is V-1
        while (result.size() < graph.V - 1) {
            // Array to store the cheapest edge for each subset
            Edge[] cheapest = new Edge[graph.V];

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
            for (int i = 0; i < graph.V; ++i) {
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
