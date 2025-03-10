package com.thealgorithms.conversions;

/**
 * This class provides methods to convert a decimal number to a binary number.
 */
final class DecimalToBinary {
    private static final int BINARY_BASE = 2;
    private static final int DECIMAL_MULTIPLIER = 10;

    private DecimalToBinary() {
    }

    /**
     * Converts a decimal number to a binary number using a conventional algorithm.
     * @param decimalNumber the decimal number to convert
     * @return the binary representation of the decimal number
     */
    public static int convertUsingConventionalAlgorithm(int decimalNumber) {
        int binaryNumber = 0;
        int position = 1;

        while (decimalNumber > 0) {
            int remainder = decimalNumber % BINARY_BASE;
            binaryNumber += remainder * position;
            position *= DECIMAL_MULTIPLIER;
            decimalNumber /= BINARY_BASE;
        }

        return binaryNumber;
    }

    /**
     * Converts a decimal number to a binary number using a bitwise algorithm.
     * @param decimalNumber the decimal number to convert
     * @return the binary representation of the decimal number
     */
    public static int convertUsingBitwiseAlgorithm(int decimalNumber) {
        int binaryNumber = 0;
        int position = 1;

        while (decimalNumber > 0) {
            int leastSignificantBit = decimalNumber & 1;
            binaryNumber += leastSignificantBit * position;
            position *= DECIMAL_MULTIPLIER;
            decimalNumber >>= 1;
        }
        return binaryNumber;
    }
}
