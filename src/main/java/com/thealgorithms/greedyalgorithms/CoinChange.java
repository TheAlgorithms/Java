package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CoinChange {
    // Function to solve the coin change problem
    public static void coinChangeProblem(int amount) {
        // Define an array of coin denominations in descending order
        Integer coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 2000};

        // Sort the coin denominations in descending order
        Arrays.sort(coins, Comparator.reverseOrder());

        int count = 0; // Variable to keep track of the total number of coins used
        ArrayList<Integer> ans = new ArrayList<>(); // List to store selected coins

        // Iterate through the coin denominations
        for (int i = 0; i < coins.length; i++) {
            // Check if the current coin denomination can be used to reduce the remaining amount
            if (coins[i] <= amount) {
                // Repeatedly subtract the coin denomination from the remaining amount
                while (coins[i] <= amount) {
                    count++; // Increment the count of coins used
                    ans.add(coins[i]); // Add the coin to the list of selected coins
                    amount -= coins[i]; // Update the remaining amount
                }
            }
        }

        // Print the selected coins
        System.out.println("Coins: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }

    public static void main(String args[]) {
        // Call the coinChangeProblem function with the given amount (591)
        coinChangeProblem(591);
    }
}
