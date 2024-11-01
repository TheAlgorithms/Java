import java.util.Arrays;

public class DFSrecursive {

    private int[] visited;

    //initializes the visited array for the number of vertices
    public DFSrecursive(int numVertices)
    {
        this.visited = new int [numVertices];
    }

    //recursive dfs to check if there is a path from src to dest
    public boolean dfsPathCheck(graph g, int v, int dest)
    {
        int numVertices = g.getNumVertices();
        for(int w = 0; w < numVertices; w++)
        {
            if(g.adjacent(v, w) && visited[w] == -1)
            {
                visited[w] = v;
                if(w == dest){
                    return true;
                }else if (dfsPathCheck(g, w, dest)){
                    return true;
                }
            } 
        }
        return false;
    }

    public boolean findPathDFS(graph g, int src, int dest)
    {
        Arrays.fill(visited, -1);//reset visited array
        visited[src] = src;
        return dfsPathCheck(g, src, dest);
    }

    public static void main(String[] args) {

        int V = 6;
        graph g = new graph(V);

        g.insertEdge(0, 1);
        g.insertEdge(0, 4);
        g.insertEdge(0, 5);
        g.insertEdge(5, 4);
        g.insertEdge(4, 2);
        g.insertEdge(4, 3);
        g.insertEdge(5, 3);
        g.insertEdge(1, 2);
        g.insertEdge(3, 2);

        DFSrecursive dfs = new DFSrecursive(g.getNumVertices());
        int src = 0, dest = 5;
        if(dfs.findPathDFS(g, src, dest))
        {
            System.out.print("Path found: ");
            int v = dest;
            while(v != src)
            {
                System.out.print(v + " <- ");
                v = dfs.visited[v];
            }
            System.out.println(src);
        }else{
            System.out.println("No path found from " + src + " to " + dest);
        }
    
    }

}