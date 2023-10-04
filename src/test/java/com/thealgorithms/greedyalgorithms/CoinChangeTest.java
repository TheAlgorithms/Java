package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class CoinChangeTest {
    @Test
    public void testCoinChangeProblemWithValidAmount() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(500, 50, 20, 20, 1));
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(591);
        assertEquals(expected, coins);
    }

    @Test
    public void testCoinChangeProblemWithLargeAmount() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2000));
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(2000);
        assertEquals(expected, coins);
    }

    @Test
    public void testCoinChangeProblemWithPartialCoins2() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(500, 50, 20));
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(570);
        assertEquals(expected, coins);
    }

    @Test
    public void testCoinChangeProblemWithSmallAmount() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2, 1));
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(3);
        assertEquals(expected, coins);
    }

    @Test
    public void testCoinChangeProblemWithLargeAmountAndMultipleDenominations() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2000, 2000, 2000, 2000, 500, 500, 500, 100, 100, 100, 100, 50, 20, 20, 5, 2, 2));
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(9999);
        assertEquals(expected, coins);
    }

    @Test
    public void testCoinChangeProblemWithAllDenominations() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2000, 500, 100, 100, 100, 50, 20, 10, 5, 2, 1));
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(2888);
        assertEquals(expected, coins);
    }

    @Test
    public void testCoinChangeProblemWithZeroAmount() {
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayList<Integer> coins = CoinChange.coinChangeProblem(0);
        assertEquals(expected, coins);
    }
}
