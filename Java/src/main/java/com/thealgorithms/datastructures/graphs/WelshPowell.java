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
    private static final int BLANK_COLOR = -1; // Representing uncolored state

    private WelshPowell() {
    }

    static final class Graph {
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

        HashSet<Integer> getAdjacencyList(int vertex) {
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

    public static int[] findColoring(Graph graph) {
        int[] colors = initializeColors(graph.getNumVertices());
        Integer[] sortedVertices = getSortedNodes(graph);
        for (int vertex : sortedVertices) {
            if (isBlank(colors[vertex])) {
                boolean[] usedColors = computeUsedColors(graph, vertex, colors);
                final var newColor = firstUnusedColor(usedColors);
                colors[vertex] = newColor;
                Arrays.stream(sortedVertices).forEach(otherVertex -> {
                    if (isBlank(colors[otherVertex]) && !isAdjacentToColored(graph, otherVertex, colors)) {
                        colors[otherVertex] = newColor;
                    }
                });
            }
        }
        return colors;
    }

    private static boolean isBlank(int color) {
        return color == BLANK_COLOR;
    }

    private static boolean isAdjacentToColored(Graph graph, int vertex, int[] colors) {
        return graph.getAdjacencyList(vertex).stream().anyMatch(otherVertex -> !isBlank(colors[otherVertex]));
    }

    private static int[] initializeColors(int numberOfVertices) {
        int[] colors = new int[numberOfVertices];
        Arrays.fill(colors, BLANK_COLOR);
        return colors;
    }

    private static Integer[] getSortedNodes(final Graph graph) {
        return IntStream.range(0, graph.getNumVertices()).boxed().sorted(Comparator.comparingInt(v -> - graph.getAdjacencyList(v).size())).toArray(Integer[] ::new);
    }

    private static boolean[] computeUsedColors(final Graph graph, final int vertex, final int[] colors) {
        boolean[] usedColors = new boolean[graph.getNumVertices()];
        graph.getAdjacencyList(vertex).stream().map(neighbor -> colors[neighbor]).filter(color -> !isBlank(color)).forEach(color -> usedColors[color] = true);
        return usedColors;
    }

    private static int firstUnusedColor(boolean[] usedColors) {
        return IntStream.range(0, usedColors.length).filter(color -> !usedColors[color]).findFirst().getAsInt();
    }
}
