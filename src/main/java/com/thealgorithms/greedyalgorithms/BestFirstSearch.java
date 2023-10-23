package com.thealgorithms.greedyalgorithms;

import java.util.*;

// Problem Link : https://en.wikipedia.org/wiki/Best-first_search

class Node {
    int value;
    int heuristic;

    public Node(int value, int heuristic) {
        this.value = value;
        this.heuristic = heuristic;
    }
}

public class BestFirstSearch {
    public static Node bestFirstSearch(int start, int target, Map<Integer, List<Integer>> graph,
            Map<Integer, Integer> heuristic) {
        // Check if empty graph
        if (graph.isEmpty()) {
            return null;
        }

        // Set to keep track of visited nodes
        Set<Integer> visited = new HashSet<>();

        // Priority queue to store nodes in the order of their heuristic values
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));

        // Add the start node to the queue
        Node startNode = new Node(start, heuristic.get(start));
        queue.add(startNode);

        // Iterate until the queue is empty
        while (!queue.isEmpty()) {
            // Pop the node with the lowest heuristic value
            Node currentNode = queue.poll();
            int currentVal = currentNode.value;

            // Check if the target node has been reached
            if (currentVal == target) {
                return currentNode;
            }

            visited.add(currentVal);

            // Iterate through the neighbors of the current node
            for (int neighbor : graph.getOrDefault(currentVal, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    Integer neighborHeuristic = heuristic.get(neighbor);
                    if (neighborHeuristic != null) {
                        Node neighborNode = new Node(neighbor, neighborHeuristic);
                        queue.add(neighborNode);
                    }
                }
            }
        }

        return null; // Target not found
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(4));
        graph.put(3, Arrays.asList(5, 6));
        graph.put(4, Arrays.asList(7));
        graph.put(5, Arrays.asList(8));
        graph.put(6, Arrays.asList(9));

        Map<Integer, Integer> heuristic = new HashMap<>();
        heuristic.put(1, 8);
        heuristic.put(2, 6);
        heuristic.put(3, 5);
        heuristic.put(4, 4);
        heuristic.put(5, 3);
        heuristic.put(6, 2);
        heuristic.put(7, 1);
        heuristic.put(8, 0);
        heuristic.put(9, 0);

        Node result = bestFirstSearch(1, 8, graph, heuristic);
        if (result != null) {
            System.out.println("Found: " + result.value);
        } else {
            System.out.println("Target not found.");
        }
    }
}
