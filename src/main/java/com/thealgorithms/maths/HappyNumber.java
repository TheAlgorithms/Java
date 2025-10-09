package com.thealgorithms.maths;

/**
 * A Happy Number is defined as a number which eventually reaches 1 when replaced
 * by the sum of the squares of each digit.
 * If it falls into a cycle that does not include 1, then it is not a happy number.

 * Example:
 * 19 → 1² + 9² = 82
 * 82 → 8² + 2² = 68
 * 68 → 6² + 8² = 100
 * 100 → 1² + 0² + 0² = 1 → Happy Number!
 */
public final class HappyNumber {

    private HappyNumber() {
    }

    /**
     * Checks whether the given number is a Happy Number.
     * Uses Floyd’s Cycle Detection algorithm (tortoise and hare method)
     * to detect loops efficiently.
     *
     * @param n The number to check
     * @return true if n is a Happy Number, false otherwise
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = sumOfSquares(slow); // move 1 step
            fast = sumOfSquares(sumOfSquares(fast)); // move 2 steps
        } while (slow != fast);

        return slow == 1; // If cycle ends in 1 → Happy number
    }

    /**
     * Calculates the sum of squares of the digits of a number.
     *
     * Example:
     * num = 82 → 8² + 2² = 64 + 4 = 68
     *
     * @param num The number to calculate sum of squares of digits
     * @return The sum of squares of the digits
     */
    private static int sumOfSquares(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }
}
