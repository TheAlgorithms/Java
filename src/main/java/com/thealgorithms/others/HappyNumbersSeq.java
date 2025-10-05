package com.thealgorithms.others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A utility class for working with Happy Numbers.
 *
 * <p>
 * A Happy Number is defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the
 * squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it
 * loops endlessly in a
 * cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers, while those
 * that do not end
 * in 1 are unhappy (or sad) numbers.
 *
 * <p>
 * For example:
 * <ul>
 * <li>7 is a happy number: 7 → 49 → 97 → 130 → 10 → 1</li>
 * <li>2 is not a happy number (sad number): 2 → 4 → 16 → 37 → 58 → 89 → 145 →
 * 42 → 20 → 4 (cycle)</li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Happy_number">Happy Number -
 *      Wikipedia</a>
 * @see <a href="https://mathworld.wolfram.com/HappyNumber.html">Happy Number -
 *      Wolfram MathWorld</a>
 */
public final class HappyNumbersSeq {
    private HappyNumbersSeq() {
    }

    /**
     * Known cycle numbers that indicate a sad number.
     * If the sequence reaches any of these numbers, it will cycle indefinitely
     * without reaching 1.
     */
    private static final Set<Integer> CYCLE_NUMS = new HashSet<>(Arrays.asList(4, 16, 20, 37, 58, 145));

    /**
     * Main method to demonstrate happy number detection.
     * Reads a number from user input and displays the sequence until it reaches 1
     * (happy)
     * or enters a cycle (sad).
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = in.nextInt();
        while (n != 1 && !isSad(n)) {
            System.out.print(n + " ");
            n = sumSquares(n);
        }
        String res = n == 1 ? "1 Happy number" : "Sad number";
        System.out.println(res);
        in.close();
    }

    /**
     * Determines if a number is a happy number.
     *
     * @param n the number to check (must be positive)
     * @return {@code true} if the number is happy, {@code false} otherwise
     * @throws IllegalArgumentException if n is not positive
     */
    public static boolean isHappy(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        while (n != 1 && !isSad(n)) {
            n = sumSquares(n);
        }
        return n == 1;
    }

    /**
     * Computes the sum of the squares of the digits of a number.
     *
     * @param n the number whose digits will be squared and summed
     * @return the sum of the squares of the digits
     */
    static int sumSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    /**
     * Checks if a number is part of the known cycle that indicates a sad number.
     *
     * @param n the number to check
     * @return {@code true} if the number is in the sad cycle, {@code false}
     *         otherwise
     */
    static boolean isSad(int n) {
        return CYCLE_NUMS.contains(n);
    }
}
