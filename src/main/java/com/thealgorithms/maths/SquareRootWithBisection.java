package com.thealgorithms.maths;

/**
 * Calculates the square root of x using the bisection method.
 * The bisection method is an iterative numerical technique for finding
 * the square root of a number.
 *
 * @author Sooraj-Mlahi
 */
public class SquareRootWithBisection {

    /**
     * Computes the square root of the given number using the bisection method.
     *
     * @param x The number for which the square root is to be calculated.
     * @return The approximate square root of x.
     */
    public static double calculateSquareRoot(double x) {
        if (x == 0 || x == 1) {
            return x;
        }

        double low = 0;
        double high = Math.max(1, x);
        double guess = (high + low) / 2;

        while (Math.abs(guess * guess - x) > 1e-10) {
            if (guess * guess < x) {
                low = guess;
            } else {
                high = guess;
            }
            guess = (high + low) / 2;
        }

        return guess;
    }

}
