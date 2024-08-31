package com.thealgorithms.conversions;

public final class BinaryToOctal {
    private static final int BITS_PER_OCTAL_DIGIT = 3;
    private static final int BINARY_BASE = 2;
    private static final int DECIMAL_BASE = 10;

    private BinaryToOctal() {
    }

    /**
     * This method converts a binary number to an octal number.
     *
     * @param binary The binary number
     * @return The octal number
     * @throws IllegalArgumentException if the input is not a valid binary number
     */
    public static String convertBinaryToOctal(int binary) {
        if (binary == 0) {
            return "0";
        }

        if (!String.valueOf(binary).matches("[01]+")) {
            throw new IllegalArgumentException("Input is not a valid binary number.");
        }

        StringBuilder octal = new StringBuilder();
        int currentBit;
        int bitValueMultiplier = 1;

        while (binary != 0) {
            int octalDigit = 0;
            for (int i = 0; i < BITS_PER_OCTAL_DIGIT && binary != 0; i++) {
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
