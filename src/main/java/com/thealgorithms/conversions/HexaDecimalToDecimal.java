package com.thealgorithms.conversions;

/**
 * Utility class for converting a hexadecimal string to its decimal representation.
 * <p>
 * A hexadecimal number uses the base-16 numeral system, with the following characters:
 * <ul>
 *   <li>Digits: 0-9</li>
 *   <li>Letters: A-F (case-insensitive)</li>
 * </ul>
 * Each character represents a power of 16. For example:
 * <pre>
 *   Hexadecimal "A1" = 10*16^1 + 1*16^0 = 161 (decimal)
 * </pre>
 *
 * <p>This class provides a method to perform the conversion without using built-in Java utilities.</p>
 */
public final class HexaDecimalToDecimal {
    private HexaDecimalToDecimal() {
    }

    /**
     * Converts a hexadecimal string to its decimal integer equivalent.
     * <p>The input string is case-insensitive, and must contain valid hexadecimal characters [0-9, A-F].</p>
     *
     * @param hex the hexadecimal string to convert
     * @return the decimal integer representation of the input hexadecimal string
     * @throws IllegalArgumentException if the input string contains invalid characters
     */
    public static int getHexaToDec(String hex) {
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;

        for (int i = 0; i < hex.length(); i++) {
            int d = digits.indexOf(hex.charAt(i));
            if (d == -1) {
                throw new IllegalArgumentException("Invalid hexadecimal character: " + hex.charAt(i));
            }
            val = 16 * val + d;
        }

        return val;
    }
}
