package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void negativeCapacity() {
        int[] values = {10, 20, 30};
        int[] weights = {1, 1, 1};
        int capacity = -10;
        int itemCount = values.length;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOneTabulation.compute(values, weights, capacity, itemCount));
        assertEquals("Capacity must not be negative.", exception.getMessage());
    }

    @Test
    public void mismatchedLengths() {
        int[] values = {60, 100}; // Only 2 values
        int[] weights = {10, 20, 30}; // 3 weights
        int capacity = 50;
        int itemCount = 2; // Matches `values.length`

        // You could either expect 0 or throw an IllegalArgumentException in your compute function
        assertThrows(IllegalArgumentException.class, () -> { KnapsackZeroOneTabulation.compute(values, weights, capacity, itemCount); });
    }

    @Test
    public void nullInputs() {
        int[] weights = {1, 2, 3};
        int capacity = 10;
        int itemCount = 3;

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOneTabulation.compute(null, weights, capacity, itemCount));
        assertEquals("Values and weights arrays must not be null.", exception1.getMessage());

        int[] values = {1, 2, 3};

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOneTabulation.compute(values, null, capacity, itemCount));
        assertEquals("Values and weights arrays must not be null.", exception2.getMessage());
    }
}
