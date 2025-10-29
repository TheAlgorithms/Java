package com.thealgorithms.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Implementation of the A* Search Algorithm for shortest path finding.
 *
 * <p>
 * A* combines Dijkstra's algorithm with a heuristic to efficiently find the
 * shortest path in weighted graphs.
 * </p>
 *
 * <p>
 * Reference: https://en.wikipedia.org/wiki/A*_search_algorithm
 * </p>
 *
 * <p>
 * Time Complexity: O(E + V log V) with a binary heap priority queue.
 * Space Complexity: O(V + E)
 * </p>
 */
public final class AStarSearch {

    private static final class Node implements Comparable<Node> {
        private final int id;
        private final double costFromStart;
        private final double heuristicCost;
        private final double totalCost;
        private final Node parent;

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

    /** Constructs an empty graph. */
    public AStarSearch() {
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

    /**
     * Main method to demonstrate A* algorithm with user input.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AStarSearch aStar = new AStarSearch();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();
        System.out.println("Enter edges in format: u v weight");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            aStar.addEdge(u, v, w);
        }

        System.out.print("Enter start node: ");
        int start = sc.nextInt();
        System.out.print("Enter goal node: ");
        int goal = sc.nextInt();

        List<Integer> path = aStar.findPath(start, goal);
        if (path.isEmpty()) {
            System.out.println("No path found from " + start + " → " + goal);
        } else {
            System.out.println("Shortest path from " + start + " → " + goal + ": " + path);
        }
        sc.close();
    }
}
