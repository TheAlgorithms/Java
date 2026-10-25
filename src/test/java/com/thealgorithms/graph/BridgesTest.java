package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class BridgesTest {

    @Test
    void testTriangleHasNoBridges() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};

        List<int[]> result = Bridges.findBridges(3, edges);

        assertEquals(0, result.size());
    }

    @Test
    void testChainHasTwoBridges() {
        int[][] edges = {{0, 1}, {1, 2}};

        List<int[]> result = Bridges.findBridges(3, edges);

        assertEquals(2, result.size());
        assertArrayEquals(new int[] {0, 1}, result.get(0));
        assertArrayEquals(new int[] {1, 2}, result.get(1));
    }

    @Test
    void testDisconnectedGraph() {
        int[][] edges = {{0, 1}, {2, 3}, {3, 4}};

        List<int[]> result = Bridges.findBridges(5, edges);

        assertEquals(3, result.size());
        assertArrayEquals(new int[] {0, 1}, result.get(0));
        assertArrayEquals(new int[] {2, 3}, result.get(1));
        assertArrayEquals(new int[] {3, 4}, result.get(2));
    }

    @Test
    void testEmptyGraph() {
        List<int[]> result = Bridges.findBridges(4, new int[0][]);

        assertEquals(0, result.size());
    }

    @Test
    void testParallelEdgesAreNotBridges() {
        int[][] edges = {{0, 1}, {0, 1}};

        List<int[]> result = Bridges.findBridges(2, edges);

        assertEquals(0, result.size());
    }

    @Test
    void testSelfLoopIsNotBridge() {
        int[][] edges = {{0, 0}};

        List<int[]> result = Bridges.findBridges(1, edges);

        assertEquals(0, result.size());
    }

    @Test
    void testNegativeVertexCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Bridges.findBridges(-1, new int[0][]));
    }

    @Test
    void testNullEdgesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Bridges.findBridges(2, null));
    }

    @Test
    void testMalformedEdgeThrowsException() {
        int[][] edges = {{0}};

        assertThrows(IllegalArgumentException.class, () -> Bridges.findBridges(2, edges));
    }

    @Test
    void testInvalidVertexThrowsException() {
        int[][] edges = {{0, 2}};

        assertThrows(IllegalArgumentException.class, () -> Bridges.findBridges(2, edges));
    }
}
