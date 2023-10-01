package com.thealgorithms.greedyalgorithms;

import java.util.PriorityQueue;

// Link: https://en.wikipedia.org/wiki/Dijkstra's_algorithm

public class DijkstrasAlgorithm {

    static class Pair implements Comparable<Pair> {
        int n;
        int path;

        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    // Function to perform Dijkstra's algorithm to find shortest paths
    public static int[] dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n];

        // Initialize distances to all nodes as infinity except the source node
        for (int i = 0; i < n; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] vis = new boolean[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            // Mark the current node as visited
            if (!vis[curr.n]) {
                vis[curr.n] = true;

                // Explore neighbors of the current node
                for (int v = 0; v < n; v++) {
                    if (graph[curr.n][v] != 0) { // Check if there is an edge
                        int wt = graph[curr.n][v];

                        // Relaxation step: Update the distance if a shorter path is found
                        if (dist[curr.n] != Integer.MAX_VALUE && dist[curr.n] + wt < dist[v]) {
                            dist[v] = dist[curr.n] + wt;
                            pq.add(new Pair(v, dist[v]));
                        }
                    }
                }
            }
        }

        return dist;
    }

    public static void printShortestDistance(int[] dist) {
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }
}
