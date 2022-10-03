package com.thealgorithms.datastructures.graphs;

/**
 * Java program for Hamiltonian Cycle (https://en.wikipedia.org/wiki/Hamiltonian_path)
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public class HamiltonianCycle {

    private int V, pathCount;
    private int[] cycle;
    private int[][] graph;

    /**
     * Find hamiltonian cycle for given graph G(V,E)
     * @param graph Adjacency matrix of a graph G(V, E)
     * for which hamiltonian path is to be found
     * @return Array containing hamiltonian cycle
     * else returns 1D array with value -1.
     */
    public int[] findHamiltonianCycle(int[][] graph) {
        this.V = graph.length;
        this.cycle = new int[this.V + 1];

        //Initialize path array with -1 value
        for (int i = 0; i < this.cycle.length; i++) {
            this.cycle[i] = -1;
        }

        this.graph = graph;

        this.cycle[0] = 0;
        this.pathCount = 1;
        if (!isPathFound(0)) {
            for (int i = 0; i < this.cycle.length; i++) {
                this.cycle[i] = -1;
            }
        } else {
            this.cycle[this.cycle.length - 1] = this.cycle[0];
        }

        return cycle;
    }

    /** function to find paths recursively
     * Find paths recursively from given vertex
     * @param vertex Vertex from which path is to be found
     * @returns true if path is found false otherwise
     */
    public boolean isPathFound(int vertex) {
        if (this.graph[vertex][0] == 1 && this.pathCount == this.V) {
            return true;
        }

        /** all vertices selected but last vertex not linked to 0 **/
        if (this.pathCount == this.V) {
            return false;
        }

        for (int v = 0; v < this.V; v++) {
            /** if connected **/
            if (this.graph[vertex][v] == 1) {
                /** add to path **/
                this.cycle[this.pathCount++] = v;

                /** remove connection **/
                this.graph[vertex][v] = 0;
                this.graph[v][vertex] = 0;

                /** if vertex not already selected  solve recursively **/
                if (!isPresent(v)) {
                    return isPathFound(v);
                }

                /** restore connection **/
                this.graph[vertex][v] = 1;
                this.graph[v][vertex] = 1;

                /** remove path **/
                this.cycle[--this.pathCount] = -1;
            }
        }
        return false;
    }

    /** function to check if path is already selected
     * Check if path is already selected
     * @param vertex Starting vertex
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
