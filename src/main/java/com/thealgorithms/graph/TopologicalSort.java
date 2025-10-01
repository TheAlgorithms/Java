package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Topological Sort Algorithm
 *
 * Topological sorting of a directed graph is a linear ordering of its vertices
 * such that for every directed edge (u,v) from vertex u to vertex v, u comes
 * before v in the ordering.
 *
 * A topological sort is possible only in a directed acyclic graph (DAG).
 * This file contains code of finding topological sort using Depth First Search technique.
 */
public final class TopologicalSort {

    private TopologicalSort() {
        throw new AssertionError("No instances.");
    }

    /**
     * Class that represents a directed graph and provides methods for
     * manipulating the graph
     */
    public static class Graph {
        private final int n; // Number of nodes
        private final List<List<Integer>> adj; // Adjacency list representation

        /**
         * Constructor for the Graph class
         * @param nodes Number of nodes in the graph
         */
        public Graph(int nodes) {
            this.n = nodes;
            this.adj = new ArrayList<>(nodes);
            for (int i = 0; i < nodes; i++) {
                adj.add(new ArrayList<>());
            }
        }

        /**
         * Function that adds an edge between two nodes or vertices of graph
         * @param u Start node of the edge
         * @param v End node of the edge
         */
        public void addEdge(int u, int v) {
            adj.get(u).add(v);
        }

        /**
         * Get the adjacency list of the graph
         * @return The adjacency list
         */
        public List<List<Integer>> getAdjacencyList() {
            return adj;
        }

        /**
         * Get the number of nodes in the graph
         * @return The number of nodes
         */
        public int getNumNodes() {
            return n;
        }
    }

    /**
     * Function to perform Depth First Search on the graph
     * @param v Starting vertex for depth-first search
     * @param visited Array representing whether each node has been visited
     * @param recStack Array representing nodes in current recursion stack
     * @param graph Adjacency list of the graph
     * @param stack Stack containing the vertices for topological sorting
     * @return true if cycle is detected, false otherwise
     */
    private static boolean dfs(int v, boolean[] visited, boolean[] recStack, List<List<Integer>> graph, Stack<Integer> stack) {
        visited[v] = true;
        recStack[v] = true;

        for (int neighbour : graph.get(v)) {
            if (!visited[neighbour]) {
                if (dfs(neighbour, visited, recStack, graph, stack)) {
                    return true; // Cycle detected
                }
            } else if (recStack[neighbour]) {
                return true; // Back edge found - cycle detected
            }
        }

        recStack[v] = false; // Remove from recursion stack
        stack.push(v);
        return false;
    }

    /**
     * Function to get the topological sort of the graph
     * @param g Graph object
     * @return A list containing the topological order of nodes
     * @throws IllegalArgumentException if cycle is detected
     */
    public static List<Integer> sort(Graph g) {
        int n = g.getNumNodes();
        List<List<Integer>> adj = g.getAdjacencyList();
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, visited, recStack, adj, stack)) {
                    throw new IllegalArgumentException("cycle detected in graph");
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            int elem = stack.pop();
            ans.add(elem);
        }

        return ans;
    }
}
