package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoinChangeTest {

    @Test
    void testChangeBasic() {
        int amount = 12;
        int[] coins = {2, 4, 5};

        assertEquals(5, CoinChange.change(coins, amount));
    }

    @Test
    void testChangeNoCoins() {
        int amount = 12;
        int[] coins = {};

        assertEquals(0, CoinChange.change(coins, amount));
    }

    @Test
    void testChangeNoAmount() {
        int amount = 0;
        int[] coins = {2, 4, 5};

        assertEquals(1, CoinChange.change(coins, amount));
    }

    @Test
    void testChangeImpossibleAmount() {
        int amount = 3;
        int[] coins = {2, 4, 5};

        assertEquals(0, CoinChange.change(coins, amount));
    }

    @Test
    void testMinimumCoinsBasic() {
        int amount = 12;
        int[] coins = {2, 4, 5};

        assertEquals(3, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsNoCoins() {
        int amount = 12;
        int[] coins = {};

        assertEquals(Integer.MAX_VALUE, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsNoAmount() {
        int amount = 0;
        int[] coins = {2, 4, 5};

        assertEquals(0, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsImpossibleAmount() {
        int amount = 3;
        int[] coins = {2, 4, 5};

        assertEquals(Integer.MAX_VALUE, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsExactAmount() {
        int amount = 10;
        int[] coins = {1, 5, 10};

        assertEquals(1, CoinChange.minimumCoins(coins, amount));
    }
}
