package com.thealgorithms.dynamicprogramming;
import java.util.Arrays;

class CountPalindromicSubsequences {
    static int MOD = (int) (1e9 + 7);
    public static long countPS(String str) {

        long[][] memo = new long[str.length()][str.length()];
        for (long[] m : memo) Arrays.fill(m, -1);
        return f(0, str, str.length() - 1, memo);
    }
    static long f(int i, String str, int j, long[][] memo) {
        if (i > j) return 0;
        if (i == j) {
            if (str.charAt(i) == str.charAt(j)) return 1;
            return 0;
        }
        if (memo[i][j] != -1) return memo[i][j];
        if (str.charAt(i) == str.charAt(j)) return memo[i][j] = (1 + f(i + 1, str, j, memo) % MOD + f(i, str, j - 1, memo) % MOD) % MOD;
        else return memo[i][j] = (f(i + 1, str, j, memo) % MOD + f(i, str, j - 1, memo) % MOD - f(i + 1, str, j - 1, memo) % MOD + MOD) % MOD;
    }
}