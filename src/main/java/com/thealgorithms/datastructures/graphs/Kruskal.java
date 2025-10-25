package com.thealgorithms.datastructures.graphs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * The Kruskal class implements Kruskal's Algorithm to find the Minimum Spanning Tree (MST)
 * of a connected, undirected graph. The algorithm constructs the MST by selecting edges
 * with the least weight, ensuring no cycles are formed, and using union-find to track the
 * connected components.
 *
 * <p><strong>Key Features:</strong></p>
 * <ul>
 *   <li>The graph is represented using an adjacency list, where each node points to a set of edges.</li>
 *   <li>Each edge is processed in ascending order of weight using a priority queue.</li>
 *   <li>The algorithm stops when all nodes are connected or no more edges are available.</li>
 * </ul>
 *
 * <p><strong>Time Complexity:</strong> O(E log V), where E is the number of edges and V is the number of vertices.</p>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Kruskal {

    /**
     * Represents an edge in the graph with a source, destination, and weight.
     */
    static class Edge {

        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Adds an edge to the graph.
     *
     * @param graph the adjacency list representing the graph
     * @param from the source vertex of the edge
     * @param to the destination vertex of the edge
     * @param weight the weight of the edge
     */
    static void addEdge(HashSet<Edge>[] graph, int from, int to, int weight) {
        graph[from].add(new Edge(from, to, weight));
    }

    /**
     * Kruskal's algorithm to find the Minimum Spanning Tree (MST) of a graph.
     *
     * @param graph the adjacency list representing the input graph
     * @return the adjacency list representing the MST
     */
    public HashSet<Edge>[] kruskal(HashSet<Edge>[] graph) {
        int nodes = graph.length;
        int[] captain = new int[nodes]; // Stores the "leader" of each node's connected component
        HashSet<Integer>[] connectedGroups = new HashSet[nodes];
        HashSet<Edge>[] minGraph = new HashSet[nodes];
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        for (int i = 0; i < nodes; i++) {
            minGraph[i] = new HashSet<>();
            connectedGroups[i] = new HashSet<>();
            connectedGroups[i].add(i);
            captain[i] = i;
            edges.addAll(graph[i]);
        }
        int connectedElements = 0;
        while (connectedElements != nodes && !edges.isEmpty()) {
            Edge edge = edges.poll();

            // Avoid forming cycles by checking if the nodes belong to different connected components
            if (!connectedGroups[captain[edge.from]].contains(edge.to) && !connectedGroups[captain[edge.to]].contains(edge.from)) {
                // Merge the two sets of nodes connected by the edge
                connectedGroups[captain[edge.from]].addAll(connectedGroups[captain[edge.to]]);

                // Update the captain for each merged node
                connectedGroups[captain[edge.from]].forEach(i -> captain[i] = captain[edge.from]);

                // Add the edge to the resulting MST graph
                addEdge(minGraph, edge.from, edge.to, edge.weight);

                // Update the count of connected nodes
                connectedElements = connectedGroups[captain[edge.from]].size();
            }
        }
        return minGraph;
    }
}
