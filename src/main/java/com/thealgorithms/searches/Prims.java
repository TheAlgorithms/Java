package com.thealgorithms.graphs;

import java.util.*;

/**
 * Implementation of Prim's Algorithm for finding the Minimum Spanning Tree (MST)
 * of a connected, undirected, weighted graph.
 *
 * Prim’s algorithm builds the MST by always choosing the minimum weight edge 
 * that connects a vertex in the MST to a vertex outside it.
 *
 * Time Complexity: O(E log V)
 * Space Complexity: O(V)
 *
 * https://en.wikipedia.org/wiki/Prim%27s_algorithm
 *
 * Example:
 *   Graph:
 *     A - B (4)
 *     A - C (3)
 *     B - C (1)
 *     B - D (2)
 *     C - D (4)
 *
 *   MST edges:
 *     B - C (1)
 *     B - D (2)
 *     A - C (3)
 *
 * @author OpenAI
 */
public final class Prims {

    private Prims() {
    }

    /**
     * Represents an edge in the graph.
     */
    private static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Graph represented as adjacency list.
     */
    public static class Graph {
        private final Map<String, List<Edge>> adj = new LinkedHashMap<>();

        /**
         * Adds an undirected weighted edge between u and v.
         */
        public void addEdge(String u, String v, int weight) {
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, weight));
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, weight));
        }
    }

    /**
     * Runs Prim’s Algorithm to compute the MST.
     *
     * @param graph  the input graph
     * @param start  the starting vertex
     * @return list of edges representing the MST
     */
    public static List<String> primsMST(Graph graph, String start) {
        List<String> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        visited.add(start);
        pq.addAll(graph.adj.get(start));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited.contains(edge.to)) continue;

            visited.add(edge.to);
            mstEdges.add(edge.to);

            for (Edge next : graph.adj.get(edge.to)) {
                if (!visited.contains(next.to)) {
                    pq.offer(next);
                }
            }
        }

        return mstEdges;
    }

    /**
     * Example usage.
     */
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 4);

        List<String> mst = primsMST(graph, "A");
        System.out.println("MST vertices (order added): " + mst);
    }
}
