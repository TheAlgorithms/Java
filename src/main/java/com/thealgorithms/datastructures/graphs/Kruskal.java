package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Edge {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    private int vertices;
    private List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }

    public List<Edge> kruskalMST() {
        List<Edge> minimumSpanningTree = new ArrayList<>();
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int edgeCount = 0;
        int index = 0;
        while (edgeCount < vertices - 1 && index < edges.size()) {
            Edge nextEdge = edges.get(index++);
            int sourceParent = find(parent, nextEdge.source);
            int destinationParent = find(parent, nextEdge.destination);

            if (sourceParent != destinationParent) {
                minimumSpanningTree.add(nextEdge);
                union(parent, sourceParent, destinationParent);
                edgeCount++;
            }
        }

        return minimumSpanningTree;
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    private void union(int[] parent, int source, int destination) {
        int sourceParent = find(parent, source);
        int destinationParent = find(parent, destination);
        parent[sourceParent] = destinationParent;
    }
}

public class KruskalAlgorithm {
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        // Add edges to the graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 3);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 7);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 6);

        List<Edge> minimumSpanningTree = graph.kruskalMST();
        
        System.out.println("Edges in Minimum Spanning Tree:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
