import java.util.Arrays;

public class dfs_recursive {

    private static final int MAX_NODES = 1000;
    private int[] visited = new int[MAX_NODES]; //array to store visiting order

    public Graph(int numbersOfVertices)
    {
        this.visited = new int[numberOfVertices];
        Arrays.fill(this.visited, -1);
    }

    public boolean dfsPathCheck(Graph g, int nV, int v, int dest) {
        for (int w = 0; w < nV; w++) {
            if (g.adjacent(v, w) && visited[w] == -1) {
                visited[w] = v;
                if (w == dest) {
                    return true;
                } else if (dfsPathCheck(g, nV, w, dest)) {
                    return true;
                }
            }
        }
        return false;
    }


}