package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// Problem Link : https://en.wikipedia.org/wiki/Change-making_problem

public final class CoinChange {
    private CoinChange() {
    }
    // Function to solve the coin change problem
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
