package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@code ZeroOneKnapsack}.
 */
public class ZeroOneKnapsackTest {

    /**
     * Tests the knapsack computation for a basic example.
     */
    @Test
    public void testKnapsackBasic() {
        int[] val = {15, 14, 10, 45, 30};
        int[] wt = {2, 5, 1, 3, 4};
        int W = 7;
        assertEquals(75, ZeroOneKnapsack.compute(val, wt, W, val.length), "Expected maximum value is 75.");
    }

    /**
     * Tests the knapsack computation when the knapsack capacity is zero.
     */
    @Test
    public void testZeroCapacity() {
        int[] val = {10, 20, 30};
        int[] wt = {1, 1, 1};
        int W = 0;
        assertEquals(0, ZeroOneKnapsack.compute(val, wt, W, val.length), "Expected maximum value is 0 for zero capacity.");
    }

    /**
     * Tests the knapsack computation when there are no items.
     */
    @Test
    public void testNoItems() {
        int[] val = {};
        int[] wt = {};
        int W = 10;
        assertEquals(0, ZeroOneKnapsack.compute(val, wt, W, 0), "Expected maximum value is 0 when no items are available.");
    }

    /**
     * Tests the knapsack computation when items exactly fit the capacity.
     */
    @Test
    public void testExactFit() {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        assertEquals(220, ZeroOneKnapsack.compute(val, wt, W, val.length), "Expected maximum value is 220 for exact fit.");
    }
}
