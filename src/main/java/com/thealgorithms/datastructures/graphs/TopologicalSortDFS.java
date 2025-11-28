package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Topological Sorting using Depth-First Search (DFS)
 *
 * <p>Topological sorting for a Directed Acyclic Graph (DAG) is a linear ordering of vertices
 * such that for every directed edge u → v, vertex u comes before v in the ordering.
 *
 * <p>This algorithm uses DFS traversal to compute the topological order. It maintains a visited
 * set to track processed nodes and a recursion stack to detect cycles.
 *
 * <p>Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * <p>Space Complexity: O(V) for the recursion stack and data structures
 *
 * <p>Applications:
 * - Task scheduling with dependencies
 * - Build systems (e.g., makefiles)
 * - Course prerequisite resolution
 * - Compilation order in software projects
 *
 * @author Raghu0703
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological Sorting</a>
 */
public final class TopologicalSortDFS {

    private TopologicalSortDFS() {
    }

    /**
     * Performs topological sorting on a directed graph using DFS
     *
     * @param graph the adjacency list representation of the directed graph
     * @return a list containing vertices in topological order
     * @throws IllegalArgumentException if the graph contains a cycle
     */
    public static List<Integer> topologicalSort(List<List<Integer>> graph) {
        if (graph == null || graph.isEmpty()) {
            return new ArrayList<>();
        }

        int vertices = graph.size();
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        // Perform DFS from all unvisited vertices
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (hasCycleDFS(graph, i, visited, recursionStack, stack)) {
                    throw new IllegalArgumentException("Graph contains a cycle. Topological sort is not possible for cyclic graphs.");
                }
            }
        }

        // Pop all vertices from stack to get topological order
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * Helper method to perform DFS and detect cycles
     *
     * @param graph the adjacency list representation of the graph
     * @param vertex current vertex being processed
     * @param visited array to track visited vertices
     * @param recursionStack array to track vertices in current recursion path
     * @param stack stack to store vertices in reverse topological order
     * @return true if a cycle is detected, false otherwise
     */
    private static boolean hasCycleDFS(List<List<Integer>> graph, int vertex, boolean[] visited, boolean[] recursionStack, Stack<Integer> stack) {
        // Mark current node as visited and part of recursion stack
        visited[vertex] = true;
        recursionStack[vertex] = true;

        // Recur for all adjacent vertices
        List<Integer> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                // If neighbor is not visited, recursively check for cycles
                if (!visited[neighbor]) {
                    if (hasCycleDFS(graph, neighbor, visited, recursionStack, stack)) {
                        return true;
                    }
                } else if (recursionStack[neighbor]) {
                    // If neighbor is in recursion stack, cycle detected
                    return true;
                }
            }
        }

        // Remove vertex from recursion stack and push to result stack
        recursionStack[vertex] = false;
        stack.push(vertex);
        return false;
    }

    /**
     * Checks if the given directed graph is a Directed Acyclic Graph (DAG)
     *
     * @param graph the adjacency list representation of the directed graph
     * @return true if graph is a DAG, false if it contains a cycle
     */
    public static boolean isDAG(List<List<Integer>> graph) {
        if (graph == null || graph.isEmpty()) {
            return true;
        }

        int vertices = graph.size();
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (detectCycle(graph, i, visited, recursionStack)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper method to detect cycle in a directed graph
     *
     * @param graph the adjacency list representation of the graph
     * @param vertex current vertex being processed
     * @param visited array to track visited vertices
     * @param recursionStack array to track vertices in current recursion path
     * @return true if a cycle is detected, false otherwise
     */
    private static boolean detectCycle(List<List<Integer>> graph, int vertex, boolean[] visited, boolean[] recursionStack) {
        visited[vertex] = true;
        recursionStack[vertex] = true;

        List<Integer> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    if (detectCycle(graph, neighbor, visited, recursionStack)) {
                        return true;
                    }
                } else if (recursionStack[neighbor]) {
                    return true;
                }
            }
        }

        recursionStack[vertex] = false;
        return false;
    }
}
