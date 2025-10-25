package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Finds the strongly connected components in a directed graph.
 *
 * @param adjList The adjacency list representation of the graph.
 * @param n The number of nodes in the graph.
 * @return The number of strongly connected components.
 */
public class StronglyConnectedComponentOptimized {

    public void btrack(HashMap<Integer, List<Integer>> adjList, int[] visited, Stack<Integer> dfsCallsNodes, int currentNode) {
        visited[currentNode] = 1;
        List<Integer> neighbors = adjList.get(currentNode);
        // Check for null before iterating
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (visited[neighbor] == -1) {
                    btrack(adjList, visited, dfsCallsNodes, neighbor);
                }
            }
        }
        dfsCallsNodes.add(currentNode);
    }

    public void btrack2(HashMap<Integer, List<Integer>> adjRevList, int[] visited, int currentNode, List<Integer> newScc) {
        visited[currentNode] = 1;
        newScc.add(currentNode);
        List<Integer> neighbors = adjRevList.get(currentNode);
        // Check for null before iterating
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (visited[neighbor] == -1) {
                    btrack2(adjRevList, visited, neighbor, newScc);
                }
            }
        }
    }

    public int getOutput(HashMap<Integer, List<Integer>> adjList, int n) {
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        Stack<Integer> dfsCallsNodes = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                btrack(adjList, visited, dfsCallsNodes, i);
            }
        }

        HashMap<Integer, List<Integer>> adjRevList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjRevList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = adjList.get(i);
            // Check for null before iterating
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    adjRevList.get(neighbor).add(i);
                }
            }
        }

        Arrays.fill(visited, -1);
        int stronglyConnectedComponents = 0;

        while (!dfsCallsNodes.isEmpty()) {
            int node = dfsCallsNodes.pop();
            if (visited[node] == -1) {
                List<Integer> newScc = new ArrayList<>();
                btrack2(adjRevList, visited, node, newScc);
                stronglyConnectedComponents++;
            }
        }

        return stronglyConnectedComponents;
    }
}
