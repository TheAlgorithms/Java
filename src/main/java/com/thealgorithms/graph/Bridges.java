
package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds bridges in an undirected graph using depth-first search.
 *
 * <p>A bridge is an edge whose removal increases the number of connected
 * components in the graph.
 *
 * <p>Vertices are numbered from 0 to {@code vertexCount - 1}.
 *
 * <p>Time complexity: O(V + E), where V is the number of vertices
 * and E is the number of edges.
 */
public final class Bridges {

    private Bridges() {
        // Utility class; do not instantiate.
    }

    /**
     * Returns all bridges in an undirected graph.
     *
     * @param vertexCount number of vertices
     * @param edges array of edges, with each edge represented as {u, v}
     * @return list of bridges, with each bridge represented as {u, v}
     * @throws IllegalArgumentException if the vertex count is negative,
     *                                  or an edge is null, malformed,
     *                                  or references an invalid vertex
     */
    public static List<int[]> findBridges(int vertexCount, int[][] edges) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Vertex count cannot be negative.");
        }

        if (edges == null) {
            throw new IllegalArgumentException("Edges cannot be null.");
        }

        List<List<Integer>> adjacencyList = new ArrayList<>(vertexCount);

        for (int i = 0; i < vertexCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            if (edge == null || edge.length != 2) {
                throw new IllegalArgumentException(
                    "Each edge must contain exactly two vertices."
                );
            }

            int u = edge[0];
            int v = edge[1];

            if (u < 0 || u >= vertexCount || v < 0 || v >= vertexCount) {
                throw new IllegalArgumentException(
                    "Edge contains an invalid vertex."
                );
            }

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        int[] discoveryTime = new int[vertexCount];
        int[] low = new int[vertexCount];
        Arrays.fill(discoveryTime, -1);

        List<int[]> bridges = new ArrayList<>();
        int[] timer = {0};

        for (int vertex = 0; vertex < vertexCount; vertex++) {
            if (discoveryTime[vertex] == -1) {
                dfs(
                    vertex,
                    -1,
                    adjacencyList,
                    discoveryTime,
                    low,
                    timer,
                    bridges
                );
            }
        }

        return bridges;
    }

    private static void dfs(
        int vertex,
        int parent,
        List<List<Integer>> adjacencyList,
        int[] discoveryTime,
        int[] low,
        int[] timer,
        List<int[]> bridges
    ) {
        discoveryTime[vertex] = timer[0];
        low[vertex] = timer[0];
        timer[0]++;

        for (int neighbor : adjacencyList.get(vertex)) {
            if (neighbor == parent) {
                continue;
            }

            if (discoveryTime[neighbor] != -1) {
                low[vertex] = Math.min(low[vertex], discoveryTime[neighbor]);
            } else {
                dfs(
                    neighbor,
                    vertex,
                    adjacencyList,
                    discoveryTime,
                    low,
                    timer,
                    bridges
                );

                low[vertex] = Math.min(low[vertex], low[neighbor]);

                if (low[neighbor] > discoveryTime[vertex]) {
                    bridges.add(new int[] {
                        Math.min(vertex, neighbor),
                        Math.max(vertex, neighbor)
                    });
                }
            }
        }
    }
}

