package com.thealgorithms.maths;

/**
 * Neon Number algorithm.
 * A number whose sum of digits of its square equals the number itself.
 * Example: 9^2=81⇒8+1=9
 *
 * @see <a href="https://en.wikipedia.org/wiki/Recreational_mathematics">Wikipedia</a>
 */
public class NeonNumber {

    private NeonNumber() {
    }

    public static boolean isNeon(int number) {
        int square = number * number;
        int digitSum = 0;
        while (square > 0) {
            digitSum = digitSum + square % 10;
            square /= 10;
        }
        return digitSum == number;
    }
}