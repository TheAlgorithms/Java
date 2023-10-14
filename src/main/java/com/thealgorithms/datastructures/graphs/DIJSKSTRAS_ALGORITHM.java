/*
    This Java program implements Dijkstra's shortest path algorithm, which is a popular algorithm for finding the shortest paths between nodes in a graph, which may represent, for example, road networks. Here's a breakdown of the code:

The class dijkstras is declared to contain the Dijkstra's algorithm implementation.
The class has an instance variable k which is initialized to 9. This variable represents the number of vertices in the graph.
The method minDist is used to find the vertex with the minimum distance value, from the set of vertices not yet included in the shortest path tree.
The print method is used to print the distances from the source vertex to all other vertices.
The dijkstra method is the main implementation of the Dijkstra's algorithm. It initializes an array dist to store the shortest distance from the source to each vertex and a Boolean array Set to keep track of vertices included in the shortest path tree.
The provided graph is represented as a 2D array graph with 9 vertices. The adjacency matrix representation is used, where graph[i][j] represents the weight of the edge from vertex i to vertex j.
The main method creates an instance of the dijkstras class and initializes a sample graph. It then calls the dijkstra method with the graph and a source vertex (in this case, the source vertex is 0).
The expected output of the program is the shortest distances from the source vertex to all other vertices, as shown in the comment at the end of the code.

This program can be useful for finding the shortest paths between nodes in various types of graphs, such as road networks, computer networks, and more. It provides a simple implementation of the Dijkstra's algorithm for educational purposes.
*/
/*
Refer https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
for better understanding
 */
package com.thealgorithms.datastructures.graphs;

class dijkstras {

    int k = 9;

    int minDist(int[] dist, Boolean[] Set) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int r = 0; r < k; r++) {
            if (!Set[r] && dist[r] <= min) {
                min = dist[r];
                min_index = r;
            }
        }

        return min_index;
    }

    void print(int[] dist) {
        System.out.println("Vertex \t\t Distance");
        for (int i = 0; i < k; i++) {
            System.out.println(i + " \t " + dist[i]);
        }
    }

    void dijkstra(int[][] graph, int src) {
        int[] dist = new int[k];
        Boolean[] Set = new Boolean[k];

        for (int i = 0; i < k; i++) {
            dist[i] = Integer.MAX_VALUE;
            Set[i] = false;
        }

        dist[src] = 0;

        for (int c = 0; c < k - 1; c++) {
            int u = minDist(dist, Set);

            Set[u] = true;

            for (int v = 0; v < k; v++) {
                if (!Set[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        print(dist);
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0},
        };
        dijkstras t = new dijkstras();
        t.dijkstra(graph, 0);
    } // main
} // djikstras
/*
OUTPUT :
Vertex   Distance
0            0
1            4
2            12
3            19
4            21
5            11
6            9
7            8
8            14
 */
