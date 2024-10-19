package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RodCutting class.
 * This test class verifies the correctness of the cutRod method for various input cases.
 */
class RodCuttingTest {

    /**
     * Test case for cutting a rod of length 1.
     * The expected maximum obtainable value is the price of the piece of length 1.
     */
    @Test
    void testCutRodLength1() {
        int[] prices = {1}; // Price for piece of length 1
        int length = 1;
        int expectedValue = 1;
        assertEquals(expectedValue, RodCutting.cutRod(prices, length), "The maximum obtainable value for a rod of length 1 should be 1.");
    }

    /**
     * Test case for cutting a rod of length 2.
     * The expected maximum obtainable value is the best price combination for length 2.
     */
    @Test
    void testCutRodLength2() {
        int[] prices = {1, 5}; // Prices for lengths 1 and 2
        int length = 2;
        int expectedValue = 5; // Best value is to cut it into a single piece of length 2
        assertEquals(expectedValue, RodCutting.cutRod(prices, length), "The maximum obtainable value for a rod of length 2 should be 5.");
    }

    /**
     * Test case for cutting a rod of length 3.
     * The expected maximum obtainable value is the best price combination for length 3.
     */
    @Test
    void testCutRodLength3() {
        int[] prices = {1, 5, 8}; // Prices for lengths 1, 2, and 3
        int length = 3;
        int expectedValue = 8; // Best value is to cut it into a single piece of length 3
        assertEquals(expectedValue, RodCutting.cutRod(prices, length), "The maximum obtainable value for a rod of length 3 should be 8.");
    }

    /**
     * Test case for cutting a rod of length 4.
     * The expected maximum obtainable value is the best price combination for length 4.
     */
    @Test
    void testCutRodLength4() {
        int[] prices = {1, 5, 8, 9}; // Prices for lengths 1, 2, 3, and 4
        int length = 4;
        int expectedValue = 10; // Best value is to cut it into two pieces of length 2
        assertEquals(expectedValue, RodCutting.cutRod(prices, length), "The maximum obtainable value for a rod of length 4 should be 10.");
    }

    /**
     * Test case for cutting a rod of length 5.
     * The expected maximum obtainable value is the best price combination for length 5.
     */
    @Test
    void testCutRodLength5() {
        int[] prices = {1, 5, 8, 9, 10}; // Prices for lengths 1, 2, 3, 4, and 5
        int length = 5;
        int expectedValue = 13; // Best value is to cut it into pieces of lengths 2 and 3
        assertEquals(expectedValue, RodCutting.cutRod(prices, length), "The maximum obtainable value for a rod of length 5 should be 13.");
    }

    /**
     * Test case for cutting a rod of length 0.
     * The expected maximum obtainable value should be 0 since the rod has no length.
     */
    @Test
    void testCutRodLength0() {
        int[] prices = {1, 5, 8, 9, 10}; // Prices are irrelevant for length 0
        int length = 0;
        int expectedValue = 0; // No value obtainable from a rod of length 0
        assertEquals(expectedValue, RodCutting.cutRod(prices, length), "The maximum obtainable value for a rod of length 0 should be 0.");
    }

    /**
     * Test case for an empty prices array.
     * The expected maximum obtainable value should still be 0 for any length.
     */
    @Test
    void testCutRodEmptyPrices() {
        int[] prices = {};
        int length = 5;
        assertThrows(IllegalArgumentException.class, () -> RodCutting.cutRod(prices, length), "An empty prices array should throw an IllegalArgumentException.");
    }
    @Test
    void testCutRodNegativeLength() {
        int[] prices = {1, 5, 8, 9, 10}; // Prices are irrelevant for negative length
        int length = -1;
        assertThrows(IllegalArgumentException.class, () -> RodCutting.cutRod(prices, length), "A negative rod length should throw an IllegalArgumentException.");
    }
}
