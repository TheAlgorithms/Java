package com.thealgorithms.conversions;

/**
 * This class converts Decimal numbers to Octal Numbers
 */
public final class DecimalToOctal {
    private static final int OCTAL_BASE = 8;
    private static final int INITIAL_OCTAL_VALUE = 0;
    private static final int INITIAL_PLACE_VALUE = 1;

    private DecimalToOctal() {
    }

    /**
     * Converts a decimal number to its octal equivalent.
     *
     * @param decimal The decimal number to convert.
     * @return The octal equivalent as an integer.
     * @throws IllegalArgumentException if the decimal number is negative.
     */
    public static int convertToOctal(int decimal) {
        if (decimal < 0) {
            throw new IllegalArgumentException("Decimal number cannot be negative.");
        }

        int octal = INITIAL_OCTAL_VALUE;
        int placeValue = INITIAL_PLACE_VALUE;

        while (decimal != 0) {
            int remainder = decimal % OCTAL_BASE;
            octal += remainder * placeValue;
            decimal /= OCTAL_BASE;
            placeValue *= 10;
        }

        return octal;
    }
}
