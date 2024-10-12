package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the WineProblem class.
 * This test class verifies the correctness of the wine selling problem solutions.
 */
class WineProblemTest {

    /**
     * Test for wpRecursion method.
     */
    @Test
    void testWpRecursion() {
        int[] wines = {2, 3, 5, 1, 4}; // Prices of wines
        int expectedProfit = 50; // The expected maximum profit
        assertEquals(expectedProfit, WineProblem.wpRecursion(wines, 0, wines.length - 1), "The maximum profit using recursion should be 50.");
    }

    /**
     * Test for wptd method (Top-Down DP with Memoization).
     */
    @Test
    void testWptd() {
        int[] wines = {2, 3, 5, 1, 4}; // Prices of wines
        int expectedProfit = 50; // The expected maximum profit
        assertEquals(expectedProfit, WineProblem.wptd(wines, 0, wines.length - 1, new int[wines.length][wines.length]), "The maximum profit using top-down DP should be 50.");
    }

    /**
     * Test for wpbu method (Bottom-Up DP with Tabulation).
     */
    @Test
    void testWpbu() {
        int[] wines = {2, 3, 5, 1, 4}; // Prices of wines
        int expectedProfit = 50; // The expected maximum profit
        assertEquals(expectedProfit, WineProblem.wpbu(wines), "The maximum profit using bottom-up DP should be 50.");
    }

    /**
     * Test with a single wine.
     */
    @Test
    void testSingleWine() {
        int[] wines = {10}; // Only one wine
        int expectedProfit = 10; // Selling the only wine at year 1
        assertEquals(expectedProfit, WineProblem.wpbu(wines), "The maximum profit for a single wine should be 10.");
    }

    /**
     * Test with multiple wines of the same price.
     */
    @Test
    void testSamePriceWines() {
        int[] wines = {5, 5, 5}; // All wines have the same price
        int expectedProfit = 30; // Profit is 5 * (1 + 2 + 3)
        assertEquals(expectedProfit, WineProblem.wpbu(wines), "The maximum profit with same price wines should be 30.");
    }

    /**
     * Test with no wines.
     */
    @Test
    void testNoWines() {
        int[] wines = {};
        assertThrows(IllegalArgumentException.class, () -> WineProblem.wpbu(wines), "The maximum profit for no wines should throw an IllegalArgumentException.");
    }
}
