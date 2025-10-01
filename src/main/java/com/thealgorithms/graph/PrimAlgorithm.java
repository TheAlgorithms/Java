package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

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
     * <p>The algorithm uses a PriorityQueue (min-heap) to always pick
     * the edge with the smallest weight that connects a new vertex to
     * the growing MST. It ensures that no cycles are formed.</p>
     *
     * @param V   number of vertices in the graph
     * @param adj adjacency list representation of the graph
     *            for each node, the adjacency list contains a list of
     *            {adjacentNode, edgeWeight}
     * @return the sum of the edge weights in the MST
     *
     * <p>Time Complexity: O(E log V), where E is the number of edges
     * and V is the number of vertices.</p>
     * <p>Space Complexity: O(V + E) due to adjacency list and visited array.</p>
     */
    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        // Min-heap to pick edge with the smallest weight
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);

        // Array to keep track of visited vertices
        boolean[] visited = new boolean[V];

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
            for (ArrayList<Integer> edge : adj.get(node)) {
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
