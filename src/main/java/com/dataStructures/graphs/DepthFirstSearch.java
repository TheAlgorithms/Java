package com.dataStructures.graphs;

import java.util.LinkedList;

/**
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
 * The algorithm starts at the root node and explores as far as possible along each branch before backtracking.
 */

public class DepthFirstSearch {
    private int _numberOfVertices;
    private LinkedList<Integer>[] _adjacencyList;
    private int _source;

    /**
     * This method returns the source vertex
     *
     * @return source vertex
     */
    public int getSource() {
        return this._source;
    }

    /**
     * This method sets the source
     *
     * @param source source vertex
     */
    private void setSource(int source) {
        this._source = source;
    }

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
     * Constructor for depth first search
     *
     * @param givenNumberOfVertices number of vertices
     */
    public DepthFirstSearch(int givenNumberOfVertices, int source) {
        this.setSource(source);
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
    }

    /**
     * This method marks the current node visited and recur for all the vertices adjacent to this vertex
     * @param vertex a given vertex
     * @param visited visited edges
     * @param text vertices as strings
     */
    public void DFSUtil(int vertex, boolean[] visited, StringBuilder text) {
        visited[vertex] = true;
        text.append(vertex + " ");
        for (int n : this._adjacencyList[vertex]) {
            if (!visited[n])
                DFSUtil(n, visited, text);
        }
    }

    /**
     * This method prints DFS traversal from a given source vertex
     * @return DFS traversal
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        boolean[] visited = new boolean[this._numberOfVertices];
        DFSUtil(this.getSource(), visited, text);
        return text.toString();
    }
}
