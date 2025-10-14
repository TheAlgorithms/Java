package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An implementation of Dial's Algorithm for the single-source shortest path problem.
 * This algorithm is an optimization of Dijkstra's algorithm and is particularly
 * efficient for graphs with small, non-negative integer edge weights.
 *
 * It uses a bucket queue (implemented here as a List of HashSets) to store vertices,
 * where each bucket corresponds to a specific distance from the source. This is more
 * efficient than a standard priority queue when the range of edge weights is small.
 *
 * Time Complexity: O(E + W * V), where E is the number of edges, V is the number
 * of vertices, and W is the maximum weight of any edge.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Dial's_algorithm">Wikipedia - Dial's Algorithm</a>
 */
public final class DialsAlgorithm {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private DialsAlgorithm() {
    }
    /**
     * Represents an edge in the graph, connecting to a destination vertex with a given weight.
     */
    public static class Edge {
        private final int destination;
        private final int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
    /**
     * Finds the shortest paths from a source vertex to all other vertices in a weighted graph.
     *
     * @param graph The graph represented as an adjacency list.
     * @param source The source vertex to start from (0-indexed).
     * @param maxEdgeWeight The maximum weight of any single edge in the graph.
     * @return An array of integers where the value at each index `i` is the
     * shortest distance from the source to vertex `i`. Unreachable vertices
     * will have a value of Integer.MAX_VALUE.
     * @throws IllegalArgumentException if the source vertex is out of bounds.
     */
    public static int[] run(List<List<Edge>> graph, int source, int maxEdgeWeight) {
        int numVertices = graph.size();
        if (source < 0 || source >= numVertices) {
            throw new IllegalArgumentException("Source vertex is out of bounds.");
        }

        // Initialize distances array
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // The bucket queue. Size is determined by the max possible path length.
        int maxPathWeight = maxEdgeWeight * (numVertices > 0 ? numVertices - 1 : 0);
        List<Set<Integer>> buckets = new ArrayList<>(maxPathWeight + 1);
        for (int i = 0; i <= maxPathWeight; i++) {
            buckets.add(new HashSet<>());
        }

        // Add the source vertex to the first bucket
        buckets.get(0).add(source);

        // Process buckets in increasing order of distance
        for (int d = 0; d <= maxPathWeight; d++) {
            // Process all vertices in the current bucket
            while (!buckets.get(d).isEmpty()) {
                // Get and remove a vertex from the current bucket
                int u = buckets.get(d).iterator().next();
                buckets.get(d).remove(u);

                // If we've found a shorter path already, skip
                if (d > distances[u]) {
                    continue;
                }

                // Relax all adjacent edges
                for (Edge edge : graph.get(u)) {
                    int v = edge.getDestination();
                    int weight = edge.getWeight();

                    // If a shorter path to v is found
                    if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                        // If v was already in a bucket, remove it from the old one
                        if (distances[v] != Integer.MAX_VALUE) {
                            buckets.get(distances[v]).remove(v);
                        }
                        // Update distance and move v to the new bucket
                        distances[v] = distances[u] + weight;
                        buckets.get(distances[v]).add(v);
                    }
                }
            }
        }
        return distances;
    }
}
