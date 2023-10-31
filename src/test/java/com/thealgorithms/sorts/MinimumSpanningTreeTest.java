package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class MinimumSpanningTreeTest {

    @Test
    public void testMSTWithEmptyGraph() {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        List<Edge> edges = new ArrayList<>();
        List<Edge> minimumSpanningTree = mst.FindMST(edges, 0);
        assertTrue(minimumSpanningTree.isEmpty());
    }

    @Test
    public void testMSTWithSingleNodeGraph() {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 0, 0)); // Single node
        List<Edge> minimumSpanningTree = mst.FindMST(edges, 1);
        assertTrue(minimumSpanningTree.isEmpty());
    }

    @Test
    public void testMSTWithKEqualTo2() {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        List<Edge> edges = new ArrayList<>();

        // Create a graph with multiple edges
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 3));
        edges.add(new Edge(2, 3, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 6));

        List<Edge> minimumSpanningTree = mst.FindMST(edges, 5);

        // Test the number of edges in the MST
        assertEquals(4, minimumSpanningTree.size());

        // You can add more specific assertions here to check the MST structure.
    }

    @Test
    public void testMSTWithKEqualTo3() {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        List<Edge> edges = new ArrayList<>();

        // Create a graph with multiple edges
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 3));
        edges.add(new Edge(2, 3, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 6));

        List<Edge> minimumSpanningTree = mst.FindMST(edges, 5);

        // Test the number of edges in the MST
        assertEquals(4, minimumSpanningTree.size());

        // You can add more specific assertions here to check the MST structure.
    }
}
