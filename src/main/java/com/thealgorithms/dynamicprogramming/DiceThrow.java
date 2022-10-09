package com.thealgorithms.dynamicprogramming;

// Given N dice each with M faces, numbered from 1 to M, find the number of ways to get sum X.
// X is the summation of values on each face when all the dice are thrown.

/* The Naive approach is to find all the possible combinations of values from n dice and
keep on counting the results that sum to X. This can be done using recursion. */
// The above recursion solution exhibits overlapping subproblems.

/* Hence, storing the results of the solved sub-problems saves time.
And it can be done using Dynamic Programming(DP).
Following is implementation of Dynamic Programming approach. */
// Code ---->
// Java program to find number of ways to get sum 'x' with 'n'
// dice where every dice has 'm' faces
class DP {

    /* The main function that returns the number of ways to get sum 'x' with 'n' dice and 'm' with m faces. */
    public static long findWays(int m, int n, int x) {
        /* Create a table to store the results of subproblems. 
    One extra row and column are used for simplicity 
    (Number of dice is directly used as row index and sum is directly used as column index). 
    The entries in 0th row and 0th column are never used. */
        long[][] table = new long[n + 1][x + 1];

        /* Table entries for only one dice */
        for (int j = 1; j <= m && j <= x; j++) {
            table[1][j] = 1;
        }

        /* Fill rest of the entries in table using recursive relation 
    i: number of dice, j: sum */
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                for (int k = 1; k < j && k <= m; k++) {
                    table[i][j] += table[i - 1][j - k];
                }
            }
        }

        return table[n][x];
    }

    public static void main(String[] args) {
        System.out.println(findWays(4, 2, 1));
        System.out.println(findWays(2, 2, 3));
        System.out.println(findWays(6, 3, 8));
        System.out.println(findWays(4, 2, 5));
        System.out.println(findWays(4, 3, 5));
    }
}
/*
OUTPUT:
0
2
21
4
6
 */
// Time Complexity: O(m * n * x) where m is number of faces, n is number of dice and x is given sum.
