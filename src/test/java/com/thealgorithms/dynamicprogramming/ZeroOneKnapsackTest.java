package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ZeroOneKnapsack algorithm.
 */
public class ZeroOneKnapsackTest {

    /**
     * Test the knapsack algorithm with a basic example.
     */
    @Test
    void testKnapsackBasic() {
        int[] val = {15, 14, 10, 45, 30};
        int[] wt = {2, 5, 1, 3, 4};
        int W = 7;
        int expected = 75;
        assertEquals(expected, ZeroOneKnapsack.knapsack(val, wt, W, val.length));
    }

    /**
     * Test the knapsack algorithm when the knapsack capacity is zero.
     * Expected result is zero since nothing can be added.
     */
    @Test
    void testZeroCapacity() {
        int[] val = {10, 20, 30};
        int[] wt = {1, 1, 1};
        int W = 0;
        int expected = 0;
        assertEquals(expected, ZeroOneKnapsack.knapsack(val, wt, W, val.length));
    }

    /**
     * Test the knapsack algorithm when there are no items.
     * Expected result is zero since there is nothing to add.
     */
    @Test
    void testNoItems() {
        int[] val = {};
        int[] wt = {};
        int W = 10;
        int expected = 0;
        assertEquals(expected, ZeroOneKnapsack.knapsack(val, wt, W, 0));
    }

    /**
     * Test the knapsack algorithm with items that exactly fit the knapsack.
     */
    @Test
    void testExactFit() {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        int expected = 220;
        assertEquals(expected, ZeroOneKnapsack.knapsack(val, wt, W, val.length));
    }
}
