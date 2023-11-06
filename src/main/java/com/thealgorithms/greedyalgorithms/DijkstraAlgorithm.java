package com.thealgorithms.greedyalgorithms;

import java.util.*;

/**
 *  Graph class represents a graph using an adjacency list and supports Dijkstra's shortest path algorithm.
 *  Implements Dijkstra's Shortest Path Algorithm.
 *  Problem Statement: Given a graph and a source vertex in the graph, find the shortest paths
 *  from the source to all vertices in the given graph.
 *  Dijkstra's Algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 *  */

public class DijkstraAlgorithm {

    /**
     * Demonstrates the use of the Graph class and Dijkstra's algorithm.
     */
    static class Graph {
        private final int vertices;
        private final List<List<Vertex>> adjList;

        /**
         * Initializes a new Graph with a certain number of vertices.
         *
         * @param vertices The number of vertices in the graph.
         */
        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        /**
         * Adds an undirected edge between two vertices with a specified weight.
         *
         * @param source      The source vertex for the edge.
         * @param destination The destination vertex for the edge.
         * @param weight      The weight of the edge.
         */
        public void addEdge(int source, int destination, int weight) {
            adjList.get(source).add(new Vertex(destination, weight));
            adjList.get(destination).add(new Vertex(source, weight));
        }

        /**
         * Runs Dijkstra's algorithm from a given start vertex and calculates the minimum distance to all other vertices.
         *
         * @param startVertex The starting vertex for the paths.
         * @return An array of minimum distances from the start vertex to each other vertex.
         */
        public int[] dijkstraGetMinDistances(int startVertex) {
            int[] distances = new int[vertices];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[startVertex] = 0;

            PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.weight));
            pq.offer(new Vertex(startVertex, 0));

            boolean[] visited = new boolean[vertices];

            while (!pq.isEmpty()) {
                Vertex current = pq.poll();
                if (visited[current.id]) continue;
                visited[current.id] = true;

                for (Vertex edge : adjList.get(current.id)) {
                    if (!visited[edge.id]) {
                        int newDist = distances[current.id] + edge.weight;
                        if (newDist < distances[edge.id]) {
                            distances[edge.id] = newDist;
                            pq.offer(new Vertex(edge.id, newDist));
                        }
                    }
                }
            }
            return distances;
        }

        /**
         * Returns the number of vertices in the graph.
         *
         * @return The number of vertices.
         */
        public int getVertices() {
            return vertices;
        }

        /**
         * Private helper class to encapsulate the properties of a vertex in the graph.
         */
        private static class Vertex {
            int id;
            int weight;

            /**
             * Creates a new vertex with the given ID and weight.
             *
             * @param id     The ID of the vertex.
             * @param weight The weight associated with this vertex (used in Dijkstra's algorithm).
             */
            Vertex(int id, int weight) {
                this.id = id;
                this.weight = weight;
            }
        }
    }
    /**
     * Main entry point for the application. Sets up a graph, adds edges, and finds shortest paths.
     *
     * @param args Command line arguments, not used here.
     */
    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        int[] distances = g.dijkstraGetMinDistances(0);
        for (int i = 0; i < g.getVertices(); i++) {
            System.out.printf("Distance from 0 to %d is %d%n", i, distances[i]);
        }
    }
}
