package com.dataStructures.graphs;

/**
 * Creates an adjacency matrix from a given graph
 */
public class AdjacencyMatrixGraph {
    private int _numberOfVertices;
    private int _numberOfEdges;
    private int[][] _adjacency;

    static final int EDGE_EXIST = 1;
    static final int EDGE_NONE = 0;

    /**
     * This method sets the number of vertices
     * @param numberOfVertices number of vertices in the graph
     */
    private void setNumberOfVertices(int numberOfVertices) {
        this._numberOfVertices = numberOfVertices;
    }

    /**
     * This method returns the number of vertices
     * @return the number of vertices
     */
    public int getNumberOfVertices() {
        return this._numberOfVertices;
    }

    /**
     * This method sets the number of edges
     * @param numberOfEdges the number of edges
     */
    private void setNumberOfEdges(int numberOfEdges) {
        this._numberOfEdges = numberOfEdges;
    }

    /**
     * This method returns the number of edges
     * @return the number of edges
     */
    public int getNumberOfEdges() {
        return this._numberOfEdges;
    }

    /**
     * This method sets the adjacency matrix
     * @param adjacency the adjacency matrix represented as a 2D array
     */
    private void setAdjacency(int[][] adjacency) {
        this._adjacency = adjacency;
    }

    /**
     * This method returns the adjacency matrix
     * @return adjacency matrix as 2D array
     */
    private int[][] adjacency() {
        return this._adjacency;
    }

    /**
     * This methods checks if there is an edge between the two vertices
     * @param from source vertex
     * @param to target vertex
     * @return true or false
     */
    private boolean adjacencyOfEdgeDoesExist(int from, int to) {
        return (this.adjacency()[from][to] != AdjacencyMatrixGraph.EDGE_NONE);
    }

    /**
     * Constructor for the adjacency matrix
     * @param givenNumberOfVertices number of vertices
     */
    public AdjacencyMatrixGraph(int givenNumberOfVertices) {
        this.setNumberOfVertices(givenNumberOfVertices);
        this.setNumberOfEdges(0);
        this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
        for (int i = 0; i < givenNumberOfVertices; i++) {
            for (int j = 0; j < givenNumberOfVertices; j++) {
                this.adjacency()[i][j] = AdjacencyMatrixGraph.EDGE_NONE;
            }
        }
    }

    /**
     * This method checks if the given vertex exists
     * @param vertex a vertex of the graph
     * @return true or false
     */
    public boolean vertexDoesExist(int vertex) {
        if (vertex >= 0 && vertex < this.getNumberOfVertices()) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the edge between the source and target vertex exists
     * @param from source vertex
     * @param to target vertex
     * @return true or false
     */
    public boolean edgeDoesExist(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            return (this.adjacencyOfEdgeDoesExist(from, to));
        }
        return false;
    }
    /**
     * This method adds an edge to the graph between two specified vertices
     * @param from source vertex
     * @param to  target vertex
     * @return returns true if the edge does not exist, return false if it already does
     */
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            if (!this.adjacencyOfEdgeDoesExist(from, to)) {
                this.adjacency()[from][to] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.adjacency()[to][from] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.setNumberOfEdges(this.getNumberOfEdges() + 1);
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes an edge from the graph between two specified vertices
     * @param from source vertex
     * @param to target vertex
     * @return returns false if the edge doesn't exist, returns true if the edge exists and is removed
     */
    public boolean removeEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            if (this.adjacencyOfEdgeDoesExist(from, to)) {
                this.adjacency()[from][to] = AdjacencyMatrixGraph.EDGE_NONE;
                this.adjacency()[to][from] = AdjacencyMatrixGraph.EDGE_NONE;
                this.setNumberOfEdges(this.getNumberOfEdges() - 1);
                return true;
            }
        }
        return false;
    }

    /**
     * This method gives a list of vertices in the graph and their adjacencies
     * @return returns a string describing this graph
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < this.getNumberOfVertices(); i++) {
            for (int j = 0; j < this.getNumberOfVertices(); j++) {
                text.append(this._adjacency[i][j]).append(" ");
            }
            text.append("\n");
        }
        return text.toString();
    }
}
