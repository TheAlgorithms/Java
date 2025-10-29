package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Scanner;

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
 * Space Complexity: O(V + E)
 * </p>
 */
public class AStarSearch {

    private static class Node implements Comparable<Node> {
        int id;
        double g; // cost from start
        double h; // heuristic to goal
        double f; // total cost = g + h
        Node parent;

        Node(int id, double g, double h, Node parent) {
            this.id = id;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.f, other.f);
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
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight});
    }

    /**
     * Heuristic function for A* (simplified as absolute difference).
     *
     * @param node current node
     * @param goal goal node
     * @return heuristic estimate
     */
    private double heuristic(int node, int goal) {
        return Math.abs(goal - node);
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
            for (int[] edge : graph.getOrDefault(current.id, new ArrayList<>())) {
                int neighbor = edge[0];
                double tentativeG = current.g + edge[1];
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                if (tentativeG < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    gScore.put(neighbor, tentativeG);
                    openSet.add(new Node(neighbor, tentativeG, heuristic(neighbor, goal), current));
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
        while (node != null) {
            path.add(node.id);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    /** Reads input dynamically and runs A* algorithm. */
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
