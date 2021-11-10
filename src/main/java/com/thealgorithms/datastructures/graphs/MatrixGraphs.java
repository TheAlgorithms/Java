package com.thealgorithms.datastructures.graphs;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Implementation of a graph in a matrix form Also known as an adjacency matrix
 * representation [Adjacency matrix -
 * Wikipedia](https://en.wikipedia.org/wiki/Adjacency_matrix)
 *
 * @author Unknown
 */
public class MatrixGraphs {

    public static void main(String args[]) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 9);
        graph.addEdge(9, 1);
        graph.addEdge(9, 8);
        graph.addEdge(1, 8);
        graph.addEdge(5, 6);
        System.out.println("The graph matrix:");
        System.out.println(graph);
        System.out.println("Depth first order beginning at node '1':");
        System.out.println(graph.depthFirstOrder(1));
        System.out.println("Breadth first order beginning at node '1':");
        System.out.println(graph.breadthFirstOrder(1));
    }
}

/**
 * AdjacencyMatrixGraph Implementation
 */
class AdjacencyMatrixGraph {

    /**
     * The number of vertices in the graph
     */
    private int _numberOfVertices;

    /**
     * The number of edges in the graph
     */
    private int _numberOfEdges;

    /**
     * The adjacency matrix for the graph
     */
    private int[][] _adjacency;

    /**
     * Static variables to define whether or not an edge exists in the adjacency
     * matrix
     */
    static final int EDGE_EXIST = 1;
    static final int EDGE_NONE = 0;

    /**
     * Constructor
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
     * Updates the number of vertices in the graph
     *
     * @param newNumberOfVertices the new number of vertices
     */
    private void setNumberOfVertices(int newNumberOfVertices) {
        this._numberOfVertices = newNumberOfVertices;
    }

    /**
     * Getter for `this._numberOfVertices`
     *
     * @return the number of vertices in the graph
     */
    public int numberOfVertices() {
        return this._numberOfVertices;
    }

    /**
     * Updates the number of edges in the graph
     *
     * @param newNumberOfEdges
   *
     */
    private void setNumberOfEdges(int newNumberOfEdges) {
        this._numberOfEdges = newNumberOfEdges;
    }

    /**
     * Getter for `this._numberOfEdges`
     *
     * @return the number of edges
     */
    public int numberOfEdges() {
        return this._numberOfEdges;
    }

    /**
     * Sets a new matrix as the adjacency matrix
     *
     * @param newAdjacency the new adjaceny matrix
     */
    private void setAdjacency(int[][] newAdjacency) {
        this._adjacency = newAdjacency;
    }

    /**
     * Getter for the adjacency matrix
     *
     * @return the adjacency matrix
     */
    private int[][] adjacency() {
        return this._adjacency;
    }

    /**
     * Checks if two vertices are connected by an edge
     *
     * @param from the parent vertex to check for adjacency
     * @param to the child vertex to check for adjacency
     * @return whether or not the vertices are adjancent
     */
    private boolean adjacencyOfEdgeDoesExist(int from, int to) {
        return (this.adjacency()[from][to] != AdjacencyMatrixGraph.EDGE_NONE);
    }

    /**
     * Checks if a particular vertex exists in a graph
     *
     * @param aVertex the vertex to check for existence
     * @return whether or not the vertex exists
     */
    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numberOfVertices()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if two vertices are connected by an edge
     *
     * @param from the parent vertex to check for adjacency
     * @param to the child vertex to check for adjacency
     * @return whether or not the vertices are adjancent
     */
    public boolean edgeDoesExist(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            return (this.adjacencyOfEdgeDoesExist(from, to));
        }

        return false;
    }

    /**
     * This method adds an edge to the graph between two specified vertices
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return returns true if the edge did not exist, return false if it
     * already did
     */
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            if (!this.adjacencyOfEdgeDoesExist(from, to)) {
                this.adjacency()[from][to] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.adjacency()[to][from] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.setNumberOfEdges(this.numberOfEdges() + 1);
                return true;
            }
        }

        return false;
    }

    /**
     * this method removes an edge from the graph between two specified vertices
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return returns false if the edge doesn't exist, returns true if the edge
     * exists and is removed
     */
    public boolean removeEdge(int from, int to) {
        if (!this.vertexDoesExist(from) || !this.vertexDoesExist(to)) {
            if (this.adjacencyOfEdgeDoesExist(from, to)) {
                this.adjacency()[from][to] = AdjacencyMatrixGraph.EDGE_NONE;
                this.adjacency()[to][from] = AdjacencyMatrixGraph.EDGE_NONE;
                this.setNumberOfEdges(this.numberOfEdges() - 1);
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a list of the vertices in a depth first order
     * beginning with the specified vertex
     *
     * @param startVertex the vertex to begin the traversal
     * @return the list of the ordered vertices
     */
    public List<Integer> depthFirstOrder(int startVertex) {
        // If the startVertex is invalid, return an empty list
        if (startVertex >= _numberOfVertices || startVertex < 0) {
            return new ArrayList<Integer>();
        }

        // Create an array to track the visited vertices
        boolean[] visited = new boolean[_numberOfVertices];

        // Create a list to keep track of the order of our traversal
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        // Perform our DFS algorithm
        depthFirstOrder(startVertex, visited, orderList);

        return orderList;
    }

    /**
     * Helper method for public depthFirstOrder(int) that will perform a depth
     * first traversal recursively on the graph
     *
     * @param currentVertex the currently exploring vertex
     * @param visited the array of values denoting whether or not that vertex
     * has been visited
     * @param orderList the list to add vertices to as they are visited
     */
    private void depthFirstOrder(int currentVertex, boolean[] visited, List<Integer> orderList) {
        // If this vertex has already been visited, do nothing and return
        if (visited[currentVertex]) {
            return;
        }

        // Visit the currentVertex by marking it as visited and adding it
        // to the orderList
        visited[currentVertex] = true;
        orderList.add(currentVertex);

        // Get the adjacency array for this vertex
        int[] adjacent = _adjacency[currentVertex];
        for (int i = 0; i < adjacent.length; i++) // If an edge exists between the currentVertex and the vertex
        // we are considering exploring, recurse on it
        {
            if (adjacent[i] == AdjacencyMatrixGraph.EDGE_EXIST) {
                depthFirstOrder(i, visited, orderList);
            }
        }
    }

    /**
     * This method returns a list of the vertices in a breadth first order
     * beginning with the specified vertex
     *
     * @param startVertex the vertext to begin the traversal
     * @return the list of the ordered vertices
     */
    public List<Integer> breadthFirstOrder(int startVertex) {
        // If the specified startVertex is invalid, return an empty list
        if (startVertex >= _numberOfVertices || startVertex < 0) {
            return new ArrayList<Integer>();
        }

        // Create an array to keep track of the visited vertices
        boolean[] visited = new boolean[_numberOfVertices];

        // Create a list to keep track of the ordered vertices
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        // Create a queue for our BFS algorithm and add the startVertex
        // to the queue
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startVertex);

        // Continue until the queue is empty
        while (!queue.isEmpty()) {
            // Remove the first vertex in the queue
            int currentVertex = queue.poll();

            // If we've visited this vertex, skip it
            if (visited[currentVertex]) {
                continue;
            }

            // We now visit this vertex by adding it to the orderList and
            // marking it as visited
            orderList.add(currentVertex);
            visited[currentVertex] = true;

            // Get the adjacency array for the currentVertex and 
            // check each node
            int[] adjacent = _adjacency[currentVertex];
            for (int vertex = 0; vertex < adjacent.length; vertex++) // If an edge exists between the current vertex and the
            // vertex we are considering exploring, we add it to the queue
            {
                if (adjacent[vertex] == AdjacencyMatrixGraph.EDGE_EXIST) {
                    queue.add(vertex);
                }
            }
        }

        return orderList;
    }

    /**
     * this gives a list of vertices in the graph and their adjacencies
     *
     * @return returns a string describing this graph
     */
    public String toString() {
        String s = "    ";
        for (int i = 0; i < this.numberOfVertices(); i++) {
            s = s + String.valueOf(i) + " ";
        }
        s = s + " \n";

        for (int i = 0; i < this.numberOfVertices(); i++) {
            s = s + String.valueOf(i) + " : ";
            for (int j = 0; j < this.numberOfVertices(); j++) {
                s = s + String.valueOf(this._adjacency[i][j]) + " ";
            }
            s = s + "\n";
        }
        return s;
    }
}
