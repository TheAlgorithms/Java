package com.dataStructures.graphs;

/**
 * Creates a minimum spanning tree. A spanning tree of a graph is a subgraph that is a tree and connects all
 * the vertices together. A minimum spanning tree has the smallest weight.
 */
public class PrimMinimumSpanningTree {
    private int _numberOfVertices;
    private int[][] _graph;
    private int[] _parent;

    /**
     * This method sets the graph
     *
     * @param graph input graph as an adjacency matrix
     */
    private void setGraph(int[][] graph) {
        this._graph = graph;
    }

    /**
     * This method sets the constructed MST
     *
     * @param parent the constructed MST
     */
    private void setParent(int[] parent) {
        this._parent = parent;
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
     * This method returns the number of vertices
     *
     * @return the number of vertices
     */
    public int getNumberOfVertices() {
        return this._numberOfVertices;
    }

    /**
     * This method finds the vertex with the minimum key value
     *
     * @param key    array of keys
     * @param mstSet set of vertices
     * @return
     */
    public int findMinimumKey(int key[], Boolean mstSet[]) {
        int minimum = Integer.MAX_VALUE;
        int minimumIndex = -1;

        for (int i = 0; i < _numberOfVertices; i++)
            if (!mstSet[i] && key[i] < minimum) {
                minimum = key[i];
                minimumIndex = i;
            }
        return minimumIndex;
    }

    /**
     * Constructor for MST represented by using adjacency matrix
     *
     * @param graph                 adjacency matrix for the graph
     * @param givenNumberOfVertices number of vertices in the graph
     */
    public PrimMinimumSpanningTree(int[][] graph, int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        int[] parent = new int[givenNumberOfVertices];
        int[] key = new int[givenNumberOfVertices];
        Boolean[] mstSet = new Boolean[givenNumberOfVertices];

        for (int i = 0; i < givenNumberOfVertices; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < givenNumberOfVertices - 1; count++) {
            int u = findMinimumKey(key, mstSet);
            mstSet[u] = true;
            for (int i = 0; i < _numberOfVertices; i++)
                if (graph[u][i] != 0 && !mstSet[i] && graph[u][i] < key[i]) {
                    parent[i] = u;
                    key[i] = graph[u][i];
                }
        }
        this.setGraph(graph);
        this.setParent(parent);
    }

    /**
     * This method gives a list of vertices in the minimum spanning tree and their weights
     *
     * @return returns a string describing this graph
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 1; i < _numberOfVertices; i++) {
            text.append(this._parent[i] + " - " + i + ", weight: " + this._graph[i][this._parent[i]] + "\n");
        }
        return text.toString();
    }
}
