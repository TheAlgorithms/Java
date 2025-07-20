package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@code ZeroOneKnapsackTab}.
 */
public class ZeroOneknapsackTabTest {

    /**
     * Tests the 0-1 Knapsack tabulation approach with known values.
     */
    @Test
    public void testKnownValues() {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        int n = val.length;

        // Expected result is 220 (items with weight 20 and 30)
        assertEquals(220, ZeroOneKnapsackTab.compute(val, wt, W, n), "Maximum value for capacity 50 should be 220.");
    }

    @Test
    public void testZeroCapacity() {
        int[] val = {10, 20, 30};
        int[] wt = {1, 1, 1};
        int W = 0;
        int n = val.length;

        // With zero capacity, the result should be 0
        assertEquals(0, ZeroOneKnapsackTab.compute(val, wt, W, n), "Maximum value for capacity 0 should be 0.");
    }

    @Test
    public void testZeroItems() {
        int[] val = {};
        int[] wt = {};
        int W = 10;
        int n = val.length;

        // With no items, the result should be 0
        assertEquals(0, ZeroOneKnapsackTab.compute(val, wt, W, n), "Maximum value with no items should be 0.");
    }

    @Test
    public void testExactFit() {
        int[] val = {5, 10, 15};
        int[] wt = {1, 2, 3};
        int W = 6;
        int n = val.length;

        // All items fit exactly into capacity 6
        assertEquals(30, ZeroOneKnapsackTab.compute(val, wt, W, n), "Maximum value for exact fit should be 30.");
    }
}
