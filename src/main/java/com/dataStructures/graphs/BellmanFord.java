package com.dataStructures.graphs;

/**
 * This algorithm find shortest paths from src to all vertices in the given graph. The graph may contain negative weight edges.
 */
public class BellmanFord {
    private int[] _dist;
    private boolean _negativeWeightCycle;

    static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static class Graph {
        int numberOfVertices;
        int numberOfEdges;
        Edge[] edge;

        public Graph(int vertices, int edges, Edge[] edge) {
            this.numberOfEdges = edges;
            this.numberOfVertices = vertices;
            this.edge = edge;
        }
    }

    /**
     * This method sets the distance matrix
     *
     * @param dist distance array
     */
    private void setDist(int[] dist) {
        this._dist = dist;
    }

    /**
     * Constructor for Bellman-Ford algorithm
     *
     * @param graph  input graph
     * @param source source vertex
     */
    public BellmanFord(Graph graph, int source) {
        int totalVertices = graph.numberOfVertices;
        int totalEdges = graph.numberOfEdges;

        this.setDist(new int[totalVertices]);

        for (int i = 0; i < totalVertices; i++) {
            this._dist[i] = Integer.MAX_VALUE;
        }
        this._dist[source] = 0;

        for (int i = 1; i < totalVertices; i++) {
            for (int j = 0; j < totalEdges; j++) {
                int u = graph.edge[j].start;
                int v = graph.edge[j].end;
                int w = graph.edge[j].weight;

                if (this._dist[u] != Integer.MAX_VALUE && this._dist[v] > this._dist[u] + w) {
                    this._dist[v] = this._dist[u] + w;
                }
            }
        }

        for (int i = 0; i < totalEdges; i++) {
            int u = graph.edge[i].start;
            int v = graph.edge[i].end;
            int w = graph.edge[i].weight;
            if (this._dist[u] != Integer.MAX_VALUE && this._dist[v] > this._dist[u] + w) {
                this._negativeWeightCycle = true;
                return;
            }
        }

    }

    /**
     * This method returns the vertices and their distance from the source vertex
     *
     * @return result as a String
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < this._dist.length; i++) {
            text.append("Vertex: ").append(i).append(" distance: ").append(this._dist[i]).append("\n");
        }
        if (this._negativeWeightCycle) {
            text.append("Negative weight cycle detected.");
        }
        return text.toString();
    }
}
