package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public final class WelshPowell {
    private static final int BLANK_COLOR = -1; // Constant representing an uncolored state

    private WelshPowell() {
    }

    /**
     * Represents a graph using an adjacency list.
     */
    static final class Graph {
        private final List<Set<Integer>> adjacencyLists;

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

            adjacencyLists = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjacencyLists.add(new HashSet<>());
            }
        }

        /**
         * Adds an edge between two vertices in the graph.
         *
         * @param vertexA one end of the edge
         * @param vertexB the other end of the edge
         * @throws IllegalArgumentException if the vertices are out of bounds or if a self-loop is attempted
         */
        private void addEdge(int vertexA, int vertexB) {
            validateVertex(vertexA);
            validateVertex(vertexB);
            if (vertexA == vertexB) {
                throw new IllegalArgumentException("Self-loops are not allowed");
            }
            adjacencyLists.get(vertexA).add(vertexB);
            adjacencyLists.get(vertexB).add(vertexA);
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
         * Returns the adjacency list for a specific vertex. The returned set is a read-only
         * view: callers cannot mutate the graph's internal structure through it.
         *
         * @param vertex the index of the vertex
         * @return the set of adjacent vertices
         */
        Set<Integer> getAdjacencyList(int vertex) {
            return Collections.unmodifiableSet(adjacencyLists.get(vertex));
        }

        /**
         * Returns the number of vertices in the graph.
         *
         * @return the number of vertices
         */
        int getNumVertices() {
            return adjacencyLists.size();
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
        return new Coloring(graph).run();
    }

    /**
     * Bundles the graph being colored together with its in-progress color assignment.
     * These two values were previously passed as a pair to nearly every helper method
     * (a data clump); packaging them here lets each step read as an instance method
     * with implicit access to shared state, instead of threading both through every call.
     */
    private static final class Coloring {
        private final Graph graph;
        private final int[] colors;

        private Coloring(Graph graph) {
            this.graph = graph;
            this.colors = new int[graph.getNumVertices()];
            Arrays.fill(colors, BLANK_COLOR);
        }

        private int[] run() {
            Integer[] sortedVertices = getSortedVertices();
            for (int vertex : sortedVertices) {
                if (isBlank(colors[vertex])) {
                    boolean[] usedColors = computeUsedColors(vertex);
                    int newColor = firstUnusedColor(usedColors);
                    colors[vertex] = newColor;
                    for (int otherVertex : sortedVertices) {
                        if (isBlank(colors[otherVertex]) && !isAdjacentToColored(otherVertex)) {
                            colors[otherVertex] = newColor;
                        }
                    }
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
        private boolean isBlank(int color) {
            return color == BLANK_COLOR;
        }

        /**
         * Checks if a vertex has adjacent colored vertices
         *
         * @param vertex the vertex to check
         * @return {@code true} if the vertex has adjacent colored vertices, {@code false} otherwise
         */
        private boolean isAdjacentToColored(int vertex) {
            return graph.getAdjacencyList(vertex).stream().anyMatch(neighbor -> !isBlank(colors[neighbor]));
        }

        /**
         * Sorts the vertices by their degree in descending order
         *
         * @return an array of integers representing the vertices sorted by degree
         */
        private Integer[] getSortedVertices() {
            return IntStream.range(0, graph.getNumVertices())
                    .boxed()
                    .sorted(Comparator.<Integer>comparingInt(v -> graph.getAdjacencyList(v).size()).reversed())
                    .toArray(Integer[] ::new);
        }

        /**
         * Computes the colors already used by the adjacent vertices
         *
         * @param vertex the vertex to check
         * @return an array of booleans representing the colors used by the adjacent vertices
         */
        private boolean[] computeUsedColors(int vertex) {
            boolean[] usedColors = new boolean[graph.getNumVertices()];
            graph.getAdjacencyList(vertex).stream().map(neighbor -> colors[neighbor]).filter(color -> !isBlank(color)).forEach(color -> usedColors[color] = true);
            return usedColors;
        }

        /**
         * Finds the first unused color.
         *
         * @param usedColors the array of colors used by the adjacent vertices
         * @return the first unused color
         */
        private int firstUnusedColor(boolean[] usedColors) {
            // Always present: a vertex has at most (numVertices - 1) neighbors, so at least
            // one color in a numVertices-sized palette is guaranteed to remain unused.
            return IntStream.range(0, usedColors.length).filter(color -> !usedColors[color]).findFirst().getAsInt();
        }
    }
}