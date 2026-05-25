package com.thealgorithms.dynamicprogramming;
import java.util.Arrays;

/**
 * A generalized template for the Digit Dynamic Programming (Digit DP)
 * technique.
 * Digit DP is used to count numbers within a range [L, R] that satisfy specific
 * digit properties.
 * This specific implementation demonstrates counting the numbers whose digit
 * sum equals a target value.
 *
 * <p>
 * Example:
 * countRangeWithDigitSum(1, 100, 5) returns 6 (numbers: 5, 14, 23, 32, 41, 50)
 */
public final class DigitDP {

    // Maximum theoretical digit sum for a 64-bit signed long integer (9 * 19 digits
    // = 171)
    private static final int MAX_DIGIT_SUM = 171;

    private DigitDP() {
        // Prevent instantiation for utility/algorithm template class
    }

    /**
     * Counts how many numbers in the range [L, R] have a digit sum equal to the
     * target.
     *
     * @param l      The lower bound of the range (inclusive).
     * @param r      The upper bound of the range (inclusive).
     * @param target The exact sum of digits required.
     * @return The count of valid integers.
     */
    public static long countRangeWithDigitSum(long l, long r, int target) {
        if (l > r || target < 0 || target > MAX_DIGIT_SUM) {
            return 0;
        }
        long countR = countWithDigitSum(r, target);
        long countLMinus1 = countWithDigitSum(l - 1, target);
        return countR - countLMinus1;
    }

    private static long countWithDigitSum(long number, int target) {
        if (number < 0) {
            return 0;
        }
        String numStr = Long.toString(number);
        int length = numStr.length();

        // dp[index][current_sum][tight]
        long[][][] dp = new long[length][MAX_DIGIT_SUM + 1][2];
        for (long[][] row : dp) {
            for (long[] col : row) {
                Arrays.fill(col, -1);
            }
        }

        return solve(0, 0, 1, numStr, target, dp);
    }

    /**
     * Recursive memoized function to explore digit placements.
     *
     * Time Complexity: O(number_of_digits * target_sum * 10)
     * Space Complexity: O(number_of_digits * target_sum * 2)
     *
     * @param index      Current digit position from left to right (most significant
     *                   first).
     * @param currentSum Cumulative sum of digits chosen so far.
     * @param tight      Flag indicating if current prefix matches the original
     *                   number boundary.
     * @param numStr     String representation of the upper ceiling limit.
     * @param target     The exact required sum of digits.
     * @param dp         Memoization matrix cache table.
     * @return Total valid combinations from the current state configuration.
     */
    private static long solve(int index, int currentSum, int tight, String numStr, int target, long[][][] dp) {
        // Base case: If we have processed all digits
        if (index == numStr.length()) {
            return currentSum == target ? 1 : 0;
        }

        // Return memoized state if already evaluated
        if (dp[index][currentSum][tight] != -1) {
            return dp[index][currentSum][tight];
        }

        long ans = 0;
        // Determine the maximum limit for the current position digit
        int limit = (tight == 1) ? (numStr.charAt(index) - '0') : 9;

        // Iterate through all possible valid digits for this position
        for (int digit = 0; digit <= limit; digit++) {
            int nextSum = currentSum + digit;

            // Optimization: If the digit sum exceeds the target, prune branch
            if (nextSum > target) {
                continue;
            }

            // Next state remains tight only if current state is tight and we place the
            // exact limit digit
            int nextTight = (tight == 1 && digit == limit) ? 1 : 0;
            ans += solve(index + 1, nextSum, nextTight, numStr, target, dp);
        }

        dp[index][currentSum][tight] = ans;
        return ans;
    }
}
