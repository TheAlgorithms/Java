package com.thealgorithms.backtracking;

// Author: Jivan Jamdar

/*
Dijkstra's Algorithm

Problem Statement: 
find the shortest path from a source vertex to all other vertices in a weighted graph with non-negative edge weights.

|| Graph Structure ||:

    (0)
   / | \
  1  4  2
 /   |   \
(1)---3-->(2)
 |         |
 2         1
 |         |
(3)------->(4)

Edges and Weights:

(0) to (1): weight 1
(0) to (2): weight 2
(0) to (3): weight 4
(1) to (2): weight 3
(1) to (3): weight 2
(2) to (4): weight 1
(3) to (4): weight 1

Given the graph above, the algorithm calculates the shortest path distances from Node 0 to all other nodes.

Result: 
For the given graph, the shortest path distances from Node 0 would be:

Distance from Node 0 to:
- Node 0: 0
- Node 1: 1
- Node 2: 2
- Node 3: 3
- Node 4: 3
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    static class Edge {
        int target;  
        int weight; 

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Method to perform Dijkstra's algorithm
    public int[] dijkstra(List<List<Edge>> graph, int source) {
        int V = graph.size();  // Number of vertices in the graph
        int[] dist = new int[V];  // Distance array to store shortest path distances
        Arrays.fill(dist, Integer.MAX_VALUE);  // Initialize distances to infinity
        dist[source] = 0;   

        // Min heap priority queue to get the vertex with the smallest distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0, source});  // Add source to the priority queue

        // Dijkstra's algorithm loop
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[1];  

            // Explore all neighboring vertices
            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weightUV = edge.weight;

                // If a shorter path to vertex v is found, update and push to queue
                if (dist[u] + weightUV < dist[v]) {
                    dist[v] = dist[u] + weightUV;
                    pq.add(new int[] {dist[v], v});
                }
            }
        }

        return dist;  
    }
}
