package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Greedy coin change (renamed for clarity).
 *
 * <p>Resolves naming conflict: "CoinChange" exists in both dynamicprogramming (DP: count combinations,
 * minimum coins) and greedyalgorithms. This class is the greedy approach using fixed denominations.
 *
 * <p>Returns a list of coins that sum to the given amount using a greedy strategy with
 * standard denominations (1, 2, 5, 10, 20, 50, 100, 500, 2000).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Change-making_problem">Change-making problem</a>
 */
public final class GreedyCoinChange {
    private GreedyCoinChange() {
    }

    /**
     * Returns a list of coins (in descending order of use) that sum to the given amount.
     * Uses greedy selection with fixed denominations.
     *
     * @param amount the target amount
     * @return list of coin values that sum to amount (may be empty if amount is 0)
     */
    public static ArrayList<Integer> coinChangeProblem(int amount) {
        Integer[] coins = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
        Arrays.sort(coins, Comparator.reverseOrder());

        ArrayList<Integer> ans = new ArrayList<>();

        for (Integer coin : coins) {
            while (coin <= amount) {
                ans.add(coin);
                amount -= coin;
            }
        }
        return ans;
    }
}
