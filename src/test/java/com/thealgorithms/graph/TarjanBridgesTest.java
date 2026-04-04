package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TarjanBridges}.
 *
 * <p>Tests cover a wide range of graph configurations including simple graphs,
 * cycles, trees, disconnected components, multigraph-like structures, and
 * various edge cases to ensure correct bridge detection.</p>
 */
class TarjanBridgesTest {

    /**
     * Helper to build a symmetric adjacency list for an undirected graph.
     */
    private static List<List<Integer>> buildGraph(int vertexCount, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }

    /**
     * Sorts bridges for deterministic comparison.
     */
    private static void sortBridges(List<int[]> bridges) {
        bridges.sort(Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
    }

    @Test
    void testSimpleGraphWithOneBridge() {
        // Graph: 0-1-2-3 where 1-2 is the only bridge
        //   0---1---2---3
        //   |       |
        //   +-------+ (via 0-2 would make cycle, but not here)
        // Actually: 0-1 in a cycle with 0-1, and 2-3 in a cycle with 2-3
        // Let's use: 0--1--2 (linear chain). All edges are bridges.
        List<List<Integer>> adj = buildGraph(3, new int[][] {{0, 1}, {1, 2}});
        List<int[]> bridges = TarjanBridges.findBridges(3, adj);
        sortBridges(bridges);
        assertEquals(2, bridges.size());
        assertEquals(0, bridges.get(0)[0]);
        assertEquals(1, bridges.get(0)[1]);
        assertEquals(1, bridges.get(1)[0]);
        assertEquals(2, bridges.get(1)[1]);
    }

    @Test
    void testCycleGraphHasNoBridges() {
        // Graph: 0-1-2-0 (triangle). No bridges.
        List<List<Integer>> adj = buildGraph(3, new int[][] {{0, 1}, {1, 2}, {2, 0}});
        List<int[]> bridges = TarjanBridges.findBridges(3, adj);
        assertTrue(bridges.isEmpty());
    }

    @Test
    void testTreeGraphAllEdgesAreBridges() {
        // Tree:    0
        //         / \
        //        1   2
        //       / \
        //      3   4
        List<List<Integer>> adj = buildGraph(5, new int[][] {{0, 1}, {0, 2}, {1, 3}, {1, 4}});
        List<int[]> bridges = TarjanBridges.findBridges(5, adj);
        assertEquals(4, bridges.size());
    }

    @Test
    void testGraphWithMixedBridgesAndCycles() {
        // Graph:
        //   0---1
        //   |   |
        //   3---2---4---5
        //               |
        //               6
        // Cycle: 0-1-2-3-0 (no bridges within)
        // Bridges: 2-4, 4-5, 5-6
        List<List<Integer>> adj = buildGraph(7, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {2, 4}, {4, 5}, {5, 6}});
        List<int[]> bridges = TarjanBridges.findBridges(7, adj);
        sortBridges(bridges);
        assertEquals(3, bridges.size());
        assertEquals(2, bridges.get(0)[0]);
        assertEquals(4, bridges.get(0)[1]);
        assertEquals(4, bridges.get(1)[0]);
        assertEquals(5, bridges.get(1)[1]);
        assertEquals(5, bridges.get(2)[0]);
        assertEquals(6, bridges.get(2)[1]);
    }

    @Test
    void testDisconnectedGraphWithBridges() {
        // Component 1: 0-1 (bridge)
        // Component 2: 2-3-4-2 (cycle, no bridges)
        List<List<Integer>> adj = buildGraph(5, new int[][] {{0, 1}, {2, 3}, {3, 4}, {4, 2}});
        List<int[]> bridges = TarjanBridges.findBridges(5, adj);
        assertEquals(1, bridges.size());
        assertEquals(0, bridges.get(0)[0]);
        assertEquals(1, bridges.get(0)[1]);
    }

    @Test
    void testSingleVertex() {
        List<List<Integer>> adj = buildGraph(1, new int[][] {});
        List<int[]> bridges = TarjanBridges.findBridges(1, adj);
        assertTrue(bridges.isEmpty());
    }

    @Test
    void testTwoVerticesWithOneEdge() {
        List<List<Integer>> adj = buildGraph(2, new int[][] {{0, 1}});
        List<int[]> bridges = TarjanBridges.findBridges(2, adj);
        assertEquals(1, bridges.size());
        assertEquals(0, bridges.get(0)[0]);
        assertEquals(1, bridges.get(0)[1]);
    }

    @Test
    void testEmptyGraph() {
        List<List<Integer>> adj = buildGraph(0, new int[][] {});
        List<int[]> bridges = TarjanBridges.findBridges(0, adj);
        assertTrue(bridges.isEmpty());
    }

    @Test
    void testIsolatedVertices() {
        // 5 vertices, no edges — all isolated
        List<List<Integer>> adj = buildGraph(5, new int[][] {});
        List<int[]> bridges = TarjanBridges.findBridges(5, adj);
        assertTrue(bridges.isEmpty());
    }

    @Test
    void testLargeCycleNoBridges() {
        // Cycle: 0-1-2-3-4-5-6-7-0
        int n = 8;
        int[][] edges = new int[n][2];
        for (int i = 0; i < n; i++) {
            edges[i] = new int[] {i, (i + 1) % n};
        }
        List<List<Integer>> adj = buildGraph(n, edges);
        List<int[]> bridges = TarjanBridges.findBridges(n, adj);
        assertTrue(bridges.isEmpty());
    }

    @Test
    void testComplexGraphWithMultipleCyclesAndBridges() {
        // Two cycles connected by a single bridge edge:
        // Cycle A: 0-1-2-0
        // Cycle B: 3-4-5-3
        // Bridge: 2-3
        List<List<Integer>> adj = buildGraph(6, new int[][] {{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 3}, {2, 3}});
        List<int[]> bridges = TarjanBridges.findBridges(6, adj);
        assertEquals(1, bridges.size());
        assertEquals(2, bridges.get(0)[0]);
        assertEquals(3, bridges.get(0)[1]);
    }

    @Test
    void testNegativeVertexCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> TarjanBridges.findBridges(-1, new ArrayList<>()));
    }

    @Test
    void testNullAdjacencyListThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> TarjanBridges.findBridges(3, null));
    }

    @Test
    void testMismatchedAdjacencyListSizeThrowsException() {
        List<List<Integer>> adj = buildGraph(2, new int[][] {{0, 1}});
        assertThrows(IllegalArgumentException.class, () -> TarjanBridges.findBridges(5, adj));
    }

    @Test
    void testStarGraphAllEdgesAreBridges() {
        // Star graph: center vertex 0 connected to 1, 2, 3, 4
        List<List<Integer>> adj = buildGraph(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4}});
        List<int[]> bridges = TarjanBridges.findBridges(5, adj);
        assertEquals(4, bridges.size());
    }

    @Test
    void testBridgeBetweenTwoCycles() {
        // Two squares connected by one bridge:
        // Square 1: 0-1-2-3-0
        // Square 2: 4-5-6-7-4
        // Bridge: 3-4
        List<List<Integer>> adj = buildGraph(8, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {4, 5}, {5, 6}, {6, 7}, {7, 4}, {3, 4}});
        List<int[]> bridges = TarjanBridges.findBridges(8, adj);
        assertEquals(1, bridges.size());
        assertEquals(3, bridges.get(0)[0]);
        assertEquals(4, bridges.get(0)[1]);
    }
}
