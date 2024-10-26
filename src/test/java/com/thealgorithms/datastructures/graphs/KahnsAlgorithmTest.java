package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class KahnsAlgorithmTest {

    @Test
    void testBasicGraph() {
        // Test case with a basic directed acyclic graph (DAG)
        AdjacencyList<String> graph = new AdjacencyList<>();
        graph.addEdge("a", "b");
        graph.addEdge("c", "a");
        graph.addEdge("a", "d");
        graph.addEdge("b", "d");

        TopologicalSort<String> topSort = new TopologicalSort<>(graph);
        ArrayList<String> result = topSort.topSortOrder();

        String[] expectedOrder = {"c", "a", "b", "d"};
        assertArrayEquals(expectedOrder, result.toArray());
    }

    @Test
    void testGraphWithMultipleSources() {
        // Test case where graph has multiple independent sources
        AdjacencyList<String> graph = new AdjacencyList<>();
        graph.addEdge("a", "c");
        graph.addEdge("b", "c");

        TopologicalSort<String> topSort = new TopologicalSort<>(graph);
        ArrayList<String> result = topSort.topSortOrder();

        String[] expectedOrder = {"a", "b", "c"};
        assertArrayEquals(expectedOrder, result.toArray());
    }

    @Test
    void testDisconnectedGraph() {
        // Test case for disconnected graph
        AdjacencyList<String> graph = new AdjacencyList<>();
        graph.addEdge("a", "b");
        graph.addEdge("c", "d");

        TopologicalSort<String> topSort = new TopologicalSort<>(graph);
        ArrayList<String> result = topSort.topSortOrder();

        String[] expectedOrder = {"a", "c", "b", "d"};
        assertArrayEquals(expectedOrder, result.toArray());
    }

    @Test
    void testGraphWithCycle() {
        // Test case for a graph with a cycle - topological sorting is not possible
        AdjacencyList<String> graph = new AdjacencyList<>();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "a");

        TopologicalSort<String> topSort = new TopologicalSort<>(graph);

        assertThrows(IllegalStateException.class, () -> topSort.topSortOrder());
    }

    @Test
    void testSingleNodeGraph() {
        AdjacencyList<String> graph = new AdjacencyList<>();
        graph.addEdge("a", "a"); // self-loop

        TopologicalSort<String> topSort = new TopologicalSort<>(graph);

        assertThrows(IllegalStateException.class, () -> topSort.topSortOrder());
    }
}
