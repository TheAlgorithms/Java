package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HierholzerEulerianPath}.
 *
 * This test suite validates Hierholzer's Algorithm implementation
 * for finding Eulerian Paths and Circuits in directed graphs.
 *
 * <p>Coverage includes:
 * <ul>
 *   <li>Basic Eulerian Circuit</li>
 *   <li>Eulerian Path</li>
 *   <li>Disconnected graphs</li>
 *   <li>Single-node graphs</li>
 *   <li>Graphs with no edges</li>
 *   <li>Graphs that do not have any Eulerian Path/Circuit</li>
 * </ul>
 * </p>
 */
class HierholzerEulerianPathTest {

    @Test
    void testSimpleEulerianCircuit() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Eulerian Circuit: [0, 1, 2, 0]
        List<Integer> expected = Arrays.asList(0, 1, 2, 0);
        assertEquals(expected, result);
    }

    @Test
    void testEulerianPathDifferentStartEnd() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Eulerian Path: [0, 1, 2, 3, 1]
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 1);
        assertEquals(expected, result);
    }

    @Test
    void testNoEulerianPathExists() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        // Edge 2->0 missing, so it's not Eulerian Circuit

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        assertEquals(result, Arrays.asList(0, 1, 2));
    }

    @Test
    void testDisconnectedGraph() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3); // disconnected component

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Disconnected graph cannot have an Eulerian path
        assertTrue(result.isEmpty());
    }

    @Test
    void testGraphWithSelfLoop() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(3);
        graph.addEdge(0, 0); // self loop
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Eulerian Circuit with self-loop included: [0, 0, 1, 2, 0]
        assertEquals(Arrays.asList(0, 0, 1, 2, 0), result);
    }

    @Test
    void testSingleNodeNoEdges() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(1);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Only one vertex and no edges
        assertEquals(Collections.singletonList(0), result);
    }

    @Test
    void testSingleNodeWithLoop() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(1);
        graph.addEdge(0, 0);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Eulerian circuit on a single node with a self-loop
        assertEquals(Arrays.asList(0, 0), result);
    }

    @Test
    void testComplexEulerianCircuit() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 1);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Verify all edges are used
        int totalEdges = 7;
        assertEquals(totalEdges + 1, result.size(), "Path must contain all edges + 1 vertices");
        assertEquals(result.get(0), result.get(result.size() - 1), "Must form a circuit");
    }

    @Test
    void testMultipleEdgesBetweenSameNodes() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Hava a Eulerian Path but not a Eulerian Circuit
        assertEquals(result, Arrays.asList(0, 1, 2, 0, 1));
    }

    @Test
    void testCoverageForEmptyGraph() {
        HierholzerEulerianPath.Graph graph = new HierholzerEulerianPath.Graph(0);
        HierholzerEulerianPath solver = new HierholzerEulerianPath(graph);
        List<Integer> result = solver.findEulerianPath();

        // Empty graph has no vertices or path
        assertTrue(result.isEmpty());
    }
}
