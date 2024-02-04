package com.thealgorithms.datastructures.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.IntStream;

/*
 *  The Welsh-Powell algorithm is a graph coloring algorithm
 *  used for coloring a graph with the minimum number of colors.
 *  https://en.wikipedia.org/wiki/Graph_coloring
 */

public class WelshPowell {

    public static class WPGraph {
        private final int numVer; // Number of vertices in the graph
        private HashSet<Integer>[] adjLists; // Adjacency lists for the graph using HashSet

        public WPGraph(int vertices) {
            numVer = vertices;
            adjLists = new HashSet[vertices];
            Arrays.setAll(adjLists, i -> new HashSet<>());
        }

        public void addEdge(int nodeA, int nodeB) {
            assert (0 <= nodeA && nodeA < numVer);
            assert (0 <= nodeB && nodeB < numVer);
            adjLists[nodeA].add(nodeB);
            adjLists[nodeB].add(nodeA);
        }

        public HashSet<Integer> getAdjList(int vertex) {
            return adjLists[vertex];
        }

        public int getNumVertices() {
            return numVer;
        }
    }

    public static int[] welshPowellColoring(WPGraph graph) {
        int numVer = graph.getNumVertices();
        int[] colors = initializeColors(numVer); // Use helper method to initialize color array
        Integer[] sortedVertices = getSortedNodes(graph, numVer); // Use helper method to get sorted vertices

        for (int vertex : sortedVertices) {
            if (colors[vertex] != -1) {
                continue;
            }
            boolean[] usedColors = computeUsedColors(graph, vertex, colors, numVer); // Use helper method to compute used colors
            colors[vertex] = firstUnusedColor(usedColors, numVer); // Use helper method to find first unused color
        }

        return colors;
    }

    private static int[] initializeColors(int numVer) {
        int[] colors = new int[numVer];
        Arrays.fill(colors, -1);
        return colors;
    }

    private static Integer[] getSortedNodes(WPGraph graph, int numVer) {
        return IntStream.range(0, numVer).boxed().sorted(Comparator.comparingInt(v -> - graph.getAdjList(v).size())).toArray(Integer[] ::new);
    }

    private static boolean[] computeUsedColors(WPGraph graph, int vertex, int[] colors, int numVer) {
        boolean[] usedColors = new boolean[numVer];
        graph.getAdjList(vertex).stream().map(neighbor -> colors[neighbor]).filter(color -> color != -1).forEach(color -> usedColors[color] = true);
        return usedColors;
    }

    private static int firstUnusedColor(boolean[] usedColors, int numVer) {
        return IntStream.range(0, numVer).filter(color -> !usedColors[color]).findFirst().getAsInt();
    }

    // Public static methods for testing purpose
    public static WPGraph createGraph(int vertices) {
        return new WPGraph(vertices);
    }

    public static void addEdge(WPGraph graph, int nodeA, int nodeB) {
        graph.addEdge(nodeA, nodeB);
    }
}
