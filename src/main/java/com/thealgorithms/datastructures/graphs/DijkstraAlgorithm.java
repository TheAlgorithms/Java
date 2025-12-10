package com.thealgorithms.datastructures.graphs;

import java.util.Arrays;

/**
 * Dijkstra's algorithm for finding the shortest path from a single source vertex to all other vertices in a graph.
 */
public class DijkstraAlgorithm {

    private final int vertexCount;

    /**
     * Constructs a Dijkstra object with the given number of vertices.
     *
     * @param vertexCount The number of vertices in the graph.
     */
    public DijkstraAlgorithm(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    /**
     * Executes Dijkstra's algorithm on the provided graph to find the shortest paths from the source vertex to all other vertices.
     *
     * The graph is represented as an adjacency matrix where {@code graph[i][j]} represents the weight of the edge from vertex {@code i}
     * to vertex {@code j}. A value of 0 indicates no edge exists between the vertices.
     *
     * @param graph The graph represented as an adjacency matrix.
     * @param source The source vertex.
     * @return An array where the value at each index {@code i} represents the shortest distance from the source vertex to vertex {@code i}.
     * @throws IllegalArgumentException if the source vertex is out of range.
     */
    public int[] run(int[][] graph, int source) {
        if (source < 0 || source >= vertexCount) {
            throw new IllegalArgumentException("Incorrect source");
        }

        int[] distances = new int[vertexCount];
        boolean[] processed = new boolean[vertexCount];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(processed, false);
        distances[source] = 0;

        for (int count = 0; count < vertexCount - 1; count++) {
            int u = getMinDistanceVertex(distances, processed);
            processed[u] = true;

            for (int v = 0; v < vertexCount; v++) {
                if (!processed[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }

        printDistances(distances);
        return distances;
    }

    /**
     * Finds the vertex with the minimum distance value from the set of vertices that have not yet been processed.
     *
     * @param distances The array of current shortest distances from the source vertex.
     * @param processed The array indicating whether each vertex has been processed.
     * @return The index of the vertex with the minimum distance value.
     */
    private int getMinDistanceVertex(int[] distances, boolean[] processed) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertexCount; v++) {
            if (!processed[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    /**
     * Prints the shortest distances from the source vertex to all other vertices.
     *
     * @param distances The array of shortest distances.
     */
    private void printDistances(int[] distances) {
        System.out.println("Vertex \t Distance");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println(i + " \t " + distances[i]);
        }
    }
}
