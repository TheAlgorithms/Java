package com.thealgorithms.datastructures.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Test cases for TopologicalSortDFS
 * 
 * @author Your Name
 */
class TopologicalSortDFSTest {
    
    @Test
    void testSimpleDAG() {
        // Create a simple DAG: 0 -> 1 -> 2
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        
        List<Integer> result = graph.topologicalSort();
        
        assertNotNull(result, "Topological sort should not be null for valid DAG");
        assertEquals(3, result.size(), "Result should contain all vertices");
        
        // Verify order: 0 should come before 1, and 1 before 2
        assertTrue(result.indexOf(0) < result.indexOf(1), "Vertex 0 should come before vertex 1");
        assertTrue(result.indexOf(1) < result.indexOf(2), "Vertex 1 should come before vertex 2");
    }
    
    @Test
    void testComplexDAG() {
        // Create a more complex DAG
        TopologicalSortDFS graph = new TopologicalSortDFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        
        List<Integer> result = graph.topologicalSort();
        
        assertNotNull(result, "Topological sort should not be null for valid DAG");
        assertEquals(6, result.size(), "Result should contain all vertices");
        
        // Verify dependencies are respected
        assertTrue(result.indexOf(5) < result.indexOf(2), "5 should come before 2");
        assertTrue(result.indexOf(5) < result.indexOf(0), "5 should come before 0");
        assertTrue(result.indexOf(4) < result.indexOf(0), "4 should come before 0");
        assertTrue(result.indexOf(4) < result.indexOf(1), "4 should come before 1");
        assertTrue(result.indexOf(2) < result.indexOf(3), "2 should come before 3");
        assertTrue(result.indexOf(3) < result.indexOf(1), "3 should come before 1");
    }
    
    @Test
    void testCyclicGraph() {
        // Create a cyclic graph: 0 -> 1 -> 2 -> 0
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Creates cycle
        
        List<Integer> result = graph.topologicalSort();
        
        assertNull(result, "Topological sort should return null for cyclic graph");
        assertTrue(graph.hasCycle(), "Graph should be detected as cyclic");
    }
    
    @Test
    void testSingleVertex() {
        // Graph with single vertex
        TopologicalSortDFS graph = new TopologicalSortDFS(1);
        
        List<Integer> result = graph.topologicalSort();
        
        assertNotNull(result, "Topological sort should work for single vertex");
        assertEquals(1, result.size(), "Result should contain the single vertex");
        assertEquals(0, result.get(0), "Single vertex should be 0");
    }
    
    @Test
    void testDisconnectedGraph() {
        // Graph with disconnected components: 0 -> 1 and 2 -> 3
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        
        List<Integer> result = graph.topologicalSort();
        
        assertNotNull(result, "Topological sort should work for disconnected graph");
        assertEquals(4, result.size(), "Result should contain all vertices");
        
        // Verify ordering within components
        assertTrue(result.indexOf(0) < result.indexOf(1), "0 should come before 1");
        assertTrue(result.indexOf(2) < result.indexOf(3), "2 should come before 3");
    }
    
    @Test
    void testEmptyGraph() {
        // Graph with no edges
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        
        List<Integer> result = graph.topologicalSort();
        
        assertNotNull(result, "Topological sort should work for graph with no edges");
        assertEquals(3, result.size(), "Result should contain all vertices");
    }
    
    @Test
    void testSelfLoop() {
        // Graph with self-loop: 0 -> 0
        TopologicalSortDFS graph = new TopologicalSortDFS(2);
        graph.addEdge(0, 0); // Self-loop
        
        List<Integer> result = graph.topologicalSort();
        
        assertNull(result, "Topological sort should return null for graph with self-loop");
        assertTrue(graph.hasCycle(), "Self-loop should be detected as cycle");
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
        
        assertNotNull(result, "Topological sort should work for linear chain");
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), result, "Linear chain should maintain order");
    }
    
    @Test
    void testMultiplePaths() {
        // Graph with multiple paths: 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        
        List<Integer> result = graph.topologicalSort();
        
        assertNotNull(result, "Topological sort should work for graph with multiple paths");
        assertEquals(4, result.size(), "Result should contain all vertices");
        
        // Verify all dependencies
        assertTrue(result.indexOf(0) < result.indexOf(1), "0 should come before 1");
        assertTrue(result.indexOf(0) < result.indexOf(2), "0 should come before 2");
        assertTrue(result.indexOf(1) < result.indexOf(3), "1 should come before 3");
        assertTrue(result.indexOf(2) < result.indexOf(3), "2 should come before 3");
    }
    
    @Test
    void testInvalidVertexIndex() {
        TopologicalSortDFS graph = new TopologicalSortDFS(3);
        
        // Test invalid source vertex
        assertThrows(IllegalArgumentException.class, () -> {
            graph.addEdge(-1, 1);
        }, "Should throw exception for negative vertex index");
        
        // Test invalid destination vertex
        assertThrows(IllegalArgumentException.class, () -> {
            graph.addEdge(0, 5);
        }, "Should throw exception for vertex index out of bounds");
    }
    
    @Test
    void testComplexCycle() {
        // More complex cycle: 0 -> 1 -> 2 -> 3 -> 1
        TopologicalSortDFS graph = new TopologicalSortDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1); // Creates cycle back to 1
        
        List<Integer> result = graph.topologicalSort();
        
        assertNull(result, "Topological sort should return null for complex cycle");
        assertTrue(graph.hasCycle(), "Complex cycle should be detected");
    }
}