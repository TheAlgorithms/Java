package com.thealgorithms.conversions;

/**
 * Provides methods to convert between Decimal and Binary Coded Decimal (BCD).
 * Decimal to BCD: Each decimal digit is converted to its 4-bit binary equivalent.
 * Example: 123 -> 0001 0010 0011
 *
 * BCD to Decimal: Each 4-bit binary number is converted to its decimal equivalent.
 * Example: 0001 0010 0011 -> 123
 *
 * Applications: Used in digital systems like calculators and clocks.
 *
 * @author Hardvan
 */
public final class BCDConverter {
    private BCDConverter() {
    }

    /**
     * Converts a decimal number to its BCD (Binary Coded Decimal) representation.
     * @param number the decimal number to be converted.
     * @return the BCD as a String.
     */
    public static String decimalToBCD(int number) {
        StringBuilder bcd = new StringBuilder();
        while (number > 0) {
            int digit = number % 10;
            bcd.insert(0, String.format("%04d", convertToBinary(digit)));
            number /= 10;
        }
        return bcd.toString();
    }

    /**
     * Converts a single digit to its 4-bit binary representation.
     * @param digit the digit to convert (0-9).
     * @return the binary representation as an int.
     */
    private static int convertToBinary(int digit) {
        int binary = 0;
        int multiplier = 1;
        while (digit > 0) {
            binary += (digit % 2) * multiplier;
            digit /= 2;
            multiplier *= 10;
        }
        return binary;
    }

    /**
     * Converts a BCD string back to its decimal representation.
     * @param bcd the BCD string to convert.
     * @return the decimal number as an integer.
     */
    public static int bcdToDecimal(String bcd) {
        int decimal = 0;
        for (int i = 0; i < bcd.length(); i += 4) {
            String digitBinary = bcd.substring(i, i + 4);
            decimal = decimal * 10 + Integer.parseInt(digitBinary, 2);
        }
        return decimal;
    }
}
