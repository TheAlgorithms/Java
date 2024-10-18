package com.thealgorithms.conversions;

/**
 * Converts between big-endian and little-endian formats.
 * Big-endian is the most significant byte first, while little-endian is the least significant byte first.
 * Big-endian to little-endian: 0x12345678 -> 0x78563412
 *
 * Little-endian to big-endian: 0x12345678 -> 0x78563412
 *
 * @author Hardvan
 */
public final class EndianConverter {
    private EndianConverter() {
    }

    public static int bigToLittleEndian(int value) {
        return Integer.reverseBytes(value);
    }

    public static int littleToBigEndian(int value) {
        return Integer.reverseBytes(value);
    }
}
