package com.thealgorithms.conversions;

/**
 * Class for converting an octal number to a decimal number. Octal numbers are based on 8, using digits from 0 to 7.
 *
 */
public final class OctalToDecimal {
    private static final int OCTAL_BASE = 8;

    private OctalToDecimal() {
    }

    /**
     * Converts a given octal number (as a string) to its decimal representation.
     * If the input is not a valid octal number (i.e., contains characters other than 0-7),
     * the method throws an IllegalArgumentException.
     *
     * @param inputOctal The octal number as a string
     * @return The decimal equivalent of the octal number
     * @throws IllegalArgumentException if the input is not a valid octal number
     */
    public static int convertOctalToDecimal(String inputOctal) {
        if (inputOctal == null || inputOctal.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        int decimalValue = 0;

        for (int i = 0; i < inputOctal.length(); i++) {
            char currentChar = inputOctal.charAt(i);

            if (currentChar < '0' || currentChar > '7') {
                throw new IllegalArgumentException("Incorrect input: Expecting an octal number (digits 0-7)");
            }

            int currentDigit = currentChar - '0';
            decimalValue = decimalValue * OCTAL_BASE + currentDigit;
        }

        return decimalValue;
    }
}
