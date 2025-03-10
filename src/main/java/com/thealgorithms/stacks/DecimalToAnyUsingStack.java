package com.thealgorithms.stacks;

import java.util.Stack;

public final class DecimalToAnyUsingStack {
    private DecimalToAnyUsingStack() {
    }

    /**
     * Convert a decimal number to another radix.
     *
     * @param number the number to be converted
     * @param radix the radix
     * @return the number represented in the new radix as a String
     * @throws IllegalArgumentException if <tt>number</tt> is negative or <tt>radix</tt> is not between 2 and 16 inclusive
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

        char[] tables = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        Stack<Character> bits = new Stack<>();
        while (number > 0) {
            bits.push(tables[number % radix]);
            number = number / radix;
        }

        StringBuilder result = new StringBuilder();
        while (!bits.isEmpty()) {
            result.append(bits.pop());
        }
        return result.toString();
    }
}
