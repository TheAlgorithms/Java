package com.thealgorithms.dynamicprogramming;

public class Tribonacci {
    public static long calculateTribonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        
        if (n <= 2) {
            return 1;
        }

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 10; // Change n to the desired Tribonacci number you want to calculate
        long result = calculateTribonacci(n);
        System.out.println("The " + n + "th Tribonacci number is: " + result);
    }
}
