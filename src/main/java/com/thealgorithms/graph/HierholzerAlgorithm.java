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
 * Implementation of Hierholzer's algorithm to find an Eulerian Circuit in an undirected graph.
 * <p>
 * An Eulerian circuit is a trail in a graph that visits every edge exactly once,
 * starting and ending at the same vertex. This algorithm finds such a circuit if one exists.
 * </p>
 * <p>
 * This implementation is designed for an <strong>undirected graph</strong>. For a valid Eulerian
 * circuit to exist, the graph must satisfy two conditions:
 * <ol>
 * <li>All vertices with a non-zero degree must be part of a single connected component.</li>
 * <li>Every vertex must have an even degree (an even number of edges connected to it).</li>
 * </ol>
 * </p>
 * <p>
 * The algorithm runs in O(E + V) time, where E is the number of edges and V is the number of vertices.
 * The graph is represented by a Map where keys are vertices and values are a LinkedList of adjacent vertices.
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Eulerian_path#Hierholzer's_algorithm">Wikipedia: Hierholzer's algorithm</a>
 */
public final class HierholzerAlgorithm {

    private final Map<Integer, LinkedList<Integer>> graph;

    public HierholzerAlgorithm(Map<Integer, LinkedList<Integer>> graph) {
        this.graph = (graph == null) ? new HashMap<>() : graph;
    }

    public boolean hasEulerianCircuit() {
        if (graph.isEmpty()) {
            return true;
        }

        for (List<Integer> neighbors : graph.values()) {
            if (neighbors.size() % 2 != 0) {
                return false;
            }
        }

        return isCoherentlyConnected();
    }

    public List<Integer> findEulerianCircuit() {
        if (!hasEulerianCircuit()) {
            return Collections.emptyList();
        }

        Map<Integer, LinkedList<Integer>> tempGraph = new HashMap<>();
        for (Map.Entry<Integer, LinkedList<Integer>> entry : graph.entrySet()) {
            tempGraph.put(entry.getKey(), new LinkedList<>(entry.getValue()));
        }

        Stack<Integer> currentPath = new Stack<>();
        LinkedList<Integer> circuit = new LinkedList<>();

        int startVertex = -1;
        for (Map.Entry<Integer, LinkedList<Integer>> entry : tempGraph.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                startVertex = entry.getKey();
                break;
            }
        }

        if (startVertex == -1) {
            if (graph.isEmpty()) {
                return Collections.emptyList();
            }
            return Collections.singletonList(graph.keySet().iterator().next());
        }

        currentPath.push(startVertex);

        while (!currentPath.isEmpty()) {
            int currentVertex = currentPath.peek();

            if (tempGraph.containsKey(currentVertex) && !tempGraph.get(currentVertex).isEmpty()) {
                int nextVertex = tempGraph.get(currentVertex).pollFirst();
                tempGraph.get(nextVertex).remove(Integer.valueOf(currentVertex));
                currentPath.push(nextVertex);
            } else {
                circuit.addFirst(currentVertex);
                currentPath.pop();
            }
        }

        return circuit;
    }

    private boolean isCoherentlyConnected() {
        if (graph.isEmpty()) {
            return true;
        }

        Set<Integer> visited = new HashSet<>();
        int startNode = -1;

        for (Map.Entry<Integer, LinkedList<Integer>> entry : graph.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                startNode = entry.getKey();
                break;
            }
        }

        if (startNode == -1) {
            return true;
        }

        dfs(startNode, visited);

        for (Map.Entry<Integer, LinkedList<Integer>> entry : graph.entrySet()) {
            if (!entry.getValue().isEmpty() && !visited.contains(entry.getKey())) {
                return false;
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
