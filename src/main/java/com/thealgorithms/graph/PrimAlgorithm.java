package com.thealgorithms.graph;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * This class provides a method to compute the weight of the
 * Minimum Spanning Tree (MST) of a graph using Prim's Algorithm.
 */
public final class PrimAlgorithm {

    private PrimAlgorithm() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Helper record representing an edge with its associated weight and node.
     *
     * @param node   the target node connected by the edge
     * @param weight the weight of the edge
     */
    private record Pair(int node, int weight) {}

    /**
     * Computes the total weight of the Minimum Spanning Tree (MST)
     * for a given undirected, weighted graph.
     *
     * @param vertices number of vertices in the graph
     * @param adj adjacency list representation of the graph
     *            for each node, the adjacency list contains a list of
     *            {adjacentNode, edgeWeight}
     * @return the sum of the edge weights in the MST
     *
     * <p>Time Complexity: O(E log V), where E is the number of edges
     * and V is the number of vertices.</p>
     * <p>Space Complexity: O(V + E) due to adjacency list and visited array.</p>
     */
    public static int spanningTree(int vertices, List<? extends List<? extends List<Integer>>> adj) {
        // Min-heap to pick edge with the smallest weight
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::weight));

        // Array to keep track of visited vertices
        boolean[] visited = new boolean[vertices];

        // Start with node 0 (arbitrary choice), with edge weight = 0
        pq.add(new Pair(0, 0));

        int mstWeightSum = 0;

        // Process nodes until the priority queue is empty
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int node = current.node();
            int weight = current.weight();

            // Skip if the node is already included in MST
            if (visited[node]) {
                continue;
            }

            // Include the node in MST
            visited[node] = true;
            mstWeightSum += weight;

            // Traverse all adjacent edges
            for (List<Integer> edge : adj.get(node)) {
                int adjNode = edge.get(0);
                int edgeWeight = edge.get(1);

                // Only consider unvisited nodes
                if (!visited[adjNode]) {
                    pq.add(new Pair(adjNode, edgeWeight));
                }
            }
        }

        return mstWeightSum;
    }
}
