package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implementation of Topological Sort using Depth-First Search (DFS).
 *
 * <p>Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering
 * of vertices such that for every directed edge u → v, vertex u comes before v
 * in the ordering.
 */
public final class TopologicalSortDFS {

    // Private constructor to prevent instantiation
    private TopologicalSortDFS() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Performs topological sorting on a directed acyclic graph.
     *
     * @param vertices the number of vertices in the graph
     * @param adjacencyList the adjacency list representing the graph
     * @return a list containing vertices in topologically sorted order
     */
    public static List<Integer> topologicalSort(int vertices, List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack, adjacencyList);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adjacencyList) {
        visited[node] = true;
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack, adjacencyList);
            }
        }
        stack.push(node);
    }
}
