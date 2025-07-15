package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Utility class for converting a non-negative decimal (base-10) integer
 * to its representation in another radix (base) between 2 and 16, inclusive.
 *
 * <p>This class uses a stack-based approach to reverse the digits obtained from
 * successive divisions by the target radix.
 *
 * <p>This class cannot be instantiated.</p>
 */
public final class DecimalToAnyUsingStack {

    private DecimalToAnyUsingStack() {
    }

    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * Convert a decimal number to another radix.
     *
     * @param number the number to be converted
     * @param radix the radix
     * @return the number represented in the new radix as a String
     * @throws IllegalArgumentException if number is negative or radix is not between 2 and 16 inclusive
     */
    public static String convert(int number, int radix) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
        if (radix < 2 || radix > 16) {
            throw new IllegalArgumentException(String.format("Invalid radix: %d. Radix must be between 2 and 16.", radix));
        }

        if (number == 0) {
            return "0";
        }

        Stack<Character> digitStack = new Stack<>();
        while (number > 0) {
            digitStack.push(DIGITS[number % radix]);
            number /= radix;
        }

        StringBuilder result = new StringBuilder(digitStack.size());
        while (!digitStack.isEmpty()) {
            result.append(digitStack.pop());
        }

        return result.toString();
    }
}
