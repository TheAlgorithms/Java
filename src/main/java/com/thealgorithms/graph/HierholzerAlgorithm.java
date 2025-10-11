package com.thealgorithms.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class HierholzerAlgorithm {

    private final Map<Integer, LinkedList<Integer>> graph;

    public HierholzerAlgorithm(Map<Integer, LinkedList<Integer>> graph) {
        this.graph = (graph == null) ? new HashMap<>() : graph;
    }

    public boolean hasEulerianCircuit() {
        if (graph.isEmpty()) {
            return true;
        }

        // FINAL FIX: Loop over values directly for efficiency.
        for (List<Integer> neighbors : graph.values()) {
            if (neighbors.size() % 2 != 0) {
                return false;
            }
        }

        if (!isCoherentlyConnected()) {
            return false;
        }

        return true;
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
        List<Integer> circuit = new LinkedList<>();

        int startVertex = -1;
        // FINAL FIX: Use entrySet for efficiency.
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
                circuit.add(0, currentPath.pop());
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

        // FINAL FIX: Use entrySet for efficiency.
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

        // FINAL FIX: Use entrySet for efficiency.
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
