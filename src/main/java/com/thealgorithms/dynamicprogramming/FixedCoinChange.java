package com.thealgorithms.dynamicprogramming;
/* 
Dynamic Programming solution for the "Coin Change" problem, which calculates 
the distinct ways to obtain change for a given amount while considering the
single presence of each coin:

*/
public class FixedCoinChange {

    public static void main(String[] args) {
        int targetAmount = 10;
        int[] availableCoins = { 1, 2, 5 };
        int numDistinctWays = countDistinctWaysToGetChange(targetAmount, availableCoins);

        System.out.println("Number of distinct ways to get change: " + numDistinctWays);
    }

    /**
     * The provided method utilizes a recursive approach to determine
     * the number of combinations possible for obtaining change for a
     * given amount, while ensuring that no coin is repeated in the process.
     **/
    public static int countDistinctWaysToGetChange(int targetAmount, int[] availableCoins) {
        int[] distinctWays = new int[targetAmount + 1];
        distinctWays[0] = 1;

        for (int coin : availableCoins) {
            for (int amount = coin; amount <= targetAmount; amount++) {
                distinctWays[amount] += distinctWays[amount - coin];
            }
        }

        return distinctWays[targetAmount];
    }
}
