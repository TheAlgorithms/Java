package com.thealgorithms.backtracking;
//  Problem Statement: Find the shortest path from a start node to a goal node using heuristics.

// Example:
// Start (S) -> Goal (G)
// S(0,0) ----- (2,0) ----- G(4,0)

// Heuristic: Manhattan distance.
// Start Node: S(0,0)
// Goal Node: G(4,0)

// A* uses both the actual cost and estimated distance to guide the search.
// Shortest path: S -> (2,0) -> G, with a total cost of 4.


import java.util.*;

// Node class to represent each node in the graph
class Node implements Comparable<Node> {
    public int vertex;
    public int gCost;  // Actual cost to reach this node
    public int hCost;  // Heuristic cost to goal
    public Node parent;

    public Node(int vertex, int gCost, int hCost, Node parent) {
        this.vertex = vertex;
        this.gCost = gCost;
        this.hCost = hCost;
        this.parent = parent;
    }

    // f(n) = g(n) + h(n)
    public int getFCost() {
        return gCost + hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.getFCost(), other.getFCost());
    }
}

// Graph class to represent a weighted graph using adjacency list
class Graph {
    private final int V;
    private final List<List<Node>> adjacencyList;

    public Graph(int V) {
        this.V = V;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjacencyList.get(u).add(new Node(v, weight, 0, null));
    }

    public List<List<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNumberOfVertices() {
        return V;
    }
}

// Heuristic interface for Strategy Pattern
interface Heuristic {
    int calculate(int current, int goal);
}

// Example Heuristic: Manhattan Distance (for grid graphs)
class ManhattanHeuristic implements Heuristic {
    private final int[] xCoords;
    private final int[] yCoords;

    public ManhattanHeuristic(int[] xCoords, int[] yCoords) {
        this.xCoords = xCoords;
        this.yCoords = yCoords;
    }

    @Override
    public int calculate(int current, int goal) {
        return Math.abs(xCoords[current] - xCoords[goal]) + Math.abs(yCoords[current] - yCoords[goal]);
    }
}

// Example Heuristic: Euclidean Distance
class EuclideanHeuristic implements Heuristic {
    private final int[] xCoords;
    private final int[] yCoords;

    public EuclideanHeuristic(int[] xCoords, int[] yCoords) {
        this.xCoords = xCoords;
        this.yCoords = yCoords;
    }

    @Override
    public int calculate(int current, int goal) {
        return (int) Math.sqrt(Math.pow(xCoords[current] - xCoords[goal], 2) +
                               Math.pow(yCoords[current] - yCoords[goal], 2));
    }
}

// AStar class for A* algorithm implementation
class AStar {
    private final Graph graph;
    private final Heuristic heuristic;

    public AStar(Graph graph, Heuristic heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    public List<Integer> findShortestPath(int start, int goal) {
        int V = graph.getNumberOfVertices();
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Integer> closedSet = new HashSet<>();

        Node startNode = new Node(start, 0, heuristic.calculate(start, goal), null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();

            if (currentNode.vertex == goal) {
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode.vertex);

            for (Node neighbor : graph.getAdjacencyList().get(currentNode.vertex)) {
                if (closedSet.contains(neighbor.vertex)) {
                    continue;  // Skip already processed nodes
                }

                int tentativeGCost = currentNode.gCost + neighbor.gCost;
                Node newNeighborNode = new Node(neighbor.vertex, tentativeGCost, heuristic.calculate(neighbor.vertex, goal), currentNode);

                openSet.add(newNeighborNode);
            }
        }

        return Collections.emptyList();  // Return an empty list if no path found
    }

    private List<Integer> reconstructPath(Node goalNode) {
        List<Integer> path = new ArrayList<>();
        Node currentNode = goalNode;
        while (currentNode != null) {
            path.add(currentNode.vertex);
            currentNode = currentNode.parent;
        }
        Collections.reverse(path);  // Reverse the path to get the correct order
        return path;
    }
}
