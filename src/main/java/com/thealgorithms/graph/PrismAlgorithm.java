package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 * A helper class representing an edge with its
 * associated weight and the connected node.
 */
class Pair {
    int node;
    int weight;

    /**
     * Constructs a Pair object to hold an edge's weight and node.
     *
     * @param weight The weight of the edge.
     * @param node   The target node connected by the edge.
     */
    public Pair(int weight, int node) {
        this.node = node;
        this.weight = weight;
    }
}

/**
 * This class provides a method to compute the weight of the
 * Minimum Spanning Tree (MST) of a graph using Prim's Algorithm.
 */
public class PrismAlgorithm {

    /**
     * Computes the total weight of the Minimum Spanning Tree (MST)
     * for a given undirected, weighted graph.
     *
     * <p>The algorithm uses a PriorityQueue (min-heap) to always pick
     * the edge with the smallest weight that connects a new vertex to
     * the growing MST. It ensures that no cycles are formed.</p>
     *
     * @param V   Number of vertices in the graph.
     * @param adj Adjacency list representation of the graph.
     *            For each node, the adjacency list contains a list of
     *            {adjacentNode, edgeWeight}.
     * @return The sum of the edge weights in the MST.
     *
     * <p>Time Complexity: O(E log V) where E is the number of edges
     * and V is the number of vertices.</p>
     * <p>Space Complexity: O(V + E) due to adjacency list and visited array.</p>
     */
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        // Min-heap to pick edge with the smallest weight
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);

        // Array to keep track of visited vertices
        int[] visited = new int[V];

        // Start with node 0 (arbitrary choice), with edge weight = 0
        pq.add(new Pair(0, 0));

        int mstWeightSum = 0;

        // Process nodes until the priority queue is empty
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int weight = current.weight;
            int node = current.node;

            // Skip if the node is already included in MST
            if (visited[node] == 1) continue;

            // Include the node in MST
            visited[node] = 1;
            mstWeightSum += weight;

            // Traverse all adjacent edges
            for (int i = 0; i < adj.get(node).size(); i++) {
                int adjNode = adj.get(node).get(i).get(0);
                int edgeWeight = adj.get(node).get(i).get(1);

                // Only consider unvisited nodes
                if (visited[adjNode] == 0) {
                    pq.add(new Pair(edgeWeight, adjNode));
                }
            }
        }

        return mstWeightSum;
    }
}
