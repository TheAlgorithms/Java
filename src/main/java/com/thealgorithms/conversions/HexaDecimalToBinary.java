package com.thealgorithms.conversions;

/**
 * Utility class for converting hexadecimal numbers to binary representation.
 * <p>
 * A hexadecimal number consists of digits from {@code [0-9]} and {@code [A-F]} (case-insensitive),
 * while binary representation uses only {@code [0, 1]}.
 * <p>
 * This class provides methods to:
 * <ul>
 *   <li>Convert a hexadecimal string to its binary string equivalent.</li>
 *   <li>Ensure the binary output is padded to 8 bits (1 byte).</li>
 * </ul>
 * <p>
 * Example:
 * <ul>
 *   <li>{@code "A1"} → {@code "10100001"}</li>
 *   <li>{@code "1"} → {@code "00000001"}</li>
 * </ul>
 *
 * <p>This class assumes that the input hexadecimal string is valid.</p>
 */
public class HexaDecimalToBinary {

    /**
     * Converts a hexadecimal string to its binary string equivalent.
     * The binary output is padded to a minimum of 8 bits (1 byte).
     * Steps:
     * <ol>
     *     <li>Convert the hexadecimal string to an integer.</li>
     *     <li>Convert the integer to a binary string.</li>
     *     <li>Pad the binary string to ensure it is at least 8 bits long.</li>
     *     <li>Return the padded binary string.</li>
     * </ol>
     *
     * @param numHex the hexadecimal string (e.g., "A1", "7F")
     * @throws NumberFormatException if the input string is not a valid hexadecimal number
     * @return the binary string representation, padded to 8 bits (e.g., "10100001")
     */
    public String convert(String numHex) {
        int conHex = Integer.parseInt(numHex, 16);
        String binary = Integer.toBinaryString(conHex);
        return completeDigits(binary);
    }

    /**
     * Pads the binary string to ensure it is at least 8 bits long.
     * If the binary string is shorter than 8 bits, it adds leading zeros.
     *
     * @param binNum the binary string to pad
     * @return the padded binary string with a minimum length of 8
     */
    public String completeDigits(String binNum) {
        final int byteSize = 8;
        StringBuilder binNumBuilder = new StringBuilder(binNum);
        while (binNumBuilder.length() < byteSize) {
            binNumBuilder.insert(0, "0");
        }
        binNum = binNumBuilder.toString();
        return binNum;
    }
}
