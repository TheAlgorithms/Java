package com.thealgorithms.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Implementation of the A* Search Algorithm for shortest path finding.
 *
 * @author Your Name
 * @version 1.0
 */
public final class AStar {

    /**
     * Represents a node in the graph for A* algorithm.
     */
    private static final class Node implements Comparable<Node> {
        private final int id;
        private final double costFromStart;
        private final double heuristicCost;
        private final double totalCost;
        private final Node parent;

        /**
         * Constructs a new Node.
         *
         * @param id the node identifier
         * @param costFromStart the cost from start node to this node
         * @param heuristicCost the heuristic cost from this node to goal
         * @param parent the parent node
         */
        Node(int id, double costFromStart, double heuristicCost, Node parent) {
            this.id = id;
            this.costFromStart = costFromStart;
            this.heuristicCost = heuristicCost;
            this.totalCost = costFromStart + heuristicCost;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.totalCost, other.totalCost);
        }
    }

    private final Map<Integer, List<int[]>> graph;

    /**
     * Constructs an empty graph.
     */
    public AStar() {
        graph = new HashMap<>();
    }

    /**
     * Adds an undirected edge between nodes u and v with the given weight.
     *
     * @param u first node
     * @param v second node
     * @param weight edge weight
     */
    public void addEdge(int u, int v, int weight) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[] {v, weight});
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[] {u, weight});
    }

    /**
     * Heuristic function for A* (simplified as absolute difference).
     *
     * @param currentNode current node
     * @param goalNode goal node
     * @return heuristic estimate
     */
    private double heuristic(int currentNode, int goalNode) {
        return Math.abs(goalNode - currentNode);
    }

    /**
     * Finds the shortest path from start to goal using A* algorithm.
     *
     * @param start start node
     * @param goal goal node
     * @return list of nodes representing the shortest path
     */
    public List<Integer> findPath(int start, int goal) {
        if (start == goal) {
            return List.of(start);
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Integer, Double> gScore = new HashMap<>();
        Set<Integer> closedSet = new HashSet<>();

        openSet.add(new Node(start, 0.0, heuristic(start, goal), null));
        gScore.put(start, 0.0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.id == goal) {
                return reconstructPath(current);
            }

            closedSet.add(current.id);

            List<int[]> edges = graph.getOrDefault(current.id, Collections.emptyList());
            for (int[] edge : edges) {
                int neighbor = edge[0];
                double edgeWeight = edge[1];
                double tentativeG = current.costFromStart + edgeWeight;

                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double currentGScore = gScore.getOrDefault(neighbor, Double.MAX_VALUE);
                if (tentativeG < currentGScore) {
                    gScore.put(neighbor, tentativeG);
                    double neighborHeuristic = heuristic(neighbor, goal);
                    openSet.add(new Node(neighbor, tentativeG, neighborHeuristic, current));
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * Reconstructs the path by following parent nodes.
     *
     * @param node end node
     * @return path from start to end
     */
    private List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        Node current = node;
        while (current != null) {
            path.add(current.id);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
