package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FractionalKnapsackTest {

    @Test
    public void testFractionalKnapsackWithExampleCase() {
        int[] weight = {10, 20, 30};
        int[] value = {60, 100, 120};
        int capacity = 50;
        assertEquals(240, FractionalKnapsack.fractionalKnapsack(weight, value, capacity));
    }

    @Test
    public void testFractionalKnapsackWithZeroCapacity() {
        int[] weight = {10, 20, 30};
        int[] value = {60, 100, 120};
        int capacity = 0;
        assertEquals(0, FractionalKnapsack.fractionalKnapsack(weight, value, capacity));
    }

    @Test
    public void testFractionalKnapsackWithEmptyItems() {
        int[] weight = {};
        int[] value = {};
        int capacity = 50;
        assertEquals(0, FractionalKnapsack.fractionalKnapsack(weight, value, capacity));
    }
}
