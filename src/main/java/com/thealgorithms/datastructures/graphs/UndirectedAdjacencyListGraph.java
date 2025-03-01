package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UndirectedAdjacencyListGraph {
    private ArrayList<HashMap<Integer, Integer>> adjacencyList = new ArrayList<>();

    /**
     * Adds a new node to the graph by adding an empty HashMap for its neighbors.
     * @return the index of the newly added node in the adjacency list
     */
    public int addNode() {
        adjacencyList.add(new HashMap<>());
        return adjacencyList.size() - 1;
    }

    /**
     * Adds an undirected edge between the origin node (@orig) and the destination node (@dest) with the specified weight.
     * If the edge already exists, no changes are made.
     * @param orig the index of the origin node
     * @param dest the index of the destination node
     * @param weight the weight of the edge between @orig and @dest
     * @return true if the edge was successfully added, false if the edge already exists or if any node index is invalid
     */
    public boolean addEdge(int orig, int dest, int weight) {
        int numNodes = adjacencyList.size();
        if (orig >= numNodes || dest >= numNodes || orig < 0 || dest < 0) {
            return false;
        }

        if (adjacencyList.get(orig).containsKey(dest)) {
            return false;
        }

        adjacencyList.get(orig).put(dest, weight);
        adjacencyList.get(dest).put(orig, weight);
        return true;
    }

    /**
     * Returns the set of all adjacent nodes (neighbors) for the given node.
     * @param node the index of the node whose neighbors are to be retrieved
     * @return a HashSet containing the indices of all neighboring nodes
     */
    public HashSet<Integer> getNeighbors(int node) {
        return new HashSet<>(adjacencyList.get(node).keySet());
    }

    /**
     * Returns the weight of the edge between the origin node (@orig) and the destination node (@dest).
     * If no edge exists, returns null.
     * @param orig the index of the origin node
     * @param dest the index of the destination node
     * @return the weight of the edge between @orig and @dest, or null if no edge exists
     */
    public Integer getEdgeWeight(int orig, int dest) {
        return adjacencyList.get(orig).getOrDefault(dest, null);
    }

    /**
     * Returns the number of nodes currently in the graph.
     * @return the number of nodes in the graph
     */
    public int size() {
        return adjacencyList.size();
    }
}
