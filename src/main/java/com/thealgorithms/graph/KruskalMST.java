package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Kruskal's Algorithm to find
 * the Minimum Spanning Tree (MST) of a connected,
 * undirected, weighted graph.
 */
public final class KruskalMST {

    private KruskalMST() {
        // Utility class
    }

    /**
     * Finds the Minimum Spanning Tree using Kruskal's Algorithm.
     *
     * @param vertices number of vertices in the graph
     * @param edges list of all edges in the graph
     * @return list of edges forming the MST
     * @throws IllegalArgumentException if vertices <= 0
     */
    public static List<Edge> findMST(final int vertices, final List<Edge> edges) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive");
        }

        final List<Edge> mst = new ArrayList<>();
        final DisjointSetUnion dsu = new DisjointSetUnion(vertices);

        Collections.sort(edges);

        for (final Edge edge : edges) {
            final int rootU = dsu.find(edge.source);
            final int rootV = dsu.find(edge.destination);

            if (rootU != rootV) {
                mst.add(edge);
                dsu.union(rootU, rootV);

                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        return mst;
    }

    /**
     * Disjoint Set Union (Union-Find) with
     * path compression and union by rank.
     */
    private static final class DisjointSetUnion {

        private final int[] parent;
        private final int[] rank;

        private DisjointSetUnion(final int size) {
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        private int find(final int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        private void union(final int u, final int v) {
            if (rank[u] < rank[v]) {
                parent[u] = v;
            } else if (rank[u] > rank[v]) {
                parent[v] = u;
            } else {
                parent[v] = u;
                rank[u]++;
            }
        }
    }
}
