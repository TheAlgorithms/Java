package com.thealgorithms.datastructures.graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

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

    private static class Node implements Comparable<Node> {
        int id;
        int distance;

        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
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
        PriorityQueue<Node> unprocessed = new PriorityQueue<>();

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        unprocessed.add(new Node(source, 0));

        while (!unprocessed.isEmpty()) {
            Node current = unprocessed.poll();
            int u = current.id;

            if (processed[u]) {
                continue;
            }
            processed[u] = true;

            for (int v = 0; v < vertexCount; v++) {
                if (!processed[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                    unprocessed.add(new Node(v, distances[v]));
                }
            }
        }

        printDistances(distances);
        return distances;
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
