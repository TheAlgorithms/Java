package main.java.com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

// final cost of the Minimum Spanning Tree
public class PriMincost {

    // Edge class to represent an edge between two vertices with a weight
    static class Edge {
        int src;  // source vertex
        int dest; // destination vertex
        int wt;   // weight of the edge

        // Constructor for the Edge class
        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    // Method to create a graph using adjacency list representation
    static void createGraph(ArrayList<Edge> graph[]) {
        // Initialize each vertex's list in the adjacency list
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Adding edges between vertices (undirected graph)
        graph[0].add(new Edge(0, 1, 10));  // Edge from 0 to 1 with weight 10
        graph[0].add(new Edge(0, 2, 15));  // Edge from 0 to 2 with weight 15
        graph[0].add(new Edge(0, 3, 30));  // Edge from 0 to 3 with weight 30

        graph[1].add(new Edge(1, 0, 10));  // Edge from 1 to 0 (since it's undirected)
        graph[1].add(new Edge(1, 3, 40));  // Edge from 1 to 3 with weight 40

        graph[2].add(new Edge(2, 0, 15));  // Edge from 2 to 0 with weight 15
        graph[2].add(new Edge(2, 3, 50));  // Edge from 2 to 3 with weight 50

        graph[3].add(new Edge(3, 4, 40));  // Edge from 3 to 4 with weight 40
        graph[3].add(new Edge(3, 2, 50));  // Edge from 3 to 2 with weight 50 (undirected graph)
    }

    // Helper class to represent pairs of vertices and their corresponding cost (used in the priority queue)
    static class Pair implements Comparable<Pair> {
        int v;    // vertex
        int cost; // cost of the edge to reach the vertex

        // Constructor for the Pair class
        Pair(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        // Comparator to order Pairs by the cost (ascending order) for the priority queue
        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    // Prim's algorithm to find the Minimum Spanning Tree (MST)
    public static void prims(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length]; // Array to track visited vertices
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // Priority queue to select the edge with the minimum weight
        pq.add(new Pair(0, 0)); // Start from vertex 0 with a cost of 0
        int finalCost = 0; // Variable to store the total cost of the MST

        // While there are still vertices to process in the priority queue
        while (!pq.isEmpty()) {
            Pair curr = pq.remove(); // Get the pair with the smallest cost
            if (!vis[curr.v]) { // If the vertex has not been visited yet
                vis[curr.v] = true; // Mark it as visited
                finalCost += curr.cost; // Add the cost to the final cost

                // Iterate through the adjacent edges of the current vertex
                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i); // Get each adjacent edge
                    pq.add(new Pair(e.dest, e.wt)); // Add the adjacent vertex and edge weight to the priority queue
                }
            }
        }

        // Output the final cost of the Minimum Spanning Tree
        System.out.println("Final cost of the Minimum Spanning Tree: " + finalCost);
    }

    // Main method to test Prim's algorithm
    public static void main(String[] args) {
        int V = 4; // Number of vertices
        ArrayList<Edge> graph[] = new ArrayList[V]; // Create an array of adjacency lists
        createGraph(graph); // Initialize the graph with edges
        prims(graph); // Call Prim's algorithm to find the MST
    }
}
