package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public final class TravelingSalesmanBitmaskTest {

    @Test
    public void solveShouldReturnMinimumTourCost() {
        int[][] distanceMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        assertEquals(80, TravelingSalesmanBitmask.solve(distanceMatrix));
    }

    @Test
    public void solveShouldReturnZeroForEmptyMatrix() {
        assertEquals(0, TravelingSalesmanBitmask.solve(new int[0][0]));
    }

    @Test
    public void solveShouldReturnZeroForSingleCity() {
        int[][] distanceMatrix = {
            {0}
        };

        assertEquals(0, TravelingSalesmanBitmask.solve(distanceMatrix));
    }

    @Test
    public void solveShouldReturnZeroWhenTourIsDisconnected() {
        int inf = Integer.MAX_VALUE;
        int[][] distanceMatrix = {
            {0, 1, inf},
            {1, 0, inf},
            {inf, inf, 0}
        };

        assertEquals(0, TravelingSalesmanBitmask.solve(distanceMatrix));
    }

    @Test
    public void solveShouldRejectNonSquareMatrix() {
        int[][] distanceMatrix = {
            {0, 1, 2},
            {1, 0, 3}
        };

        assertThrows(IllegalArgumentException.class, () -> TravelingSalesmanBitmask.solve(distanceMatrix));
    }
}