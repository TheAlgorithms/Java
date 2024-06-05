package com.thealgorithms.conversions;

// hex = [0 - 9] -> [A - F]
final class DecimalToHexaDecimal {
    private DecimalToHexaDecimal() {
    }

    private static final int SIZE_OF_INT_IN_HALF_BYTES = 8;
    private static final int NUMBER_OF_BITS_IN_HALF_BYTE = 4;
    private static final int HALF_BYTE = 0x0F;
    private static final char[] HEX_DIGITS = {
        '0',
        '1',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9',
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
    };

    // Returns the hex value of the dec entered in the parameter.
    public static String decToHex(int dec) {
        StringBuilder hexBuilder = new StringBuilder(SIZE_OF_INT_IN_HALF_BYTES);
        hexBuilder.setLength(SIZE_OF_INT_IN_HALF_BYTES);
        for (int i = SIZE_OF_INT_IN_HALF_BYTES - 1; i >= 0; --i) {
            int j = dec & HALF_BYTE;
            hexBuilder.setCharAt(i, HEX_DIGITS[j]);
            dec >>= NUMBER_OF_BITS_IN_HALF_BYTE;
        }
        return hexBuilder.toString().toLowerCase();
    }

    // Test above function.
    public static void main(String[] args) {
        System.out.println("Test...");
        int dec = 305445566;
        String libraryDecToHex = Integer.toHexString(dec);
        String decToHex = decToHex(dec);
        System.out.println("Result from the library : " + libraryDecToHex);
        System.out.println("Result decToHex method : " + decToHex);
    }
}
