package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumPathSumTest {

    @Test
    public void testMinimumPathSumWithRegularGrid() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        assertEquals(7, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSumWithOneRowOneColumnGrid() {
        int[][] grid = {{2}};
        assertEquals(2, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSumWithEmptyGrid() {
        int[][] grid = {{}};
        assertEquals(0, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSumWithOneColumnGrid() {
        int[][] grid = {{1}, {2}, {3}};
        assertEquals(6, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSumGridOneRowGrid() {
        int[][] grid = {{1, 2, 3}};
        assertEquals(6, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSumWithDiffRowAndColumnGrid() {
        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        assertEquals(30, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSumWithNegativeNumberGrid() {
        int[][] grid = {{1, 3, 1}, {3, 4, 1}, {4, -3, 1}};
        assertEquals(6, MinimumPathSum.minimumPathSum(grid));
    }
}
