package com.dataStructures.graphs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This algorithm, given m colors, finds a way of coloring the vertices of a graph such that no two adjacent vertices
 * are colored using same color.
 */

public class GraphColoring {
    private int _numberOfVertices;
    private LinkedList<Integer>[] _adjacencyList;

    /**
     * This method sets the number of vertices
     *
     * @param numberOfVertices number of vertices in the graph
     */
    private void setNumberOfVertices(int numberOfVertices) {
        this._numberOfVertices = numberOfVertices;
    }

    /**
     * This method sets the adjacency list
     *
     * @param adjacencyList given adjacency list
     */
    private void setAdjacencyList(LinkedList<Integer>[] adjacencyList) {
        this._adjacencyList = adjacencyList;
    }

    /**
     * Constructor for graph coloring
     *
     * @param givenNumberOfVertices given number of vertices
     */
    GraphColoring(int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setAdjacencyList(new LinkedList[givenNumberOfVertices]);
        for (int i = 0; i < givenNumberOfVertices; ++i)
            this._adjacencyList[i] = new LinkedList();
    }

    /**
     * This method adds an edge between source vertex and target vertex
     *
     * @param source source vertex
     * @param target target vertex
     */
    public void addEdge(int source, int target) {
        this._adjacencyList[source].add(target);
        this._adjacencyList[target].add(source);
    }

    /**
     * Assigns colors to all vertices starting from 0
     */
    public int[] greedyColoring() {
        int[] result = new int[this._numberOfVertices];
        Arrays.fill(result, -1);
        result[0] = 0;
        boolean[] available = new boolean[this._numberOfVertices];
        Arrays.fill(available, true);

        for (int u = 1; u < this._numberOfVertices; u++) {
            for (int i : this._adjacencyList[u]) {
                if (result[i] != -1)
                    available[result[i]] = false;
            }
            int colorIndex;
            for (colorIndex = 0; colorIndex < _numberOfVertices; colorIndex++) {
                if (available[colorIndex])
                    break;
            }
            result[u] = colorIndex;
            Arrays.fill(available, true);
        }
        return result;
    }

    /**
     * This method prints all the vertexes with their color
     *
     * @return graph coloring
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        int[] result = this.greedyColoring();
        for (int u = 0; u < this._numberOfVertices; u++) {
            text.append("Vertex ").append(u).append(" --->  Color ").append(result[u]).append("\n");
        }
        return text.toString();
    }
}
