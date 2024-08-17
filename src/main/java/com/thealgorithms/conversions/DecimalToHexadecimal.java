package com.thealgorithms.conversions;

/**
 * This class provides a method to convert a decimal number to a hexadecimal string.
 */
final class DecimalToHexadecimal {
    private static final int SIZE_OF_INT_IN_HALF_BYTES = 8;
    private static final int NUMBER_OF_BITS_IN_HALF_BYTE = 4;
    private static final int HALF_BYTE_MASK = 0x0F;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private DecimalToHexadecimal() {
    }

    /**
     * Converts a decimal number to a hexadecimal string.
     * @param decimal the decimal number to convert
     * @return the hexadecimal representation of the decimal number
     */
    public static String decToHex(int decimal) {
        StringBuilder hexBuilder = new StringBuilder(SIZE_OF_INT_IN_HALF_BYTES);
        for (int i = SIZE_OF_INT_IN_HALF_BYTES - 1; i >= 0; --i) {
            int currentHalfByte = decimal & HALF_BYTE_MASK;
            hexBuilder.insert(0, HEX_DIGITS[currentHalfByte]);
            decimal >>= NUMBER_OF_BITS_IN_HALF_BYTE;
        }
        return removeLeadingZeros(hexBuilder.toString().toLowerCase());
    }

    private static String removeLeadingZeros(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }

        return i == str.length() ? "0" : str.substring(i);
    }
}
