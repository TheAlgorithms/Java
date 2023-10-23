package com.thealgorithms.maths;

// POWER (exponentials) Examples (a^b)
public class Pow {

    public static void main(String[] args) {
        assert pow(2, 0) == Math.pow(2, 0); // == 1
        assert pow(0, 2) == Math.pow(0, 2); // == 0
        assert pow(2, 10) == Math.pow(2, 10); // == 1024
        assert pow(10, 2) == Math.pow(10, 2); // == 100

        // powPlus method test.
        assert powPlus(2.00000, 10) == Math.pow(2.00000, 10); // 1024.00000
        System.out.println(powPlus(2.00000, 10) + " " + Math.pow(2.00000, 10));
        assert powPlus(2.10000, 3) == Math.pow(2.10000, 3); // 9.26100
        System.out.println(powPlus(2.10000, 3) + " " + Math.pow(2.10000, 3));
        assert powPlus(2.00000, 2) == Math.pow(2.00000, -2); // 0.25000
        System.out.println(powPlus(2.00000, -2) + " " + Math.pow(2.00000, -2));
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument
     *
     * @param a the base.
     * @param b the exponent.
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    public static long pow(int a, int b) {
        long result = 1;
        for (int i = 1; i <= b; i++) {
            result *= a;
        }
        return result;
    }

    /**
     * A power of nth method that can calculate floating-point type data.
     * @param x the base number
     * @param n the exponent
     * @return the value {@code x}<sup>{@code n}</sup>
     */
    public static double powPlus(double x, int n) {

        if (x == 0.0f) {
            return 0.0d;
        }

        long b = n;
        double res = 1.0;

        if (b < 0) {
            x = 1 / x;
            b = -b;
        }

        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }

        return res;
    }
}
