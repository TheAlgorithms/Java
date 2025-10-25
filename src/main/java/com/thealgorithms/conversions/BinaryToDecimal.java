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

    /**
     * Converts a binary String to its decimal equivalent using bitwise operators.
     *
     * @param binary The binary number to convert.
     * @return The decimal equivalent of the binary number.
     * @throws IllegalArgumentException If the binary number contains digits other than 0 and 1.
     */
    public static long binaryStringToDecimal(String binary) {
        boolean isNegative = binary.charAt(0) == '-';
        if (isNegative) {
            binary = binary.substring(1);
        }

        long decimalValue = 0;

        for (char bit : binary.toCharArray()) {
            if (bit != '0' && bit != '1') {
                throw new IllegalArgumentException("Incorrect binary digit: " + bit);
            }
            // shift left by 1 (multiply by 2) and add bit value
            decimalValue = (decimalValue << 1) | (bit - '0');
        }

        return isNegative ? -decimalValue : decimalValue;
    }
}
