package com.thealgorithms.conversions;

/**
 * A utility class to convert an octal (base-8) number into its binary (base-2) representation.
 *
 * <p>This class provides methods to:
 * <ul>
 *     <li>Convert an octal number to its binary equivalent</li>
 *     <li>Convert individual octal digits to binary</li>
 * </ul>
 *
 * <h2>Octal to Binary Conversion:</h2>
 * <p>An octal number is converted to binary by converting each octal digit to its 3-bit binary equivalent.
 * The result is a long representing the full binary equivalent of the octal number.</p>
 *
 * <h2>Example Usage</h2>
 * <pre>
 *   long binary = OctalToBinary.convertOctalToBinary(52); // Output: 101010 (52 in octal is 101010 in binary)
 * </pre>
 *
 * @author Bama Charan Chhandogi
 * @see <a href="https://en.wikipedia.org/wiki/Octal">Octal Number System</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_number">Binary Number System</a>
 */
public final class OctalToBinary {
    private OctalToBinary() {
    }

    /**
     * Converts an octal number to its binary representation.
     *
     * <p>Each octal digit is individually converted to its 3-bit binary equivalent, and the binary
     * digits are concatenated to form the final binary number.</p>
     *
     * @param octalNumber the octal number to convert (non-negative integer)
     * @return the binary equivalent as a long
     */
    public static long convertOctalToBinary(int octalNumber) {
        long binaryNumber = 0;
        int digitPosition = 1;

        while (octalNumber != 0) {
            int octalDigit = octalNumber % 10;
            long binaryDigit = convertOctalDigitToBinary(octalDigit);

            binaryNumber += binaryDigit * digitPosition;

            octalNumber /= 10;
            digitPosition *= 1000;
        }

        return binaryNumber;
    }

    /**
     * Converts a single octal digit (0-7) to its binary equivalent.
     *
     * <p>For example:
     * <ul>
     *     <li>Octal digit 7 is converted to binary 111</li>
     *     <li>Octal digit 3 is converted to binary 011</li>
     * </ul>
     * </p>
     *
     * @param octalDigit a single octal digit (0-7)
     * @return the binary equivalent as a long
     */
    public static long convertOctalDigitToBinary(int octalDigit) {
        long binaryDigit = 0;
        int binaryMultiplier = 1;

        while (octalDigit != 0) {
            int octalDigitRemainder = octalDigit % 2;
            binaryDigit += octalDigitRemainder * binaryMultiplier;

            octalDigit /= 2;
            binaryMultiplier *= 10;
        }

        return binaryDigit;
    }
}
