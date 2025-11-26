package com.thealgorithms.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Topological Sorting using Depth First Search (DFS)
 * Topological sorting is a linear ordering of vertices in a Directed Acyclic Graph (DAG)
 * such that for every directed edge (u, v), vertex u comes before v in the ordering.
 *
 * Time Complexity: O(V + E) where V is vertices and E is edges
 * Space Complexity: O(V) for recursion stack and visited array
 *
 * @author Gowtham
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological Sorting</a>
 */
public final class TopologicalSortDFS {
    private final int vertices;
    private final List<List<Integer>> adjList;

    /**
     * Constructor to initialize the graph
     *
     * @param vertices Number of vertices in the graph
     */
    public TopologicalSortDFS(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Add a directed edge from source to destination
     *
     * @param src Source vertex
     * @param dest Destination vertex
     */
    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
    }

    /**
     * Perform topological sort on the graph
     *
     * @return List of vertices in topological order
     * @throws IllegalArgumentException if graph contains a cycle
     */
    public List<Integer> topologicalSort() {
        if (!isDAG()) {
            throw new IllegalArgumentException("Graph contains a cycle. Topological sort is not possible.");
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortDFS(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * DFS helper method to detect cycles
     *
     * @param vertex Current vertex
     * @param visited Visited array
     * @param recursionStack Recursion stack to detect back edges
     * @return true if cycle is detected, false otherwise
     */
    private boolean hasCycleDFS(int vertex, boolean[] visited, boolean[] recursionStack) {
        visited[vertex] = true;
        recursionStack[vertex] = true;

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                if (hasCycleDFS(neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack[neighbor]) {
                return true; // Back edge found - cycle detected
            }
        }

        recursionStack[vertex] = false;
        return false;
    }

    /**
     * DFS helper method for topological sort
     *
     * @param vertex Current vertex
     * @param visited Visited array
     * @param stack Stack to store topological order
     */
    private void topologicalSortDFS(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                topologicalSortDFS(neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }

    /**
     * Check if the graph is a DAG (Directed Acyclic Graph)
     *
     * @return true if graph is DAG, false otherwise
     */
    public boolean isDAG() {
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (hasCycleDFS(i, visited, recursionStack)) {
                    return false;
                }
            }
        }
        return true;
    }
}
