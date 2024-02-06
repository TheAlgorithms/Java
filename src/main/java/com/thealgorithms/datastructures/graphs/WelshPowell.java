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

public final class WelshPowell {
    private WelshPowell() {
    }

    static class Graph {
        private HashSet<Integer>[] adjacencyLists;

        private Graph(int vertices) {
            if (vertices < 0) {
                throw new IllegalArgumentException("Number of vertices cannot be negative");
            }

            adjacencyLists = new HashSet[vertices];
            Arrays.setAll(adjacencyLists, i -> new HashSet<>());
        }

        private void addEdge(int nodeA, int nodeB) {
            validateVertex(nodeA);
            validateVertex(nodeB);
            if (nodeA == nodeB) {
                throw new IllegalArgumentException("Self-loops are not allowed");
            }
            adjacencyLists[nodeA].add(nodeB);
            adjacencyLists[nodeB].add(nodeA);
        }

        private void validateVertex(int vertex) {
            if (vertex < 0 || vertex >= getNumVertices()) {
                throw new IllegalArgumentException("Vertex " + vertex + " is out of bounds");
            }
        }

        HashSet<Integer> getAdjList(int vertex) {
            return adjacencyLists[vertex];
        }

        int getNumVertices() {
            return adjacencyLists.length;
        }
    }

    public static Graph makeGraph(int numberOfVertices, int[][] listOfEdges) {
        Graph graph = new Graph(numberOfVertices);
        for (int[] edge : listOfEdges) {
            if (edge.length != 2) {
                throw new IllegalArgumentException("Edge array must have exactly two elements");
            }
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

    public static int[] findColoring(Graph graph, int preColoredVertex, int preColor) {
        int numVertices = graph.getNumVertices();
        int[] colors = initializeColors(numVertices);

        if (preColoredVertex >= 0 && preColoredVertex < numVertices && preColor >= 0) {
            colors[preColoredVertex] = preColor;
        }

        Integer[] sortedVertices = getSortedNodes(graph);

        for (int vertex : sortedVertices) {
            if (colors[vertex] != -1) {
                continue; // Skip if already colored (including pre-colored)
            }

            // Determine used colors among adjacent vertices
            boolean[] usedColors = computeUsedColors(graph, vertex, colors);

            colors[vertex] = firstUnusedColor(usedColors);
        }

        // Ensure every vertex is colored
        for (int i = 0; i < numVertices; i++) {
            if (colors[i] == -1) {
                colors[i] = firstUnusedColor(new boolean[numVertices]);
            }
        }

        return colors;
    }

    // Overloaded method for normal use without pre-coloring
    public static int[] findColoring(Graph graph) {
        return findColoring(graph, -1, -1);
    }

    private static int[] initializeColors(int numberOfVerticies) {
        int[] colors = new int[numberOfVerticies];
        Arrays.fill(colors, -1);
        return colors;
    }

    private static Integer[] getSortedNodes(final Graph graph) {
        return IntStream.range(0, graph.getNumVertices()).boxed().sorted(Comparator.comparingInt(v -> - graph.getAdjList(v).size())).toArray(Integer[] ::new);
    }

    private static boolean[] computeUsedColors(final Graph graph, final int vertex, final int[] colors) {
        boolean[] usedColors = new boolean[graph.getNumVertices()];
        graph.getAdjList(vertex).stream().map(neighbor -> colors[neighbor]).filter(color -> color != -1).forEach(color -> usedColors[color] = true);
        return usedColors;
    }

    private static int firstUnusedColor(boolean[] usedColors) {
        return IntStream.range(0, usedColors.length).filter(color -> !usedColors[color]).findFirst().getAsInt();
    }
}
