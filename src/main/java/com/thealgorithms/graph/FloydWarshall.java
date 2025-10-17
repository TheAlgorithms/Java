package com.thealgorithms.graph;

import java.util.Arrays;

/**
 * This class implements the Floyd-Warshall algorithm for finding the shortest
 * distances between all pairs of vertices in a weighted graph.
 * It works for both positive and negative edge weights but no negative cycles.
 * 
 * Time Complexity: O(V^3), where V is the number of vertices.
 * Space Complexity: O(V^2)
 * 
 * Example usage:
 * <pre>
 *     FloydWarshall.Graph graph = new FloydWarshall.Graph(4);
 *     graph.addEdge(0, 1, 5);
 *     graph.addEdge(0, 3, 10);
 *     graph.addEdge(1, 2, 3);
 *     graph.addEdge(2, 3, 1);
 *     FloydWarshall fw = new FloydWarshall(graph);
 *     int[][] distances = fw.solve();
 * </pre>
 * 
 * Author: <a href="https://github.com/YourGitHubUsername">Your Name</a>
 */
public class FloydWarshall {

    /** Graph representation using adjacency matrix */
    public static class Graph {
        private final int numVertices;
        private final int[][] adjacencyMatrix;
        private static final int INF = Integer.MAX_VALUE / 2; // avoid overflow

        public Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyMatrix = new int[numVertices][numVertices];
            for (int i = 0; i < numVertices; i++) {
                Arrays.fill(adjacencyMatrix[i], INF);
                adjacencyMatrix[i][i] = 0;
            }
        }

        /**
         * Adds a directed edge from 'from' to 'to' with given weight.
         *
         * @param from   starting vertex
         * @param to     ending vertex
         * @param weight edge weight
         */
        public void addEdge(int from, int to, int weight) {
            adjacencyMatrix[from][to] = weight;
        }

        public int getNumVertices() {
            return numVertices;
        }

        public int[][] getAdjacencyMatrix() {
            return adjacencyMatrix;
        }
    }

    private final Graph graph;

    public FloydWarshall(Graph graph) {
        this.graph = graph;
    }

    /**
     * Solves the Floyd-Warshall algorithm to compute shortest paths
     * between all pairs of vertices.
     *
     * @return distance matrix where dist[i][j] is the shortest distance from i to j
     */
    public int[][] solve() {
        int V = graph.getNumVertices();
        int[][] dist = new int[V][V];

        // Initialize distances with adjacency matrix
        for (int i = 0; i < V; i++) {
            dist[i] = Arrays.copyOf(graph.getAdjacencyMatrix()[i], V);
        }

        // Main Floyd-Warshall loop
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    /** Simple main method for testing */
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 3, 10);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);

        FloydWarshall fw = new FloydWarshall(graph);
        int[][] distances = fw.solve();

        System.out.println("All-pairs shortest distances:");
        for (int[] row : distances) {
            System.out.println(Arrays.toString(row));
        }
    }
}
