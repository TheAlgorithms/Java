package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class KnapsackZeroOneTest {

    @Test
    void basicCheck() {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        int expected = 220;

        int result = KnapsackZeroOne.compute(values, weights, capacity, values.length);
        assertEquals(expected, result);
    }

    @Test
    void zeroCapacity() {
        int[] values = {10, 20, 30};
        int[] weights = {1, 1, 1};
        int capacity = 0;

        int result = KnapsackZeroOne.compute(values, weights, capacity, values.length);
        assertEquals(0, result);
    }

    @Test
    void zeroItems() {
        int[] values = {};
        int[] weights = {};
        int capacity = 10;

        int result = KnapsackZeroOne.compute(values, weights, capacity, 0);
        assertEquals(0, result);
    }

    @Test
    void weightsExceedingCapacity() {
        int[] values = {10, 20};
        int[] weights = {100, 200};
        int capacity = 50;

        int result = KnapsackZeroOne.compute(values, weights, capacity, values.length);
        assertEquals(0, result);
    }

    @Test
    void throwsOnNullArrays() {
        assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOne.compute(null, new int[] {1}, 10, 1));
        assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOne.compute(new int[] {1}, null, 10, 1));
    }

    @Test
    void throwsOnMismatchedArrayLengths() {
        assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOne.compute(new int[] {10, 20}, new int[] {5}, 15, 2));
    }

    @Test
    void throwsOnNegativeInputs() {
        assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOne.compute(new int[] {10}, new int[] {5}, -1, 1));

        assertThrows(IllegalArgumentException.class, () -> KnapsackZeroOne.compute(new int[] {10}, new int[] {5}, 5, -1));
    }
}
