package com.thealgorithms.datastructures.graphs;

import java.util.*;

/**
 * Topological Sorting using Depth-First Search (DFS)
 * 
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices
 * such that for every directed edge u -> v, vertex u comes before v in the ordering.
 * 
 * Applications:
 * - Task scheduling with dependencies
 * - Build systems and compilation order
 * - Course prerequisite ordering
 * - Package dependency resolution
 * 
 * Time Complexity: O(V + E) where V is vertices and E is edges
 * Space Complexity: O(V) for the stack and visited array
 * 
 * @author Your Name
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
        adjList = new ArrayList<>(vertices);
        
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
        if (src >= 0 && src < vertices && dest >= 0 && dest < vertices) {
            adjList.get(src).add(dest);
        } else {
            throw new IllegalArgumentException("Invalid vertex index");
        }
    }
    
    /**
     * Recursive DFS helper function for topological sorting
     * 
     * @param vertex Current vertex being visited
     * @param visited Array to track visited vertices
     * @param stack Stack to store the topological order
     */
    private void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
        // Mark current vertex as visited
        visited[vertex] = true;
        
        // Recursively visit all adjacent vertices
        for (Integer neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        
        // Push current vertex to stack after visiting all neighbors
        // This ensures vertices are in reverse topological order in the stack
        stack.push(vertex);
    }
    
    /**
     * Perform topological sort on the graph
     * 
     * @return List of vertices in topological order, or null if cycle detected
     */
    public List<Integer> topologicalSort() {
        // Check if graph has cycles
        if (hasCycle()) {
            return null; // Topological sort not possible for cyclic graphs
        }
        
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];
        
        // Call the recursive helper function for all unvisited vertices
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
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
     * Check if the graph contains a cycle using DFS
     * 
     * @return true if cycle exists, false otherwise
     */
    public boolean hasCycle() {
        boolean[] visited = new boolean[vertices];
        boolean[] recStack = new boolean[vertices];
        
        // Check for cycle starting from each vertex
        for (int i = 0; i < vertices; i++) {
            if (hasCycleUtil(i, visited, recStack)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Helper function to detect cycle using DFS
     * 
     * @param vertex Current vertex
     * @param visited Array to track visited vertices
     * @param recStack Recursion stack to track vertices in current path
     * @return true if cycle detected, false otherwise
     */
    private boolean hasCycleUtil(int vertex, boolean[] visited, boolean[] recStack) {
        // If vertex is in recursion stack, cycle detected
        if (recStack[vertex]) {
            return true;
        }
        
        // If already visited and not in recursion stack, no cycle from this vertex
        if (visited[vertex]) {
            return false;
        }
        
        // Mark vertex as visited and add to recursion stack
        visited[vertex] = true;
        recStack[vertex] = true;
        
        // Check all neighbors
        for (Integer neighbor : adjList.get(vertex)) {
            if (hasCycleUtil(neighbor, visited, recStack)) {
                return true;
            }
        }
        
        // Remove vertex from recursion stack before backtracking
        recStack[vertex] = false;
        
        return false;
    }
    
    /**
     * Get the adjacency list representation of the graph
     * 
     * @return Adjacency list
     */
    public List<List<Integer>> getAdjList() {
        return adjList;
    }
    
    /**
     * Main method with example usage
     */
    public static void main(String[] args) {
        // Example 1: Valid DAG
        System.out.println("Example 1: Course Prerequisites");
        TopologicalSortDFS graph1 = new TopologicalSortDFS(6);
        
        // Course dependencies: must take course X before course Y
        graph1.addEdge(5, 2); // Course 5 before Course 2
        graph1.addEdge(5, 0); // Course 5 before Course 0
        graph1.addEdge(4, 0); // Course 4 before Course 0
        graph1.addEdge(4, 1); // Course 4 before Course 1
        graph1.addEdge(2, 3); // Course 2 before Course 3
        graph1.addEdge(3, 1); // Course 3 before Course 1
        
        List<Integer> result1 = graph1.topologicalSort();
        if (result1 != null) {
            System.out.println("Topological Order: " + result1);
        } else {
            System.out.println("Cycle detected! Topological sort not possible.");
        }
        
        // Example 2: Cyclic Graph
        System.out.println("\nExample 2: Cyclic Graph");
        TopologicalSortDFS graph2 = new TopologicalSortDFS(3);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 0); // Creates a cycle
        
        List<Integer> result2 = graph2.topologicalSort();
        if (result2 != null) {
            System.out.println("Topological Order: " + result2);
        } else {
            System.out.println("Cycle detected! Topological sort not possible.");
        }
        
        // Example 3: Build System Dependencies
        System.out.println("\nExample 3: Build System");
        TopologicalSortDFS graph3 = new TopologicalSortDFS(4);
        graph3.addEdge(0, 1); // Module 0 must be built before Module 1
        graph3.addEdge(0, 2); // Module 0 must be built before Module 2
        graph3.addEdge(1, 3); // Module 1 must be built before Module 3
        graph3.addEdge(2, 3); // Module 2 must be built before Module 3
        
        List<Integer> result3 = graph3.topologicalSort();
        if (result3 != null) {
            System.out.println("Build Order: " + result3);
        } else {
            System.out.println("Cycle detected! Build order not possible.");
        }
    }
}