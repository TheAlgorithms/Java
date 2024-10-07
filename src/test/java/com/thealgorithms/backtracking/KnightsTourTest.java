package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnightsTourTest {

    @BeforeEach
    void setUp() {
        // Call the reset method in the KnightsTour class
        KnightsTour.resetBoard();
    }

    @Test
    void testGridInitialization() {
        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 12; c++) {
                if (r < 2 || r > 12 - 3 || c < 2 || c > 12 - 3) {
                    assertEquals(-1, KnightsTour.grid[r][c], "Border cells should be -1");
                } else {
                    assertEquals(0, KnightsTour.grid[r][c], "Internal cells should be 0");
                }
            }
        }
    }

    @Test
    void testCountNeighbors() {
        // Manually place a knight at (3, 3) and mark nearby cells to test counting
        KnightsTour.grid[3][3] = 1; // Knight is here
        KnightsTour.grid[5][4] = -1; // Block one potential move

        int neighborCount = KnightsTour.countNeighbors(3, 3);
        assertEquals(3, neighborCount, "Knight at (3, 3) should have 3 neighbors (one blocked)");

        KnightsTour.grid[4][1] = -1; // Block another move
        neighborCount = KnightsTour.countNeighbors(3, 3);
        assertEquals(3, neighborCount, "Knight at (3, 3) should have 3 neighbors (two blocked)");
    }

    @Test
    void testNeighbors() {
        // Test the list of valid neighbors for a given cell (3, 3)
        List<int[]> neighbors = KnightsTour.neighbors(3, 3);
        assertEquals(4, neighbors.size(), "Knight at (3, 3) should have 8 valid neighbors");
    }

    @Test
    void testSolveSuccessful() {
        // Test if the solve method works for a successful knight's tour
        KnightsTour.grid[2][2] = 1; // Start the knight at (2, 2)
        boolean result = KnightsTour.solve(2, 2, 2);
        assertTrue(result, "solve() should successfully complete a Knight's tour");
    }
}
