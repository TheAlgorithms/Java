package com.thealgorithms.dynamicprogramming;

/*
Coin Tower Problem:

A and B are playing a new game today. They form a tower of N coins and make a move in alternate turns. B plays first. In one step, the player can remove either 1, X, or Y coins from the tower. The person to make the last move wins the game. Can you find out who wins the game?


Input : N (Number of coins), x & y (How many coins a player can remove)
Ouput: 'A' or 'B' (who is the winner)
*/

public class CoinTower {
    public static String coinTower(int N, int x, int y) {
        // swapping values of x and y if x is greater than y
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        boolean[] dp = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i == 1 || i == x || i == y) {
                dp[i] = true;
            } else if (i < x) {
                dp[i] = !dp[i - 1];
            } else if (i < y) {
                dp[i] = !(dp[i - 1] && dp[i - x]);
            } else {
                dp[i] = !(dp[i - 1] && dp[i - x] && dp[i - y]);
            }
        }

        boolean result = dp[N];

        // returning the winner
        if (result) {
            return "B";
        } else {
            return "A";
        }
    }
}
