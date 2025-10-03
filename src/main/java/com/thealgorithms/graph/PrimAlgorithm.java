package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of Prim's algorithm for computing Minimum Spanning Tree (MST).
 * This class provides methods to find the MST of a weighted undirected graph
 * using a priority queue based approach for optimal performance.
 *
 * <p>Time Complexity: O(E log V) where E is edges and V is vertices
 * <p>Space Complexity: O(V + E) for storing graph and auxiliary structures
 *
 */
public final class PrimAlgorithm {

    public PrimAlgorithm(List<List<Edge>> adjacencyList, int vertexCount) {
        this.adjacencyList = adjacencyList;
        this.vertexCount = vertexCount;
    }

    /**
     * Represents a weighted edge in the graph structure.
     * This inner class encapsulates edge information including destination
     * vertex and associated weight for MST computation.
     */
    private static final class Edge implements Comparable<Edge> {
        private final int destination;
        private final int weight;

        /**
         * Constructs an edge with specified destination and weight.
         *
         * @param destination the target vertex of this edge
         * @param weight the cost associated with traversing this edge
         */
        Edge(final int destination, final int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * Retrieves the destination vertex of this edge.
         *
         * @return the destination vertex identifier
         */
        public int getDestination() {
            return destination;
        }

        /**
         * Retrieves the weight of this edge.
         *
         * @return the edge weight value
         */
        public int getWeight() {
            return weight;
        }

        /**
         * Compares edges based on weight for priority queue ordering.
         *
         * @param other the edge to compare against
         * @return negative if this edge weighs less, positive if more, zero if equal
         */
        @Override
        public int compareTo(final Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * Stores the result of MST computation including total cost and edge list.
     */
    public static final class MstResult {
        private final int totalWeight;
        private final List<int[]> edges;

        /**
         * Constructs an MST result object.
         *
         * @param totalWeight the sum of all edge weights in the MST
         * @param edges list of edges where each edge is [source, dest, weight]
         */
        public MstResult(final int totalWeight, final List<int[]> edges) {
            this.totalWeight = totalWeight;
            this.edges = new ArrayList<>(edges);
        }

        /**
         * Returns the total weight of the MST.
         *
         * @return sum of all edge weights in the minimum spanning tree
         */
        public int getTotalWeight() {
            return totalWeight;
        }

        /**
         * Returns the list of edges in the MST.
         *
         * @return unmodifiable list of edges, each represented as [source, dest, weight]
         */
        public List<int[]> getEdges() {
            return new ArrayList<>(edges);
        }
    }

    private final List<List<Edge>> adjacencyList;
    private final int vertexCount;

    /**
     * Constructs a graph representation for Prim's algorithm.
     *
     * @param vertexCount the total number of vertices in the graph
     * @throws IllegalArgumentException if vertexCount is negative
     */
    public PrimAlgorithm(final int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Vertex count must be non-negative");
        }
        this.vertexCount = vertexCount;
        this.adjacencyList = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    /**
     * Adds an undirected weighted edge between two vertices.
     * Since the graph is undirected, edges are added in both directions.
     *
     * @param source the starting vertex of the edge
     * @param destination the ending vertex of the edge
     * @param weight the cost of traversing this edge
     * @throws IllegalArgumentException if vertices are out of bounds or weight is negative
     */
    public void addEdge(final int source, final int destination, final int weight) {
        validateVertex(source);
        validateVertex(destination);
        if (weight < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight));
    }

    /**
     * Validates that a vertex identifier is within acceptable range.
     *
     * @param vertex the vertex to validate
     * @throws IllegalArgumentException if vertex is out of bounds
     */
    private void validateVertex(final int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException(
                    "Vertex " + vertex + " is out of bounds [0, " + (vertexCount - 1) + "]"
            );
        }
    }

    /**
     * Computes the Minimum Spanning Tree using Prim's algorithm.
     * Starts from vertex 0 and greedily selects minimum weight edges
     * that connect unvisited vertices to the growing MST.
     *
     * @return MstResult containing total weight and list of MST edges
     * @throws IllegalStateException if graph is empty or disconnected
     */
    public MstResult computeMinimumSpanningTree() {
        if (vertexCount == 0) {
            throw new IllegalStateException("Cannot compute MST on empty graph");
        }

        final boolean[] visitedVertices = new boolean[vertexCount];
        final int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);

        final PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        final List<int[]> mstEdges = new ArrayList<>();
        int totalWeight = 0;

        // Start from vertex 0
        visitedVertices[0] = true;
        for (final Edge edge : adjacencyList.get(0)) {
            minHeap.offer(new Edge(edge.getDestination(), edge.getWeight()));
        }

        int processedVertices = 1;

        while (!minHeap.isEmpty() && processedVertices < vertexCount) {
            final Edge currentEdge = minHeap.poll();
            final int currentVertex = currentEdge.getDestination();

            if (visitedVertices[currentVertex]) {
                continue;
            }

            visitedVertices[currentVertex] = true;
            totalWeight += currentEdge.getWeight();
            processedVertices++;

            // Find the source vertex for this edge
            int sourceVertex = findSourceVertex(currentVertex, visitedVertices);
            mstEdges.add(new int[]{sourceVertex, currentVertex, currentEdge.getWeight()});

            // Add all edges from newly visited vertex
            for (final Edge neighborEdge : adjacencyList.get(currentVertex)) {
                if (!visitedVertices[neighborEdge.getDestination()]) {
                    minHeap.offer(neighborEdge);
                }
            }
        }

        if (processedVertices < vertexCount) {
            throw new IllegalStateException("Graph is disconnected - MST cannot be formed");
        }

        return new MstResult(totalWeight, mstEdges);
    }

    /**
     * Finds the source vertex that connects to the given destination in the MST.
     *
     * @param destination the destination vertex to find source for
     * @param visited array tracking which vertices are in the MST
     * @return the source vertex identifier
     */
    private int findSourceVertex(final int destination, final boolean[] visited) {
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            if (visited[vertex]) {
                for (final Edge edge : adjacencyList.get(vertex)) {
                    if (edge.getDestination() == destination) {
                        return vertex;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return vertex count
     */
    public int getVertexCount() {
        return vertexCount;
    }
}