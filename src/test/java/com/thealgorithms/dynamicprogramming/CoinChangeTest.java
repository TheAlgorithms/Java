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

        // No coins are available, so no combinations are possible
        assertEquals(0, CoinChange.change(coins, amount));
    }

    @Test
    void testChangeNoAmount() {
        int amount = 0;
        int[] coins = {2, 4, 5};

        // For amount 0, there is exactly 1 way to make change (using no coins)
        assertEquals(1, CoinChange.change(coins, amount));
    }

    @Test
    void testChangeImpossibleAmount() {
        int amount = 3;
        int[] coins = {2, 4, 5};

        // It's impossible to make change for amount 3 with the given coins
        assertEquals(0, CoinChange.change(coins, amount));
    }

    @Test
    void testMinimumCoinsBasic() {
        int amount = 12;
        int[] coins = {2, 4, 5};

        // The minimum number of coins to make 12 is 3 (4 + 4 + 4)
        assertEquals(3, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsNoCoins() {
        int amount = 12;
        int[] coins = {};

        // No coins are available, so it's impossible to make the amount
        assertEquals(Integer.MAX_VALUE, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsNoAmount() {
        int amount = 0;
        int[] coins = {2, 4, 5};

        // For amount 0, 0 coins are required
        assertEquals(0, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsImpossibleAmount() {
        int amount = 3;
        int[] coins = {2, 4, 5};

        // It's impossible to make amount 3 with the given coins
        assertEquals(Integer.MAX_VALUE, CoinChange.minimumCoins(coins, amount));
    }

    @Test
    void testMinimumCoinsExactAmount() {
        int amount = 10;
        int[] coins = {1, 5, 10};

        // The minimum number of coins to make 10 is 1 (using the 10-coin)
        assertEquals(1, CoinChange.minimumCoins(coins, amount));
    }
}
