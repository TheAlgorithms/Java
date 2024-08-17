package com.thealgorithms.conversions;

/**
 * Converts any Binary number to an Octal Number
 *
 * @author Zachary Jones
 */
public final class BinaryToOctal {
    private static final int BITS_IN_GROUP = 3;
    private static final int BINARY_BASE = 2;
    private static final int DECIMAL_BASE = 10;

    private BinaryToOctal() {
    }

    /**
     * This method converts a binary number to an octal number.
     *
     * @param binary The binary number
     * @return The octal number
     */
    public static String convertBinaryToOctal(int binary) {
        if (binary == 0) {
            return "0";
        }

        StringBuilder octal = new StringBuilder();
        int currentBit;
        int bitValueMultiplier = 1;

        while (binary != 0) {
            int octalDigit = 0;
            for (int i = 0; i < BITS_IN_GROUP && binary != 0; i++) {
                currentBit = binary % DECIMAL_BASE;
                binary /= DECIMAL_BASE;
                octalDigit += currentBit * bitValueMultiplier;
                bitValueMultiplier *= BINARY_BASE;
            }
            octal.insert(0, octalDigit);
            bitValueMultiplier = 1; // Reset multiplier for the next group
        }

        return octal.toString();
    }
}
