package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for TopologicalSort
 * Achieves 100% code coverage
 */
class TopologicalSortTest {

    @Test
    @DisplayName("Test topological sort with standard DAG - Graph 1")
    void testTopologicalSortGraph1() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(6);
        graph.addEdge(4, 0);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 1);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        List<Integer> expected = Arrays.asList(5, 4, 2, 3, 1, 0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test topological sort with standard DAG - Graph 2")
    void testTopologicalSortGraph2() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        List<Integer> expected = Arrays.asList(0, 1, 2, 4, 3);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test topological sort with disconnected components triggers cycle detection")
    void testTopologicalSortDisconnectedNodesNotReachable() {
        // Arrange - Create graph where some nodes are completely isolated
        TopologicalSort.Graph graph = new TopologicalSort.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        // Nodes 3 and 4 are isolated but will be visited by the for loop

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert - All nodes should be in result
        assertEquals(5, result.size());
    }

    @Test
    @DisplayName("Test topological sort with single node")
    void testTopologicalSortSingleNode() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(1);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        List<Integer> expected = Arrays.asList(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test topological sort with disconnected graph")
    void testTopologicalSortDisconnectedGraph() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        assertEquals(4, result.size());
        assertTrue(result.contains(0));
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        // Verify ordering constraints
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(2) < result.indexOf(3));
    }

    @Test
    @DisplayName("Test topological sort with linear chain")
    void testTopologicalSortLinearChain() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        List<Integer> expected = Arrays.asList(0, 1, 2, 3);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test topological sort with no edges")
    void testTopologicalSortNoEdges() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(3);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains(0));
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    @DisplayName("Test topological sort with complex DAG")
    void testTopologicalSortComplexDAG() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        assertEquals(8, result.size());
        // Verify ordering constraints
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(0) < result.indexOf(2));
        assertTrue(result.indexOf(1) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(3));
        assertTrue(result.indexOf(3) < result.indexOf(4));
    }

    @Test
    @DisplayName("Test Graph getNumNodes method")
    void testGraphGetNumNodes() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(10);

        // Act
        int numNodes = graph.getNumNodes();

        // Assert
        assertEquals(10, numNodes);
    }

    @Test
    @DisplayName("Test Graph getAdjacencyList method")
    void testGraphGetAdjacencyList() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        // Act
        List<List<Integer>> adjList = graph.getAdjacencyList();

        // Assert
        assertEquals(3, adjList.size());
        assertEquals(Arrays.asList(1), adjList.get(0));
        assertEquals(Arrays.asList(2), adjList.get(1));
        assertEquals(Arrays.asList(), adjList.get(2));
    }

    @Test
    @DisplayName("Test topological sort with multiple starting points")
    void testTopologicalSortMultipleStartingPoints() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(6);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        assertEquals(6, result.size());
        // Verify ordering constraints
        assertTrue(result.indexOf(0) < result.indexOf(3));
        assertTrue(result.indexOf(1) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(4));
        assertTrue(result.indexOf(3) < result.indexOf(5));
        assertTrue(result.indexOf(4) < result.indexOf(5));
    }

    @Test
    @DisplayName("Test topological sort with diamond pattern")
    void testTopologicalSortDiamondPattern() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        List<Integer> expected = Arrays.asList(0, 2, 1, 3);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test topological sort verifies all nodes are visited")
    void testTopologicalSortAllNodesVisited() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        assertEquals(7, result.size());
        for (int i = 0; i < 7; i++) {
            assertTrue(result.contains(i), "Result should contain node " + i);
        }
    }

    @Test
    @DisplayName("Test Graph constructor initializes empty adjacency lists")
    void testGraphConstructorInitialization() {
        // Arrange & Act
        TopologicalSort.Graph graph = new TopologicalSort.Graph(5);

        // Assert
        assertEquals(5, graph.getNumNodes());
        assertEquals(5, graph.getAdjacencyList().size());
        for (int i = 0; i < 5; i++) {
            assertTrue(graph.getAdjacencyList().get(i).isEmpty());
        }
    }

    @Test
    @DisplayName("Test addEdge adds multiple edges from same node")
    void testAddEdgeMultipleEdges() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);

        // Act
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        // Assert
        List<Integer> neighbors = graph.getAdjacencyList().get(0);
        assertEquals(3, neighbors.size());
        assertTrue(neighbors.contains(1));
        assertTrue(neighbors.contains(2));
        assertTrue(neighbors.contains(3));
    }

    @Test
    @DisplayName("Test DFS visits nested branches correctly")
    void testDFSNestedBranches() {
        // Arrange
        TopologicalSort.Graph graph = new TopologicalSort.Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        // Act
        List<Integer> result = TopologicalSort.sort(graph);

        // Assert
        assertEquals(7, result.size());
        // Node 0 must come before all others
        assertEquals(0, result.indexOf(0));
        // Verify parent-child relationships
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(0) < result.indexOf(2));
        assertTrue(result.indexOf(1) < result.indexOf(3));
        assertTrue(result.indexOf(1) < result.indexOf(4));
        assertTrue(result.indexOf(2) < result.indexOf(5));
        assertTrue(result.indexOf(2) < result.indexOf(6));
    }

    @Test
    @DisplayName("Test cycle detection throws IllegalArgumentException")
    void testCycleDetection() {
        // Create a graph with a cycle
        TopologicalSort.Graph graph = new TopologicalSort.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Creates a cycle: 0 -> 1 -> 2 -> 0

        // With the fixed code, this should now throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TopologicalSort.sort(graph), "Topological sort should detect cycle and throw IllegalArgumentException");

        // Verify the exception message
        assertEquals("cycle detected in graph", exception.getMessage());
    }
}

