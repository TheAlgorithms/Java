package com.thealgorithms.graph;

import java.util.*;



/**
 * This class provides a method to compute the Minimum Spanning Tree (MST)
 * weight using Prim's Algorithm without any custom helper class.
 */
public class PrismAlgorithm {

    /**
     * Computes the total weight of the Minimum Spanning Tree (MST)
     * for a given undirected, weighted graph using Prim's Algorithm.
     *
     * @param V   Number of vertices in the graph.
     * @param adj Adjacency list representation of the graph.
     *            Each entry: {adjacentNode, edgeWeight}.
     * @return The sum of the edge weights in the MST.
     *
     * <p>Time Complexity: O(E log V)</p>
     * <p>Space Complexity: O(V + E)</p>
     */
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        // Min-heap storing {weight, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int[] visited = new int[V]; // visited array
        int mstWeightSum = 0;

        // Start from node 0, with weight = 0
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int weight = current[0];
            int node = current[1];

            if (visited[node] == 1) continue;

            visited[node] = 1;
            mstWeightSum += weight;

            // Explore adjacent edges
            for (ArrayList<Integer> edge : adj.get(node)) {
                int adjNode = edge.get(0);
                int edgeWeight = edge.get(1);

                if (visited[adjNode] == 0) {
                    pq.add(new int[]{edgeWeight, adjNode});
                }
            }
        }

        return mstWeightSum;
    }
}
