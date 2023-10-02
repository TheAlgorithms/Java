package com.thealgorithms.dynamicprogramming;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PaintHouseTest {

    @Test
    public void testMinCost() {
        // Create an instance of the PaintHouse class
        PaintHouse painter = new PaintHouse();

        // Test case 1
        int[][] costs1 = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        int expected1 = 10;
        int result1 = painter.paintCost(costs1);
        assertEquals(expected1, result1);

        // Test case 2
        int[][] costs2 = {{7, 6, 2}, {12, 8, 10}, {1, 3, 5}};
        int expected2 = 11;
        int result2 = painter.paintCost(costs2);
        assertEquals(expected2, result2);

        // Add more test cases as needed
    }
}
