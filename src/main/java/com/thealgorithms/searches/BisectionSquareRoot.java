package com.thealgorithms.searches;

public class BisectionSquareRoot {
    /**
     * Calculates the square root of a number using the bisection method and rounds the result to the nearest integer.
     *
     * @param x The number for which to find the square root.
     * @return The integer square root of the number x.
     */
    public static int squareRootBisection(int x) {
        int a = 0;
        int b = x;

        while (a <= b) {
            int c = (a + b) / 2;
            long cSquared = (long) c * c;

            if (cSquared == x) {
                return c;
            } else if (cSquared < x) {
                a = c + 1;
            } else {
                b = c - 1;
            }
        }

        return b;
    }
}

