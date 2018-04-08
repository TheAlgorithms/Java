
public class FloydWarshall {

    private final static int INF = 99999;

    private static void printMatrix(int [][] matrix) {

        int n_vertices = matrix.length;

        for (int i=0; i < n_vertices ; i++) {
            for (int j=0; j < n_vertices; j++) {
                if (matrix[i][j] == INF)
                    System.out.print(String.format("%7s", "INF"));
                else
                    System.out.print(String.format("%7d", matrix[i][j]));
            }
            System.out.println();
        }
    }

    private static void findShortestPaths(int[][] graph) {

        int n_vertices = graph.length;
        int [][] dp_distances = new int[n_vertices][n_vertices];

        // Copy graph
        for (int i = 0; i < n_vertices; i++)
            dp_distances[i] = graph[i].clone();

        for (int k=0; k < n_vertices ; k++)
            for (int i=0; i < n_vertices; i++)
                for (int j=0; j < n_vertices; j++)
                    dp_distances[i][j] = Math.min(dp_distances[i][j], dp_distances[i][k] + dp_distances[k][j]);

        printMatrix(dp_distances);
    }

    public static void main(String args[]) {

        int graph[][] = { {0,   5,  INF, 10},
                          {INF, 0,   3, INF},
                          {INF, INF, 0,   1},
                          {INF, INF, INF, 0}
                        };

        System.out.println("To the given graph, the shortest distances between every pair of vertices are: \n");
        findShortestPaths(graph);
    }
}
