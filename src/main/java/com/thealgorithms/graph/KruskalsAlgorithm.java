package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's Algorithm for finding Minimum Spanning Tree (MST)
 * 
 * Kruskal's algorithm is a greedy algorithm that finds a minimum spanning tree
 * for a connected weighted graph. It works by sorting all edges by weight and
 * adding them one by one to the MST if they don't form a cycle.
 * 
 * Time Complexity: O(E log E) where E is the number of edges
 * Space Complexity: O(V + E) where V is the number of vertices
 * 
 * @author YourName
 */
public final class KruskalsAlgorithm {
    private KruskalsAlgorithm() {
    }

    /**
     * Edge class representing a weighted edge in the graph
     */
    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

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

    /**
     * Disjoint Set (Union-Find) data structure
     */
    static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Find the representative (root) of the set containing element x
         * Uses path compression for optimization
         */
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        /**
         * Unite two sets containing elements x and y
         * Uses union by rank for optimization
         */
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
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
        }
    }

    /**
     * Find Minimum Spanning Tree using Kruskal's Algorithm
     * 
     * @param vertices Number of vertices in the graph
     * @param edges List of edges in the graph
     * @return List of edges in the Minimum Spanning Tree
     */
    public static List<Edge> kruskalMST(int vertices, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        
        // Sort edges by weight in ascending order
        Collections.sort(edges);
        
        DisjointSet ds = new DisjointSet(vertices);
        
        // Iterate through sorted edges
        for (Edge edge : edges) {
            int srcRoot = ds.find(edge.src);
            int destRoot = ds.find(edge.dest);
            
            // If including this edge doesn't form a cycle, add it to MST
            if (srcRoot != destRoot) {
                mst.add(edge);
                ds.union(srcRoot, destRoot);
                
                // MST is complete when we have V-1 edges
                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }
        
        return mst;
    }

    /**
     * Calculate total weight of the MST
     * 
     * @param mst List of edges in the Minimum Spanning Tree
     * @return Total weight of the MST
     */
    public static int getMSTWeight(List<Edge> mst) {
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.weight;
        }
        return totalWeight;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int vertices = 4;
        List<Edge> edges = new ArrayList<>();
        
        // Example graph
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));
        
        List<Edge> mst = kruskalMST(vertices, edges);
        
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " -- " + edge.dest + " : " + edge.weight);
        }
        
        System.out.println("\nTotal weight of MST: " + getMSTWeight(mst));
    }
}
