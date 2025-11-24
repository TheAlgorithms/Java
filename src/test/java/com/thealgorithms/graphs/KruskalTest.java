package com.thealgorithms.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.graphs.Kruskal.Edge;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test cases for Kruskal's Algorithm
 * 
 * @author Raghu0703
 */
class KruskalTest {

    @Test
    void testSimpleGraph() {
        // Graph with 4 vertices
        int vertices = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        // MST should have exactly 3 edges (V-1)
        assertEquals(3, mst.size());
        
        // Total weight should be 19 (4 + 5 + 10)
        assertEquals(19, Kruskal.getMSTWeight(mst));
    }

    @Test
    void testDisconnectedComponents() {
        // Graph with 5 vertices but only 3 can be connected
        int vertices = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(3, 4, 3));

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        // MST should have 3 edges (not complete spanning tree due to disconnection)
        assertEquals(3, mst.size());
        assertEquals(6, Kruskal.getMSTWeight(mst));
    }

    @Test
    void testSingleEdge() {
        int vertices = 2;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5));

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        assertEquals(1, mst.size());
        assertEquals(5, Kruskal.getMSTWeight(mst));
    }

    @Test
    void testCompleteGraph() {
        // Complete graph with 4 vertices
        int vertices = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(0, 3, 3));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 6));

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        // MST should have 3 edges
        assertEquals(3, mst.size());
        
        // Total weight should be 6 (1 + 2 + 3)
        assertEquals(6, Kruskal.getMSTWeight(mst));
    }

    @Test
    void testGraphWithEqualWeights() {
        int vertices = 3;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 2, 5));
        edges.add(new Edge(0, 2, 5));

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        assertEquals(2, mst.size());
        assertEquals(10, Kruskal.getMSTWeight(mst));
    }

    @Test
    void testEmptyGraph() {
        int vertices = 3;
        List<Edge> edges = new ArrayList<>();

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        assertTrue(mst.isEmpty());
    }

    @Test
    void testInvalidVertexCount() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));

        assertThrows(IllegalArgumentException.class, () -> {
            Kruskal.kruskalMST(0, edges);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Kruskal.kruskalMST(-5, edges);
        });
    }

    @Test
    void testNullEdges() {
        assertThrows(IllegalArgumentException.class, () -> {
            Kruskal.kruskalMST(5, null);
        });
    }

    @Test
    void testLargerGraph() {
        // Graph with 6 vertices
        int vertices = 6;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 8));
        edges.add(new Edge(2, 4, 10));
        edges.add(new Edge(3, 4, 2));
        edges.add(new Edge(3, 5, 6));
        edges.add(new Edge(4, 5, 3));

        List<Edge> mst = Kruskal.kruskalMST(vertices, edges);

        // MST should have 5 edges (6-1)
        assertEquals(5, mst.size());
        
        // Verify minimum total weight
        assertEquals(16, Kruskal.getMSTWeight(mst));
    }
}
