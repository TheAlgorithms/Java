package com.thealgorithms.dynamicprogramming;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public final class CoinChange {
    private CoinChange() {
    }

    /**
     * This method finds the number of combinations of getting change for a
     * given amount and change coins
     *
     * @param coins The list of coins
     * @param amount The amount for which we need to find the change Finds the
     * number of combinations of change
     */
    public static int change(int[] coins, int amount) {
        int[] combinations = new int[amount + 1];
        combinations[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                combinations[i] += combinations[i - coin];
            }
        }

        return combinations[amount];
    }

    /**
     * This method finds the minimum number of coins needed for a given amount.
     *
     * @param coins The list of coins
     * @param amount The amount for which we need to find the minimum number of
     * coins. Finds the minimum number of coins that make a given value.
     */
    public static int minimumCoins(int[] coins, int amount) {
        // minimumCoins[i] will store the minimum coins needed for amount i
        int[] minimumCoins = new int[amount + 1];

        minimumCoins[0] = 0;

        for (int i = 1; i <= amount; i++) {
            minimumCoins[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    int subRes = minimumCoins[i - coin];
                    if (subRes != Integer.MAX_VALUE && subRes + 1 < minimumCoins[i]) {
                        minimumCoins[i] = subRes + 1;
                    }
                }
            }
        }

        return minimumCoins[amount];
    }
}
