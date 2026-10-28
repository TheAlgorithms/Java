package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HungarianAlgorithmTest {

    @Test
    @DisplayName("Classic 3x3 example: minimal cost 5 with assignment [1,0,2]")
    void classicSquareExample() {
        int[][] cost = {{4, 1, 3}, {2, 0, 5}, {3, 2, 2}};
        HungarianAlgorithm.Result res = HungarianAlgorithm.solve(cost);
        assertEquals(5, res.minCost);
        assertArrayEquals(new int[] {1, 0, 2}, res.assignment);
    }

    @Test
    @DisplayName("Rectangular (more rows than cols): pads to square and returns -1 for unassigned rows")
    void rectangularMoreRows() {
        int[][] cost = {{7, 3}, {2, 8}, {5, 1}};
        // Optimal selects any 2 rows: choose row1->col0 (2) and row2->col1 (1) => total 3
        HungarianAlgorithm.Result res = HungarianAlgorithm.solve(cost);
        assertEquals(3, res.minCost);
        // Two rows assigned to 2 columns; one row remains -1.
        int assigned = 0;
        for (int a : res.assignment) {
            if (a >= 0) {
                assigned++;
            }
        }
        assertEquals(2, assigned);
    }

    @Test
    @DisplayName("Zero diagonal yields zero total cost")
    void zeroDiagonal() {
        int[][] cost = {{0, 5, 9}, {4, 0, 7}, {3, 6, 0}};
        HungarianAlgorithm.Result res = HungarianAlgorithm.solve(cost);
        assertEquals(0, res.minCost);
    }
}
