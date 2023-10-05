package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class KnapsackTest {
    @Test
    public void testKnapSackBasic() {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int weightCapacity = 5;
        int expected = 7; // Maximum value should be 7 (items 1 and 4).
        int result = Knapsack.knapSack(weightCapacity, weights, values);
        assertEquals(expected, result);
    }

    @Test
    public void testKnapSackEmpty() {
        int[] weights = {};
        int[] values = {};
        int weightCapacity = 10;
        int expected = 0; // With no items, the result should be 0.
        int result = Knapsack.knapSack(weightCapacity, weights, values);
        assertEquals(expected, result);
    }

    @Test
    public void testKnapSackNoCapacity() {
        int[] weights = {2, 3, 4};
        int[] values = {3, 4, 5};
        int weightCapacity = 0;
        int expected = 0; // With no capacity, the result should be 0.
        int result = Knapsack.knapSack(weightCapacity, weights, values);
        assertEquals(expected, result);
    }

    @Test
    public void testKnapSackMaxCapacity() {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int weightCapacity = 10;
        int expected = 13; // Maximum value should be 13 (items 1, 3, and 4).
        int result = Knapsack.knapSack(weightCapacity, weights, values);
        assertEquals(expected, result);
    }

    @Test
    public void testKnapSackThrowsForInputsOfDifferentLength() {
        int[] weights = {2, 3, 4};
        int[] values = {3, 4, 5, 6}; // Different length values array.
        int weightCapacity = 5;
        assertThrows(IllegalArgumentException.class, () -> { Knapsack.knapSack(weightCapacity, weights, values); });
    }

    @Test
    public void testKnapSackThrowsForNullInputs() {
        int[] weights = {2, 3, 4};
        int[] values = {3, 4, 6};
        int weightCapacity = 5;
        assertThrows(IllegalArgumentException.class, () -> { Knapsack.knapSack(weightCapacity, null, values); });
        assertThrows(IllegalArgumentException.class, () -> { Knapsack.knapSack(weightCapacity, weights, null); });
    }

    @Test
    public void testKnapSackThrowsForNegativeCapacity() {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int weightCapacity = -5;
        assertThrows(IllegalArgumentException.class, () -> { Knapsack.knapSack(weightCapacity, weights, values); });
    }

    @Test
    public void testKnapSackThrowsForNegativeWeight() {
        int[] weights = {2, 0, 4};
        int[] values = {3, 4, 6};
        int weightCapacity = 5;
        assertThrows(IllegalArgumentException.class, () -> { Knapsack.knapSack(weightCapacity, weights, values); });
    }
}
