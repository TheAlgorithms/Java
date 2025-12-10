package com.thealgorithms.bitmanipulation;

/**
 * Utility class for performing circular bit rotations on 32-bit integers.
 * Bit rotation is a circular shift operation where bits shifted out on one end
 * are reinserted on the opposite end.
 *
 * <p>This class provides methods for both left and right circular rotations,
 * supporting only 32-bit integer operations with proper shift normalization
 * and error handling.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bit_rotation">Bit Rotation</a>
 */
public final class BitRotate {

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private BitRotate() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Performs a circular left rotation (left shift) on a 32-bit integer.
     * Bits shifted out from the left side are inserted on the right side.
     *
     * @param value the 32-bit integer value to rotate
     * @param shift the number of positions to rotate left (must be non-negative)
     * @return the result of left rotating the value by the specified shift amount
     * @throws IllegalArgumentException if shift is negative
     *
     * @example
     * // Binary: 10000000 00000000 00000000 00000001
     * rotateLeft(0x80000001, 1)
     * // Returns: 3 (binary: 00000000 00000000 00000000 00000011)
     */
    public static int rotateLeft(int value, int shift) {
        if (shift < 0) {
            throw new IllegalArgumentException("Shift amount cannot be negative: " + shift);
        }

        // Normalize shift to the range [0, 31] using modulo 32
        shift = shift % 32;

        if (shift == 0) {
            return value;
        }

        // Left rotation: (value << shift) | (value >>> (32 - shift))
        return (value << shift) | (value >>> (32 - shift));
    }

    /**
     * Performs a circular right rotation (right shift) on a 32-bit integer.
     * Bits shifted out from the right side are inserted on the left side.
     *
     * @param value the 32-bit integer value to rotate
     * @param shift the number of positions to rotate right (must be non-negative)
     * @return the result of right rotating the value by the specified shift amount
     * @throws IllegalArgumentException if shift is negative
     *
     * @example
     * // Binary: 00000000 00000000 00000000 00000011
     * rotateRight(3, 1)
     * // Returns: -2147483647 (binary: 10000000 00000000 00000000 00000001)
     */
    public static int rotateRight(int value, int shift) {
        if (shift < 0) {
            throw new IllegalArgumentException("Shift amount cannot be negative: " + shift);
        }

        // Normalize shift to the range [0, 31] using modulo 32
        shift = shift % 32;

        if (shift == 0) {
            return value;
        }

        // Right rotation: (value >>> shift) | (value << (32 - shift))
        return (value >>> shift) | (value << (32 - shift));
    }
}
