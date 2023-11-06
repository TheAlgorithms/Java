package com.thealgorithms.greedyalgorithms;

import java.util.*;


/**
 * Implements Boruvka's algorithm to find the Minimum Spanning Tree (MST) of a given connected, undirected graph
 * with non-negative edge weights.
 * <p>
 * Boruvka's algorithm is a greedy algorithm that grows the MST by adding the cheapest edge from each component
 * to another component, in each iteration, until all vertices are connected.
 * </p>
 */
public class BoruvkaMST {

    /**
     * Represents an edge in the graph.
     */
    public record Edge(int src, int dest, int weight) {
        /**
         * Constructs an Edge instance.
         *
         * @param src    The source vertex of the edge.
         * @param dest   The destination vertex of the edge.
         * @param weight The weight of the edge.
         */
        public Edge {
        }
    }

    /**
     * A utility class that represents a subset for Union-Find algorithm.
     */
    public static class UnionFind {
        private final int[] parent;
        final int[] rank;

        /**
         * Constructs a UnionFind instance with specified size.
         * Initializes all subsets as single element sets.
         *
         * @param size The total number of elements.
         */
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Finds the representative of the set to which the specified element belongs.
         *
         * @param i The element for which to find the set representative.
         * @return The representative of the set containing 'i'.
         */
        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /**
         * Unites two sets to which elements 'i' and 'j' belong.
         *
         * @param i An element of the first set.
         * @param j An element of the second set.
         */
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                if (rank[rootI] > rank[rootJ]) {
                    parent[rootJ] = rootI;
                } else if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else {
                    parent[rootJ] = rootI;
                    rank[rootI]++;
                }
            }
        }
    }

    /**
     * Represents a graph with vertices and edges.
     */
    public static class Graph {
        int vertices;
        List<Edge>[] edgeList;

        /**
         * Constructs a Graph instance with specified number of vertices.
         *
         * @param vertices The number of vertices in the graph.
         */
        public Graph(int vertices) {
            this.vertices = vertices;
            edgeList = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                edgeList[i] = new ArrayList<>();
            }
        }

        /**
         * Adds an undirected edge to the graph with a specific weight.
         * If the source or destination vertices are out of the valid range
         * (i.e., less than 0 or greater than or equal to the number of vertices),
         * an IllegalArgumentException is thrown.
         *
         * @param src    The source vertex of the edge. Must be between 0 and (number of vertices - 1).
         * @param dest   The destination vertex of the edge. Must be between 0 and (number of vertices - 1).
         * @param weight The weight of the edge. Must be non-negative.
         * @throws IllegalArgumentException if either src or dest are out of valid range.
         */
        public void addEdge(int src, int dest, int weight) {
            if (src < 0 || src >= vertices || dest < 0 || dest >= vertices) {
                throw new IllegalArgumentException("Vertex number is out of bounds.");
            }
            Edge edge = new Edge(src, dest, weight);
            edgeList[src].add(edge);
            edgeList[dest].add(edge); // Since the graph is undirected
        }


        /**
         * Updates the cheapest edges for a given vertex.
         *
         * @param edgeList The list of edges connected to the vertex.
         * @param cheapest The array storing the cheapest edge for each component.
         * @param unionFind The UnionFind instance used to manage components.
         */
        private void updateCheapestEdges(List<Edge> edgeList, Edge[] cheapest, UnionFind unionFind) {
            for (Edge edge : edgeList) {
                int set1 = unionFind.find(edge.src());
                int set2 = unionFind.find(edge.dest());

                // Check if the vertices are in different sets and update the cheapest edge for the set
                if (set1 != set2) {
                    if (cheapest[set1] == null || cheapest[set1].weight() > edge.weight()) {
                        cheapest[set1] = edge;
                    }
                    if (cheapest[set2] == null || cheapest[set2].weight() > edge.weight()) {
                        cheapest[set2] = edge;
                    }
                }
            }
        }


        /**
         * Implements Boruvka's algorithm to find the Minimum Spanning Tree of the graph.
         * The method returns a list of edges that comprise the MST.
         *
         * @return List of edges forming the Minimum Spanning Tree.
         */
        public List<Edge> boruvkaMST() {
            UnionFind unionFind = new UnionFind(vertices);
            int numTrees = vertices;
            Edge[] cheapest = new Edge[vertices];

            // Initially, all vertices are in different components and have no cheapest edge
            Arrays.fill(cheapest, null);

            List<Edge> mstEdges = new ArrayList<>();

            // Repeat until there is only one tree left
            while (numTrees > 1) {
                // Find the cheapest edge for each component
                for (int v = 0; v < vertices; v++) {
                    updateCheapestEdges(edgeList[v], cheapest, unionFind);
                }

                // Add the cheapest edges to the MST
                for (int i = 0; i < vertices; i++) {
                    Edge edge = cheapest[i];
                    if (edge != null) {
                        int set1 = unionFind.find(edge.src);
                        int set2 = unionFind.find(edge.dest);
                        if (set1 != set2) {
                            mstEdges.add(edge);
                            unionFind.union(set1, set2);
                            numTrees--;
                        }
                    }
                }

                // Clear the cheapest edges array for the next iteration
                Arrays.fill(cheapest, null);
            }

            return mstEdges;
        }
    }


    /**
     * Main method to run the Boruvka's MST algorithm.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);

        // Get the MST edges
        List<Edge> mstEdges = g.boruvkaMST();

        // Print the edges in the MST
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mstEdges) {
            System.out.println("Edge: " + edge.src + " - " + edge.dest + " | Weight: " + edge.weight);
        }
    }

}