package com.thealgorithms.graph;
import java.util.*;

/**
 * Implements Kruskal's Algorithm to find the Minimum Spanning Tree (MST)
 * of a connected, undirected, weighted graph using Union-Find.
 */
public class KruskalMinimumSpanningTree {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class UnionFind {
        int[] parent;

        UnionFind(int size) {
            parent = new int[size];
            Arrays.fill(parent, -1);
        }

        int find(int node) {
            if (parent[node] < 0) return node;
            return parent[node] = find(parent[node]);
        }

        boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return false;
            parent[rootV] = rootU;
            return true;
        }
    }

    public static List<Edge> kruskalMST(List<Edge> edges, int vertices) {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(vertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                mst.add(edge);
            }
            if (mst.size() == vertices - 1) break;
        }

        return mst;
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> mst = kruskalMST(edges, V);
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}
