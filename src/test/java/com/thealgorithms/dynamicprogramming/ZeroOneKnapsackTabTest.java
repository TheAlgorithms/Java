package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ZeroOneKnapsackTab class.
 */
public class ZeroOneKnapsackTabTest {

    /**
     * Test knapsackTab with a typical set of values and weights.
     * Checks if the maximum value for the given capacity is correct.
     */
    @Test
    public void testKnapsackTab() {
        int[] val = {15, 14, 10, 45, 30};
        int[] wt = {2, 5, 1, 3, 4};
        int W = 7;
        int expected = 75;
        assertEquals(expected, ZeroOneKnapsackTab.knapsackTab(val, wt, W, val.length));
    }

    /**
     * Test knapsackTab with empty arrays.
     * Should return 0 as there are no items to include.
     */
    @Test
    public void testKnapsackTabEmpty() {
        int[] val = {};
        int[] wt = {};
        int W = 10;
        int expected = 0;
        assertEquals(expected, ZeroOneKnapsackTab.knapsackTab(val, wt, W, 0));
    }

    /**
     * Test knapsackTab with zero capacity.
     * Should return 0 as no items can be included.
     */
    @Test
    public void testKnapsackTabZeroCapacity() {
        int[] val = {10, 20, 30};
        int[] wt = {1, 1, 1};
        int W = 0;
        int expected = 0;
        assertEquals(expected, ZeroOneKnapsackTab.knapsackTab(val, wt, W, val.length));
    }
}
