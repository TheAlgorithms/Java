package com.thealgorithms.bitmanipulation;

/**
 * This class provides methods to convert between BCD (Binary-Coded Decimal) and binary.
 *
 * Binary-Coded Decimal (BCD) is a class of binary encodings of decimal numbers where each decimal digit is represented by a fixed number of binary digits, usually four or eight.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Binary-coded_decimal">Binary-Coded Decimal</a> Wikipedia page.
 *
 * <b>Example usage:</b>
 * <pre>
 * int binary = BcdConversion.bcdToBinary(0x1234);
 * System.out.println("BCD 0x1234 to binary: " + binary); // Output: 1234
 *
 * int bcd = BcdConversion.binaryToBcd(1234);
 * System.out.println("Binary 1234 to BCD: " + Integer.toHexString(bcd)); // Output: 0x1234
 * </pre>
 */
public final class BcdConversion {
    private BcdConversion() {
    }
    /**
     * Converts a BCD (Binary-Coded Decimal) number to binary.
     *
     * @param bcd The BCD number.
     * @return The corresponding binary number.
     */
    public static int bcdToBinary(int bcd) {
        int binary = 0;
        int multiplier = 1;
        while (bcd > 0) {
            int digit = bcd & 0xF; // Extract the last 4 bits (one BCD digit)
            binary += digit * multiplier;
            multiplier *= 10;
            bcd >>= 4; // Shift right by 4 bits to process the next BCD digit
        }
        return binary;
    }

    /**
     * Converts a binary number to BCD (Binary-Coded Decimal).
     *
     * @param binary The binary number.
     * @return The corresponding BCD number.
     */
    public static int binaryToBcd(int binary) {
        int bcd = 0;
        int shift = 0;
        while (binary > 0) {
            int digit = binary % 10; // Extract the last decimal digit
            bcd |= (digit << (shift * 4)); // Shift the digit to the correct BCD position
            binary /= 10; // Remove the last decimal digit
            shift++;
        }
        return bcd;
    }
}
