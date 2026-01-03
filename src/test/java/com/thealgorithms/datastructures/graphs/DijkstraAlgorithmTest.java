package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DijkstraAlgorithmTest {

    private DijkstraAlgorithm dijkstraAlgorithm;
    private int[][] graph;

    @BeforeEach
    void setUp() {
        graph = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 },
        };

        dijkstraAlgorithm = new DijkstraAlgorithm(graph.length);
    }

    @Test
    void testRunAlgorithm() {
        int[] expectedDistances = { 0, 4, 12, 19, 21, 11, 9, 8, 14 };
        assertArrayEquals(expectedDistances, dijkstraAlgorithm.run(graph, 0));
    }

    @Test
    void testGraphWithDisconnectedNodes() {
        int[][] disconnectedGraph = {
                { 0, 3, 0, 0 }, { 3, 0, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } // Node 3 is disconnected
        };

        DijkstraAlgorithm dijkstraDisconnected = new DijkstraAlgorithm(disconnectedGraph.length);

        // Testing from vertex 0
        int[] expectedDistances = { 0, 3, 4, Integer.MAX_VALUE }; // Node 3 is unreachable
        assertArrayEquals(expectedDistances, dijkstraDisconnected.run(disconnectedGraph, 0));
    }

    @Test
    void testSingleVertexGraph() {
        int[][] singleVertexGraph = { { 0 } };
        DijkstraAlgorithm dijkstraSingleVertex = new DijkstraAlgorithm(1);

        int[] expectedDistances = { 0 }; // The only vertex's distance to itself is 0
        assertArrayEquals(expectedDistances, dijkstraSingleVertex.run(singleVertexGraph, 0));
    }

    @Test
    void testInvalidSourceVertex() {
        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.run(graph, -1));
        assertThrows(IllegalArgumentException.class, () -> dijkstraAlgorithm.run(graph, graph.length));
    }

    @Test
    void testLinearGraph() {
        // Linear graph: 0 - 1 - 2 - 3
        // with weights: 2 3 4
        int[][] linearGraph = {{0, 2, 0, 0}, {2, 0, 3, 0}, {0, 3, 0, 4}, {0, 0, 4, 0}};

        DijkstraAlgorithm dijkstraLinear = new DijkstraAlgorithm(4);
        int[] distances = dijkstraLinear.run(linearGraph, 0);

        assertArrayEquals(new int[] {0, 2, 5, 9}, distances);
    }

    @Test
    void testStarTopology() {
        // Star graph: center node 0 connected to all others
        // 1(2)
        // |
        // 3(4)-0-2(3)
        // |
        // 4(5)
        int[][] starGraph = {{0, 2, 3, 4, 5}, {2, 0, 0, 0, 0}, {3, 0, 0, 0, 0}, {4, 0, 0, 0, 0}, {5, 0, 0, 0, 0}};

        DijkstraAlgorithm dijkstraStar = new DijkstraAlgorithm(5);
        int[] distances = dijkstraStar.run(starGraph, 0);

        assertArrayEquals(new int[] {0, 2, 3, 4, 5}, distances);
    }

    @Test
    void testCompleteGraphK4() {
        // Complete graph K4 with varying weights
        int[][] completeGraph = {{0, 1, 2, 3}, {1, 0, 4, 5}, {2, 4, 0, 6}, {3, 5, 6, 0}};

        DijkstraAlgorithm dijkstraComplete = new DijkstraAlgorithm(4);
        int[] distances = dijkstraComplete.run(completeGraph, 0);

        // Direct paths from 0 are shortest
        assertArrayEquals(new int[] {0, 1, 2, 3}, distances);
    }

    @Test
    void testDifferentSourceVertex() {
        // Test running from different source vertices
        int[][] simpleGraph = {{0, 5, 0, 0}, {5, 0, 3, 0}, {0, 3, 0, 2}, {0, 0, 2, 0}};

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(4);

        // From vertex 0
        int[] distFrom0 = dijkstra.run(simpleGraph, 0);
        assertArrayEquals(new int[] {0, 5, 8, 10}, distFrom0);

        // From vertex 2
        int[] distFrom2 = dijkstra.run(simpleGraph, 2);
        assertArrayEquals(new int[] {8, 3, 0, 2}, distFrom2);

        // From vertex 3
        int[] distFrom3 = dijkstra.run(simpleGraph, 3);
        assertArrayEquals(new int[] {10, 5, 2, 0}, distFrom3);
    }

    @Test
    void testUnitWeightGraph() {
        // Graph with all unit weights (like BFS distance)
        int[][] unitGraph = {{0, 1, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}, {0, 1, 1, 0}};

        DijkstraAlgorithm dijkstraUnit = new DijkstraAlgorithm(4);
        int[] distances = dijkstraUnit.run(unitGraph, 0);

        assertArrayEquals(new int[] {0, 1, 1, 2}, distances);
    }

    @Test
    void testTwoVertexGraph() {
        int[][] twoVertexGraph = {{0, 7}, {7, 0}};

        DijkstraAlgorithm dijkstraTwo = new DijkstraAlgorithm(2);
        int[] distances = dijkstraTwo.run(twoVertexGraph, 0);

        assertArrayEquals(new int[] {0, 7}, distances);
    }

    @Test
    void testShortcutPath() {
        // Graph where direct path is longer than indirect path
        // 0 --(10)--> 2
        // 0 --(1)--> 1 --(2)--> 2
        int[][] shortcutGraph = {{0, 1, 10}, {1, 0, 2}, {10, 2, 0}};

        DijkstraAlgorithm dijkstraShortcut = new DijkstraAlgorithm(3);
        int[] distances = dijkstraShortcut.run(shortcutGraph, 0);

        // The shortest path to vertex 2 should be 3 (via vertex 1), not 10 (direct)
        assertArrayEquals(new int[] {0, 1, 3}, distances);
    }

    @Test
    void testSourceToSourceDistanceIsZero() {
        // Verify distance from source to itself is always 0
        int[] distances = dijkstraAlgorithm.run(graph, 0);
        assertEquals(0, distances[0]);

        distances = dijkstraAlgorithm.run(graph, 5);
        assertEquals(0, distances[5]);
    }

    @Test
    void testLargeWeights() {
        // Graph with large weights
        int[][] largeWeightGraph = {{0, 1000, 0}, {1000, 0, 2000}, {0, 2000, 0}};

        DijkstraAlgorithm dijkstraLarge = new DijkstraAlgorithm(3);
        int[] distances = dijkstraLarge.run(largeWeightGraph, 0);

        assertArrayEquals(new int[] {0, 1000, 3000}, distances);
    }
}
