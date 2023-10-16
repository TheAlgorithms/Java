package com.thealgorithms.datastructures.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private int V; // Number of vertices
    private LinkedList<Integer> adjList[]; // Adjacency list

    @SuppressWarnings("unchecked")

    public BFS(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add an edge to the graph
    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // Breadth-First Search starting from a given source node
    public void bfs(int s) {
        boolean visited[] = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            for (int n : adjList[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS graph = new BFS(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        System.out.println("Breadth-First Traversal (starting from vertex 0):");
        graph.bfs(0);
    }
}
