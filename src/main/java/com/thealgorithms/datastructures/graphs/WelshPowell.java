package com.thealgorithms.datastructures.graphs;

import java.util.HashSet;
import java.util.LinkedList;

/*
 *  The Welsh-Powell algorithm is a graph coloring algorithm
 *  used for coloring a graph with the minimum number of colors.
 *  https://en.wikipedia.org/wiki/Graph_coloring
 */

public class WelshPowell {

    public static class WPGraph {
        private int numVer; // Number of vertices in the graph
        private HashSet<Integer>[] adjLists; // Adjacency lists for the graph using HashSet

        public WPGraph(int vertices) {
            numVer = vertices;
            adjLists = new HashSet[vertices];
            for (int i = 0; i < vertices; i++) {
                adjLists[i] = new HashSet<>();
            }
        }

        public void addEdge(int nodeA, int nodeB) {
            adjLists[nodeA].add(nodeB);
            adjLists[nodeB].add(nodeA);
        }

        public HashSet<Integer> getAdjList(int vertex) {
            return adjLists[vertex];
        }

        public int getNumVertices() {
            return numVer;
        }

        // Helper method to check if an edge exists (used for testing)
        boolean isEdgePresent(int v1, int v2) {
            return adjLists[v1].contains(v2);
        }
    }

    public static int[] welshPowellColoring(WPGraph graph) {
        int numVer = graph.getNumVertices();
        int[] colors = new int[numVer];
        for (int i = 0; i < numVer; i++) {
            colors[i] = -1; // Initialize all vertices as uncolored
        }

        LinkedList<Integer> sortedVertices = new LinkedList<>();
        for (int i = 0; i < numVer; i++) {
            sortedVertices.add(i);
        }
        sortedVertices.sort((v1, v2) -> graph.getAdjList(v2).size() - graph.getAdjList(v1).size());

        for (int vertex : sortedVertices) {
            if (colors[vertex] == -1) {
                boolean[] usedColors = new boolean[numVer]; // Track used colors
                for (int neighbor : graph.getAdjList(vertex)) {
                    if (colors[neighbor] != -1) {
                        usedColors[colors[neighbor]] = true; // Mark neighbor's color as used
                    }
                }

                // Assign the first unused color
                for (int color = 0; color < numVer; color++) {
                    if (!usedColors[color]) {
                        colors[vertex] = color;
                        break;
                    }
                }
            }
        }

        return colors; // Return the array of colors for each vertex
    }

    // Public static method to get the adjacency list of a vertex
    public static HashSet<Integer> getAdjList(WPGraph graph, int vertex) {
        return graph.getAdjList(vertex);
    }

    // Public static methods for testing purpose
    public static WPGraph createGraph(int vertices) {
        return new WPGraph(vertices);
    }

    public static void addEdge(WPGraph graph, int nodeA, int nodeB) {
        graph.addEdge(nodeA, nodeB);
    }

    public static boolean isEdgePresent(WPGraph graph, int v1, int v2) {
        return graph.isEdgePresent(v1, v2);
    }

    public static int getNumVertices(WPGraph graph) {
        return graph.getNumVertices();
    }
}
