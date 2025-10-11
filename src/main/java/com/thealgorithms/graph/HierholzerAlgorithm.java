package com.thealgorithms.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * An implementation of Hierholzer's Algorithm to find an Eulerian Path or Circuit in an undirected graph.
 * An Eulerian path is a trail in a graph that visits every edge exactly once.
 * An Eulerian circuit is an Eulerian path that starts and ends at the same vertex.
 *
 * Wikipedia: https://en.wikipedia.org/wiki/Eulerian_path#Hierholzer's_algorithm
 */
public class HierholzerAlgorithm {

    private final int numVertices;
    private final Map<Integer, LinkedList<Integer>> graph;

    /**
     * Constructor for the algorithm.
     * @param graph The graph represented as an adjacency list.
     * Using a LinkedList for neighbors is efficient for edge removal.
     */
    public HierholzerAlgorithm(Map<Integer, LinkedList<Integer>> graph) {
        if (graph == null) {
            this.graph = new HashMap<>();
            this.numVertices = 0;
            return;
        }
        this.graph = graph;
        this.numVertices = graph.size();
    }

    /**
     * Checks if an Eulerian circuit exists in the undirected graph.
     * Condition: All vertices with a non-zero degree must be in a single connected component,
     * and all vertices must have an even degree.
     * @return true if a circuit exists, false otherwise.
     */
    public boolean hasEulerianCircuit() {
        if (graph.isEmpty()) {
            return true; // An empty graph has an empty circuit.
        }

        // Check 1: All vertices must have an even degree.
        for (int vertex : graph.keySet()) {
            if (graph.get(vertex).size() % 2 != 0) {
                return false; // Found a vertex with an odd degree.
            }
        }

        // Check 2: All vertices with edges must be connected.
        if (!isCoherentlyConnected()) {
            return false;
        }

        return true;
    }

    /**
     * Finds the Eulerian circuit.
     * @return A list of vertices representing the circuit, or an empty list if none exists.
     */
    public List<Integer> findEulerianCircuit() {
        if (!hasEulerianCircuit()) {
            return Collections.emptyList();
        }

        // Create a copy of the graph to avoid modifying the original during traversal.
        Map<Integer, LinkedList<Integer>> tempGraph = new HashMap<>();
        for (Map.Entry<Integer, LinkedList<Integer>> entry : graph.entrySet()) {
            tempGraph.put(entry.getKey(), new LinkedList<>(entry.getValue()));
        }

        // Data structures for the algorithm.
        Stack<Integer> currentPath = new Stack<>();
        List<Integer> circuit = new LinkedList<>();

        // Find a starting vertex (any vertex with edges).
        int startVertex = -1;
        for (int vertex : tempGraph.keySet()) {
            if (!tempGraph.get(vertex).isEmpty()) {
                startVertex = vertex;
                break;
            }
        }

        if (startVertex == -1) {
            if (graph.isEmpty()) {
                return Collections.emptyList();
            }
            return Collections.singletonList(graph.keySet().iterator().next()); // Graph with one isolated vertex.
        }

        currentPath.push(startVertex);

        while (!currentPath.isEmpty()) {
            int currentVertex = currentPath.peek();

            // If the current vertex has unvisited edges
            if (tempGraph.containsKey(currentVertex) && !tempGraph.get(currentVertex).isEmpty()) {
                int nextVertex = tempGraph.get(currentVertex).pollFirst(); // Get a neighbor

                // Remove the reverse edge as well (for undirected graph)
                tempGraph.get(nextVertex).remove(Integer.valueOf(currentVertex));

                // Push the neighbor to the stack to continue the tour
                currentPath.push(nextVertex);
            } else {
                // If "stuck" (no more edges), backtrack and add to the final circuit.
                circuit.add(0, currentPath.pop());
            }
        }

        return circuit;
    }

    /**
     * Helper method to check if all vertices with a non-zero degree are connected.
     * Uses a simple traversal (DFS).
     */
    private boolean isCoherentlyConnected() {
        if (graph.isEmpty()) {
            return true;
        }

        Set<Integer> visited = new HashSet<>();
        int startNode = -1;

        // Find the first vertex with a degree greater than 0
        for (int vertex : graph.keySet()) {
            if (!graph.get(vertex).isEmpty()) {
                startNode = vertex;
                break;
            }
        }

        // If no edges in the graph, it's connected.
        if (startNode == -1) {
            return true;
        }

        // Perform DFS from the start node
        dfs(startNode, visited);

        // Check if all vertices with edges were visited
        for (int vertex : graph.keySet()) {
            if (!graph.get(vertex).isEmpty() && !visited.contains(vertex)) {
                return false; // Found a vertex with edges that wasn't visited
            }
        }
        return true;
    }

    private void dfs(int u, Set<Integer> visited) {
        visited.add(u);
        if (graph.containsKey(u)) {
            for (int v : graph.get(u)) {
                if (!visited.contains(v)) {
                    dfs(v, visited);
                }
            }
        }
    }
}
