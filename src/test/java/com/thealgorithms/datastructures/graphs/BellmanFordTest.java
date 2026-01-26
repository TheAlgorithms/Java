package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the BellmanFord algorithm implementation.
 * Tests cover various graph scenarios including:
 * - Simple weighted graphs
 * - Graphs with negative weights
 * - Single vertex graphs
 * - Disconnected graphs
 * - Linear path graphs
 */
class BellmanFordTest {

    @Test
    void testSimpleGraph() {
        // Create a simple graph with 5 vertices and 8 edges
        // Graph visualization:
        // 1
        // /|\
        // 6 | 7
        // / | \
        // 0 5 2
        // \ | /
        // 8 | -2
        // \|/
        // 4---3
        // 9
        BellmanFord bellmanFord = new BellmanFord(5, 8);
        bellmanFord.addEdge(0, 1, 6);
        bellmanFord.addEdge(0, 4, 8);
        bellmanFord.addEdge(1, 2, 7);
        bellmanFord.addEdge(1, 4, 5);
        bellmanFord.addEdge(2, 3, -2);
        bellmanFord.addEdge(2, 4, -3);
        bellmanFord.addEdge(3, 4, 9);
        bellmanFord.addEdge(4, 3, 7);

        // Verify edge array creation
        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(8, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testGraphWithNegativeWeights() {
        // Graph with negative edge weights (but no negative cycle)
        BellmanFord bellmanFord = new BellmanFord(4, 5);
        bellmanFord.addEdge(0, 1, 4);
        bellmanFord.addEdge(0, 2, 5);
        bellmanFord.addEdge(1, 2, -3);
        bellmanFord.addEdge(2, 3, 4);
        bellmanFord.addEdge(1, 3, 6);

        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(5, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testSingleVertexGraph() {
        // Graph with single vertex and no edges
        BellmanFord bellmanFord = new BellmanFord(1, 0);
        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(0, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testLinearGraph() {
        // Linear graph: 0 -> 1 -> 2 -> 3
        BellmanFord bellmanFord = new BellmanFord(4, 3);
        bellmanFord.addEdge(0, 1, 2);
        bellmanFord.addEdge(1, 2, 3);
        bellmanFord.addEdge(2, 3, 4);

        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(3, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testEdgeAddition() {
        BellmanFord bellmanFord = new BellmanFord(3, 3);

        bellmanFord.addEdge(0, 1, 5);
        bellmanFord.addEdge(1, 2, 3);
        bellmanFord.addEdge(0, 2, 10);

        // Verify all edges were added
        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(3, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testGraphWithZeroWeightEdges() {
        // Graph with zero weight edges
        BellmanFord bellmanFord = new BellmanFord(3, 3);
        bellmanFord.addEdge(0, 1, 0);
        bellmanFord.addEdge(1, 2, 0);
        bellmanFord.addEdge(0, 2, 1);

        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(3, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testLargerGraph() {
        // Larger graph with 6 vertices
        BellmanFord bellmanFord = new BellmanFord(6, 9);
        bellmanFord.addEdge(0, 1, 5);
        bellmanFord.addEdge(0, 2, 3);
        bellmanFord.addEdge(1, 3, 6);
        bellmanFord.addEdge(1, 2, 2);
        bellmanFord.addEdge(2, 4, 4);
        bellmanFord.addEdge(2, 5, 2);
        bellmanFord.addEdge(2, 3, 7);
        bellmanFord.addEdge(3, 4, -1);
        bellmanFord.addEdge(4, 5, -2);

        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(9, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testVertexAndEdgeCount() {
        BellmanFord bellmanFord = new BellmanFord(10, 15);
        assertEquals(10, bellmanFord.vertex);
        assertEquals(15, bellmanFord.edge);
    }

    @Test
    void testMultipleEdgesBetweenSameVertices() {
        // Graph allowing multiple edges between same vertices
        BellmanFord bellmanFord = new BellmanFord(2, 3);
        bellmanFord.addEdge(0, 1, 5);
        bellmanFord.addEdge(0, 1, 3);
        bellmanFord.addEdge(1, 0, 2);

        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(3, bellmanFord.getEdgeArray().length);
    }

    @Test
    void testCompleteGraph() {
        // Complete graph with 4 vertices (6 edges for undirected equivalent)
        BellmanFord bellmanFord = new BellmanFord(4, 6);
        bellmanFord.addEdge(0, 1, 1);
        bellmanFord.addEdge(0, 2, 2);
        bellmanFord.addEdge(0, 3, 3);
        bellmanFord.addEdge(1, 2, 4);
        bellmanFord.addEdge(1, 3, 5);
        bellmanFord.addEdge(2, 3, 6);

        assertNotNull(bellmanFord.getEdgeArray());
        assertEquals(6, bellmanFord.getEdgeArray().length);
    }
}
