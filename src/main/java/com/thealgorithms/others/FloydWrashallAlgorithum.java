package com.thealgorithms.graph;
import java.io.*;
import java.lang.*;
import java.util.*;
 
class AllPairShortestPath {
    final static int INF = 99999, V = 4;
    void floydWarshall(int dist[][]) {
        int i, j, k;
        for (k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        printSolution(dist);
    }
  
    void printSolution(int dist[][]) {
        System.out.println("The following matrix shows the shortest "
            + "distances between every pair of vertices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }
  
    public static void main(String[] args) {
           10
        (0)------->(3)
        |         /|\
        5 |          |
        |          | 1
        \|/         |
        (1)------->(2)
           3           */
        int graph[][] = {{0, 5, INF, 10}, {INF, 0, 3, INF}, {INF, INF, 0, 1}, {INF, INF, INF, 0}};
        AllPairShortestPath a = new AllPairShortestPath();
        a.floydWarshall(graph);
    }
}
