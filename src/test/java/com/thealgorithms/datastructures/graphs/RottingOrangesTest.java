package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RottingOrangesTest {

    @Test
    void testAllOrangesRotInSingleMinute() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};

        assertEquals(4, rottingOranges.run(grid));
    }

    @Test
    void testImpossibleToRotAllOranges() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};

        assertEquals(-1, rottingOranges.run(grid));
    }

    @Test
    void testNoFreshOranges() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 2}, {2, 2}};

        assertEquals(0, rottingOranges.run(grid));
    }

    @Test
    void testNoRottenOranges() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{1, 1}, {1, 1}};

        assertEquals(-1, rottingOranges.run(grid));
    }

    @Test
    void testEmptyGrid() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {};

        assertEquals(0, rottingOranges.run(grid));
    }

    @Test
    void testSingleRottenOrange() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2}};

        assertEquals(0, rottingOranges.run(grid));
    }

    @Test
    void testSingleFreshOrange() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{1}};

        assertEquals(-1, rottingOranges.run(grid));
    }

    @Test
    void testSingleFreshOrangeNextToRottenOrange() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 1}};

        assertEquals(1, rottingOranges.run(grid));
    }

    @Test
    void testMultipleRottenSources() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 1, 0, 2}, {1, 1, 1, 1}, {0, 1, 1, 1}};

        assertEquals(3, rottingOranges.run(grid));
    }

    @Test
    void testFreshOrangeBlockedByEmptyCells() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 0, 1}, {0, 0, 0}, {1, 0, 1}};

        assertEquals(-1, rottingOranges.run(grid));
    }

    @Test
    void testLinearSpread() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 1, 1, 1, 1}};

        assertEquals(4, rottingOranges.run(grid));
    }

    @Test
    void testVerticalSpread() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2}, {1}, {1}, {1}};

        assertEquals(3, rottingOranges.run(grid));
    }

    @Test
    void testGridWithOnlyEmptyCells() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{0, 0}, {0, 0}};

        assertEquals(0, rottingOranges.run(grid));
    }

    @Test
    void testComplexGrid() {
        RottingOranges rottingOranges = new RottingOranges();

        int[][] grid = {{2, 1, 1}, {1, 1, 1}, {1, 1, 1}};

        assertEquals(4, rottingOranges.run(grid));
    }
}
