package com.thealgorithms.bitmanipulation;

/**
 * This class provides methods to convert between BCD (Binary-Coded Decimal) and binary.
 *
 * BCD is a class of binary encodings of decimal numbers where each decimal digit is represented by a fixed number of binary digits, usually four or eight.
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
     * <p>Steps:
     * <p>1. Validate the BCD number to ensure all digits are between 0 and 9.
     * <p>2. Extract the last 4 bits (one BCD digit) from the BCD number.
     * <p>3. Multiply the extracted digit by the corresponding power of 10 and add it to the binary number.
     * <p>4. Shift the BCD number right by 4 bits to process the next BCD digit.
     * <p>5. Repeat steps 1-4 until the BCD number is zero.
     *
     * @param bcd The BCD number.
     * @return The corresponding binary number.
     * @throws IllegalArgumentException if the BCD number contains invalid digits.
     */
    public static int bcdToBinary(int bcd) {
        int binary = 0;
        int multiplier = 1;

        // Validate BCD digits
        while (bcd > 0) {
            int digit = bcd & 0xF;
            if (digit > 9) {
                throw new IllegalArgumentException("Invalid BCD digit: " + digit);
            }
            binary += digit * multiplier;
            multiplier *= 10;
            bcd >>= 4;
        }
        return binary;
    }

    /**
     * Converts a binary number to BCD (Binary-Coded Decimal).
     * <p>Steps:
     * <p>1. Extract the last decimal digit from the binary number.
     * <p>2. Shift the digit to the correct BCD position and add it to the BCD number.
     * <p>3. Remove the last decimal digit from the binary number.
     * <p>4. Repeat steps 1-3 until the binary number is zero.
     *
     * @param binary The binary number.
     * @return The corresponding BCD number.
     */
    public static int binaryToBcd(int binary) {
        int bcd = 0;
        int shift = 0;
        while (binary > 0) {
            int digit = binary % 10;
            bcd |= (digit << (shift * 4));
            binary /= 10;
            shift++;
        }
        return bcd;
    }
}
