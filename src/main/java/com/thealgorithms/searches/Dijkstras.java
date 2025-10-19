package com.thealgorithms.graphs;

import java.util.*;

/**
 * Implementation of Dijkstra's Algorithm.
 * Finds the shortest path from a given source node to all other nodes in a weighted graph.
 *
 * @author: poorvikaa08
 * @date: 19 October 2025 (Sunday)
 * @wiki: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class DijkstrasAlgorithm {

    /**
     * Computes the shortest distance from a source vertex to all other vertices.
     *
     * @param graph adjacency list representing the graph, where graph[u] contains pairs (v, weight)
     * @param src   the source vertex
     * @param V     total number of vertices
     * @return an array of shortest distances from src to every vertex
     */
    public static int[] dijkstra(List<List<Node>> graph, int src, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            for (Node neighbor : graph.get(curr.vertex)) {
                int newDist = dist[curr.vertex] + neighbor.weight;
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        return dist;
    }

    /** Helper class representing an edge or connection to another vertex */
    public static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
