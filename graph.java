import java.util.LinkedList;

public class graph {
    private int numVertices;
    private LinkedList<Integer>[] adjList;

    
    public graph(int numVertices)// Constructor to initialize the graph
     {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void insertEdge(int v, int w)// method to add an edge to the graph 
    {
        adjList[v].add(w);
        adjList[w].add(v);  //for undirected graph
    }

    /*public void insertEdgeDirectedGraph(int v, int w)
     * {
     *      adjList[v].add(w);
     * }
    */
    
    public boolean adjacent(int v, int w)// method to check if two vertices are adjacent 
    {
        return adjList[v].contains(w);
    }
    
    
    public int getNumVertices()// getter for numVertices to access in dfs_recursive clas
     {
        return numVertices;
    }
}