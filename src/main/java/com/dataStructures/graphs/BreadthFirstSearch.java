package com.dataStructures.graphs;

import java.util.LinkedList;

/**
 * It is an algorithm for traversing or searching tree or graph data structures.
 * It starts at the tree root in a breadthward motion.
 */

public class BreadthFirstSearch {
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
     * Constructor for breadth first search
     *
     * @param givenNumberOfVertices number of vertices
     */
    public BreadthFirstSearch(int givenNumberOfVertices, int source) {
        this.setSource(source);
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setAdjacencyList(new LinkedList[givenNumberOfVertices]);
        for (int i = 0; i < givenNumberOfVertices; ++i) {
            this._adjacencyList[i] = new LinkedList();
        }
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
     * This method prints BFS traversal from a given source vertex
     *
     * @return BFS traversal
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        boolean[] visited = new boolean[_numberOfVertices];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[this.getSource()] = true;
        queue.add(this.getSource());
        while (queue.size() != 0) {
            this.setSource(queue.poll());
            text.append(this.getSource() + " ");
            for (int n : _adjacencyList[this.getSource()]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return text.toString();
    }
}
