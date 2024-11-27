
public class Graph {
    private int[][] edges; // matrix
    private int nV; // number of vertices
    private int nE; // number of edges

    // constructor with n vertices
    public Graph(int nV) {
        this.nV = nV;
        this.nE = 0;
        edges = new int[nV][nV]; // initialize matrix with 0
    }

    // method to check if a vertex is valid
    private boolean validV(int v) {
        return (v >= 0 && v < nV);
    }

    // method to insert edge
    public void insertEdge(int v, int w) {
        if (validV(v) && validV(w) && edges[v][w] == 0) {
            edges[v][w] = 1;
            edges[w][v] = 1; // undirected graph
            nE++;
        }
    }

    // method to remove edge
    public void removeEdge(int v, int w) {
        if (validV(v) && validV(w) && edges[v][w] == 1) {
            edges[v][w] = 0;
            edges[w][v] = 0;
            nE--;
        }
    }

    // method to check if two vertices are adjacent
    public boolean adjacent(int v, int w) {
        return validV(v) && validV(w) && edges[v][w] != 0;
    }

    // method to display graph
    public void showGraph() {
        System.out.println("Number of vertices: " + nV);
        System.out.println("Number of edges: " + nE);
        for (int i = 0; i < nV; i++) {
            for (int j = i + 1; j < nV; j++) {
                if (edges[i][j] == 1) {
                    System.out.println("Edges " + i + " - " + j);
                }
            }
        }
    }

    public int getNumVertices() {
        return nV;
    }
}
