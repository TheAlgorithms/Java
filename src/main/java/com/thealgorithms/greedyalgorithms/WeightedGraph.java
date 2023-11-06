package com.thealgorithms.greedyalgorithms;

import java.util.*;

/**
 * Implements Prim's Minimum Spanning Tree (MST) Algorithm for a weighted undirected graph.
 * Prim’s algorithm finds a subset of edges that forms a tree including every vertex, minimizing the total edge weight.
 */
public class WeightedGraph {
    private final int numVertices;
    private final Map<Integer, List<Edge>> adjList;
    private final List<Edge> mstEdges;
    private Integer cachedTotalWeight = null;

    /**
     * Constructs a Graph with a fixed number of vertices.
     *
     * @param numVertices The number of vertices in the graph.
     */
    public WeightedGraph(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new HashMap<>();
        this.mstEdges = new ArrayList<>();
    }

    /**
     * Adds an undirected, weighted edge between two vertices.
     * This will add two edge entries to the adjacency list to ensure
     * that the graph remains undirected: one from 'from' to 'to' and
     * another from 'to' to 'from' with the same weight.
     *
     * @param from   The starting vertex of the edge.
     * @param to     The ending vertex of the edge.
     * @param weight The weight of the edge.
     * @throws IllegalArgumentException if an edge with negative weight is added.
     */

    public void addEdge(int from, int to, int weight) {
        if (weight < 0) throw new IllegalArgumentException("Edge weight cannot be negative");
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(from, to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(to, from, weight));
        cachedTotalWeight = null; // Invalidate cache when a new edge is added
    }


    /**
     * Implements Prim's algorithm to find the Minimum Spanning Tree of the graph.
     *
     * @return Total weight of the Minimum Spanning Tree.
     */
    public int primMST() {
        if (cachedTotalWeight != null) return cachedTotalWeight; // Return cached value if available

        boolean[] inMST = new boolean[numVertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt((Edge e) -> e.weight).thenComparingInt(e -> e.to));
        mstEdges.clear();
        int totalWeight = 0;

        // Arbitrary starting point
        inMST[0] = true;
        adjList.getOrDefault(0, Collections.emptyList()).forEach(pq::add);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (inMST[edge.to]) continue;
            inMST[edge.to] = true;
            mstEdges.add(edge);
            totalWeight += edge.weight;

            for (Edge nextEdge : adjList.getOrDefault(edge.to, Collections.emptyList())) {
                if (!inMST[nextEdge.to]) {
                    pq.add(nextEdge);
                }
            }
        }

        cachedTotalWeight = totalWeight;
        return totalWeight;
    }

    /**
     * Prints the edges of the Minimum Spanning Tree.
     */
    public void printMST() {
        if (mstEdges.isEmpty()) {
            System.out.println("MST has not been computed or there are no edges in the MST.");
            return;
        }
        for (Edge edge : mstEdges) {
            System.out.println("Edge: " + edge);
        }
    }

    /**
     * Edge class made public to allow external use.
     */
    public record Edge(int from, int to, int weight) {
        @Override
        public String toString() {
            return String.format("(%d, %d, %d)", from, to, weight);
        }
    }


    /**
     * Validates the integrity of the constructed Minimum Spanning Tree (MST).
     *
     * @return true if the MST is valid, false otherwise.
     */
    public boolean isMSTValid() {
        if (mstEdges.size() != numVertices - 1) {
            return false; // MST should have exactly numVertices - 1 edges
        }

        // Use a union-find data structure to detect cycles
        UnionFind uf = new UnionFind(numVertices);

        for (Edge edge : mstEdges) {
            if (uf.connected(edge.from(), edge.to())) {
                return false; // A cycle is detected
            }
            uf.union(edge.from(), edge.to());
        }

        // Ensure all vertices are connected
        int representative = uf.find(0);
        for (int i = 1; i < numVertices; i++) {
            if (uf.find(i) != representative) {
                return false; // Disconnected vertex found
            }
        }

        return true;
    }

    /**
     * Union-find data structure to help detect cycles.
     */
    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int node) {
            if (node != parent[node]) {
                parent[node] = find(parent[node]); // Path compression
            }
            return parent[node];
        }

        public boolean connected(int node1, int node2) {
            return find(node1) == find(node2);
        }

        public void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 != root2) {
                if (rank[root1] < rank[root2]) {
                    parent[root1] = root2;
                } else if (rank[root1] > rank[root2]) {
                    parent[root2] = root1;
                } else {
                    parent[root2] = root1;
                    rank[root1]++;
                }
            }
        }
    }


    /**
     * Main method to demonstrate the Prim's MST algorithm.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Known MST test case
        WeightedGraph knownGraph = new WeightedGraph(4);
        knownGraph.addEdge(0, 1, 1);
        knownGraph.addEdge(0, 2, 3);
        knownGraph.addEdge(1, 2, 1);
        knownGraph.addEdge(1, 3, 4);
        knownGraph.addEdge(2, 3, 1);

        // The known MST total weight should be 3 (1+1+1)
        int knownTotalWeight = knownGraph.primMST();
        System.out.println("Known MST Total weight: " + knownTotalWeight);
        knownGraph.printMST(); // This should print edges (0,1), (1,2), and (2,3)

        // Test the initial graph
        WeightedGraph g = new WeightedGraph(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        // Validate the output of the initial graph
        int totalWeight = g.primMST();
        System.out.println("Total weight of MST: " + totalWeight);
        g.printMST(); // Use this to check the actual MST edges
    }

}
