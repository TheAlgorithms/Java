package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class CyclesTest {

    @Test
    void testTriangleCycle() {
        // Triangle graph: 0-1, 1-2, 2-0
        int nodes = 3;
        int[][] matrix = { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 0 } };

        Cycle c = new Cycle(nodes, matrix);
        c.start();
        List<List<Integer>> cycles = c.getCycles();

        // Should find at least one cycle: [0, 1, 2]
        // Note: The algorithm modifies the matrix as it goes, so it finds elementary
        // cycles.
        // For a triangle, it finds 0-1-2.

        assertFalse(cycles.isEmpty(), "Should detect at least one cycle");
        // Verify the cycle content
        boolean foundTriangle = false;
        for (List<Integer> cycle : cycles) {
            if (cycle.size() == 3 && cycle.contains(0) && cycle.contains(1) && cycle.contains(2)) {
                foundTriangle = true;
                break;
            }
        }
        assertTrue(foundTriangle, "Should detect the 0-1-2 triangle");
    }

    @Test
    void testNoCycle() {
        // Line graph: 0 -> 1 -> 2
        int nodes = 3;
        int[][] matrix = { { 0, 1, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };

        Cycle c = new Cycle(nodes, matrix);
        c.start();
        List<List<Integer>> cycles = c.getCycles();

        assertTrue(cycles.isEmpty(), "Should not detect any cycles in a line graph");
    }

    @Test
    void testSelfLoop() {
        // Node 0 has self loop
        int nodes = 1;
        int[][] matrix = { { 1 } };

        Cycle c = new Cycle(nodes, matrix);
        c.start();
        List<List<Integer>> cycles = c.getCycles();

        assertFalse(cycles.isEmpty(), "Should detect self loop");
        assertEquals(1, cycles.size());
        assertEquals(1, cycles.get(0).size());
        assertEquals(0, cycles.get(0).get(0));
    }

    @Test
    void testPrintAll() {
        int nodes = 3;
        int[][] matrix = { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 0 } };
        Cycle c = new Cycle(nodes, matrix);
        c.start();
        c.printAll(); // Ensure no exception
    }

    @Test
    void testMain() {
        Cycles.main(new String[] {});
    }
}
