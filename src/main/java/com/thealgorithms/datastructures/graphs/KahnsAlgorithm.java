package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * A class that represents the adjaceny list of a graph
 */
class AdjacencyList<E extends Comparable<E>> {

    Map<E, ArrayList<E>> adj;

    AdjacencyList() {
        adj = new LinkedHashMap<E, ArrayList<E>>();
    }

    /**
     * This function adds an Edge to the adjaceny list
     *
     * @param from , the vertex the edge is from
     * @param to, the vertex the edge is going to
     */
    void addEdge(E from, E to) {
        try {
            adj.get(from).add(to);
        } catch (Exception E) {
            adj.put(from, new ArrayList<E>());
            adj.get(from).add(to);
        }
        if (!adj.containsKey(to)) {
            adj.put(to, new ArrayList<E>());
        }
    }

    /**
     * @param v, A vertex in a graph
     * @return returns an ArrayList of all the adjacents of vertex v
     */
    ArrayList<E> getAdjacents(E v) {
        return adj.get(v);
    }

    /**
     * @return returns a set of all vertices in the graph
     */
    Set<E> getVertices() {
        return adj.keySet();
    }
}

class TopologicalSort<E extends Comparable<E>> {

    AdjacencyList<E> graph;
    Map<E, Integer> inDegree;

    TopologicalSort(AdjacencyList<E> graph) {
        this.graph = graph;
    }

    /**
     * Calculates the in degree of all vertices
     */
    void calculateInDegree() {
        inDegree = new HashMap<>();
        for (E vertex : graph.getVertices()) {
            if (!inDegree.containsKey(vertex)) {
                inDegree.put(vertex, 0);
            }
            for (E adjacent : graph.getAdjacents(vertex)) {
                try {
                    inDegree.put(adjacent, inDegree.get(adjacent) + 1);
                } catch (Exception e) {
                    inDegree.put(adjacent, 1);
                }
            }
        }
    }

    /**
     * Returns an ArrayList with vertices arranged in topological order
     */
    ArrayList<E> topSortOrder() {
        calculateInDegree();
        Queue<E> q = new LinkedList<E>();

        for (final var entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }

        ArrayList<E> answer = new ArrayList<>();

        while (!q.isEmpty()) {
            E current = q.poll();
            answer.add(current);
            for (E adjacent : graph.getAdjacents(current)) {
                inDegree.put(adjacent, inDegree.get(adjacent) - 1);
                if (inDegree.get(adjacent) == 0) {
                    q.add(adjacent);
                }
            }
        }

        return answer;
    }
}

/**
 * A driver class that sorts a given graph in topological order.
 */
public final class KahnsAlgorithm {
    private KahnsAlgorithm() {
    }

    public static void main(String[] args) {
        // Graph definition and initialization
        AdjacencyList<String> graph = new AdjacencyList<>();
        graph.addEdge("a", "b");
        graph.addEdge("c", "a");
        graph.addEdge("a", "d");
        graph.addEdge("b", "d");
        graph.addEdge("c", "u");
        graph.addEdge("u", "b");

        TopologicalSort<String> topSort = new TopologicalSort<>(graph);

        // Printing the order
        for (String s : topSort.topSortOrder()) {
            System.out.print(s + " ");
        }
    }
}
