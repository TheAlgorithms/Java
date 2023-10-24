package com.thealgorithms.maths;

// POWER (exponentials) Examples (a^b)
public class Pow {

    public static void main(String[] args) {
        assert pow(2, 0) == Math.pow(2, 0); // == 1
        assert pow(0, 2) == Math.pow(0, 2); // == 0
        assert pow(2, 10) == Math.pow(2, 10); // == 1024
        assert pow(10, 2) == Math.pow(10, 2); // == 100

        // Additional test cases
        assert pow(2.00000, 10) == 1024.00000;
        assert pow(2.10000, 3) == 9.26100;
        assert pow(0, 5) == 0; // Special case: 0^b where b is positive should always be 0
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument.
     *
     * @param a the base.
     * @param b the exponent.
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    public static double pow(double a, int b) {
        // Special case: 0^b where b is positive should always be 0
        if (a == 0 && b > 0) {
            return 0;
        }

        double result = 1;
        for (int i = 0; i < Math.abs(b); i++) {
            result *= a;
        }
        return b >= 0 ? result : 1 / result;
    }
}
