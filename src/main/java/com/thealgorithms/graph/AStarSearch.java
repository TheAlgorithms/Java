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

    @FunctionalInterface
    public interface Heuristic {
        double estimate(int node, int goal);
    }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private final Map<Integer, List<int[]>> graph;
    private final Heuristic heuristic;

    /**
     * Constructor with default heuristic (|goal - node|).
     */
    public AStarSearch() {
        this.graph = new HashMap<>();
        this.heuristic = (node, goal) -> Math.abs(goal - node);
    }

    /**
     * Constructor with custom heuristic.
     */
    public AStarSearch(Heuristic heuristic) {
        this.graph = new HashMap<>();
        this.heuristic = heuristic;
    }

    /**
     * Adds an undirected edge between nodes u and v with the given weight.
     */
    public void addEdge(int u, int v, int weight) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight});
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

        openSet.add(new Node(start, 0, heuristic.estimate(start, goal), null));
        gScore.put(start, 0.0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.id == goal) return reconstructPath(current);

            closedSet.add(current.id);

            for (int[] edge : graph.getOrDefault(current.id, new ArrayList<>())) {
                int neighbor = edge[0];
                double tentativeG = current.g + edge[1];

                if (closedSet.contains(neighbor)) continue;

                if (tentativeG < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    gScore.put(neighbor, tentativeG);
                    Node next = new Node(neighbor, tentativeG, heuristic.estimate(neighbor, goal), current);
                    openSet.add(next);
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * Reconstructs path by following parent nodes.
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
