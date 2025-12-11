package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Breadth-First Search (BFS) algorithm for graph traversal.
 *
 * <p>
 * BFS is a graph traversal algorithm that explores all vertices at the present
 * depth level before moving on to vertices at the next depth level. It uses a
 * queue
 * data structure to keep track of vertices to visit.
 *
 * <p>
 * Time Complexity: O(V + E) where V is the number of vertices and E is the
 * number of edges.
 * Space Complexity: O(V) for the visited set and queue.
 *
 * <p>
 * References:
 * <ul>
 * <li>https://en.wikipedia.org/wiki/Breadth-first_search</li>
 * </ul>
 *
 * @author Divyansh1802
 * @author prashantdubeypng (fixes and refactoring)
 */
public final class BreadthFirstSearch {

    private BreadthFirstSearch() {
        // Utility class; do not instantiate.
    }

    /**
     * Performs BFS traversal on a graph represented as an adjacency list.
     *
     * @param adjacencyList the graph represented as a map from vertex to list of
     *                      neighbors
     * @param source        the starting vertex for traversal
     * @param <T>           the type of vertices in the graph
     * @return a list of vertices in BFS order starting from the source
     * @throws IllegalArgumentException if source is null or not in the graph
     */
    public static <T> List<T> bfs(Map<T, List<T>> adjacencyList, T source) {
        if (source == null) {
            throw new IllegalArgumentException("Source vertex cannot be null");
        }
        if (adjacencyList == null || !adjacencyList.containsKey(source)) {
            throw new IllegalArgumentException("Source vertex must exist in the graph");
        }

        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.add(current);

            List<T> neighbors = adjacencyList.get(current);
            if (neighbors != null) {
                for (T neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Performs BFS traversal on a graph represented using integer vertices.
     *
     * @param adjacencyList the graph represented as a list of lists of neighbors
     * @param numVertices   the number of vertices in the graph
     * @param source        the starting vertex for traversal (0-indexed)
     * @return a list of vertices in BFS order starting from the source
     * @throws IllegalArgumentException if source is out of bounds
     */
    public static List<Integer> bfs(List<List<Integer>> adjacencyList, int numVertices, int source) {
        if (source < 0 || source >= numVertices) {
            throw new IllegalArgumentException("Source vertex is out of bounds");
        }
        if (adjacencyList == null) {
            throw new IllegalArgumentException("Adjacency list cannot be null");
        }

        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            if (current < adjacencyList.size()) {
                List<Integer> neighbors = adjacencyList.get(current);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (neighbor >= 0 && neighbor < numVertices && !visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Creates an adjacency list graph from edges.
     *
     * @param numVertices the number of vertices
     * @param edges       array of edges where each edge is {from, to}
     * @param undirected  if true, adds edges in both directions
     * @return the adjacency list representation of the graph
     */
    public static List<List<Integer>> createGraph(int numVertices, int[][] edges, boolean undirected) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            if (edge.length >= 2) {
                int from = edge[0];
                int to = edge[1];
                if (from >= 0 && from < numVertices && to >= 0 && to < numVertices) {
                    graph.get(from).add(to);
                    if (undirected) {
                        graph.get(to).add(from);
                    }
                }
            }
        }

        return graph;
    }

    /**
     * Creates a Map-based adjacency list from string vertices.
     *
     * @param edges      array of edges where each edge is {from, to}
     * @param undirected if true, adds edges in both directions
     * @return the adjacency list representation as a Map
     */
    public static Map<String, List<String>> createStringGraph(String[][] edges, boolean undirected) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String[] edge : edges) {
            if (edge.length >= 2) {
                String from = edge[0];
                String to = edge[1];

                graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
                if (undirected) {
                    graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
                }
            }
        }

        return graph;
    }
}
