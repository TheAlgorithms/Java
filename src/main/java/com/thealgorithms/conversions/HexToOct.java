package com.thealgorithms.conversions;

/**
 * Converts any Hexadecimal Number to Octal
 *
 * @author Tanmay Joshi
 */
public final class HexToOct {
    private HexToOct() {
    }

    /**
     * Converts a Hexadecimal number to a Decimal number.
     *
     * @param hex The Hexadecimal number as a String.
     * @return The Decimal equivalent as an integer.
     */
    public static int hexToDecimal(String hex) {
        String hexDigits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int decimalValue = 0;

        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            int digitValue = hexDigits.indexOf(hexChar);
            decimalValue = 16 * decimalValue + digitValue;
        }

        return decimalValue;
    }

    /**
     * Converts a Decimal number to an Octal number.
     *
     * @param decimal The Decimal number as an integer.
     * @return The Octal equivalent as an integer.
     */
    public static int decimalToOctal(int decimal) {
        int octalValue = 0;
        int placeValue = 1;

        while (decimal > 0) {
            int remainder = decimal % 8;
            octalValue += remainder * placeValue;
            decimal /= 8;
            placeValue *= 10;
        }

        return octalValue;
    }

    /**
     * Converts a Hexadecimal number to an Octal number.
     *
     * @param hex The Hexadecimal number as a String.
     * @return The Octal equivalent as an integer.
     */
    public static int hexToOctal(String hex) {
        int decimalValue = hexToDecimal(hex);
        return decimalToOctal(decimalValue);
    }
}
