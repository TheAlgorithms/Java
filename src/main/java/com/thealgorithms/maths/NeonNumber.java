package com.thealgorithms.maths;

/**
 * Neon Number algorithm.
 * A number whose sum of digits of its square equals the number itself.
 * Example: 9 - 9^2 = 81 - 8+1 = 9
 *
 * @see <a href="https://en.wikipedia.org/wiki/Recreational_mathematics">
 *     Wikipedia</a>
 */
public final class NeonNumber {

    private NeonNumber() {
    }

    /**
     * Check if a number is a Neon number.
     *
     * @param number the input number
     * @return true if neon number, false otherwise
     */
    public static boolean isNeon(final int number) {
        int square = number * number;
        int digitSum = 0;
        int temp = square;
        while (temp > 0) {
            digitSum = digitSum + temp % 10;
            temp /= 10;
        }
        return digitSum == number;
    }
}