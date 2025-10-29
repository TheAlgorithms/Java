package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Implementation of the A* Search Algorithm for shortest pathfinding.
 *
 * <p>
 * A* combines Dijkstra’s algorithm with a heuristic to efficiently find the
 * shortest path in weighted graphs.
 * </p>
 *
 * <p>
 * Reference: https://en.wikipedia.org/wiki/A*_search_algorithm
 * </p>
 *
 * <p>
 * Time Complexity: O(E + V log V) with a binary heap priority queue.<br>
 * Space Complexity: O(V + E).
 * </p>
 *
 * <p>
 * Usage is intended programmatically via the {@link Graph} and {@link #findPath}
 * method; interactive input should be handled externally or via tests.
 * </p>
 */
public class AStarSearch {

    /**
     * Represents a weighted directed graph using adjacency lists.
     */
    public static class Graph {
        private final Map<Integer, List<int[]>> adjacencyList;

        /**
         * Constructs an empty graph.
         */
        public Graph() {
            adjacencyList = new HashMap<>();
        }

        /**
         * Adds an undirected edge between nodes {@code u} and {@code v} with a weight.
         *
         * @param u      first node
         * @param v      second node
         * @param weight weight of the edge
         */
        public void addEdge(int u, int v, int weight) {
            adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[] { v, weight });
            adjacencyList.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[] { u, weight });
        }

        /**
         * Returns the adjacency list for a node.
         *
         * @param node node index
         * @return list of int[] {neighbor, weight}
         */
        public List<int[]> getEdges(int node) {
            return adjacencyList.getOrDefault(node, Collections.emptyList());
        }

        /**
         * Returns all nodes in the graph.
         *
         * @return set of node indices
         */
        public Set<Integer> getNodes() {
            return adjacencyList.keySet();
        }
    }

    /**
     * Node used in the priority queue for A*.
     */
    private static class Node implements Comparable<Node> {
        final int id;
        final double g; // cost from start
        final double h; // heuristic
        final double f; // total = g + h
        final Node parent;

        Node(int id, double g, double h, Node parent) {
            this.id = id;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.f, o.f);
        }
    }

    private final Graph graph;

    /**
     * Constructs an A* solver for a given graph.
     *
     * @param graph weighted graph
     */
    public AStarSearch(Graph graph) {
        this.graph = graph;
    }

    /**
     * Finds the shortest path from {@code start} to {@code goal} using A* search.
     *
     * @param start starting node
     * @param goal  goal node
     * @return list of nodes representing the shortest path, empty if none
     */
    public List<Integer> findPath(int start, int goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Integer, Double> gScore = new HashMap<>();
        Set<Integer> closedSet = new HashSet<>();

        openSet.add(new Node(start, 0, heuristic(start, goal), null));
        gScore.put(start, 0.0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.id == goal) {
                return reconstructPath(current);
            }

            closedSet.add(current.id);

            for (int[] edge : graph.getEdges(current.id)) {
                int neighbor = edge[0];
                double tentativeG = current.g + edge[1];

                if (closedSet.contains(neighbor)) continue;

                if (tentativeG < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    gScore.put(neighbor, tentativeG);
                    openSet.add(new Node(neighbor, tentativeG, heuristic(neighbor, goal), current));
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Simple heuristic: absolute difference between node indices.
     *
     * @param node current node
     * @param goal goal node
     * @return heuristic value
     */
    private double heuristic(int node, int goal) {
        return Math.abs(goal - node);
    }

    /**
     * Reconstructs the path from goal to start following parent links.
     *
     * @param node end node
     * @return list of node indices from start to goal
     */
    private List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        while (node != null) {
            path.add(node.id);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
