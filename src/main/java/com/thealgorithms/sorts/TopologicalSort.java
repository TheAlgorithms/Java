package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * The Topological Sorting algorithm linearly orders a DAG or Directed Acyclic Graph into
 * a linked list. A Directed Graph is proven to be acyclic when a DFS or Depth First Search is
 * performed, yielding no back-edges.
 *
 * https://en.wikipedia.org/wiki/Topological_sorting
 *
 * @author Jonathan Taylor (https://github.com/Jtmonument)
 * Based on Introduction to Algorithms 3rd Edition
 */
public final class TopologicalSort {
    private TopologicalSort() {
    }

    /*
     * Enum to represent the colors for the depth first search
     * */
    private enum Color {
        WHITE,
        GRAY,
        BLACK,
    }

    /*
     * Class to represent vertices
     * */
    private static class Vertex {

        /*
         * Name of vertex
         * */
        public final String label;

        /*
         * Represents the category of visit in DFS
         * */
        public Color color = Color.WHITE;

        /*
         * The array of names of descendant vertices
         * */
        public final ArrayList<String> next = new ArrayList<>();

        Vertex(String label) {
            this.label = label;
        }
    }

    /*
     * Graph class uses the adjacency list representation
     * */
    static class Graph {

        /*
         * Adjacency list representation
         * */
        private final HashMap<String, Vertex> adj = new LinkedHashMap<>();

        /*
         * Function to add an edge to the graph
         * */
        public void addEdge(String label, String... next) {
            adj.put(label, new Vertex(label));
            if (!next[0].isEmpty()) {
                Collections.addAll(adj.get(label).next, next);
            }
        }
    }

    /*
     * Depth First Search
     *
     * DFS(G)
     *   for each vertex u ∈ G.V
     *       u.color = WHITE
     *       u.π = NIL
     *   time = 0
     *   for each vertex u ∈ G.V
     *   if u.color == WHITE
     *       DFS-VISIT(G, u)
     *
     * Performed in Θ(V + E) time
     * */
    public static LinkedList<String> sort(Graph graph) {
        LinkedList<String> list = new LinkedList<>();
        graph.adj.forEach((name, vertex) -> {
            if (vertex.color == Color.WHITE) {
                list.addFirst(sort(graph, vertex, list));
            }
        });
        return list;
    }

    /*
     * Depth First Search Visit
     *
     * DFS-Visit(G, u)
     *   time = time + 1
     *   u.d = time
     *   u.color = GRAY
     *   for each v ∈ G.Adj[u]
     *       if v.color == WHITE
     *           v.π = u
     *           DFS-Visit(G, u)
     *   u.color = BLACK
     *   time = time + 1
     *   u.f = time
     * */
    private static String sort(Graph graph, Vertex u, LinkedList<String> list) {
        u.color = Color.GRAY;
        graph.adj.get(u.label).next.forEach(label -> {
            if (graph.adj.get(label).color == Color.WHITE) {
                list.addFirst(sort(graph, graph.adj.get(label), list));
            } else if (graph.adj.get(label).color == Color.GRAY) {
                /*
                 * A back edge exists if an edge (u, v) connects a vertex u to its ancestor vertex v
                 * in a depth first tree. If v.d ≤ u.d < u.f ≤ v.f
                 *
                 * In many cases, we will not know u.f, but v.color denotes the type of edge
                 * */
                throw new RuntimeException("This graph contains a cycle. No linear ordering is possible. Back edge: " + u.label + " -> " + label);
            }
        });
        u.color = Color.BLACK;
        return u.label;
    }
}
