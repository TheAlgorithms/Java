package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KruskalTest {

    private Kruskal kruskal;
    private HashSet<Kruskal.Edge>[] graph;

    @BeforeEach
    public void setUp() {
        kruskal = new Kruskal();
        int n = 7;
        graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }

        // Add edges to the graph
        Kruskal.addEdge(graph, 0, 1, 2);
        Kruskal.addEdge(graph, 0, 2, 3);
        Kruskal.addEdge(graph, 0, 3, 3);
        Kruskal.addEdge(graph, 1, 2, 4);
        Kruskal.addEdge(graph, 2, 3, 5);
        Kruskal.addEdge(graph, 1, 4, 3);
        Kruskal.addEdge(graph, 2, 4, 1);
        Kruskal.addEdge(graph, 3, 5, 7);
        Kruskal.addEdge(graph, 4, 5, 8);
        Kruskal.addEdge(graph, 5, 6, 9);
    }

    @Test
    public void testKruskal() {
        int n = 6;
        HashSet<Kruskal.Edge>[] graph = new HashSet[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }

        Kruskal.addEdge(graph, 0, 1, 4);
        Kruskal.addEdge(graph, 0, 2, 2);
        Kruskal.addEdge(graph, 1, 2, 1);
        Kruskal.addEdge(graph, 1, 3, 5);
        Kruskal.addEdge(graph, 2, 3, 8);
        Kruskal.addEdge(graph, 2, 4, 10);
        Kruskal.addEdge(graph, 3, 4, 2);
        Kruskal.addEdge(graph, 3, 5, 6);
        Kruskal.addEdge(graph, 4, 5, 3);

        HashSet<Kruskal.Edge>[] result = kruskal.kruskal(graph);

        List<List<Integer>> actualEdges = new ArrayList<>();
        for (HashSet<Kruskal.Edge> edges : result) {
            for (Kruskal.Edge edge : edges) {
                actualEdges.add(Arrays.asList(edge.from, edge.to, edge.weight));
            }
        }

        List<List<Integer>> expectedEdges = Arrays.asList(Arrays.asList(1, 2, 1), Arrays.asList(0, 2, 2), Arrays.asList(3, 4, 2), Arrays.asList(4, 5, 3), Arrays.asList(1, 3, 5));

        assertTrue(actualEdges.containsAll(expectedEdges) && expectedEdges.containsAll(actualEdges));
    }

    @Test
    public void testEmptyGraph() {
        HashSet<Kruskal.Edge>[] emptyGraph = new HashSet[0];
        HashSet<Kruskal.Edge>[] result = kruskal.kruskal(emptyGraph);
        assertEquals(0, result.length);
    }

    @Test
    public void testSingleNodeGraph() {
        HashSet<Kruskal.Edge>[] singleNodeGraph = new HashSet[1];
        singleNodeGraph[0] = new HashSet<>();
        HashSet<Kruskal.Edge>[] result = kruskal.kruskal(singleNodeGraph);
        assertTrue(result[0].isEmpty());
    }

    @Test
    public void testGraphWithDisconnectedNodes() {
        int n = 5;
        HashSet<Kruskal.Edge>[] disconnectedGraph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            disconnectedGraph[i] = new HashSet<>();
        }

        Kruskal.addEdge(disconnectedGraph, 0, 1, 2);
        Kruskal.addEdge(disconnectedGraph, 2, 3, 4);

        HashSet<Kruskal.Edge>[] result = kruskal.kruskal(disconnectedGraph);

        List<List<Integer>> actualEdges = new ArrayList<>();
        for (HashSet<Kruskal.Edge> edges : result) {
            for (Kruskal.Edge edge : edges) {
                actualEdges.add(Arrays.asList(edge.from, edge.to, edge.weight));
            }
        }

        List<List<Integer>> expectedEdges = Arrays.asList(Arrays.asList(0, 1, 2), Arrays.asList(2, 3, 4));

        assertTrue(actualEdges.containsAll(expectedEdges) && expectedEdges.containsAll(actualEdges));
    }
}
