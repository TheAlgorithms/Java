package com.thealgorithms.backtracking;

//   Problem Statement: Find the shortest path from a starting node to all other nodes in a weighted graph.

// Example:
//         7
//    A -------- B
//    |          |
//   3|          |2
//    |          |
//    C -------- D
//         1
// Starting Node: A
// Result:
// Shortest paths:

// A -> A: 0
// A -> B: 7
// A -> C: 3
// A -> D: 4 (A -> C -> D)

import java.util.*;

// Class representing an Edge in the graph
class Edge {
    private final int destination;
    private final int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}

// Class representing a Graph
class Graph {
    private final Map<Integer, List<Edge>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    // Add edge between two nodes with a given weight
    public void addEdge(int source, int destination, int weight) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, weight));
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge(source, weight)); // undirected graph
    }

    public List<Edge> getEdges(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Integer> getAllNodes() {
        return adjList.keySet();
    }
}

// Class representing a Node (for use in the PriorityQueue)
class Node {
    private final int node;
    private final int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public int getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }
}

// Dijkstra's Algorithm class
public class Dijkstra {

    // method to find shortest paths from a given start node
    public Map<Integer, Integer> findShortestPaths(Graph graph, int startNode) {
        // priority Queue to select the minimum distance node
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        // Initialize distances with infinity
        for (int node : graph.getAllNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            visited.put(node, false);
        }

        // Starting point
        distances.put(startNode, 0);
        pq.add(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.getNode();

            if (visited.get(current)) {
                continue;
            }
            visited.put(current, true);

            // Update the distances to the neighboring nodes
            for (Edge edge : graph.getEdges(current)) {
                int neighbor = edge.getDestination();
                int newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new Node(neighbor, newDist));
                }
            }
        }
        return distances;
    }
}
