package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Edge implements Comparable<Edge> {
    int Source, Destination, Weight;

    public Edge(int source, int destination, int weight) {
        this.Source = source;
        this.Destination = destination;
        this.Weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.Weight - edge.Weight;
    }
}

public class MinimumSpanningTree {

    public List<Edge> FindMST(List<Edge> edges, int numVertices) {
        List<Edge> MinimumSpanningTree = new ArrayList<>();
        Collections.sort(edges);

        int[] parent = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
        }

        int edgeCount = 0;
        int i = 0;

        while (edgeCount < numVertices - 1) {
            Edge edge = edges.get(i);
            int sourceParent = Find(parent, edge.Source);
            int destParent = Find(parent, edge.Destination);

            if (sourceParent != destParent) {
                MinimumSpanningTree.add(edge);
                Union(parent, sourceParent, destParent);
                edgeCount++;
            }

            i++;
        }

        return MinimumSpanningTree;
    }

    private int Find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = Find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    private void Union(int[] parent, int x, int y) {
        int xRoot = Find(parent, x);
        int yRoot = Find(parent, y);
        parent[xRoot] = yRoot;
    }
}
