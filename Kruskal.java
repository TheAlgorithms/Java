package com.thealgorithms.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's Algorithm for finding Minimum Spanning Tree (MST)
 * 
 * Kruskal's algorithm is a greedy algorithm that finds a minimum spanning tree
 * for a connected weighted undirected graph. The algorithm works by sorting all
 * edges by weight and adding them one by one to the MST, provided they don't
 * create a cycle.
 * 
 * Time Complexity: O(E log E) where E is the number of edges
 * Space Complexity: O(V + E) where V is the number of vertices
 * 
 * @author Raghu0703
 * @see <a href="https://en.wikipedia.org/wiki/Kruskal%27s_algorithm">Kruskal's Algorithm</a>
 */
public final class Kruskal {
    
    private Kruskal() {
        // Utility class, no instantiation
    }

    /**
     * Edge class representing an edge in the graph
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

        @Override
        public String toString() {
            return src + " -- " + dest + " (weight: " + weight + ")";
        }
    }

    /**
     * Disjoint Set (Union-Find) data structure for cycle detection
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
         * Find operation with path compression
         */
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        /**
         * Union operation by rank
         */
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
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
    }

    /**
     * Finds the Minimum Spanning Tree using Kruskal's Algorithm
     * 
     * @param vertices Number of vertices in the graph
     * @param edges List of all edges in the graph
     * @return List of edges that form the MST
     */
    public static List<Edge> kruskalMST(int vertices, List<Edge> edges) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive");
        }
        if (edges == null) {
            throw new IllegalArgumentException("Edges list cannot be null");
        }

        List<Edge> mst = new ArrayList<>();
        
        // Sort edges by weight
        Collections.sort(edges);
        
        // Initialize disjoint set
        DisjointSet ds = new DisjointSet(vertices);
        
        // Process edges in sorted order
        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);
            
            // If including this edge doesn't create a cycle
            if (rootSrc != rootDest) {
                mst.add(edge);
                ds.union(rootSrc, rootDest);
                
                // MST is complete when we have (V-1) edges
                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }
        
        return mst;
    }

    /**
     * Calculates the total weight of the MST
     * 
     * @param mst List of edges in the MST
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
     * Example usage and demonstration
     */
    public static void main(String[] args) {
        // Example graph with 5 vertices (0-4)
        int vertices = 5;
        List<Edge> edges = new ArrayList<>();
        
        // Add edges: (src, dest, weight)
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(2, 4, 8));
        edges.add(new Edge(3, 4, 12));
        
        // Find MST
        List<Edge> mst = kruskalMST(vertices, edges);
        
        // Print results
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge);
        }
        
        System.out.println("\nTotal weight of MST: " + getMSTWeight(mst));
    }
}
