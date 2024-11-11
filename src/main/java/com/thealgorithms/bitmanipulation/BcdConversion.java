package com.thealgorithms.bitmanipulation;

/**
 * This class provides methods to convert between BCD (Binary-Coded Decimal) and decimal numbers.
 *
 * BCD is a class of binary encodings of decimal numbers where each decimal digit is represented by a fixed number of binary digits, usually four or eight.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Binary-coded_decimal">Binary-Coded Decimal</a> Wikipedia page.
 *
 * <b>Example usage:</b>
 * <pre>
 * int decimal = BcdConversion.bcdToDecimal(0x1234);
 * System.out.println("BCD 0x1234 to decimal: " + decimal); // Output: 1234
 *
 * int bcd = BcdConversion.decimalToBcd(1234);
 * System.out.println("Decimal 1234 to BCD: " + Integer.toHexString(bcd)); // Output: 0x1234
 * </pre>
 */
public final class BcdConversion {
    private BcdConversion() {
    }

    /**
     * Converts a BCD (Binary-Coded Decimal) number to a decimal number.
     * <p>Steps:
     * <p>1. Validate the BCD number to ensure all digits are between 0 and 9.
     * <p>2. Extract the last 4 bits (one BCD digit) from the BCD number.
     * <p>3. Multiply the extracted digit by the corresponding power of 10 and add it to the decimal number.
     * <p>4. Shift the BCD number right by 4 bits to process the next BCD digit.
     * <p>5. Repeat steps 1-4 until the BCD number is zero.
     *
     * @param bcd The BCD number.
     * @return The corresponding decimal number.
     * @throws IllegalArgumentException if the BCD number contains invalid digits.
     */
    public static int bcdToDecimal(int bcd) {
        int decimal = 0;
        int multiplier = 1;

        // Validate BCD digits
        while (bcd > 0) {
            int digit = bcd & 0xF;
            if (digit > 9) {
                throw new IllegalArgumentException("Invalid BCD digit: " + digit);
            }
            decimal += digit * multiplier;
            multiplier *= 10;
            bcd >>= 4;
        }
        return decimal;
    }

    /**
     * Converts a decimal number to BCD (Binary-Coded Decimal).
     * <p>Steps:
     * <p>1. Check if the decimal number is within the valid range for BCD (0 to 9999).
     * <p>2. Extract the last decimal digit from the decimal number.
     * <p>3. Shift the digit to the correct BCD position and add it to the BCD number.
     * <p>4. Remove the last decimal digit from the decimal number.
     * <p>5. Repeat steps 2-4 until the decimal number is zero.
     *
     * @param decimal The decimal number.
     * @return The corresponding BCD number.
     * @throws IllegalArgumentException if the decimal number is greater than 9999.
     */
    public static int decimalToBcd(int decimal) {
        if (decimal < 0 || decimal > 9999) {
            throw new IllegalArgumentException("Value out of bounds for BCD representation: " + decimal);
        }

        int bcd = 0;
        int shift = 0;
        while (decimal > 0) {
            int digit = decimal % 10;
            bcd |= (digit << (shift * 4));
            decimal /= 10;
            shift++;
        }
        return bcd;
    }
}
