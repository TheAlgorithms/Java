package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * The Topological Sorting algorithm linearly orders a DAG or Directed Acyclic Graph into
 * a linked list. A Directed Graph is proven to be acyclic when a DFS or Depth First Search is
 * performed, yielding no back-edges.
 *
 * @author Jonathan Taylor (https://github.com/Jtmonument)
 * Based on Introduction to Algorithms 3rd Edition
 */
public class TopologicalSort {

     /*
     * The linked list that will contain the vertices in linear order.
     * */
    private static final LinkedList<Vertex> list = new LinkedList<>();

     /*
     * Enum to represent the colors for the depth first search
     * */
    private enum Color {
        WHITE, GRAY, BLACK
    }

     /*
     * Class to represent vertices
     * */
    static class Vertex {
        public final String label;  // name of vertex
        public int weight;  // weight of vertex (more accurately defined as the start time in DFS)
        public int finished;    // time that the vertex has finished a visit in DFS
        public Vertex predecessor;  // parent of the vertex
        public Color color = Color.WHITE;   // Represents the category of visit in DFS
        public final ArrayList<String> next = new ArrayList<>();    // array of names of descendant vertices

        public Vertex(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            StringBuilder edges = new StringBuilder();
            edges.append(predecessor == null ? "NONE" : predecessor.label).append("\n\tEdges:");
            next.forEach(vertex -> edges.append("\n\t\t").append(label).append(" -> ").append(vertex));
            return "Vertex " + label +
                    ":\n\tWeight: " + weight +
                    "\n\tFinished: " + finished +
                    "\n\tColor: " + color +
                    "\n\tPredecessor: " +
                    edges;
        }

    }

     /*
     * Graph class uses the adjacency list representation
     * */
    static class Graph {

        private final HashMap<String, Vertex> adj = new HashMap<>();    // adjacency list representation
        private int time;   //time variable in DFS
        private boolean isAcyclic;  // Checks if the graph is acyclic

        /*
        * Print the sorted linear order of vertices
        * */
        public void print() {
            list.forEach(System.out::println);
            if (isAcyclic)
                System.err.println("This graph contains a cycle. No linear ordering is possible.");
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
        public void sort() {
            adj.forEach((name, vertex) -> {
                if (vertex.color == Color.WHITE)
                    sort(vertex);
            });
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
        private void sort(Vertex u) {
            time++;
            u.weight = time;
            u.color = Color.GRAY;
            adj.get(u.label).next.forEach(label -> {
                if (adj.get(label).color == Color.WHITE) {
                    adj.get(label).predecessor = u;
                    sort(adj.get(label));
                } else if (adj.get(label).weight <= u.weight &&
                        u.weight < u.finished &&
                        u.finished <= adj.get(label).finished) {
                    System.out.printf("Back edge: %s -> %s\n", u.label, label);
                    isAcyclic = true;
                    /*
                    * A back edge exists if an edge (u, v) connects a vertex u to its ancestor vertex v
                    * in a depth first tree. If and only if v.d ≤ u.d < u.f ≤ v.f
                    * */
                }
            });
            u.color = Color.BLACK;
            time++;
            u.finished = time;
            list.addFirst(u);
        }

        /*
        * Function to add an edge to the graph
        * */
        public void addEdge(String label, String... next) {
            adj.put(label, new Vertex(label));
            if (!next[0].isEmpty())
                Collections.addAll(adj.get(label).next, next);
        }
    }

    /*
     * Professor Bumstead example DAG. Each directed edge means that garment u must be put on
     * before garment v.
     * */
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("undershorts", "pants", "shoes");
        graph.addEdge("pants", "belt", "shoes");
        graph.addEdge("belt", "jacket");
        graph.addEdge("jacket","");
        graph.addEdge("tie", "jacket");
        graph.addEdge("shirt", "belt", "tie");
        graph.addEdge("shoes", "");
        graph.addEdge("socks", "shoes");
        graph.addEdge("watch", "");
        graph.sort();
        graph.print();
    }
}

