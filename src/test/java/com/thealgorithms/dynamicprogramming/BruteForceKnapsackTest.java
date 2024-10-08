package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BruteForceKnapsackTest {

    @Test
    void testKnapSackBasicCase() {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int w = 50;
        int n = val.length;

        // The expected result for this case is 220 (items 2 and 3 are included)
        assertEquals(220, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackNoItems() {
        int[] val = {};
        int[] wt = {};
        int w = 50;
        int n = val.length;

        // With no items, the maximum value should be 0
        assertEquals(0, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackZeroCapacity() {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int w = 0;
        int n = val.length;

        // With a knapsack of 0 capacity, no items can be included, so the value is 0
        assertEquals(0, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackSingleItemFits() {
        int[] val = {100};
        int[] wt = {20};
        int w = 30;
        int n = val.length;

        // Only one item, and it fits in the knapsack, so the result is 100
        assertEquals(100, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackSingleItemDoesNotFit() {
        int[] val = {100};
        int[] wt = {20};
        int w = 10;
        int n = val.length;

        // Single item does not fit in the knapsack, so the result is 0
        assertEquals(0, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackAllItemsFit() {
        int[] val = {20, 30, 40};
        int[] wt = {1, 2, 3};
        int w = 6;
        int n = val.length;

        // All items fit into the knapsack, so the result is the sum of all values (20 + 30 + 40 = 90)
        assertEquals(90, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackNoneFit() {
        int[] val = {100, 200, 300};
        int[] wt = {100, 200, 300};
        int w = 50;
        int n = val.length;

        // None of the items fit into the knapsack, so the result is 0
        assertEquals(0, BruteForceKnapsack.knapSack(w, wt, val, n));
    }

    @Test
    void testKnapSackSomeItemsFit() {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int w = 40;
        int n = val.length;

        // Here, only the 2nd and 1st items should be included for a total value of 160
        assertEquals(180, BruteForceKnapsack.knapSack(w, wt, val, n));
    }
}
