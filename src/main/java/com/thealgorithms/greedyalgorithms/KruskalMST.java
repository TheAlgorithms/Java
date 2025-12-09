package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's algorithm for Minimum Spanning Tree (MST).
 * Uses Disjoint Set Union (Union-Find).
 *
 * @author prasanth-30011
 */
public final class KruskalMST {

    private KruskalMST() {
        // utility class
    }

    public static class Edge implements Comparable<Edge> {
        public final int u;
        public final int v;
        public final int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static class DSU {
        private final int[] parent;
        private final int[] rank;

        DSU(int n) {
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
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) {
                return false;
            }
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
            return true;
        }
    }

    /**
     * Computes the total weight of a Minimum Spanning Tree.
     *
     * @param n number of vertices (0..n-1)
     * @param edges list of edges
     * @return total MST weight, or -1 if graph is disconnected
     */
    public static int minimumSpanningTreeWeight(int n, List<Edge> edges) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive");
        }

        List<Edge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges);

        DSU dsu = new DSU(n);
        int mstWeight = 0;
        int edgesUsed = 0;

        for (Edge e : sortedEdges) {
            if (dsu.union(e.u, e.v)) {
                mstWeight += e.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) {
                    break;
                }
            }
        }

        if (edgesUsed != n - 1) {
            return -1; // graph not connected
        }
        return mstWeight;
    }
}
