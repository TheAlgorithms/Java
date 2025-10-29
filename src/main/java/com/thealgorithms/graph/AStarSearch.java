package com.thealgorithms.graph;

import java.util.*;

/**
 * A* Search Algorithm for shortest pathfinding.
 *
 * <p>Commonly used in games, navigation, and robotics.
 * Combines Dijkstra’s Algorithm and heuristic estimation.</p>
 *
 * <p>Reference: https://en.wikipedia.org/wiki/A*_search_algorithm</p>
 */
public class AStarSearch {

    static class Node implements Comparable<Node> {
        int id;
        double g; // Cost from start
        double h; // Heuristic to goal
        double f; // Total cost = g + h
        Node parent;

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

    private final Map<Integer, List<int[]>> graph;

    public AStarSearch() {
        graph = new HashMap<>();
    }

    /** Adds an undirected edge between nodes u and v with the given weight. */
    public void addEdge(int u, int v, int weight) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight});
    }

    /** Heuristic function (simplified for numeric nodes). */
    private double heuristic(int node, int goal) {
        return Math.abs(goal - node);
    }

    /**
     * Finds the shortest path from start to goal using A* algorithm.
     *
     * @param start starting node
     * @param goal  goal node
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
                    Node next = new Node(neighbor, tentativeG, heuristic(neighbor, goal), current);
                    openSet.add(next);
                }
            }
        }

        return Collections.emptyList();
    }

    /** Reconstructs path by following parent nodes. */
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
