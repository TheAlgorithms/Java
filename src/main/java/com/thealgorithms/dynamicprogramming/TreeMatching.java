package com.thealgorithms.dynamicprogramming;

import com.thealgorithms.datastructures.graphs.UndirectedAdjacencyListGraph;

/**
 * This class implements the algorithm for calculating the maximum weighted matching in a tree.
 * The tree is represented as an undirected graph with weighted edges.
 *
 * Problem Description:
 *  Given an undirected tree G = (V, E) with edge weights γ: E → N and a root r ∈ V,
 *  the goal is to find a maximum weight matching M ⊆ E such that no two edges in M
 *  share a common vertex. The sum of the weights of the edges in M, ∑ e∈M γ(e), should be maximized.
 *  For more Information: <a href="https://en.wikipedia.org/wiki/Matching_(graph_theory)">Matching (graph theory)</a>
 *
 * @author  <a href="https://github.com/DenizAltunkapan">Deniz Altunkapan</a>
 */
public class TreeMatching {

    private UndirectedAdjacencyListGraph graph;
    private int[][] dp;

    /**
     * Constructor that initializes the graph and the DP table.
     *
     * @param graph The graph that represents the tree and is used for the matching algorithm.
     */
    public TreeMatching(UndirectedAdjacencyListGraph graph) {
        this.graph = graph;
        this.dp = new int[graph.size()][2];
    }

    /**
     * Calculates the maximum weighted matching for the tree, starting from the given root node.
     *
     * @param root The index of the root node of the tree.
     * @param parent The index of the parent node (used for recursion).
     * @return The maximum weighted matching for the tree, starting from the root node.
     *
     */
    public int getMaxMatching(int root, int parent) {
        if (root < 0 || root >= graph.size()) {
            throw new IllegalArgumentException("Invalid root: " + root);
        }
        maxMatching(root, parent);
        return Math.max(dp[root][0], dp[root][1]);
    }

    /**
     * Recursively computes the maximum weighted matching for a node, assuming that the node
     * can either be included or excluded from the matching.
     *
     * @param node The index of the current node for which the matching is calculated.
     * @param parent The index of the parent node (to avoid revisiting the parent node during recursion).
     */
    private void maxMatching(int node, int parent) {
        dp[node][0] = 0;
        dp[node][1] = 0;

        int sumWithoutEdge = 0;
        for (int adjNode : graph.getNeighbors(node)) {
            if (adjNode == parent) {
                continue;
            }
            maxMatching(adjNode, node);
            sumWithoutEdge += Math.max(dp[adjNode][0], dp[adjNode][1]);
        }

        dp[node][0] = sumWithoutEdge;

        for (int adjNode : graph.getNeighbors(node)) {
            if (adjNode == parent) {
                continue;
            }
            int weight = graph.getEdgeWeight(node, adjNode);
            dp[node][1] = Math.max(dp[node][1], sumWithoutEdge - Math.max(dp[adjNode][0], dp[adjNode][1]) + dp[adjNode][0] + weight);
        }
    }
}
