package com.thealgorithms.datastructures.graphs;

import java.util.Arrays;

/**
 * Java program to find a Hamiltonian Cycle in a graph.
 * A Hamiltonian Cycle is a cycle that visits every vertex exactly once
 * and returns to the starting vertex.
 *
 * <p>For more details, see the
 * <a href="https://en.wikipedia.org/wiki/Hamiltonian_path">Wikipedia article</a>.
 *
 * @author  <a href="https://github.com/itsAkshayDubey">Akshay Dubey</a>
 */
public class HamiltonianCycle {

    private int vertex;
    private int pathCount;
    private int[] cycle;
    private int[][] graph;

    /**
     * Finds a Hamiltonian Cycle for the given graph.
     *
     * @param graph Adjacency matrix representing the graph G(V, E), where V is
     *              the set of vertices and E is the set of edges.
     * @return An array representing the Hamiltonian cycle if found, otherwise an
     *         array filled with -1 indicating no Hamiltonian cycle exists.
     */
    public int[] findHamiltonianCycle(int[][] graph) {
        // Single vertex graph
        if (graph.length == 1) {
            return new int[] {0, 0};
        }

        this.vertex = graph.length;
        this.cycle = new int[this.vertex + 1];

        // Initialize the cycle array with -1 to represent unvisited vertices
        Arrays.fill(this.cycle, -1);

        this.graph = graph;
        this.cycle[0] = 0;
        this.pathCount = 1;
        if (!isPathFound(0)) {
            Arrays.fill(this.cycle, -1);
        } else {
            this.cycle[this.cycle.length - 1] = this.cycle[0];
        }

        return cycle;
    }

    /**
     * Recursively searches for a Hamiltonian cycle from the given vertex.
     *
     * @param vertex The current vertex from which to explore paths.
     * @return {@code true} if a Hamiltonian cycle is found, otherwise {@code false}.
     */
    public boolean isPathFound(int vertex) {
        boolean isLastVertexConnectedToStart = this.graph[vertex][0] == 1 && this.pathCount == this.vertex;
        if (isLastVertexConnectedToStart) {
            return true;
        }

        // If all vertices are visited but the last vertex is not connected to the start
        if (this.pathCount == this.vertex) {
            return false;
        }

        for (int v = 0; v < this.vertex; v++) {
            if (this.graph[vertex][v] == 1) { // Check if there is an edge
                this.cycle[this.pathCount++] = v; // Add the vertex to the cycle
                this.graph[vertex][v] = 0;
                this.graph[v][vertex] = 0;

                // Recursively attempt to complete the cycle
                if (!isPresent(v)) {
                    return isPathFound(v);
                }

                // Restore the edge if the path does not work
                this.graph[vertex][v] = 1;
                this.graph[v][vertex] = 1;

                this.cycle[--this.pathCount] = -1;
            }
        }
        return false;
    }

    /**
     * Checks if a vertex is already part of the current Hamiltonian path.
     *
     * @param vertex The vertex to check.
     * @return {@code true} if the vertex is already in the path, otherwise {@code false}.
     */
    public boolean isPresent(int vertex) {
        for (int i = 0; i < pathCount - 1; i++) {
            if (cycle[i] == vertex) {
                return true;
            }
        }
        return false;
    }
}
