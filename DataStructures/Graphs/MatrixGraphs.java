package DataStructures.Graphs;

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
        System.out.println(graph);
    }

}

class AdjacencyMatrixGraph {
    private int _numberOfVertices;
    private int _numberOfEdges;
    private int[][] _adjacency;

    static final int EDGE_EXIST = 1;
    static final int EDGE_NONE = 0;

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

    private void setNumberOfVertices(int newNumberOfVertices) {
        this._numberOfVertices = newNumberOfVertices;
    }

    public int numberOfVertices() {
        return this._numberOfVertices;
    }

    private void setNumberOfEdges(int newNumberOfEdges) {
        this._numberOfEdges = newNumberOfEdges;
    }

    public int numberOfEdges() {
        return this._numberOfEdges;
    }

    private void setAdjacency(int[][] newAdjacency) {
        this._adjacency = newAdjacency;
    }

    private int[][] adjacency() {
        return this._adjacency;
    }

    private boolean adjacencyOfEdgeDoesExist(int from, int to) {
        return (this.adjacency()[from][to] != AdjacencyMatrixGraph.EDGE_NONE);
    }

    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numberOfVertices()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean edgeDoesExist(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            return (this.adjacencyOfEdgeDoesExist(from, to));
        }

        return false;
    }

    /**
     * This method adds an edge to the graph between two specified
     * vertices
     *
     * @param from the data of the vertex the edge is from
     * @param to   the data of the vertex the edge is going to
     * @return returns true if the edge did not exist, return false if it already did
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
     * this method removes an edge from the graph between two specified
     * vertices
     *
     * @param from the data of the vertex the edge is from
     * @param to   the data of the vertex the edge is going to
     * @return returns false if the edge doesn't exist, returns true if the edge exists and is removed
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
     * this gives a list of vertices in the graph and their adjacencies
     *
     * @return returns a string describing this graph
     */
    public String toString() {
        String s = new String();
        s = "    ";
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
