/* Author :SUBHAM SANGHAI
A Java program for Floyd Warshall All pair shortest path algorithm,
where given a graph and a source vertex in graph, we have to find shortest 
distances between every pair of vertices in a given edge weighted directed Graph.
The program is for adjacency matrix representation of the graph*/
import java.util.*;
import java.lang.*;
import java.io.*;
class AllPairShortestPath
{
    final static int INF = 99999, V = 4;

 
    void floydWarshall(int graph[][])
    {
        int dist[][] = new int[V][V];
        int i, j, k;
 
        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];
 
        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of a iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of a iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
 
        // Print the shortest distance matrix
        printSolution(dist);
    }


 
    // A utility function to print the constructed distance array
    void printSolution(int dist[][])
    {
        System.out.println("Following matrix shows the shortest "+
                         "distances between every pair of vertices:\n");
	System.out.print("  | ");
	for(int i=0;i<V;i++)
	    System.out.print(i+"   ");
	System.out.println();
	System.out.print("--+------------------");
	System.out.println();
        for (int i=0; i<V; ++i)
        {
	    System.out.print(i+" | ");
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
     }    
 
 
    // Driver method
    public static void main (String[] args)
    {
        /* Let us create the following weighted graph
           10
        (0)------->(3)
        |         /|\
        5 |          |
        |          | 1
        \|/         |
        (1)------->(2)
           3           */
        int graph[][] = { {0,   5,  INF, 10},
                          {INF, 0,   3, INF},
                          {INF, INF, 0,   1},
                          {INF, INF, INF, 0}
                        };
        AllPairShortestPath a = new AllPairShortestPath();
 
        // Print the solution
        a.floydWarshall(graph);
    }


}
