package com.thealgorithms.conversions;

/**
 * Utility class for converting integers between big-endian and little-endian formats.
 * <p>
 * Endianness defines how byte sequences represent multi-byte data types:
 * <ul>
 *   <li><b>Big-endian</b>: The most significant byte (MSB) comes first.</li>
 *   <li><b>Little-endian</b>: The least significant byte (LSB) comes first.</li>
 * </ul>
 * <p>
 * Example conversion:
 * <ul>
 *   <li>Big-endian to little-endian: {@code 0x12345678} → {@code 0x78563412}</li>
 *   <li>Little-endian to big-endian: {@code 0x78563412} → {@code 0x12345678}</li>
 * </ul>
 *
 * <p>Note: Both conversions in this utility are equivalent since reversing the bytes is symmetric.</p>
 *
 * <p>This class only supports 32-bit integers.</p>
 *
 * @author Hardvan
 */
public final class EndianConverter {
    private EndianConverter() {
    }

    /**
     * Converts a 32-bit integer from big-endian to little-endian.
     *
     * @param value the integer in big-endian format
     * @return the integer in little-endian format
     */
    public static int bigToLittleEndian(int value) {
        return Integer.reverseBytes(value);
    }

    /**
     * Converts a 32-bit integer from little-endian to big-endian.
     *
     * @param value the integer in little-endian format
     * @return the integer in big-endian format
     */
    public static int littleToBigEndian(int value) {
        return Integer.reverseBytes(value);
    }
}
