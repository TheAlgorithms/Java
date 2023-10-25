package com.thealgorithms.maths;

// POWER (exponentials) Examples (a^b)
public class Pow {

    public static void main(String[] args) {
        assert pow(2, 0) == Math.pow(2, 0); // == 1
        assert pow(0, 2) == Math.pow(0, 2); // == 0
        assert pow(2, 10) == Math.pow(2, 10); // == 1024
        assert pow(10, 2) == Math.pow(10, 2); // == 100
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument
     *
     * @param a the base.
     * @param b the exponent.
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    public static double pow(int a, int b) {
        if (b == 0) {
            return 1.0;
        } else if (b > 0) {
            double result = 1.0;
            for (int i = 0; i < b; i++) {
                result *= a;
            }
            return result;
        } else {
            // Handle negative exponent by taking the reciprocal of the result
            return 1.0 / pow(a, -b);
        }
    }
}
