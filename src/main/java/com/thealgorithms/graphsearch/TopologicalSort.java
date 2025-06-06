package com.thealgorithms.graphsearch;

// Topological sort: https://en.wikipedia.org/wiki/Topological_sorting

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class implements the Topological Sort algorithm using Depth-First Search
 * for Directed Acyclic Graphs. It prints the nodes in a topological order.
 */

public final class TopologicalSort {

    // Private constructor to prevent instantiation
    private TopologicalSort() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    /**
     * Main method to test the Topological Sort implementation.
     * Creates a sample graph and prints its topological ordering.
     * @param args command-line arguments(not used)
     */

    public static void main(String[] args) {
        int v = 6;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        // sample DAG edges
        adjList.get(5).add(2);
        adjList.get(5).add(0);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(2).add(3);
        adjList.get(3).add(1);

        topoSort(v, adjList);
    }

    /**
     * Performs the topological sort on the given graph represented by adjacency list.
     * Prints the nodes in topologically sorted order.
     *
     * @param v  the number of vertices in the graph
     * @param adjList adjacency list representing the graph
     */

    public static void topoSort(int v, ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                topoSortUtil(i, visited, stack, adjList);
            }
        }

        System.out.println("Topological Sort:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    /**
     *  Utility method to perform DFS starting from a given node.
     *  Marks nodes as visited and pushes nodes to stack after visiting all their neighbors.
     *
     * @param node the current node being visited
     * @param visited boolean array tracking visited nodes
     * @param stack stack to store the topological order
     * @param adjList adjacency list representing the graph
     *
     */

    public static void topoSortUtil(int node, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = true;

        for (int neighbour : adjList.get(node)) {
            if (!visited[neighbour]) {
                topoSortUtil(neighbour, visited, stack, adjList);
            }
        }
        stack.push(node); // add to stack after visiting all edges
    }
}
