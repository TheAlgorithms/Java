package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the Bellman-Ford algorithm for finding shortest paths from a single source
 * vertex to all other vertices in a weighted directed graph. Unlike Dijkstra's algorithm,
 * Bellman-Ford can handle graphs with negative weight edges and can detect negative weight cycles.
 *
 * <p>Time Complexity: O(V * E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V)
 *
 * <p>Algorithm Steps:
 * 1. Initialize distances from source to all vertices as infinite and distance to source as 0
 * 2. Relax all edges V-1 times (where V is the number of vertices)
 * 3. Check for negative weight cycles by attempting one more relaxation
 *
 * @author vardhan30016
 * @see <a href="https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm">Bellman-Ford Algorithm</a>
 */
public final class BellmanFord {

    private BellmanFord() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Represents a weighted edge in the graph.
     */
    public static class Edge {
        private final int source;
        private final int destination;
        private final int weight;

        /**
         * Creates a new edge.
         *
         * @param source the source vertex
         * @param destination the destination vertex
         * @param weight the weight of the edge
         */
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    /**
     * Represents the result of the Bellman-Ford algorithm.
     */
    public static class Result {
        private final int[] distances;
        private final int[] predecessors;
        private final boolean hasNegativeCycle;

        /**
         * Creates a new result.
         *
         * @param distances array of shortest distances from source to each vertex
         * @param predecessors array of predecessor vertices in shortest paths
         * @param hasNegativeCycle true if the graph contains a negative weight cycle
         */
        public Result(int[] distances, int[] predecessors, boolean hasNegativeCycle) {
            this.distances = Arrays.copyOf(distances, distances.length);
            this.predecessors = Arrays.copyOf(predecessors, predecessors.length);
            this.hasNegativeCycle = hasNegativeCycle;
        }

        /**
         * Gets the shortest distance to a vertex.
         *
         * @param vertex the target vertex
         * @return the shortest distance from source to the vertex, or Integer.MAX_VALUE if unreachable
         */
        public int getDistance(int vertex) {
            return distances[vertex];
        }

        /**
         * Gets all distances.
         *
         * @return array of distances from source to all vertices
         */
        public int[] getDistances() {
            return Arrays.copyOf(distances, distances.length);
        }

        /**
         * Gets the shortest path to a vertex.
         *
         * @param vertex the target vertex
         * @return list of vertices in the shortest path from source to target
         * @throws IllegalStateException if the graph contains a negative cycle
         * @throws IllegalArgumentException if the vertex is unreachable
         */
        public List<Integer> getPath(int vertex) {
            if (hasNegativeCycle) {
                throw new IllegalStateException("Graph contains a negative weight cycle");
            }
            if (distances[vertex] == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Vertex " + vertex + " is unreachable from source");
            }

            List<Integer> path = new ArrayList<>();
            for (int v = vertex; v != -1; v = predecessors[v]) {
                path.add(0, v);
            }
            return path;
        }

        /**
         * Checks if the graph contains a negative weight cycle.
         *
         * @return true if a negative cycle exists, false otherwise
         */
        public boolean hasNegativeCycle() {
            return hasNegativeCycle;
        }
    }

    /**
     * Finds shortest paths from a source vertex to all other vertices using the Bellman-Ford algorithm.
     *
     * @param vertices the number of vertices in the graph
     * @param edges list of edges in the graph
     * @param source the source vertex (0-indexed)
     * @return Result object containing distances, paths, and negative cycle information
     * @throws IllegalArgumentException if vertices is non-positive or source is invalid
     */
    public static Result findShortestPaths(int vertices, List<Edge> edges, int source) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive");
        }
        if (source < 0 || source >= vertices) {
            throw new IllegalArgumentException("Source vertex is out of bounds");
        }

        // Step 1: Initialize distances and predecessors
        int[] distances = new int[vertices];
        int[] predecessors = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[source] = 0;

        // Step 2: Relax all edges V-1 times
        for (int i = 0; i < vertices - 1; i++) {
            boolean updated = false;
            for (Edge edge : edges) {
                if (distances[edge.getSource()] != Integer.MAX_VALUE) {
                    int newDistance = distances[edge.getSource()] + edge.getWeight();
                    if (newDistance < distances[edge.getDestination()]) {
                        distances[edge.getDestination()] = newDistance;
                        predecessors[edge.getDestination()] = edge.getSource();
                        updated = true;
                    }
                }
            }
            // Early termination optimization: if no updates in this iteration, we're done
            if (!updated) {
                break;
            }
        }

        // Step 3: Check for negative weight cycles
        boolean hasNegativeCycle = false;
        for (Edge edge : edges) {
            if (distances[edge.getSource()] != Integer.MAX_VALUE) {
                int newDistance = distances[edge.getSource()] + edge.getWeight();
                if (newDistance < distances[edge.getDestination()]) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }

        return new Result(distances, predecessors, hasNegativeCycle);
    }
}
