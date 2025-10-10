package com.thealgorithms.graph;

import java.util.*;

/**
 * An implementation of Hierholzer's Algorithm to find an Eulerian Path or Circuit in an undirected graph.
 * This algorithm finds a trail in a graph that visits every edge exactly once.
 * Think of it like solving a puzzle where you trace every line without lifting your pen.
 *
 * Wikipedia: https://en.wikipedia.org/wiki/Eulerian_path#Hierholzer's_algorithm
 */
public class HierholzerAlgorithm {

    private final Map<Integer, LinkedList<Integer>> graph;

    /**
     * Sets up the algorithm with the graph we want to solve.
     * @param graph The graph represented as an adjacency list.
     */
    public HierholzerAlgorithm(Map<Integer, LinkedList<Integer>> graph) {
        this.graph = (graph == null) ? new HashMap<>() : graph;
    }

    /**
     * Before starting, we have to ask: can this puzzle even be solved?
     * This method checks the two essential rules for an undirected graph.
     * @return true if a circuit is possible, false otherwise.
     */
    public boolean hasEulerianCircuit() {
        if (graph.isEmpty()) {
            return true; // An empty puzzle is trivially solved.
        }

        // Rule 1: Every point must have an even number of lines connected to it.
        // This ensures for every way in, there's a way out.
        for (int vertex : graph.keySet()) {
            if (graph.get(vertex).size() % 2 != 0) {
                return false; // Found a point with an odd number of lines.
            }
        }

        // Rule 2: The drawing must be one single, connected piece.
        // You can't have a separate, floating part of the puzzle.
        return isCoherentlyConnected();
    }

    /**
     * This is the main event—finding the actual path.
     * @return A list of points (vertices) that make up the complete circuit.
     */
    public List<Integer> findEulerianCircuit() {
        if (!hasEulerianCircuit()) {
            // If the puzzle can't be solved, return an empty path.
            return Collections.emptyList();
        }

        // We'll work on a copy of the graph so we don't destroy the original.
        Map<Integer, LinkedList<Integer>> tempGraph = new HashMap<>();
        for (Map.Entry<Integer, LinkedList<Integer>> entry : graph.entrySet()) {
            tempGraph.put(entry.getKey(), new LinkedList<>(entry.getValue()));
        }

        // 'currentPath' is our breadcrumb trail as we explore.
        Stack<Integer> currentPath = new Stack<>();
        // 'circuit' is where we'll lay out the final, complete path.
        List<Integer> circuit = new LinkedList<>();

        // Find any point to start from.
        int startVertex = graph.keySet().stream().findFirst().orElse(-1);
        if (startVertex == -1) return Collections.emptyList();

        currentPath.push(startVertex);

        while (!currentPath.isEmpty()) {
            int currentVertex = currentPath.peek();

            // If there's an unexplored hallway from our current location...
            if (tempGraph.containsKey(currentVertex) && !tempGraph.get(currentVertex).isEmpty()) {
                // ...let's go down it.
                int nextVertex = tempGraph.get(currentVertex).pollFirst();
                // Erase the hallway behind us so we don't use it again.
                tempGraph.get(nextVertex).remove(Integer.valueOf(currentVertex));
                // Add the new location to our breadcrumb trail.
                currentPath.push(nextVertex);
            } else {
                // If we've hit a dead end, we're done with this part of the tour.
                // We add our location to the final path and backtrack.
                circuit.add(0, currentPath.pop());
            }
        }

        return circuit;
    }

    /**
     * A helper to check if the graph is one single piece.
     * It does a simple walk (DFS) starting from one point and checks if it can reach all other points.
     */
    private boolean isCoherentlyConnected() {
        if (graph.isEmpty()) return true;
        Set<Integer> visited = new HashSet<>();
        int startNode = graph.keySet().stream().findFirst().orElse(-1);
        if (startNode == -1) return true;

        dfs(startNode, visited);

        for (int vertex : graph.keySet()) {
            if (!graph.get(vertex).isEmpty() && !visited.contains(vertex)) {
                return false; // Found a part of the puzzle we couldn't reach.
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
