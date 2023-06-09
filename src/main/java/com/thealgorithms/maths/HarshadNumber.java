package com.thealgorithms.maths;

// Wikipedia for Harshad Number : https://en.wikipedia.org/wiki/Harshad_number

public class HarshadNumber {

    /**
     * A function to check if a number is Harshad number or not
     *
     * @param n The number to be checked
     * @return {@code true} if {@code a} is Harshad number, otherwise
     *         {@code false}
     */
    public static boolean isHarshad(long n) {
        if (n <= 0) return false;

        long t = n;
        int sumOfDigits = 0;
        while (t > 0) {
            sumOfDigits += t % 10;
            t /= 10;
        }

        return n % sumOfDigits == 0;
    }

    /**
     * A function to check if a number is Harshad number or not
     *
     * @param s The number in String to be checked
     * @return {@code true} if {@code a} is Harshad number, otherwise
     *         {@code false}
     */
    public static boolean isHarshad(String s) {
        long n = Long.valueOf(s);
        if (n <= 0) return false;

        int sumOfDigits = 0;
        for (char ch : s.toCharArray()) {
            sumOfDigits += ch - '0';
        }

        return n % sumOfDigits == 0;
    }
}
