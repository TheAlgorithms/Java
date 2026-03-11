package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for {@link TopologicalSortDFS}.
 */
class TopologicalSortDFSTest {

    private static void assertValidTopologicalOrder(List<Integer> order, int[][] edges) {
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            assertTrue(order.indexOf(u) < order.indexOf(v), "Expected " + u + " to appear before " + v + " in topological order. Got: " + order);
        }
    }

    @Test
    void testSimpleLinearGraph() {
        TopologicalSortDFS graph = new TopologicalSortDFS();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        List<Integer> result = graph.topologicalSort();
        assertEquals(List.of(0, 1, 2, 3), result);
    }

    @Test
    void testDAGWithMultiplePaths() {
        // 5 → 2, 5 → 0, 4 → 0, 4 → 1, 2 → 3, 3 → 1
        TopologicalSortDFS graph = new TopologicalSortDFS();
        int[][] edges = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        List<Integer> result = graph.topologicalSort();
        assertEquals(6, result.size());
        assertValidTopologicalOrder(result, edges);
    }

    @Test
    void testBuildOrderDAG() {
        // 0 → 1, 0 → 2, 1 → 3, 2 → 3
        TopologicalSortDFS graph = new TopologicalSortDFS();
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        List<Integer> result = graph.topologicalSort();
        assertEquals(4, result.size());
        assertValidTopologicalOrder(result, edges);
    }

    @Test
    void testSingleVertex() {
        TopologicalSortDFS graph = new TopologicalSortDFS();
        graph.addVertex(42);

        List<Integer> result = graph.topologicalSort();
        assertEquals(List.of(42), result);
    }

    @Test
    void testDisconnectedGraph() {
        TopologicalSortDFS graph = new TopologicalSortDFS();
        int[][] edges = {{0, 1}, {2, 3}};
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        List<Integer> result = graph.topologicalSort();
        assertEquals(4, result.size());
        assertValidTopologicalOrder(result, edges);
    }

    @Test
    void testSimpleCycleThrowsException() {
        TopologicalSortDFS graph = new TopologicalSortDFS();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        assertThrows(IllegalStateException.class, graph::topologicalSort, "Expected IllegalStateException for cyclic graph");
    }

    @Test
    void testSelfLoopThrowsException() {
        TopologicalSortDFS graph = new TopologicalSortDFS();
        graph.addEdge(0, 0);

        assertThrows(IllegalStateException.class, graph::topologicalSort, "Expected IllegalStateException for self-loop");
    }

    @Test
    void testLargerCycleThrowsException() {
        TopologicalSortDFS graph = new TopologicalSortDFS();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);

        assertThrows(IllegalStateException.class, graph::topologicalSort, "Expected IllegalStateException for graph with cycle");
    }
}