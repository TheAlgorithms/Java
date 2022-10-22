package com.thealgorithms.backtracking;

import java.util.Scanner;

/*
 * Problem Statement :
 * Find the number of ways that a given integer, N , can be expressed as the sum of the Xth powers of unique, natural numbers.
 * For example, if N=100 and X=3, we have to find all combinations of unique cubes adding up to 100. The only solution is 1^3+2^3+3^3+4^3.
 * Therefore output will be 1.
 */
public class PowerSum {

    private int count = 0, sum = 0;

    public int powSum(int N, int X) {
        Sum(N, X, 1);
        return count;
    }

    //here i is the natural number which will be raised by X and added in sum.
    public void Sum(int N, int X, int i) {
        //if sum is equal to N that is one of our answer and count is increased.
        if (sum == N) {
            count++;
            return;
        } //we will be adding next natural number raised to X only if on adding it in sum the result is less than N.
        else if (sum + power(i, X) <= N) {
            sum += power(i, X);
            Sum(N, X, i + 1);
            //backtracking and removing the number added last since no possible combination is there with it.
            sum -= power(i, X);
        }
        if (power(i, X) < N) {
            //calling the sum function with next natural number after backtracking if when it is raised to X is still less than X.
            Sum(N, X, i + 1);
        }
    }

    //creating a separate power function so that it can be used again and again when required.
    private int power(int a, int b) {
        return (int) Math.pow(a, b);
    }
}
