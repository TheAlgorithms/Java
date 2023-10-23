package com.thealgorithms.searches;

import java.util.*;

/**
 * Dijkstra's Shortest Path algorithm finds the shortest path between two nodes
 * in a weighted graph.
 *
 * <p>
 * Worst-case performance O(|E| + |V| log |V|) with a min-priority queue.
 * Best-case performance O(|E| + |V| log |V|) (typical).
 * Average performance O(|E| + |V| log |V|).
 * Worst-case space complexity O(|V|) for the distances.
 *
 * @author Adarsh U
 * @see GraphAlgorithm
 * @see DijkstraShortestPath
 */
class Graph {
    private int V; // Number of vertices
    private List<List<Node>> adj; // Adjacency list

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adj.get(source).add(new Node(destination, weight));
        adj.get(destination).add(new Node(source, weight)); // For an undirected graph
    }

    public void dijkstra(int source) {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(V, new Node());

        priorityQueue.add(new Node(source, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().node;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.cost;

                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    priorityQueue.add(new Node(v, distance[v]));
                }
            }
        }

        // Print the shortest distances from the source vertex
        System.out.println("Shortest distances from source vertex:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + distance[i]);
        }
    }

    static class Node implements Comparator<Node> {
        int node;
        int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            return 0;
        }
    }
}

public class DijkstraShortestPath {
    public static void main(String[] args) {
        int V = 6;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 1);
        graph.addEdge(4, 5, 2);

        int sourceVertex = 0;
        graph.dijkstra(sourceVertex);
    }
}
