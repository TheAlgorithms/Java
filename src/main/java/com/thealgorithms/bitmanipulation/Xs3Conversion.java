package com.thealgorithms.bitmanipulation;

/**
 * This class provides methods to convert between XS-3 (Excess-3) and binary.
 *
 * Excess-3, also called XS-3, is a binary-coded decimal (BCD) code in which each decimal digit is represented by its corresponding 4-bit binary value plus 3.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Excess-3">Excess-3</a> Wikipedia page.
 *
 * <b>Example usage:</b>
 * <pre>
 * int binary = Xs3Conversion.xs3ToBinary(0x4567);
 * System.out.println("XS-3 0x4567 to binary: " + binary); // Output: 1234
 *
 * int xs3 = Xs3Conversion.binaryToXs3(1234);
 * System.out.println("Binary 1234 to XS-3: " + Integer.toHexString(xs3)); // Output: 0x4567
 * </pre>
 */
public final class Xs3Conversion {
    private Xs3Conversion() {
    }
    /**
     * Converts an XS-3 (Excess-3) number to binary.
     *
     * @param xs3 The XS-3 number.
     * @return The corresponding binary number.
     */
    public static int xs3ToBinary(int xs3) {
        int binary = 0;
        int multiplier = 1;
        while (xs3 > 0) {
            int digit = (xs3 & 0xF) - 3; // Extract the last 4 bits (one XS-3 digit) and subtract 3
            binary += digit * multiplier;
            multiplier *= 10;
            xs3 >>= 4; // Shift right by 4 bits to process the next XS-3 digit
        }
        return binary;
    }

    /**
     * Converts a binary number to XS-3 (Excess-3).
     *
     * @param binary The binary number.
     * @return The corresponding XS-3 number.
     */
    public static int binaryToXs3(int binary) {
        int xs3 = 0;
        int shift = 0;
        while (binary > 0) {
            int digit = (binary % 10) + 3; // Extract the last decimal digit and add 3
            xs3 |= (digit << (shift * 4)); // Shift the digit to the correct XS-3 position
            binary /= 10; // Remove the last decimal digit
            shift++;
        }
        return xs3;
    }
}
