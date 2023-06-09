package com.thealgorithms.maths;

/**
 * Wikipedia link for Automorphic Number : https://en.wikipedia.org/wiki/Automorphic_number
 * A number is said to be an Automorphic, if it is present in the last digit(s)
 * of its square. Example- Let the number be 25, its square is 625. Since,
 * 25(The input number) is present in the last two digits of its square(625), it
 * is an Automorphic Number.
 */

import java.math.BigInteger;

public class AutomorphicNumber {

    /**
     * A function to check if a number is Automorphic number or not
     *
     * @param n The number to be checked
     * @return {@code true} if {@code a} is Automorphic number, otherwise
     *         {@code false}
     */
    public static boolean isAutomorphic(long n) {
        if (n < 0) return false;
        long square = n * n; // Calculating square of the number
        long t = n, numberOfdigits = 0;
        while (t > 0) {
            numberOfdigits++; // Calculating number of digits in n
            t /= 10;
        }
        long lastDigits = square % (long) Math.pow(10, numberOfdigits); // Extracting last Digits of square
        return n == lastDigits;
    }

    /**
     * A function to check if a number is Automorphic number or not by using String functions
     *
     * @param n The number to be checked
     * @return {@code true} if {@code a} is Automorphic number, otherwise
     *         {@code false}
     */
    public static boolean isAutomorphic2(long n) {
        if (n < 0) return false;
        long square = n * n; // Calculating square of the number
        return String.valueOf(square).endsWith(String.valueOf(n));
    }

    /**
     * A function to check if a number is Automorphic number or not by using BigInteger
     *
     * @param s The number in String to be checked
     * @return {@code true} if {@code a} is Automorphic number, otherwise
     *         {@code false}
     */
    public static boolean isAutomorphic3(String s) {
        BigInteger n = new BigInteger(s);
        if (n.signum() == -1) return false; // if number is negative, return false
        BigInteger square = n.multiply(n); // Calculating square of the number
        return String.valueOf(square).endsWith(String.valueOf(n));
    }
}
