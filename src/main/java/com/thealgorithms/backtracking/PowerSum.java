package com.thealgorithms.backtracking;

/**
 * Problem Statement:
 * Find the number of ways that a given integer, N, can be expressed as the sum of the Xth powers
 * of unique, natural numbers.
 * For example, if N=100 and X=3, we have to find all combinations of unique cubes adding up to 100.
 * The only solution is 1^3 + 2^3 + 3^3 + 4^3. Therefore, the output will be 1.
 *
 * N is represented by the parameter 'targetSum' in the code.
 * X is represented by the parameter 'power' in the code.
 */
public class PowerSum {

    /**
     * Calculates the number of ways to express the target sum as a sum of Xth powers of unique natural numbers.
     *
     * @param targetSum The target sum to achieve (N in the problem statement)
     * @param power The power to raise natural numbers to (X in the problem statement)
     * @return The number of ways to express the target sum
     */
    public int powSum(int targetSum, int power) {
        // Special case: when both targetSum and power are zero
        if (targetSum == 0 && power == 0) {
            return 1; // by convention, one way to sum to zero: use nothing
        }
        return sumRecursive(targetSum, power, 1, 0);
    }

    /**
     * Recursively calculates the number of ways to express the remaining sum as a sum of Xth powers.
     *
     * @param remainingSum The remaining sum to achieve
     * @param power The power to raise natural numbers to (X in the problem statement)
     * @param currentNumber The current natural number being considered
     * @param currentSum The current sum of powered numbers
     * @return The number of valid combinations
     */
    private int sumRecursive(int remainingSum, int power, int currentNumber, int currentSum) {
        int newSum = currentSum + (int) Math.pow(currentNumber, power);

        if (newSum == remainingSum) {
            return 1;
        }
        if (newSum > remainingSum) {
            return 0;
        }

        return sumRecursive(remainingSum, power, currentNumber + 1, newSum) + sumRecursive(remainingSum, power, currentNumber + 1, currentSum);
    }
}
