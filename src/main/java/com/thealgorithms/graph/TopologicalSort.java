package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of Kahn's algorithm for topological sorting of a directed
 * acyclic graph (DAG).
 *
 * <p>
 * The algorithm finds a linear ordering of vertices such that for every
 * directed edge u -> v,
 * vertex u comes before v in the ordering. A topological ordering is possible
 * if and only if the
 * graph has no directed cycles (i.e., it is a DAG).
 * </p>
 *
 * <p>
 * Time Complexity: O(V + E) where V is the number of vertices and E is the
 * number of edges
 * </p>
 * <p>
 * Space Complexity: O(V) for the queue and in-degree array
 * </p>
 *
 * <p>
 * Applications:
 * </p>
 * <ul>
 * <li>Task scheduling with dependencies</li>
 * <li>Build system dependency resolution</li>
 * <li>Course prerequisite planning</li>
 * <li>Package dependency management</li>
 * </ul>
 */
public final class TopologicalSort {

    private TopologicalSort() {
    }

    /**
     * Performs topological sorting using Kahn's algorithm.
     *
     * @param numVertices number of vertices in the graph (0 to numVertices-1)
     * @param edges       list of directed edges, where each edge is represented as
     *                    int[]{from, to}
     * @return an array containing the topologically sorted order, or null if a
     *         cycle exists
     * @throws IllegalArgumentException if edges is null or contains invalid
     *                                  vertices
     */
    public static int[] sort(int numVertices, List<int[]> edges) {
        if (edges == null) {
            throw new IllegalArgumentException("Edge list must not be null");
        }

        // Create adjacency list representation
        List<List<Integer>> graph = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Calculate in-degree for each vertex
        int[] inDegree = new int[numVertices];
        for (int[] edge : edges) {
            if (edge[0] < 0 || edge[0] >= numVertices || edge[1] < 0 || edge[1] >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex in edge: " + Arrays.toString(edge));
            }
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        // Initialize queue with vertices having no incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numVertices];
        int index = 0;

        // Process vertices in topological order
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result[index++] = vertex;

            // Reduce in-degree of neighbors and add to queue if in-degree becomes 0
            for (int neighbor : graph.get(vertex)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if topological sort is possible (no cycles)
        return index == numVertices ? result : null;
    }

    /**
     * Alternative version that returns the result as a List and throws an exception
     * if a cycle is detected.
     *
     * @param numVertices number of vertices in the graph (0 to numVertices-1)
     * @param edges       list of directed edges, where each edge is represented as
     *                    int[]{from, to}
     * @return a list containing the vertices in topologically sorted order
     * @throws IllegalArgumentException if the graph contains a cycle or if input is
     *                                  invalid
     */
    public static List<Integer> sortAndDetectCycle(int numVertices, List<int[]> edges) {
        int[] result = sort(numVertices, edges);
        if (result == null) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }
        List<Integer> sorted = new ArrayList<>(numVertices);
        for (int vertex : result) {
            sorted.add(vertex);
        }
        return sorted;
    }
}