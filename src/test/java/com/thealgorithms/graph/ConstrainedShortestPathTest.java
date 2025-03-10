package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.graph.ConstrainedShortestPath.Graph;
import org.junit.jupiter.api.Test;

public class ConstrainedShortestPathTest {

    /**
     * Tests a simple linear graph to verify if the solver calculates the shortest path correctly.
     * Expected: The minimal path cost from node 0 to node 2 should be 5 while not exceeding the resource limit.
     */
    @Test
    public void testSimpleGraph() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 2, 3);
        graph.addEdge(1, 2, 3, 2);

        int maxResource = 5;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(5, solver.solve(0, 2));
    }

    /**
     * Tests a graph where no valid path exists due to resource constraints.
     * Expected: The solver should return -1, indicating no path is feasible.
     */
    @Test
    public void testNoPath() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 2, 6);
        graph.addEdge(1, 2, 3, 6);

        int maxResource = 5;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(-1, solver.solve(0, 2));
    }

    /**
     * Tests a graph with multiple paths between source and destination.
     * Expected: The solver should choose the path with the minimal cost of 5, considering the resource limit.
     */
    @Test
    public void testMultiplePaths() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1, 1);
        graph.addEdge(1, 3, 5, 2);
        graph.addEdge(0, 2, 2, 1);
        graph.addEdge(2, 3, 3, 2);

        int maxResource = 3;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(5, solver.solve(0, 3));
    }

    /**
     * Verifies that the solver allows a path exactly matching the resource limit.
     * Expected: The path is valid with a total cost of 5.
     */
    @Test
    public void testExactResourceLimit() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 2, 3);
        graph.addEdge(1, 2, 3, 2);

        int maxResource = 5;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(5, solver.solve(0, 2));
    }

    /**
     * Tests a disconnected graph where the destination node cannot be reached.
     * Expected: The solver should return -1, as the destination is unreachable.
     */
    @Test
    public void testDisconnectedGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2, 2);
        graph.addEdge(2, 3, 3, 2);

        int maxResource = 5;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(-1, solver.solve(0, 3));
    }

    /**
     * Tests a graph with cycles to ensure the solver does not fall into infinite loops and correctly calculates costs.
     * Expected: The solver should compute the minimal path cost of 6.
     */
    @Test
    public void testGraphWithCycles() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2, 1);
        graph.addEdge(1, 2, 3, 1);
        graph.addEdge(2, 0, 1, 1);
        graph.addEdge(1, 3, 4, 2);

        int maxResource = 3;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(6, solver.solve(0, 3));
    }

    /**
     * Tests the solver's performance and correctness on a large linear graph with 1000 nodes.
     * Expected: The solver should efficiently calculate the shortest path with a cost of 999.
     */
    @Test
    public void testLargeGraphPerformance() {
        int nodeCount = 1000;
        Graph graph = new Graph(nodeCount);
        for (int i = 0; i < nodeCount - 1; i++) {
            graph.addEdge(i, i + 1, 1, 1);
        }

        int maxResource = 1000;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(999, solver.solve(0, nodeCount - 1));
    }

    /**
     * Tests a graph with isolated nodes to ensure the solver recognizes unreachable destinations.
     * Expected: The solver should return -1 for unreachable nodes.
     */
    @Test
    public void testIsolatedNodes() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2, 1);
        graph.addEdge(1, 2, 3, 1);

        int maxResource = 5;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(-1, solver.solve(0, 3));
    }

    /**
     * Tests a cyclic large graph with multiple overlapping paths.
     * Expected: The solver should calculate the shortest path cost of 5.
     */
    @Test
    public void testCyclicLargeGraph() {
        Graph graph = new Graph(10);
        for (int i = 0; i < 9; i++) {
            graph.addEdge(i, (i + 1) % 10, 1, 1);
        }
        graph.addEdge(0, 5, 5, 3);

        int maxResource = 10;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(5, solver.solve(0, 5));
    }

    /**
     * Tests a large complex graph with multiple paths and varying resource constraints.
     * Expected: The solver should identify the optimal path with a cost of 19 within the resource limit.
     */
    @Test
    public void testLargeComplexGraph() {
        Graph graph = new Graph(10);
        graph.addEdge(0, 1, 4, 2);
        graph.addEdge(0, 2, 3, 3);
        graph.addEdge(1, 3, 2, 1);
        graph.addEdge(2, 3, 5, 2);
        graph.addEdge(2, 4, 8, 4);
        graph.addEdge(3, 5, 7, 3);
        graph.addEdge(3, 6, 6, 2);
        graph.addEdge(4, 6, 3, 2);
        graph.addEdge(5, 7, 1, 1);
        graph.addEdge(6, 7, 2, 2);
        graph.addEdge(7, 8, 3, 1);
        graph.addEdge(8, 9, 2, 1);

        int maxResource = 10;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(19, solver.solve(0, 9));
    }

    /**
     * Edge case test where the graph has only one node and no edges.
     * Expected: The minimal path cost is 0, as the start and destination are the same.
     */
    @Test
    public void testSingleNodeGraph() {
        Graph graph = new Graph(1);

        int maxResource = 0;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(0, solver.solve(0, 0));
    }

    /**
     * Tests a graph with multiple paths but a tight resource constraint.
     * Expected: The solver should return -1 if no path can be found within the resource limit.
     */
    @Test
    public void testTightResourceConstraint() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 3, 4);
        graph.addEdge(1, 2, 1, 2);
        graph.addEdge(0, 2, 2, 2);

        int maxResource = 3;
        ConstrainedShortestPath solver = new ConstrainedShortestPath(graph, maxResource);

        assertEquals(2, solver.solve(0, 2));
    }
}
