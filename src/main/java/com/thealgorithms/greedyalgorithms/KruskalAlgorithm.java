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
 * <p>Time Complexity: O(E log E) or O(E log V), where E is the number of edges
 * and V is the number of vertices. Sorting edges dominates the complexity.
 *
 * <p>Space Complexity: O(V) for the Union-Find data structure.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Kruskal%27s_algorithm">Kruskal's Algorithm</a>
 * @author saikirankanakala
 */
public final class KruskalAlgorithm {

    private KruskalAlgorithm() {
    }

    /**
     * Represents a weighted edge in an undirected graph.
     */
    public static final class Edge implements Comparable<Edge> {
        private final int source;
        private final int destination;
        private final int weight;

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
     * Union-Find data structure with path compression and union by rank.
     */
    private static final class UnionFind {
        private final int[] parent;
        private final int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

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
     * Finds the Minimum Spanning Tree using Kruskal's algorithm.
     *
     * @param vertices the number of vertices in the graph
     * @param edges the list of edges in the graph
     * @return a list of edges forming the MST
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

        edges.sort(Edge::compareTo);
        UnionFind uf = new UnionFind(vertices);

        for (Edge edge : edges) {
            if (uf.union(edge.getSource(), edge.getDestination())) {
                mst.add(edge);
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
     * @return the total weight of all edges
     */
    public static int getMSTWeight(List<Edge> mst) {
        return mst.stream().mapToInt(Edge::getWeight).sum();
    }
}