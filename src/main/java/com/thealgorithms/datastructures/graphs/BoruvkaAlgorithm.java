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

        Edge(final int src, final int dest, final int weight) {
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
         * @param edges  list of edges
         */
        Graph(final int vertex, final List<Edge> edges) {
            if (vertex < 0) {
                throw new IllegalArgumentException("Number of vertices must be positive");
            }
            if (edges == null || edges.isEmpty()) {
                throw new IllegalArgumentException("Edges list must not be null or empty");
            }
            for (final var edge : edges) {
                checkEdgeVertices(edge.src, vertex);
                checkEdgeVertices(edge.dest, vertex);
            }

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
        Subset[] subsets = initializeSubsets(graph);

        // Continue until the number of edges in the MST is V-1
        while (result.size() < graph.vertex - 1) {
            // Array to store the cheapest edge for each subset
            Edge[] cheapest = new Edge[graph.vertex];

            // Iterate through all edges and update the cheapest edge for each subset
            updateCheapestEdges(graph, subsets, cheapest);

            // Add the cheapest edges to the result and perform Union operation
            addCheapestEdgesAndUnion(graph, subsets, result, cheapest);
        }
        return result;
    }

    /**
     * Initializes subsets for Union-Find
     *
     * @param graph the graph
     * @return the initialized subsets
     */
    private static Subset[] initializeSubsets(Graph graph) {
        Subset[] subsets = new Subset[graph.vertex];
        for (int v = 0; v < graph.vertex; ++v) {
            subsets[v] = new Subset(v, 0);
        }
        return subsets;
    }

    /**
     * Updates the cheapest edge for each subset based on the given graph and subsets
     *
     * @param graph    the graph
     * @param subsets  array of subsets
     * @param cheapest array to store the cheapest edge for each subset
     */
    private static void updateCheapestEdges(Graph graph, Subset[] subsets, Edge[] cheapest) {
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
    }

    /**
     * Adds the cheapest edges to the result list and performs Union operation on the subsets.
     *
     * @param graph    the graph
     * @param subsets  Array of subsets used for Union-Find operations.
     * @param result   List to store the edges of the Minimum Spanning Tree.
     * @param cheapest Array containing the cheapest edge for each subset.
     */
    private static void addCheapestEdgesAndUnion(Graph graph, Subset[] subsets, List<Edge> result, Edge[] cheapest) {
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

    /**
     * Checks if the edge vertices are in a valid range
     *
     * @param vertex     the vertex to check
     * @param upperBound the upper bound for the vertex range
     */
    private static void checkEdgeVertices(int vertex, int upperBound) {
        if (vertex < 0 || vertex >= upperBound) {
            throw new IllegalArgumentException("Edge vertex out of range");
        }
    }
}
