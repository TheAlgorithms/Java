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

    static class WPGraph {
        private int numVer; // Number of vertices in the graph
        private HashSet<Integer>[] adjLists; // Adjacency lists for the graph

        private WPGraph(int vertices) {
            numVer = vertices;
            adjLists = new HashSet[vertices];
            Arrays.setAll(adjLists, i -> new HashSet<>());
        }

        private void addEdge(int nodeA, int nodeB) {
            adjLists[nodeA].add(nodeB);
            adjLists[nodeB].add(nodeA);
        }

        HashSet<Integer> getAdjList(int vertex) {
            return adjLists[vertex];
        }

        int getNumVertices() {
            return numVer;
        }
    }

    public static WPGraph makeGraph(int numberOfVertices, int[][] listOfEdges) {
        WPGraph graph = new WPGraph(numberOfVertices);
        for (int[] edge : listOfEdges) {
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

    public static int[] welshPowellColoring(WPGraph graph) {
        int[] colors = initializeColors(graph.getNumVertices());
        Integer[] sortedVertices = getSortedNodes(graph);
        for (int vertex : sortedVertices) {
            if (colors[vertex] == -1) {
                boolean[] usedColors = computeUsedColors(graph, vertex, colors);
                colors[vertex] = firstUnusedColor(usedColors);
            }
        }
        return colors;
    }

    private static int[] initializeColors(int numVer) {
        int[] colors = new int[numVer];
        Arrays.fill(colors, -1);
        return colors;
    }

    private static Integer[] getSortedNodes(WPGraph graph) {
        return IntStream.range(0, graph.getNumVertices()).boxed().sorted(Comparator.comparingInt(v -> - graph.getAdjList(v).size())).toArray(Integer[] ::new);
    }

    private static boolean[] computeUsedColors(WPGraph graph, int vertex, int[] colors) {
        boolean[] usedColors = new boolean[graph.getNumVertices()];
        graph.getAdjList(vertex).stream().map(neighbor -> colors[neighbor]).filter(color -> color != -1).forEach(color -> usedColors[color] = true);
        return usedColors;
    }

    private static int firstUnusedColor(boolean[] usedColors) {
        return IntStream.range(0, usedColors.length).filter(color -> !usedColors[color]).findFirst().getAsInt();
    }

    public static void addEdge(WPGraph graph, int nodeA, int nodeB) {
        graph.addEdge(nodeA, nodeB);
    }
}
