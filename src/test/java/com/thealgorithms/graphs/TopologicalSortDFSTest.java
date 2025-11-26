package com.thealgorithms.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for TopologicalSortDFS
 * Tests various scenarios including basic DAG, cycles, disconnected graphs, and edge cases
 *
 * @author Gowtham
 */
class TopologicalSortDFSTest {

    @Test
    void testBasicDAG() {
        // Create a simple DAG: 0 -> 1 -> 2
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        List<Integer> result = graph.topologicalSort();

        // Valid topological orders: [0, 1, 2]
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(1, result.get(1));
        assertEquals(2, result.get(2));
        assertTrue(graph.isDAG());
    }

    @Test
    void testComplexDAG() {
        // Create a more complex DAG
        // 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3, 3 -> 4
        TopologicalSortDFS graph = new TopologicalSortDFS(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<Integer> result = graph.topologicalSort();

        assertEquals(5, result.size());
        assertEquals(0, result.get(0)); // 0 must come first
        assertEquals(4, result.get(4)); // 4 must come last

        // Verify order: 1 comes before 3, 2 comes before 3, 3 comes before 4
        int idx1 = result.indexOf(1);
        int idx2 = result.indexOf(2);
        int idx3 = result.indexOf(3);
        int idx4 = result.indexOf(4);

        assertTrue(idx1 < idx3);
        assertTrue(idx2 < idx3);
        assertTrue(idx3 < idx4);
        assertTrue(graph.isDAG());
    }

    @Test
    void testGraphWithCycle() {
        // Create a graph with a cycle: 0 -> 1 -> 2 -> 0
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        assertFalse(graph.isDAG());
        assertThrows(IllegalArgumentException.class, graph::topologicalSort);
    }

    @Test
    void testSelfLoop() {
        // Graph with self-loop
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 1); // Self-loop

        assertFalse(graph.isDAG());
        assertThrows(IllegalArgumentException.class, graph::topologicalSort);
    }

    @Test
    void testDisconnectedGraph() {
        // Disconnected graph: 0 -> 1, 2 -> 3 (no connection between them)
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        List<Integer> result = graph.topologicalSort();

        assertEquals(4, result.size());
        assertTrue(graph.isDAG());

        // Verify order within connected components
        int idx0 = result.indexOf(0);
        int idx1 = result.indexOf(1);
        int idx2 = result.indexOf(2);
        int idx3 = result.indexOf(3);

        assertTrue(idx0 < idx1);
        assertTrue(idx2 < idx3);
    }

    @Test
    void testSingleVertex() {
        // Graph with single vertex
        TopologicalSortDFS graph = new TopologicalSortDFS(1);

        List<Integer> result = graph.topologicalSort();

        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
        assertTrue(graph.isDAG());
    }

    @Test
    void testEmptyGraph() {
        // Graph with no edges
        TopologicalSortDFS graph = new TopologicalSortDFS(3);

        List<Integer> result = graph.topologicalSort();

        assertEquals(3, result.size());
        assertTrue(graph.isDAG());
    }

    @Test
    void testLinearChain() {
        // Linear chain: 0 -> 1 -> 2 -> 3 -> 4
        TopologicalSortDFS graph = new TopologicalSortDFS(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<Integer> result = graph.topologicalSort();

        assertEquals(5, result.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(i, result.get(i));
        }
        assertTrue(graph.isDAG());
    }

    @Test
    void testComplexCycle() {
        // More complex cycle: 0 -> 1 -> 2 -> 3 -> 1 (cycle between 1, 2, 3)
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        assertFalse(graph.isDAG());
        assertThrows(IllegalArgumentException.class, graph::topologicalSort);
    }

    @Test
    void testMultipleValidOrders() {
        // Graph with multiple valid topological orders
        // 0 -> 2, 1 -> 2
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);

        List<Integer> result = graph.topologicalSort();

        assertEquals(3, result.size());
        assertEquals(2, result.get(2)); // 2 must be last
        assertTrue(graph.isDAG());

        // Either 0 or 1 can be first, but both must come before 2
        int idx0 = result.indexOf(0);
        int idx1 = result.indexOf(1);
        int idx2 = result.indexOf(2);

        assertTrue(idx0 < idx2);
        assertTrue(idx1 < idx2);
    }

    @Test
    void testDiamondShape() {
        // Diamond shape DAG: 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        List<Integer> result = graph.topologicalSort();

        assertEquals(4, result.size());
        assertEquals(0, result.get(0)); // 0 must be first
        assertEquals(3, result.get(3)); // 3 must be last
        assertTrue(graph.isDAG());

        // Verify partial order
        int idx1 = result.indexOf(1);
        int idx2 = result.indexOf(2);
        int idx3 = result.indexOf(3);

        assertTrue(idx1 < idx3);
        assertTrue(idx2 < idx3);
    }
}
