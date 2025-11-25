package com.thealgorithms.graphs;

import java.util.*;

/**
 * Topological Sorting using Depth-First Search (DFS)
 * 
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering 
 * of vertices such that for every directed edge u -> v, vertex u comes before v.
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 * 
 * @author gowtham1412-p
 */
public class TopologicalSortDFS {
    
    private int vertices;
    private List<List<Integer>> adjList;
    
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
     * Add a directed edge to the graph
     * 
     * @param source Source vertex
     * @param destination Destination vertex
     */
    public void addEdge(int source, int destination) {
        if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
            throw new IllegalArgumentException("Invalid vertex");
        }
        adjList.get(source).add(destination);
    }
    
    /**
     * Performs topological sort using DFS
     * 
     * @return List of vertices in topological order
     * @throws IllegalArgumentException if graph contains a cycle
     */
    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        
        // Check for cycles and perform DFS
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (hasCycleDFS(i, visited, recursionStack)) {
                    throw new IllegalArgumentException("Graph contains a cycle. Topological sort not possible.");
                }
            }
        }
        
        // Reset visited for actual topological sort
        Arrays.fill(visited, false);
        
        // Perform DFS to get topological order
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortDFS(i, visited, stack);
            }
        }
        
        // Convert stack to list
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
     * @return true if cycle exists, false otherwise
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