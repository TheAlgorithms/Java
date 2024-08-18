package com.thealgorithms.conversions;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class that provides methods to convert a decimal number to a string representation
 *  in any specified base between 2 and 36.
 *
 * @author Varun Upadhyay (<a href="https://github.com/varunu28">...</a>)
 */
public final class DecimalToAnyBase {
    private static final int MIN_BASE = 2;
    private static final int MAX_BASE = 36;
    private static final char ZERO_CHAR = '0';
    private static final char A_CHAR = 'A';
    private static final int DIGIT_OFFSET = 10;

    private DecimalToAnyBase() {
    }

    /**
     * Converts a decimal number to a string representation in the specified base.
     * For example, converting the decimal number 10 to base 2 would return "1010".
     *
     * @param decimal the decimal number to convert
     * @param base    the base to convert to (must be between {@value #MIN_BASE} and {@value #MAX_BASE})
     * @return the string representation of the number in the specified base
     * @throws IllegalArgumentException if the base is out of the supported range
     */
    public static String convertToAnyBase(int decimal, int base) {
        if (base < MIN_BASE || base > MAX_BASE) {
            throw new IllegalArgumentException("Base must be between " + MIN_BASE + " and " + MAX_BASE);
        }

        if (decimal == 0) {
            return String.valueOf(ZERO_CHAR);
        }

        List<Character> digits = new ArrayList<>();
        while (decimal > 0) {
            digits.add(convertToChar(decimal % base));
            decimal /= base;
        }

        StringBuilder result = new StringBuilder(digits.size());
        for (int i = digits.size() - 1; i >= 0; i--) {
            result.append(digits.get(i));
        }

        return result.toString();
    }

    /**
     * Converts an integer value to its corresponding character in the specified base.
     * This method is used to convert values from 0 to 35 into their appropriate character representation.
     * For example, 0-9 are represented as '0'-'9', and 10-35 are represented as 'A'-'Z'.
     *
     * @param value the integer value to convert (should be less than the base value)
     * @return the character representing the value in the specified base
     */
    private static char convertToChar(int value) {
        if (value >= 0 && value <= 9) {
            return (char) (ZERO_CHAR + value);
        } else {
            return (char) (A_CHAR + value - DIGIT_OFFSET);
        }
    }
}
