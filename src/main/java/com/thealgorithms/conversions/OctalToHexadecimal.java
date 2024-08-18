package com.thealgorithms.conversions;

/**
 * Class for converting an Octal number to its Hexadecimal equivalent.
 *
 * @author Tanmay Joshi
 */
public final class OctalToHexadecimal {
    private static final int OCTAL_BASE = 8;
    private static final int HEX_BASE = 16;
    private static final String HEX_DIGITS = "0123456789ABCDEF";

    private OctalToHexadecimal() {
    }

    /**
     * Converts an Octal number (as a string) to its Decimal equivalent.
     *
     * @param octalNumber The Octal number as a string
     * @return The Decimal equivalent of the Octal number
     * @throws IllegalArgumentException if the input contains invalid octal digits
     */
    public static int octalToDecimal(String octalNumber) {
        if (octalNumber == null || octalNumber.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        int decimalValue = 0;
        for (int i = 0; i < octalNumber.length(); i++) {
            char currentChar = octalNumber.charAt(i);
            if (currentChar < '0' || currentChar > '7') {
                throw new IllegalArgumentException("Incorrect octal digit: " + currentChar);
            }
            int currentDigit = currentChar - '0';
            decimalValue = decimalValue * OCTAL_BASE + currentDigit;
        }

        return decimalValue;
    }

    /**
     * Converts a Decimal number to its Hexadecimal equivalent.
     *
     * @param decimalNumber The Decimal number
     * @return The Hexadecimal equivalent of the Decimal number
     */
    public static String decimalToHexadecimal(int decimalNumber) {
        if (decimalNumber == 0) {
            return "0";
        }

        StringBuilder hexValue = new StringBuilder();
        while (decimalNumber > 0) {
            int digit = decimalNumber % HEX_BASE;
            hexValue.insert(0, HEX_DIGITS.charAt(digit));
            decimalNumber /= HEX_BASE;
        }

        return hexValue.toString();
    }
}
