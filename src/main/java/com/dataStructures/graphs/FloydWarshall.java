package com.dataStructures.graphs;

/**
 * Finds shortest distances between every pair of vertices in a given weighted directed graph.
 */
public class FloydWarshall {
    private int[][] _distanceMatrix;
    private int _numberOfVertices;
    final static int INF = 99999;

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
     * This method sets the distance matrix
     *
     * @param distanceMatrix distances as a 2D array
     */
    private void setDistanceMatrix(int[][] distanceMatrix) {
        this._distanceMatrix = distanceMatrix;
    }

    /**
     * Constructor for the graph
     *
     * @param graph                 input graph
     * @param givenNumberOfVertices number of vertices
     */
    public FloydWarshall(int[][] graph, int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setDistanceMatrix(new int[this._numberOfVertices][this._numberOfVertices]);
        for (int i = 0; i < this._numberOfVertices; i++) {
            for (int j = 0; j < this._numberOfVertices; j++) {
                this._distanceMatrix[i][j] = graph[i][j];
            }
        }
        for (int k = 0; k < this._numberOfVertices; k++) {
            for (int i = 0; i < this._numberOfVertices; i++) {
                for (int j = 0; j < this._numberOfVertices; j++) {
                    if (this._distanceMatrix[i][k] + this._distanceMatrix[k][j] < this._distanceMatrix[i][j]) {
                        this._distanceMatrix[i][j] = this._distanceMatrix[i][k] + this._distanceMatrix[k][j];
                    }
                }
            }
        }
    }

    /**
     * This method shows the shortest distances between every pair of vertices
     *
     * @return returns a string describing this graph
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < this._numberOfVertices; ++i) {
            for (int j = 0; j < this._numberOfVertices; ++j) {
                if (this._distanceMatrix[i][j] == INF) {
                    text.append("INF ");
                } else {
                    text.append(this._distanceMatrix[i][j] + "  ");
                }
            }
            text.append("\n");
        }
        return text.toString();
    }
}
