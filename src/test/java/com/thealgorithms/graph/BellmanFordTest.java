package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for Bellman-Ford algorithm implementation
 */
class BellmanFordTest {

    @Test
    void testSimpleGraph() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        edges.add(new BellmanFord.Edge(0, 1, 6));
        edges.add(new BellmanFord.Edge(0, 2, 7));
        edges.add(new BellmanFord.Edge(1, 2, 8));
        edges.add(new BellmanFord.Edge(1, 3, 5));
        edges.add(new BellmanFord.Edge(1, 4, -4));
        edges.add(new BellmanFord.Edge(2, 3, -3));
        edges.add(new BellmanFord.Edge(2, 4, 9));
        edges.add(new BellmanFord.Edge(3, 1, -2));
        edges.add(new BellmanFord.Edge(4, 0, 2));
        edges.add(new BellmanFord.Edge(4, 3, 7));

        BellmanFord.Result result = BellmanFord.findShortestPaths(5, edges, 0);

        assertFalse(result.hasNegativeCycle());
        assertArrayEquals(new int[] {0, 2, 7, 4, -2}, result.getDistances());
    }

    @Test
    void testGraphWithNegativeCycle() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        edges.add(new BellmanFord.Edge(0, 1, 1));
        edges.add(new BellmanFord.Edge(1, 2, -3));
        edges.add(new BellmanFord.Edge(2, 0, 1));

        BellmanFord.Result result = BellmanFord.findShortestPaths(3, edges, 0);

        assertTrue(result.hasNegativeCycle());
    }

    @Test
    void testDisconnectedGraph() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        edges.add(new BellmanFord.Edge(0, 1, 5));
        edges.add(new BellmanFord.Edge(2, 3, 2));

        BellmanFord.Result result = BellmanFord.findShortestPaths(4, edges, 0);

        assertEquals(0, result.getDistance(0));
        assertEquals(5, result.getDistance(1));
        assertEquals(Integer.MAX_VALUE, result.getDistance(2));
        assertEquals(Integer.MAX_VALUE, result.getDistance(3));
    }

    @Test
    void testSingleVertex() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        BellmanFord.Result result = BellmanFord.findShortestPaths(1, edges, 0);

        assertFalse(result.hasNegativeCycle());
        assertArrayEquals(new int[] {0}, result.getDistances());
    }

    @Test
    void testPathReconstruction() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        edges.add(new BellmanFord.Edge(0, 1, 4));
        edges.add(new BellmanFord.Edge(0, 2, 2));
        edges.add(new BellmanFord.Edge(1, 2, 1));
        edges.add(new BellmanFord.Edge(2, 3, 3));

        BellmanFord.Result result = BellmanFord.findShortestPaths(4, edges, 0);

        List<Integer> path = result.getPath(3);
        assertNotNull(path);
        assertEquals(Arrays.asList(0, 2, 3), path);
    }

    @Test
    void testNegativeWeights() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        edges.add(new BellmanFord.Edge(0, 1, 5));
        edges.add(new BellmanFord.Edge(1, 2, -2));
        edges.add(new BellmanFord.Edge(2, 3, 3));

        BellmanFord.Result result = BellmanFord.findShortestPaths(4, edges, 0);

        assertFalse(result.hasNegativeCycle());
        assertEquals(0, result.getDistance(0));
        assertEquals(5, result.getDistance(1));
        assertEquals(3, result.getDistance(2));
        assertEquals(6, result.getDistance(3));
    }

    @Test
    void testInvalidSource() {
        List<BellmanFord.Edge> edges = new ArrayList<>();
        edges.add(new BellmanFord.Edge(0, 1, 5));

        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(2, edges, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(2, edges, 2);
        });
    }

    @Test
    void testInvalidVertexCount() {
        List<BellmanFord.Edge> edges = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(0, edges, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(-1, edges, 0);
        });
    }

    @Test
    void testNullEdges() {
        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(3, null, 0);
        });
    }
}
