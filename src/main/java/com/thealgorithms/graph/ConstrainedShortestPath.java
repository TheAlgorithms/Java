package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements a solution for the Constrained Shortest Path Problem (CSPP).
 * also known as Shortest Path Problem with Resource Constraints (SPPRC).
 * The goal is to find the shortest path between two nodes while ensuring that
 * the resource constraint is not exceeded.
 *
 * @author  <a href="https://github.com/DenizAltunkapan">Deniz Altunkapan</a>
 */
public class ConstrainedShortestPath {

    /**
     * Represents a graph using an adjacency list.
     * This graph is designed for the Constrained Shortest Path Problem (CSPP).
     */
    public static class Graph {

        private List<List<Edge>> adjacencyList;

        public Graph(int numNodes) {
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < numNodes; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        /**
         * Adds an edge to the graph.
         * @param from the starting node
         * @param to the ending node
         * @param cost the cost of the edge
         * @param resource the resource required to traverse the edge
         */
        public void addEdge(int from, int to, int cost, int resource) {
            adjacencyList.get(from).add(new Edge(from, to, cost, resource));
        }

        /**
         * Gets the edges that are adjacent to a given node.
         * @param node the node to get the edges for
         * @return the list of edges adjacent to the node
         */
        public List<Edge> getEdges(int node) {
            return adjacencyList.get(node);
        }

        /**
         * Gets the number of nodes in the graph.
         * @return the number of nodes
         */
        public int getNumNodes() {
            return adjacencyList.size();
        }

        public record Edge(int from, int to, int cost, int resource) {
        }
    }

    private Graph graph;
    private int maxResource;

    /**
     * Constructs a CSPSolver with the given graph and maximum resource constraint.
     *
     * @param graph       the graph representing the problem
     * @param maxResource the maximum allowable resource
     */
    public ConstrainedShortestPath(Graph graph, int maxResource) {
        this.graph = graph;
        this.maxResource = maxResource;
    }

    /**
     * Solves the CSP to find the shortest path from the start node to the target node
     * without exceeding the resource constraint.
     *
     * @param start  the starting node
     * @param target the target node
     * @return the minimum cost to reach the target node within the resource constraint,
     *         or -1 if no valid path exists
     */
    public int solve(int start, int target) {
        int numNodes = graph.getNumNodes();
        int[][] dp = new int[maxResource + 1][numNodes];

        // Initialize dp table with maximum values
        for (int i = 0; i <= maxResource; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][start] = 0;

        // Dynamic Programming: Iterate over resources and nodes
        for (int r = 0; r <= maxResource; r++) {
            for (int u = 0; u < numNodes; u++) {
                if (dp[r][u] == Integer.MAX_VALUE) {
                    continue;
                }
                for (Graph.Edge edge : graph.getEdges(u)) {
                    int v = edge.to();
                    int cost = edge.cost();
                    int resource = edge.resource();

                    if (r + resource <= maxResource) {
                        dp[r + resource][v] = Math.min(dp[r + resource][v], dp[r][u] + cost);
                    }
                }
            }
        }

        // Find the minimum cost to reach the target node
        int minCost = Integer.MAX_VALUE;
        for (int r = 0; r <= maxResource; r++) {
            minCost = Math.min(minCost, dp[r][target]);
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
}
