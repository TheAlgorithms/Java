# Replace the entire file with the fixed version
cat > src/main/java/com/thealgorithms/graphs/TopologicalSortDFS.java << 'EOF'
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
                dfs(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * Recursive DFS helper method for topological sort
     *
     * @param vertex Current vertex
     * @param visited Visited array
     * @param stack Stack to store the topological order
     */
    private void dfs(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }

    /**
     * Check if the graph is a Directed Acyclic Graph (DAG)
     *
     * @return true if graph is DAG, false otherwise
     */
    boolean isDAG() {
        boolean[] visited = new boolean[vertices];
        boolean[] recStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (hasCycle(i, visited, recStack)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper method to detect cycle in the graph
     *
     * @param vertex Current vertex
     * @param visited Visited array
     * @param recStack Recursion stack to track vertices in current path
     * @return true if cycle is detected, false otherwise
     */
    private boolean hasCycle(int vertex, boolean[] visited, boolean[] recStack) {
        if (recStack[vertex]) {
            return true;
        }

        if (visited[vertex]) {
            return false;
        }

        visited[vertex] = true;
        recStack[vertex] = true;

        for (int neighbor : adjList.get(vertex)) {
            if (hasCycle(neighbor, visited, recStack)) {
                return true;
            }
        }

        recStack[vertex] = false;
        return false;
    }

    /**
     * Get the adjacency list of the graph
     *
     * @return Adjacency list
     */
    public List<List<Integer>> getAdjList() {
        return adjList;
    }

    /**
     * Get the number of vertices in the graph
     *
     * @return Number of vertices
     */
    public int getVertices() {
        return vertices;
    }
}
