package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test cases for TopologicalSortDFS
 *
 * @author Raghu0703
 */
class TopologicalSortDFSTest {

    /**
     * Helper method to create a graph with given number of vertices
     */
    private List<List<Integer>> createGraph(int vertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Helper method to add a directed edge to the graph
     */
    private void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
    }

    @Test
    void testSimpleDAG() {
        // Graph: 0 → 1 → 2
        //        ↓
        //        3
        List<List<Integer>> graph = createGraph(4);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 3);
        addEdge(graph, 1, 2);

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        // Valid topological orders: [0, 1, 3, 2] or [0, 3, 1, 2]
        assertEquals(4, result.size());
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(0) < result.indexOf(3));
        assertTrue(result.indexOf(1) < result.indexOf(2));
    }

    @Test
    void testComplexDAG() {
        // Graph representing course dependencies
        // 5 → 2 → 3 → 1
        //     ↓       ↑
        //     0 → 4 → 
        List<List<Integer>> graph = createGraph(6);
        addEdge(graph, 5, 2);
        addEdge(graph, 5, 0);
        addEdge(graph, 4, 0);
        addEdge(graph, 4, 1);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 1);

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        // Verify dependencies are maintained
        assertEquals(6, result.size());
        assertTrue(result.indexOf(5) < result.indexOf(2));
        assertTrue(result.indexOf(5) < result.indexOf(0));
        assertTrue(result.indexOf(4) < result.indexOf(1));
        assertTrue(result.indexOf(2) < result.indexOf(3));
        assertTrue(result.indexOf(3) < result.indexOf(1));
    }

    @Test
    void testLinearDAG() {
        // Graph: 0 → 1 → 2 → 3 → 4
        List<List<Integer>> graph = createGraph(5);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        assertEquals(Arrays.asList(0, 1, 2, 3, 4), result);
    }

    @Test
    void testDisconnectedDAG() {
        // Graph with disconnected components:
        // 0 → 1    2 → 3
        List<List<Integer>> graph = createGraph(4);
        addEdge(graph, 0, 1);
        addEdge(graph, 2, 3);

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        assertEquals(4, result.size());
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(2) < result.indexOf(3));
    }

    @Test
    void testSingleVertex() {
        // Graph with single vertex and no edges
        List<List<Integer>> graph = createGraph(1);

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        assertEquals(Arrays.asList(0), result);
    }

    @Test
    void testEmptyGraph() {
        // Empty graph
        List<List<Integer>> graph = new ArrayList<>();

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGraphWithSelfLoop() {
        // Graph with self loop: 0 → 0
        List<List<Integer>> graph = createGraph(1);
        addEdge(graph, 0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSortDFS.topologicalSort(graph);
        });
    }

    @Test
    void testSimpleCycle() {
        // Graph with cycle: 0 → 1 → 2 → 0
        List<List<Integer>> graph = createGraph(3);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSortDFS.topologicalSort(graph);
        });
    }

    @Test
    void testComplexCycle() {
        // Graph with cycle: 0 → 1 → 2
        //                    ↑       ↓
        //                    4 ← 3 ← 
        List<List<Integer>> graph = createGraph(5);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSortDFS.topologicalSort(graph);
        });
    }

    @Test
    void testIsDAGWithValidGraph() {
        // Valid DAG: 0 → 1 → 2
        List<List<Integer>> graph = createGraph(3);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);

        assertTrue(TopologicalSortDFS.isDAG(graph));
    }

    @Test
    void testIsDAGWithCycle() {
        // Graph with cycle: 0 → 1 → 2 → 0
        List<List<Integer>> graph = createGraph(3);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);

        assertFalse(TopologicalSortDFS.isDAG(graph));
    }

    @Test
    void testIsDAGWithEmptyGraph() {
        List<List<Integer>> graph = new ArrayList<>();
        assertTrue(TopologicalSortDFS.isDAG(graph));
    }

    @Test
    void testIsDAGWithSelfLoop() {
        List<List<Integer>> graph = createGraph(1);
        addEdge(graph, 0, 0);

        assertFalse(TopologicalSortDFS.isDAG(graph));
    }

    @Test
    void testNullGraph() {
        List<Integer> result = TopologicalSortDFS.topologicalSort(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testTaskSchedulingScenario() {
        // Real-world scenario: Build system dependencies
        // Task 0: Download dependencies
        // Task 1: Compile source
        // Task 2: Run tests
        // Task 3: Package
        // Task 4: Deploy
        // Dependencies: 0 → 1 → 2 → 3 → 4
        //               0 → 2 (tests need dependencies)
        List<List<Integer>> graph = createGraph(5);
        addEdge(graph, 0, 1); // Download before compile
        addEdge(graph, 1, 2); // Compile before test
        addEdge(graph, 2, 3); // Test before package
        addEdge(graph, 3, 4); // Package before deploy
        addEdge(graph, 0, 2); // Download before test

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        // Verify all dependencies are respected
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(1) < result.indexOf(2));
        assertTrue(result.indexOf(2) < result.indexOf(3));
        assertTrue(result.indexOf(3) < result.indexOf(4));
        assertTrue(result.indexOf(0) < result.indexOf(2));
    }

    @Test
    void testCoursePrerequisiteScenario() {
        // Course prerequisites scenario
        // Course 0: Intro to CS
        // Course 1: Data Structures
        // Course 2: Algorithms
        // Course 3: Advanced Algorithms
        // Course 4: Machine Learning
        List<List<Integer>> graph = createGraph(5);
        addEdge(graph, 0, 1); // Intro → Data Structures
        addEdge(graph, 1, 2); // Data Structures → Algorithms
        addEdge(graph, 2, 3); // Algorithms → Advanced Algorithms
        addEdge(graph, 2, 4); // Algorithms → Machine Learning
        addEdge(graph, 1, 4); // Data Structures → Machine Learning

        List<Integer> result = TopologicalSortDFS.topologicalSort(graph);

        // Verify course prerequisites
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(1) < result.indexOf(2));
        assertTrue(result.indexOf(2) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(4));
        assertTrue(result.indexOf(1) < result.indexOf(4));
    }
}
