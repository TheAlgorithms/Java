package com.thealgorithms.datastructures.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * The Welsh-Powell algorithm is a graph coloring algorithm that aims to color a graph
 * using the minimum number of colors such that no two adjacent vertices share the same color.
 *
 * <p>
 * The algorithm works by:
 * <ol>
 * <li>Sorting the vertices in descending order based on their degrees (number of edges connected).</li>
 * <li>Iterating through each vertex and assigning it the smallest available color that has not been used by its adjacent vertices.</li>
 * <li>Coloring adjacent vertices with the same color is avoided.</li>
 * </ol>
 * </p>
 *
 * <p>
 * For more information, see <a href="https://en.wikipedia.org/wiki/Graph_coloring">Graph Coloring</a>.
 * </p>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class WelshPowell {
    private static final int BLANK_COLOR = -1; // Constant representing an uncolored state

    private WelshPowell() {
    }

    /**
     * Represents a graph using an adjacency list.
     */
    static final class Graph {
        private final HashSet<Integer>[] adjacencyLists;

        /**
         * Initializes a graph with a specified number of vertices.
         *
         * @param vertices the number of vertices in the graph
         * @throws IllegalArgumentException if the number of vertices is negative
         */
        private Graph(int vertices) {
            if (vertices < 0) {
                throw new IllegalArgumentException("Number of vertices cannot be negative");
            }

            adjacencyLists = new HashSet[vertices];
            Arrays.setAll(adjacencyLists, i -> new HashSet<>());
        }

        /**
         * Adds an edge between two vertices in the graph.
         *
         * @param nodeA one end of the edge
         * @param nodeB the other end of the edge
         * @throws IllegalArgumentException if the vertices are out of bounds or if a self-loop is attempted
         */
        private void addEdge(int nodeA, int nodeB) {
            validateVertex(nodeA);
            validateVertex(nodeB);
            if (nodeA == nodeB) {
                throw new IllegalArgumentException("Self-loops are not allowed");
            }
            adjacencyLists[nodeA].add(nodeB);
            adjacencyLists[nodeB].add(nodeA);
        }

        /**
         * Validates that the vertex index is within the bounds of the graph.
         *
         * @param vertex the index of the vertex to validate
         * @throws IllegalArgumentException if the vertex is out of bounds
         */
        private void validateVertex(int vertex) {
            if (vertex < 0 || vertex >= getNumVertices()) {
                throw new IllegalArgumentException("Vertex " + vertex + " is out of bounds");
            }
        }

        /**
         * Returns the adjacency list for a specific vertex.
         *
         * @param vertex the index of the vertex
         * @return the set of adjacent vertices
         */
        HashSet<Integer> getAdjacencyList(int vertex) {
            return adjacencyLists[vertex];
        }

        /**
         * Returns the number of vertices in the graph.
         *
         * @return the number of vertices
         */
        int getNumVertices() {
            return adjacencyLists.length;
        }
    }

    /**
     * Creates a graph with the specified number of vertices and edges.
     *
     * @param numberOfVertices the total number of vertices
     * @param listOfEdges a 2D array representing edges where each inner array contains two vertex indices
     * @return a Graph object representing the created graph
     * @throws IllegalArgumentException if the edge array is invalid or vertices are out of bounds
     */
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

    /**
     * Finds the coloring of the given graph using the Welsh-Powell algorithm.
     *
     * @param graph the input graph to color
     * @return an array of integers where each index represents a vertex and the value represents the color assigned
     */
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

    /**
     * Helper method to check if a color is unassigned
     *
     * @param color the color to check
     * @return {@code true} if the color is unassigned, {@code false} otherwise
     */
    private static boolean isBlank(int color) {
        return color == BLANK_COLOR;
    }

    /**
     * Checks if a vertex has adjacent colored vertices
     *
     * @param graph the input graph
     * @param vertex the vertex to check
     * @param colors the array of colors assigned to the vertices
     * @return {@code true} if the vertex has adjacent colored vertices, {@code false} otherwise
     */
    private static boolean isAdjacentToColored(Graph graph, int vertex, int[] colors) {
        return graph.getAdjacencyList(vertex).stream().anyMatch(otherVertex -> !isBlank(colors[otherVertex]));
    }

    /**
     * Initializes the colors array with blank color
     *
     * @param numberOfVertices the number of vertices in the graph
     * @return an array of integers representing the colors assigned to the vertices
     */
    private static int[] initializeColors(int numberOfVertices) {
        int[] colors = new int[numberOfVertices];
        Arrays.fill(colors, BLANK_COLOR);
        return colors;
    }

    /**
     * Sorts the vertices by their degree in descending order
     *
     * @param graph the input graph
     * @return an array of integers representing the vertices sorted by degree
     */
    private static Integer[] getSortedNodes(final Graph graph) {
        return IntStream.range(0, graph.getNumVertices()).boxed().sorted(Comparator.comparingInt(v -> - graph.getAdjacencyList(v).size())).toArray(Integer[] ::new);
    }

    /**
     * Computes the colors already used by the adjacent vertices
     *
     * @param graph the input graph
     * @param vertex the vertex to check
     * @param colors the array of colors assigned to the vertices
     * @return an array of booleans representing the colors used by the adjacent vertices
     */
    private static boolean[] computeUsedColors(final Graph graph, final int vertex, final int[] colors) {
        boolean[] usedColors = new boolean[graph.getNumVertices()];
        graph.getAdjacencyList(vertex).stream().map(neighbor -> colors[neighbor]).filter(color -> !isBlank(color)).forEach(color -> usedColors[color] = true);
        return usedColors;
    }

    /**
     * Finds the first unused color
     *
     * @param usedColors the array of colors used by the adjacent vertices
     * @return the first unused color
     */
    private static int firstUnusedColor(boolean[] usedColors) {
        return IntStream.range(0, usedColors.length).filter(color -> !usedColors[color]).findFirst().getAsInt();
    }
}
