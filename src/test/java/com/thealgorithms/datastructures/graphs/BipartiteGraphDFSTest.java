package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class BipartiteGraphDFSTest {

    // Helper method to create an adjacency list from edges
    private ArrayList<ArrayList<Integer>> createAdjacencyList(int numVertices, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int vertexU = edge[0];
            int vertexV = edge[1];
            adj.get(vertexU).add(vertexV);
            adj.get(vertexV).add(vertexU);
        }
        return adj;
    }

    @Test
    public void testBipartiteGraphEvenCycle() {
        int numVertices = 4;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 0}}; // Even cycle
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(numVertices, edges);
        assertTrue(BipartiteGraphDFS.isBipartite(numVertices, adj), "Graph should be bipartite (even cycle)");
    }

    @Test
    public void testBipartiteGraphOddCycle() {
        int numVertices = 5;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {1, 3}, {3, 4}}; // Odd cycle
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(numVertices, edges);
        assertFalse(BipartiteGraphDFS.isBipartite(numVertices, adj), "Graph should not be bipartite (odd cycle)");
    }

    @Test
    public void testBipartiteGraphDisconnected() {
        int numVertices = 6;
        int[][] edges = {{0, 1}, {2, 3}, {4, 5}}; // Disconnected bipartite graphs
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(numVertices, edges);
        assertTrue(BipartiteGraphDFS.isBipartite(numVertices, adj), "Graph should be bipartite (disconnected)");
    }

    @Test
    public void testBipartiteGraphSingleVertex() {
        int numVertices = 1;
        int[][] edges = {}; // Single vertex, no edges
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(numVertices, edges);
        assertTrue(BipartiteGraphDFS.isBipartite(numVertices, adj), "Graph should be bipartite (single vertex)");
    }

    @Test
    public void testBipartiteGraphCompleteBipartite() {
        int numVertices = 4;
        int[][] edges = {{0, 2}, {0, 3}, {1, 2}, {1, 3}}; // K2,2 (Complete bipartite graph)
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(numVertices, edges);
        assertTrue(BipartiteGraphDFS.isBipartite(numVertices, adj), "Graph should be bipartite (complete bipartite)");
    }

    @Test
    public void testBipartiteGraphNonBipartite() {
        int numVertices = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}}; // Triangle (odd cycle)
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(numVertices, edges);
        assertFalse(BipartiteGraphDFS.isBipartite(numVertices, adj), "Graph should not be bipartite (triangle)");
    }
}
