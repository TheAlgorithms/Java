package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class HamiltonianCycleTest {

    private final HamiltonianCycle hamiltonianCycle = new HamiltonianCycle();

    @Test
    void testFindHamiltonianCycleShouldReturnHamiltonianCycle() {
        int[] expectedArray = {0, 1, 2, 4, 3, 0};
        int[][] inputArray = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
        };

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }

    @Test
    void testFindHamiltonianCycleShouldReturnInfinityArray() {
        int[] expectedArray = {-1, -1, -1, -1, -1, -1};

        int[][] inputArray = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
        };

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }

    @Test
    void testSingleVertexGraph() {
        int[] expectedArray = {0, 0};
        int[][] inputArray = {{0}};

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }

    @Test
    void testDisconnectedGraphShouldReturnInfinityArray() {
        int[] expectedArray = {-1, -1, -1, -1, -1};
        int[][] inputArray = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }

    @Test
    void testCompleteGraphShouldReturnHamiltonianCycle() {
        int[] expectedArray = {0, 1, 2, 3, 4, 0};
        int[][] inputArray = {
            {0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0},
        };

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }

    @Test
    void testGraphWithNoEdgesShouldReturnInfinityArray() {
        int[] expectedArray = {-1, -1, -1, -1, -1, -1};

        int[][] inputArray = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
        };

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }

    @Test
    void testLargeGraphWithHamiltonianCycle() {
        int[] expectedArray = {0, 1, 2, 3, 4, 0};
        int[][] inputArray = {
            {0, 1, 0, 1, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0},
        };

        assertArrayEquals(expectedArray, hamiltonianCycle.findHamiltonianCycle(inputArray));
    }
}
