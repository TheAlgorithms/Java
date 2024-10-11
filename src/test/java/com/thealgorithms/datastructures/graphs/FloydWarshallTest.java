package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class FloydWarshallTest {

    @Test
    void testSmallGraph() {
        int[][] adjacencyMatrix = {{0, 0, 0, 0}, // Ignored row (0 index)
            {0, 0, 3, FloydWarshall.INFINITY}, {0, FloydWarshall.INFINITY, 0, 1}, {0, FloydWarshall.INFINITY, FloydWarshall.INFINITY, 0}};

        FloydWarshall fw = new FloydWarshall(3);
        fw.floydwarshall(adjacencyMatrix);

        int[][] expectedDistanceMatrix = {{0, 0, 0, 0}, {0, 0, 3, 4}, {0, FloydWarshall.INFINITY, 0, 1}, {0, FloydWarshall.INFINITY, FloydWarshall.INFINITY, 0}};

        assertArrayEquals(expectedDistanceMatrix, fw.getDistanceMatrix());
    }

    @Test
    void testLargerGraph() {
        int[][] adjacencyMatrix = {{0, 0, 0, 0, 0}, {0, 0, 1, FloydWarshall.INFINITY, 2}, {0, FloydWarshall.INFINITY, 0, 4, FloydWarshall.INFINITY}, {0, FloydWarshall.INFINITY, FloydWarshall.INFINITY, 0, 3}, {0, FloydWarshall.INFINITY, FloydWarshall.INFINITY, FloydWarshall.INFINITY, 0}};

        FloydWarshall fw = new FloydWarshall(4);
        fw.floydwarshall(adjacencyMatrix);

        int[][] expectedDistanceMatrix = {{0, 0, 0, 0, 0}, {0, 0, 1, 5, 2}, {0, FloydWarshall.INFINITY, 0, 4, 7}, {0, FloydWarshall.INFINITY, FloydWarshall.INFINITY, 0, 3}, {0, FloydWarshall.INFINITY, FloydWarshall.INFINITY, FloydWarshall.INFINITY, 0}};

        assertArrayEquals(expectedDistanceMatrix, fw.getDistanceMatrix());
    }
}
