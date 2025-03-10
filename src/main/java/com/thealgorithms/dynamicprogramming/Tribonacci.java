package com.thealgorithms.dynamicprogramming;

/**
 * The {@code Tribonacci} class provides a method to compute the n-th number in the Tribonacci sequence.
 * N-th Tribonacci Number - https://leetcode.com/problems/n-th-tribonacci-number/description/
 */
public final class Tribonacci {
    private Tribonacci() {
    }

    /**
     * Computes the n-th Tribonacci number.
     *
     * @param n the index of the Tribonacci number to compute
     * @return the n-th Tribonacci number
     */
    public static int compute(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int first = 0;
        int second = 1;
        int third = 1;

        for (int i = 3; i <= n; i++) {
            int next = first + second + third;
            first = second;
            second = third;
            third = next;
        }

        return third;
    }
}
