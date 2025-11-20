package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Kruskal's Algorithm for finding the Minimum Spanning Tree (MST)
 * of a connected, undirected, weighted graph.
 *
 * <p>Kruskal's algorithm is a greedy algorithm that finds an MST by:
 * <ol>
 *   <li>Sorting all edges by weight in ascending order</li>
 *   <li>Iteratively adding edges with minimum weight that don't create a cycle</li>
 *   <li>Using Union-Find (Disjoint Set Union) to efficiently detect cycles</li>
 * </ol>
 *
 * <p><strong>Time Complexity:</strong> O(E log E) or O(E log V), where E is the number of edges
 * and V is the number of vertices. Sorting edges dominates the complexity.
 *
 * <p><strong>Space Complexity:</strong> O(V) for the Union-Find data structure.
 *
 * @author guillermolara01
 */
public final class KruskalAlgorithm {

    private KruskalAlgorithm() {
        // Utility class, prevent instantiation
    }

    /**
     * Represents a weighted edge in an undirected graph.
     */
    public static class Edge implements Comparable<Edge> {
        private final int source;
        private final int destination;
        private final int weight;

        /**
         * Creates an edge with the specified endpoints and weight.
         *
         * @param source the source vertex
         * @param destination the destination vertex
         * @param weight the weight of the edge
         */
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d, weight: %d)", source, destination, weight);
        }
    }

    /**
     * Union-Find (Disjoint Set Union) data structure with path compression
     * and union by rank for efficient cycle detection.
     */
    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        /**
         * Initializes the Union-Find structure with n elements.
         *
         * @param n the number of elements
         */
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Finds the representative (root) of the set containing element x.
         * Uses path compression for optimization.
         *
         * @param x the element to find
         * @return the representative of the set
         */
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        /**
         * Unites the sets containing elements x and y.
         * Uses union by rank for optimization.
         *
         * @param x the first element
         * @param y the second element
         * @return true if the sets were merged, false if they were already in the same set
         */
        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false; // Already in the same set (would create a cycle)
            }

            // Union by rank
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    /**
     * Finds the Minimum Spanning Tree of a graph using Kruskal's algorithm.
     *
     * @param vertices the number of vertices in the graph
     * @param edges the list of edges in the graph
     * @return a list of edges that form the MST
     * @throws IllegalArgumentException if vertices is less than 1 or edges is null
     */
    public static List<Edge> kruskal(int vertices, List<Edge> edges) {
        if (vertices < 1) {
            throw new IllegalArgumentException("Number of vertices must be at least 1");
        }
        if (edges == null) {
            throw new IllegalArgumentException("Edges list cannot be null");
        }

        List<Edge> mst = new ArrayList<>();
        if (edges.isEmpty()) {
            return mst;
        }

        // Sort edges by weight
        edges.sort(Edge::compareTo);

        UnionFind uf = new UnionFind(vertices);

        // Iterate through sorted edges
        for (Edge edge : edges) {
            // If adding this edge doesn't create a cycle, include it in MST
            if (uf.union(edge.getSource(), edge.getDestination())) {
                mst.add(edge);

                // MST is complete when we have (V-1) edges
                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        return mst;
    }

    /**
     * Calculates the total weight of the MST.
     *
     * @param mst the list of edges in the MST
     * @return the total weight of all edges in the MST
     */
    public static int getMSTWeight(List<Edge> mst) {
        return mst.stream().mapToInt(Edge::getWeight).sum();
    }
}