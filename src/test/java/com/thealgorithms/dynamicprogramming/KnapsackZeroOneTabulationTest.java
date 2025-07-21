package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KnapsackZeroOneTabulationTest {

    @Test
    public void basicCheck() {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        int itemCount = values.length;

        int expected = 220; // Best choice: item 1 (100) and item 2 (120)
        int result = KnapsackZeroOneTabulation.compute(values, weights, capacity, itemCount);
        assertEquals(expected, result);
    }

    @Test
    public void emptyKnapsack() {
        int[] values = {};
        int[] weights = {};
        int capacity = 50;
        int itemCount = 0;

        assertEquals(0, KnapsackZeroOneTabulation.compute(values, weights, capacity, itemCount));
    }

    @Test
    public void zeroCapacity() {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 0;
        int itemCount = values.length;

        assertEquals(0, KnapsackZeroOneTabulation.compute(values, weights, capacity, itemCount));
    }
}
