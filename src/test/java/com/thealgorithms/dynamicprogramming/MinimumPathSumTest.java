package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MinimumPathSumTest {
    
    @Test
    public void testMinimumPathSum_Regular() {
        // Given
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        // Then
        assertEquals(7, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSum_OneRowOneColumn() {
        // Given
        int[][] grid = {{2}};

        // Then
        assertEquals(2, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSum_EmptyGrid() {
        // Given
        int[][] grid = {{}};

        // Then
        assertEquals(0, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSum_OneColumn() {
        // Given
        int[][] grid = {{1}, {2}, {3}};

        // Then
        assertEquals(6, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSum_OneRow() {
        // Given
        int[][] grid = {{1, 2, 3}};

        // Then
        assertEquals(6, MinimumPathSum.minimumPathSum(grid));
    }

    @Test
    public void testMinimumPathSum_DiffRowAndColumn() {
        // Given
        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        // Then
        assertEquals(30, MinimumPathSum.minimumPathSum(grid));
    }
}
