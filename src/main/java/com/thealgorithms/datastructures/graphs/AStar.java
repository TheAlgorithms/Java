package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * AStar class implements the A* pathfinding algorithm to find the shortest path in a graph.
 * The graph is represented using an adjacency list, and the algorithm uses a heuristic to estimate
 * the cost to reach the destination node.
 * Time Complexity = O(E), where E is equal to the number of edges
 */
public final class AStar {
    private AStar() {
    }

    /**
     * Represents a graph using an adjacency list.
     */
    static class Graph {
        private ArrayList<ArrayList<Edge>> graph;

        Graph(int size) {
            this.graph = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                this.graph.add(new ArrayList<>());
            }
        }

        private ArrayList<Edge> getNeighbours(int from) {
            return this.graph.get(from);
        }

        // Add a bidirectional edge to the graph
        private void addEdge(Edge edge) {
            this.graph.get(edge.getFrom()).add(new Edge(edge.getFrom(), edge.getTo(), edge.getWeight()));
            this.graph.get(edge.getTo()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }
    }

    /**
     * Represents an edge in the graph with a start node, end node, and weight.
     */
    private static class Edge {
        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    /**
     * Contains information about the path and its total distance.
     */
    static class PathAndDistance {
        private int distance; // total distance from the start node
        private ArrayList<Integer> path; // list of nodes in the path
        private int estimated; // heuristic estimate for reaching the destination

        PathAndDistance(int distance, ArrayList<Integer> path, int estimated) {
            this.distance = distance;
            this.path = path;
            this.estimated = estimated;
        }

        public int getDistance() {
            return distance;
        }

        public ArrayList<Integer> getPath() {
            return path;
        }

        public int getEstimated() {
            return estimated;
        }
    }

    // Initializes the graph with edges defined in the input data
    static void initializeGraph(Graph graph, List<Integer> data) {
        for (int i = 0; i < data.size(); i += 4) {
            graph.addEdge(new Edge(data.get(i), data.get(i + 1), data.get(i + 2)));
        }
    }

    /**
     * Implements the A* pathfinding algorithm to find the shortest path from a start node to a destination node.
     *
     * @param from     the starting node
     * @param to       the destination node
     * @param graph    the graph representation of the problem
     * @param heuristic the heuristic estimates for each node
     * @return a PathAndDistance object containing the shortest path and its distance
     */
    public static PathAndDistance aStar(int from, int to, Graph graph, int[] heuristic) {
        // PriorityQueue to explore nodes based on their distance and estimated cost to reach the destination
        PriorityQueue<PathAndDistance> queue = new PriorityQueue<>(Comparator.comparingInt(a -> (a.getDistance() + a.getEstimated())));

        // Start with the initial node
        queue.add(new PathAndDistance(0, new ArrayList<>(List.of(from)), heuristic[from]));

        boolean solutionFound = false;
        PathAndDistance currentData = new PathAndDistance(-1, null, -1);

        while (!queue.isEmpty() && !solutionFound) {
            currentData = queue.poll(); // get the best node from the queue
            int currentPosition = currentData.getPath().get(currentData.getPath().size() - 1); // current node

            // Check if the destination has been reached
            if (currentPosition == to) {
                solutionFound = true;
            } else {
                for (Edge edge : graph.getNeighbours(currentPosition)) {
                    // Avoid cycles by checking if the next node is already in the path
                    if (!currentData.getPath().contains(edge.getTo())) {
                        ArrayList<Integer> updatedPath = new ArrayList<>(currentData.getPath());
                        updatedPath.add(edge.getTo());

                        // Update the distance and heuristic for the new path
                        queue.add(new PathAndDistance(currentData.getDistance() + edge.getWeight(), updatedPath, heuristic[edge.getTo()]));
                    }
                }
            }
        }
        return (solutionFound) ? currentData : new PathAndDistance(-1, null, -1);
    }
}
