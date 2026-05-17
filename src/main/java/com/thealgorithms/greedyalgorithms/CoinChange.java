package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// Problem Link : https://en.wikipedia.org/wiki/Change-making_problem

/**
 * The Coin Change problem finds the minimum number of coins needed
 * to make a given amount using a greedy approach.
 *
 * <p>Note: This greedy approach works optimally for standard coin systems
 * (like Indian currency), but may not work for all arbitrary coin sets.
 * For arbitrary denominations, dynamic programming is preferred.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Change-making_problem">Change-making problem</a>
 */
public final class CoinChange {
    private CoinChange() {
    }

    /**
     * Returns the list of coins used to make the given amount
     * using a greedy algorithm with standard denominations.
     *
     * <p>Time Complexity: O(n log n) where n is the number of coin denominations
     * <p>Space Complexity: O(n)
     *
     * @param amount the total amount to make change for
     * @return list of coins used to make the amount
     */
    public static ArrayList<Integer> coinChangeProblem(int amount) {
        // Define an array of coin denominations in descending order
        Integer[] coins = {1, 2, 5, 10, 20, 50, 100, 500, 2000};

        // Sort the coin denominations in descending order
        Arrays.sort(coins, Comparator.reverseOrder());

        ArrayList<Integer> ans = new ArrayList<>(); // List to store selected coins

        // Iterate through the coin denominations
        for (int i = 0; i < coins.length; i++) {
            // Check if the current coin denomination can be used to reduce the remaining amount
            if (coins[i] <= amount) {
                // Repeatedly subtract the coin denomination from the remaining amount
                while (coins[i] <= amount) {
                    ans.add(coins[i]); // Add the coin to the list of selected coins
                    amount -= coins[i]; // Update the remaining amount
                }
            }
        }
        return ans;
    }
}
