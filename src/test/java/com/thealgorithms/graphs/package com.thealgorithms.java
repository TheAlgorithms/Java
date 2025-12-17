package com.thealgorithms.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra's algorithm finds the shortest path from a source vertex to all other vertices
 * in a weighted graph. This implementation uses a PriorityQueue for optimal performance.
 *
 * Applications: GPS routing (Google Maps), Network routing (OSPF protocol).
 *
 * Time Complexity: O((V + E) log V)
 * Space Complexity: O(V)
 */
public class package com.thealgorithms.java {

    public static class Edge {
        int target;
        int weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    /**
     * Finds the shortest paths from the source to all other vertices.
     *
     * @param source the starting vertex
     * @param graph the adjacency list representation of the graph
     * @param numVertices total number of vertices in the graph
     * @return an array of shortest distances from source
     * @throws IllegalArgumentException if any edge weight is negative or input is invalid
     */
    public int[] runDijkstra(int source, Map<Integer, List<Edge>> graph, int numVertices) {
        // Validation for number of vertices
        if (numVertices <= 0) {
            return new int[0];
        }

        // Validation for null graph or invalid source index
        if (graph == null || source < 0 || source >= numVertices) {
            int[] emptyDist = new int[numVertices];
            Arrays.fill(emptyDist, Integer.MAX_VALUE);
            if (source >= 0 && source < numVertices) {
                emptyDist[source] = 0;
            }
            return emptyDist;
        }

        // Min-priority queue based on distance (int[1])
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;
        pq.add(new int[] {source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];

            // If current distance is already greater than stored distance, skip
            // This is a common "Partial" coverage point if not tested with multiple paths
            if (d > dist[u]) {
                continue;
            }

            List<Edge> neighbors = graph.get(u);
            if (neighbors == null) {
                continue;
            }

            for (Edge edge : neighbors) {
                // Dijkstra's algorithm does not support negative weights
                if (edge.weight < 0) {
                    throw new IllegalArgumentException("Graph contains negative weight edge: " + edge.weight);
                }

                if (dist[u] != Integer.MAX_VALUE && dist[u] + edge.weight < dist[edge.target]) {
                    dist[edge.target] = dist[u] + edge.weight;
                    pq.add(new int[] {edge.target, dist[edge.target]});
                }
            }
        }
        return dist;
    }
}