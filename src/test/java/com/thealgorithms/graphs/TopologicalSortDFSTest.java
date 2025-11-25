package com.thealgorithms.graphs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test cases for TopologicalSortDFS
 *
 * @author gowtham1412-p
 */
class TopologicalSortDFSTest {

    @Test
    void testSimpleDAG() {
        TopologicalSortDFS graph = new TopologicalSortDFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        List<Integer> result = graph.topologicalSort();

        // Verify the order is valid
        assertTrue(isValidTopologicalOrder(result, new int[][] {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}}));
    }

    @Test
    void testLinearGraph() {
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        List<Integer> result = graph.topologicalSort();
        assertEquals(Arrays.asList(0, 1, 2, 3), result);
    }

    @Test
    void testSingleVertex() {
        TopologicalSortDFS graph = new TopologicalSortDFS(1);
        List<Integer> result = graph.topologicalSort();
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    void testDisconnectedGraph() {
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        List<Integer> result = graph.topologicalSort();
        assertEquals(4, result.size());
    }

    @Test
    void testCycleDetection() {
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Creates a cycle

        assertThrows(IllegalArgumentException.class, () -> { graph.topologicalSort(); });
    }

    @Test
    void testSelfLoop() {
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 1); // Self loop

        assertThrows(IllegalArgumentException.class, () -> { graph.topologicalSort(); });
    }

    @Test
    void testIsDAG() {
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.isDAG());
    }

    @Test
    void testIsNotDAG() {
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        assertFalse(graph.isDAG());
    }

    @Test
    void testInvalidVertex() {
        TopologicalSortDFS graph = new TopologicalSortDFS(3);

        assertThrows(IllegalArgumentException.class, () -> { graph.addEdge(-1, 0); });

        assertThrows(IllegalArgumentException.class, () -> { graph.addEdge(0, 5); });
    }

    @Test
    void testComplexDAG() {
        TopologicalSortDFS graph = new TopologicalSortDFS(8);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 7);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 6);

        List<Integer> result = graph.topologicalSort();

        // Verify valid ordering
        assertTrue(result.indexOf(0) < result.indexOf(3));
        assertTrue(result.indexOf(3) < result.indexOf(5));
        assertTrue(result.indexOf(4) < result.indexOf(6));
    }

    /**
     * Helper method to verify topological order
     */
    private boolean isValidTopologicalOrder(List<Integer> order, int[][] edges) {
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (order.indexOf(u) > order.indexOf(v)) {
                return false;
            }
        }
        return true;
    }
}
