
/**
 * Loot Houses Problem Solution
 * Problem Statement:
 * A thief wants to loot houses. He knows the amount of money in each house.
 * He cannot loot two consecutive houses. Find the maximum amount of money he can loot.
 *
 * Reference:
 * Wikipedia - Loot Houses Problem: https://en.wikipedia.org/wiki/House_robber_problem
 */

package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class LootHouses {
    // Recursive function to calculate maximum loot using given indices
    public static int maxLootRecursive(List<Integer> houses, int index) {
        if (index >= houses.size()) {
            return 0;
        }

        // Calculate maximum loot if we take the current house
        int take = houses.get(index) + maxLootRecursive(houses, index + 2);

        // Calculate maximum loot if we skip the current house
        int notTake = maxLootRecursive(houses, index + 1);

        // Return the maximum of the two options
        return Math.max(take, notTake);
    }

    // Dynamic programming function to calculate maximum loot
    public static int maxLootDP(List<Integer> houses) {
        int n = houses.size();
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            if (i >= n) {
                dp[i] = 0;
            } else {
                int take = houses.get(i);

                if (i + 2 < n) {
                    take += dp[i + 2];
                }

                int notTake = dp[i + 1];

                dp[i] = Math.max(take, notTake);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        List<Integer> houses = Arrays.asList(2, 7, 13, 8, 1);

        // Calculate maximum loot using recursion
        int maxLootRecursion = maxLootRecursive(houses, 0);
        System.out.println("Maximum loot (Recursive): " + maxLootRecursion);

        // Calculate maximum loot using dynamic programming
        int maxLootDynamic = maxLootDP(houses);
        System.out.println("Maximum loot (Dynamic Programming): " + maxLootDynamic);
    }
}
