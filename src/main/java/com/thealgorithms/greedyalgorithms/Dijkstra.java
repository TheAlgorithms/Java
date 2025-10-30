package com.thealgorithms.greedyalgorithms;

import java.util.*;

/**
 * Dijkstra's shortest path algorithm for non-negative weighted graphs.
 *
 * Provides a method to compute shortest distances from a source node to all
 * other nodes using an adjacency list representation.
 */
public final class Dijkstra {
    private Dijkstra() {}

    /**
     * Compute shortest distances from source to all vertices.
     *
     * @param n number of vertices (vertices are 0..n-1)
     * @param adj adjacency list where adj[u] is a list of (v, weight) pairs
     * @param src source vertex
     * @return array of distances where dist[i] is shortest distance or Long.MAX_VALUE if unreachable
     */
    public static long[] shortestPaths(int n, List<List<Edge>> adj, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0L;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.add(new Node(src, 0L));

        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.id;
            if (visited[u]) continue;
            visited[u] = true;

            for (Edge e : adj.get(u)) {
                int v = e.to;
                long w = e.weight;
                if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }

    /** Simple edge class for adjacency lists */
    public static class Edge {
        public final int to;
        public final long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Node {
        final int id;
        final long dist;

        Node(int id, long dist) {
            this.id = id;
            this.dist = dist;
        }
    }
}
