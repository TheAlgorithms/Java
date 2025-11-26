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
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 1, 6});
        edges.add(new int[] {0, 2, 7});
        edges.add(new int[] {1, 2, 8});
        edges.add(new int[] {1, 3, 5});
        edges.add(new int[] {1, 4, -4});
        edges.add(new int[] {2, 3, -3});
        edges.add(new int[] {2, 4, 9});
        edges.add(new int[] {3, 1, -2});
        edges.add(new int[] {4, 0, 2});
        edges.add(new int[] {4, 3, 7});

        BellmanFord.Result result = BellmanFord.findShortestPaths(5, edges, 0);

        assertTrue(result.hasPath());
        assertFalse(result.hasNegativeCycle());
        assertArrayEquals(new double[] {0, 2, 7, 4, -2}, result.distances());
    }

    @Test
    void testGraphWithNegativeCycle() {
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 1, 1});
        edges.add(new int[] {1, 2, -3});
        edges.add(new int[] {2, 0, 1});

        BellmanFord.Result result = BellmanFord.findShortestPaths(3, edges, 0);

        assertTrue(result.hasNegativeCycle());
    }

    @Test
    void testDisconnectedGraph() {
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 1, 5});
        edges.add(new int[] {2, 3, 2});

        BellmanFord.Result result = BellmanFord.findShortestPaths(4, edges, 0);

        assertTrue(result.hasPath());
        assertEquals(0, result.distances()[0]);
        assertEquals(5, result.distances()[1]);
        assertEquals(Double.POSITIVE_INFINITY, result.distances()[2]);
        assertEquals(Double.POSITIVE_INFINITY, result.distances()[3]);
    }

    @Test
    void testSingleVertex() {
        List<int[]> edges = new ArrayList<>();
        BellmanFord.Result result = BellmanFord.findShortestPaths(1, edges, 0);

        assertTrue(result.hasPath());
        assertFalse(result.hasNegativeCycle());
        assertArrayEquals(new double[] {0}, result.distances());
    }

    @Test
    void testPathReconstruction() {
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 1, 4});
        edges.add(new int[] {0, 2, 2});
        edges.add(new int[] {1, 2, 1});
        edges.add(new int[] {2, 3, 3});

        BellmanFord.Result result = BellmanFord.findShortestPaths(4, edges, 0);

        List<Integer> path = result.getPath(3);
        assertNotNull(path);
        assertEquals(Arrays.asList(0, 2, 3), path);
    }

    @Test
    void testNegativeWeights() {
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 1, 5});
        edges.add(new int[] {1, 2, -2});
        edges.add(new int[] {2, 3, 3});

        BellmanFord.Result result = BellmanFord.findShortestPaths(4, edges, 0);

        assertTrue(result.hasPath());
        assertFalse(result.hasNegativeCycle());
        assertEquals(0, result.distances()[0]);
        assertEquals(5, result.distances()[1]);
        assertEquals(3, result.distances()[2]);
        assertEquals(6, result.distances()[3]);
    }

    @Test
    void testInvalidSource() {
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 1, 5});

        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(2, edges, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BellmanFord.findShortestPaths(2, edges, 2);
        });
    }

    @Test
    void testInvalidVertexCount() {
        List<int[]> edges = new ArrayList<>();

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
