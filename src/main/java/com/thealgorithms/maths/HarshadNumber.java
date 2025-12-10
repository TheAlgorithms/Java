package com.thealgorithms.maths;

/**
 * A Harshad number (or Niven number) in a given number base is an integer that
 * is divisible by the sum of its digits.
 * For example, 18 is a Harshad number because 18 is divisible by (1 + 8) = 9.
 * The name "Harshad" comes from the Sanskrit words "harṣa" (joy) and "da"
 * (give), meaning "joy-giver".
 *
 * @author <a href="https://github.com/Hardvan">Hardvan</a>
 * @see <a href="https://en.wikipedia.org/wiki/Harshad_number">Harshad Number -
 *      Wikipedia</a>
 */
public final class HarshadNumber {
    private HarshadNumber() {
    }

    /**
     * Checks if a number is a Harshad number.
     * A Harshad number is a positive integer that is divisible by the sum of its
     * digits.
     *
     * @param n the number to be checked (must be positive)
     * @return {@code true} if {@code n} is a Harshad number, otherwise
     *         {@code false}
     * @throws IllegalArgumentException if {@code n} is less than or equal to 0
     */
    public static boolean isHarshad(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer. Received: " + n);
        }

        long temp = n;
        long sumOfDigits = 0;
        while (temp > 0) {
            sumOfDigits += temp % 10;
            temp /= 10;
        }

        return n % sumOfDigits == 0;
    }

    /**
     * Checks if a number represented as a string is a Harshad number.
     * A Harshad number is a positive integer that is divisible by the sum of its
     * digits.
     *
     * @param s the string representation of the number to be checked
     * @return {@code true} if the number is a Harshad number, otherwise
     *         {@code false}
     * @throws IllegalArgumentException if {@code s} is null, empty, or represents a
     *                                  non-positive integer
     * @throws NumberFormatException    if {@code s} cannot be parsed as a long
     */
    public static boolean isHarshad(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        final long n;
        try {
            n = Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input string must be a valid integer: " + s, e);
        }

        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer. Received: " + n);
        }

        int sumOfDigits = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                sumOfDigits += ch - '0';
            }
        }

        return n % sumOfDigits == 0;
    }
}
