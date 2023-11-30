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
    private static class Component {
        int parent;
        int rank;

        Component(final int parent, final int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    /**
     * Represents the state of Union-Find components and the result list
     */
    private static class BoruvkaState {
        List<Edge> result;
        Component[] components;
        final Graph graph;

        BoruvkaState(final Graph graph) {
            this.result = new ArrayList<>();
            this.components = initializeComponents(graph);
            this.graph = graph;
        }

        /**
         * Adds the cheapest edges to the result list and performs Union operation on the subsets.
         *
         * @param cheapest Array containing the cheapest edge for each subset.
         */
        void merge(final Edge[] cheapest) {
            for (int i = 0; i < graph.vertex; ++i) {
                if (cheapest[i] != null) {
                    final var component1 = find(components, cheapest[i].src);
                    final var component2 = find(components, cheapest[i].dest);

                    if (component1 != component2) {
                        result.add(cheapest[i]);
                        union(components, component1, component2);
                    }
                }
            }
        }

        /**
         * Checks if there are more edges to add to the result list
         *
         * @return true if there are more edges to add, false otherwise
         */
        boolean hasMoreEdgesToAdd() {
            return result.size() < graph.vertex - 1;
        }

        /**
         * Computes the cheapest edges for each subset in the Union-Find structure.
         *
         * @return an array containing the cheapest edge for each subset.
         */
        private Edge[] computeCheapestEdges() {
            Edge[] cheapest = new Edge[graph.vertex];
            for (final var edge : graph.edges) {
                final var set1 = find(components, edge.src);
                final var set2 = find(components, edge.dest);

                if (set1 != set2) {
                    if (cheapest[set1] == null || edge.weight < cheapest[set1].weight) {
                        cheapest[set1] = edge;
                    }
                    if (cheapest[set2] == null || edge.weight < cheapest[set2].weight) {
                        cheapest[set2] = edge;
                    }
                }
            }
            return cheapest;
        }

        /**
         * Initializes subsets for Union-Find
         *
         * @param graph the graph
         * @return the initialized subsets
         */
        private static Component[] initializeComponents(final Graph graph) {
            Component[] components = new Component[graph.vertex];
            for (int v = 0; v < graph.vertex; ++v) {
                components[v] = new Component(v, 0);
            }
            return components;
        }
    }

    /**
     * Finds the parent of the subset using path compression
     *
     * @param components array of subsets
     * @param i          index of the subset
     * @return the parent of the subset
     */
    static int find(final Component[] components, final int i) {
        if (components[i].parent != i) {
            components[i].parent = find(components, components[i].parent);
        }
        return components[i].parent;
    }

    /**
     * Performs the Union operation for Union-Find
     *
     * @param components array of subsets
     * @param x          index of the first subset
     * @param y          index of the second subset
     */
    static void union(Component[] components, final int x, final int y) {
        final int xroot = find(components, x);
        final int yroot = find(components, y);

        if (components[xroot].rank < components[yroot].rank) {
            components[xroot].parent = yroot;
        } else if (components[xroot].rank > components[yroot].rank) {
            components[yroot].parent = xroot;
        } else {
            components[yroot].parent = xroot;
            components[xroot].rank++;
        }
    }

    /**
     * Boruvka's algorithm to find the Minimum Spanning Tree
     *
     * @param graph the graph
     * @return list of edges in the Minimum Spanning Tree
     */
    static List<Edge> boruvkaMST(final Graph graph) {
        var boruvkaState = new BoruvkaState(graph);

        while (boruvkaState.hasMoreEdgesToAdd()) {
            final var cheapest = boruvkaState.computeCheapestEdges();
            boruvkaState.merge(cheapest);
        }
        return boruvkaState.result;
    }

    /**
     * Checks if the edge vertices are in a valid range
     *
     * @param vertex     the vertex to check
     * @param upperBound the upper bound for the vertex range
     */
    private static void checkEdgeVertices(final int vertex, final int upperBound) {
        if (vertex < 0 || vertex >= upperBound) {
            throw new IllegalArgumentException("Edge vertex out of range");
        }
    }
}
