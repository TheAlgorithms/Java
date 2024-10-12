package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the SwapNumbersUsingXor class.
 */
public class SwapNumbersUsingXorTest {

    /**
     * Test the swap method with positive numbers.
     */
    @Test
    public void testSwapPositiveNumbers() {
        int[] result = SwapNumbersUsingXor.swap(5, 3);
        assertArrayEquals(new int[]{3, 5}, result);
    }

    /**
     * Test the swap method with negative numbers.
     */
    @Test
    public void testSwapNegativeNumbers() {
        int[] result = SwapNumbersUsingXor.swap(-5, -3);
        assertArrayEquals(new int[]{-3, -5}, result);
    }

    /**
     * Test the swap method with a positive and a negative number.
     */
    @Test
    public void testSwapPositiveAndNegativeNumbers() {
        int[] result = SwapNumbersUsingXor.swap(5, -3);
        assertArrayEquals(new int[]{-3, 5}, result);
    }

    /**
     * Test the swap method with zero.
     */
    @Test
    public void testSwapWithZero() {
        int[] result = SwapNumbersUsingXor.swap(0, 3);
        assertArrayEquals(new int[]{3, 0}, result);
    }
}
