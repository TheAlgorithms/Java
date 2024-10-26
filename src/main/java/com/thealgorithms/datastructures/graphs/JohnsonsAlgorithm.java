package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements Johnson's algorithm for finding all-pairs shortest paths in a weighted,
 * directed graph that may contain negative edge weights.
 *
 * Johnson's algorithm works by using the Bellman-Ford algorithm to compute a transformation of the
 * input graph that removes all negative weights, allowing Dijkstra's algorithm to be used for
 * efficient shortest path computations.
 *
 * Time Complexity: O(V^2 * log(V) + V*E)
 * Space Complexity: O(V^2)
 *
 * Where V is the number of vertices and E is the number of edges in the graph.
 *
 * For more information, please visit {@link https://en.wikipedia.org/wiki/Johnson%27s_algorithm}
 */
public final class JohnsonsAlgorithm {

    private static final double INF = Double.POSITIVE_INFINITY;

    private JohnsonsAlgorithm() {
    }

    /**
     * Executes Johnson's algorithm on the given graph.
     * Steps:
     * 1. Add a new vertex to the graph and run Bellman-Ford to compute modified weights
     * 2. t the graph using the modified weights
     * 3. Run Dijkstra's algorithm for each vertex to compute the shortest paths
     * The final result is a 2D array of shortest distances between all pairs of vertices.
     *
     * @param graph The input graph represented as an adjacency matrix.
     * @return A 2D array representing the shortest distances between all pairs of vertices.
     */
    public static double[][] johnsonAlgorithm(double[][] graph) {
        int numVertices = graph.length;
        double[][] edges = convertToEdgeList(graph);

        double[] modifiedWeights = bellmanFord(edges, numVertices);

        double[][] reweightedGraph = reweightGraph(graph, modifiedWeights);

        double[][] shortestDistances = new double[numVertices][numVertices];
        for (int source = 0; source < numVertices; source++) {
            shortestDistances[source] = dijkstra(reweightedGraph, source, modifiedWeights);
        }

        return shortestDistances;
    }

    /**
     * Converts the adjacency matrix representation of the graph to an edge list.
     *
     * @param graph The input graph as an adjacency matrix.
     * @return An array of edges, where each edge is represented as [from, to, weight].
     */
    public static double[][] convertToEdgeList(double[][] graph) {
        int numVertices = graph.length;
        List<double[]> edgeList = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j && !Double.isInfinite(graph[i][j])) {
                    // Only add edges that are not self-loops and have a finite weight
                    edgeList.add(new double[] {i, j, graph[i][j]});
                }
            }
        }

        return edgeList.toArray(new double[0][]);
    }

    /**
     * Implements the Bellman-Ford algorithm to compute the shortest paths from a new vertex
     * to all other vertices. This is used to calculate the weight function h(v) for reweighting.
     *
     * @param edges The edge list of the graph.
     * @param numVertices The number of vertices in the original graph.
     * @return An array of modified weights for each vertex.
     */
    private static double[] bellmanFord(double[][] edges, int numVertices) {
        double[] dist = new double[numVertices + 1];
        Arrays.fill(dist, INF);
        dist[numVertices] = 0;

        // Add edges from the new vertex to all original vertices
        double[][] allEdges = Arrays.copyOf(edges, edges.length + numVertices);
        for (int i = 0; i < numVertices; i++) {
            allEdges[edges.length + i] = new double[] {numVertices, i, 0};
        }

        // Relax all edges V times
        for (int i = 0; i < numVertices; i++) {
            for (double[] edge : allEdges) {
                int u = (int) edge[0];
                int v = (int) edge[1];
                double weight = edge[2];
                if (dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        for (double[] edge : allEdges) {
            int u = (int) edge[0];
            int v = (int) edge[1];
            double weight = edge[2];
            if (dist[u] + weight < dist[v]) {
                throw new IllegalArgumentException("Graph contains a negative weight cycle");
            }
        }

        return Arrays.copyOf(dist, numVertices);
    }

    /**
     * Reweights the graph using the modified weights computed by Bellman-Ford.
     *
     * @param graph The original graph.
     * @param modifiedWeights The modified weights from Bellman-Ford.
     * @return The reweighted graph.
     */
    public static double[][] reweightGraph(double[][] graph, double[] modifiedWeights) {
        int numVertices = graph.length;
        double[][] reweightedGraph = new double[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] != 0) {
                    // New weight = original weight + h(u) - h(v)
                    reweightedGraph[i][j] = graph[i][j] + modifiedWeights[i] - modifiedWeights[j];
                }
            }
        }

        return reweightedGraph;
    }

    /**
     * Implements Dijkstra's algorithm for finding shortest paths from a source vertex.
     *
     * @param reweightedGraph The reweighted graph to run Dijkstra's on.
     * @param source The source vertex.
     * @param modifiedWeights The modified weights from Bellman-Ford.
     * @return An array of shortest distances from the source to all other vertices.
     */
    public static double[] dijkstra(double[][] reweightedGraph, int source, double[] modifiedWeights) {
        int numVertices = reweightedGraph.length;
        double[] dist = new double[numVertices];
        boolean[] visited = new boolean[numVertices];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && reweightedGraph[u][v] != 0 && dist[u] != INF && dist[u] + reweightedGraph[u][v] < dist[v]) {
                    dist[v] = dist[u] + reweightedGraph[u][v];
                }
            }
        }

        // Adjust distances back to the original graph weights
        for (int i = 0; i < numVertices; i++) {
            if (dist[i] != INF) {
                dist[i] = dist[i] - modifiedWeights[source] + modifiedWeights[i];
            }
        }

        return dist;
    }

    /**
     * Finds the vertex with the minimum distance value from the set of vertices
     * not yet included in the shortest path tree.
     *
     * @param dist Array of distances.
     * @param visited Array of visited vertices.
     * @return The index of the vertex with minimum distance.
     */
    public static int minDistance(double[] dist, boolean[] visited) {
        double min = INF;
        int minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}
