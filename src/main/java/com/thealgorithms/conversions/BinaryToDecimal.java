package com.thealgorithms.conversions;

/**
 * This class converts a Binary number to a Decimal number
 */
final class BinaryToDecimal {
    private static final int BINARY_BASE = 2;

    private BinaryToDecimal() {
    }

    /**
     * Converts a binary number to its decimal equivalent.
     *
     * @param binaryNumber The binary number to convert.
     * @return The decimal equivalent of the binary number.
     * @throws IllegalArgumentException If the binary number contains digits other than 0 and 1.
     */
    public static long binaryToDecimal(long binaryNumber) {
        long decimalValue = 0;
        long power = 0;

        while (binaryNumber != 0) {
            long digit = binaryNumber % 10;
            if (digit > 1) {
                throw new IllegalArgumentException("Incorrect binary digit: " + digit);
            }
            decimalValue += (long) (digit * Math.pow(BINARY_BASE, power++));
            binaryNumber /= 10;
        }
        return decimalValue;
    }
}
