package com.thealgorithms.datastructures.graphs;

/**
 * A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
 * Adjacency matrix representation of the graph.
 */
public class PrimMST {

    // Number of vertices in the graph
    private static final int V = 5;

    // A utility function to find the vertex with the minimum key
    // value, from the set of vertices not yet included in the MST
    int minKey(int[] key, Boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Function to construct MST for a graph using adjacency matrix representation
    public int[] primMST(int[][] graph) {
        int[] parent = new int[V]; // Array to store constructed MST
        int[] key = new int[V]; // Key values to pick minimum weight edge
        Boolean[] mstSet = new Boolean[V]; // Vertices not yet included in MST

        // Initialize all keys as INFINITE and mstSet[] as false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = Boolean.FALSE;
        }

        // Always include the first vertex in MST
        key[0] = 0; // Make key 0 to pick the first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex not yet included in MST
            int u = minKey(key, mstSet);
            mstSet[u] = Boolean.TRUE;

            // Update key value and parent index of adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        return parent; // Return the MST parent array
    }
}
