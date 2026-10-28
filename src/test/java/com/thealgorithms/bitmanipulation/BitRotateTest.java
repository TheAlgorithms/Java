package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for BitRotate class covering typical, boundary, and edge cases.
 * Tests verify correct behavior for 32-bit circular bit rotations.
 *
 * @author Yajunesh
 */
public class BitRotateTest {

    // ===== rotateLeft Tests =====

    @Test
    public void testRotateLeftBasic() {
        // Basic left rotation
        assertEquals(0b00000000_00000000_00000000_00000010, BitRotate.rotateLeft(1, 1));
        assertEquals(0b00000000_00000000_00000000_00000100, BitRotate.rotateLeft(1, 2));
        assertEquals(0b00000000_00000000_00000000_00001000, BitRotate.rotateLeft(1, 3));
    }

    @Test
    public void testRotateLeftWithCarry() {
        // Test bits carrying from left to right
        // Binary: 10000000_00000000_00000000_00000001
        int value = 0x80000001;
        // After left rotate by 1: 00000000_00000000_00000000_00000011
        assertEquals(3, BitRotate.rotateLeft(value, 1));

        // Binary: 11000000_00000000_00000000_00000000
        value = 0xC0000000;
        // After left rotate by 1: 10000000_00000000_00000000_00000001
        assertEquals(0x80000001, BitRotate.rotateLeft(value, 1));
    }

    @Test
    public void testRotateLeftShift32() {
        // Shift of 32 should be same as shift of 0 (modulo behavior)
        int value = 0x12345678;
        assertEquals(value, BitRotate.rotateLeft(value, 32));
        assertEquals(value, BitRotate.rotateLeft(value, 64));
        assertEquals(value, BitRotate.rotateLeft(value, 96));
    }

    @Test
    public void testRotateLeftShiftNormalization() {
        // Test that shifts > 32 are properly normalized
        int value = 1;
        assertEquals(BitRotate.rotateLeft(value, 1), BitRotate.rotateLeft(value, 33));
        assertEquals(BitRotate.rotateLeft(value, 5), BitRotate.rotateLeft(value, 37));
    }

    @Test
    public void testRotateLeftZeroShift() {
        // Zero shift should return original value
        int value = 0xABCD1234;
        assertEquals(value, BitRotate.rotateLeft(value, 0));
    }

    // ===== rotateRight Tests =====

    @Test
    public void testRotateRightBasic() {
        // Basic right rotation
        assertEquals(0b10000000_00000000_00000000_00000000, BitRotate.rotateRight(1, 1));
        assertEquals(0b01000000_00000000_00000000_00000000, BitRotate.rotateRight(1, 2));
        assertEquals(0b00100000_00000000_00000000_00000000, BitRotate.rotateRight(1, 3));
    }

    @Test
    public void testRotateRightWithCarry() {
        // Test bits carrying from right to left
        // Binary: 00000000_00000000_00000000_00000011
        int value = 3;
        // After right rotate by 1: 10000000_00000000_00000000_00000001
        assertEquals(0x80000001, BitRotate.rotateRight(value, 1));

        // Binary: 00000000_00000000_00000000_00000001
        value = 1;
        // After right rotate by 1: 10000000_00000000_00000000_00000000
        assertEquals(0x80000000, BitRotate.rotateRight(value, 1));
    }

    @Test
    public void testRotateRightShift32() {
        // Shift of 32 should be same as shift of 0 (modulo behavior)
        int value = 0x9ABCDEF0;
        assertEquals(value, BitRotate.rotateRight(value, 32));
        assertEquals(value, BitRotate.rotateRight(value, 64));
        assertEquals(value, BitRotate.rotateRight(value, 96));
    }

    @Test
    public void testRotateRightShiftNormalization() {
        // Test that shifts > 32 are properly normalized
        int value = 1;
        assertEquals(BitRotate.rotateRight(value, 1), BitRotate.rotateRight(value, 33));
        assertEquals(BitRotate.rotateRight(value, 7), BitRotate.rotateRight(value, 39));
    }

    @Test
    public void testRotateRightZeroShift() {
        // Zero shift should return original value
        int value = 0xDEADBEEF;
        assertEquals(value, BitRotate.rotateRight(value, 0));
    }

    // ===== Edge Case Tests =====

    @Test
    public void testRotateLeftMaxValue() {
        // Test with maximum integer value
        int value = Integer.MAX_VALUE; // 0x7FFFFFFF
        int rotated = BitRotate.rotateLeft(value, 1);
        // MAX_VALUE << 1 should become 0xFFFFFFFE, but with rotation it becomes different
        assertEquals(0xFFFFFFFE, rotated);
    }

    @Test
    public void testRotateRightMinValue() {
        // Test with minimum integer value (treated as unsigned)
        int value = Integer.MIN_VALUE; // 0x80000000
        int rotated = BitRotate.rotateRight(value, 1);
        // MIN_VALUE >>> 1 should become 0x40000000, but with rotation from left
        assertEquals(0x40000000, rotated);
    }

    @Test
    public void testRotateAllOnes() {
        // Test with all bits set
        int value = 0xFFFFFFFF; // All ones
        assertEquals(value, BitRotate.rotateLeft(value, 13));
        assertEquals(value, BitRotate.rotateRight(value, 27));
    }

    @Test
    public void testRotateAllZeros() {
        // Test with all bits zero
        int value = 0x00000000;
        assertEquals(value, BitRotate.rotateLeft(value, 15));
        assertEquals(value, BitRotate.rotateRight(value, 19));
    }

    // ===== Exception Tests =====

    @Test
    public void testRotateLeftNegativeShift() {
        // Negative shifts should throw IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BitRotate.rotateLeft(42, -1));
        assertTrue(exception.getMessage().contains("negative"));
    }

    @Test
    public void testRotateRightNegativeShift() {
        // Negative shifts should throw IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BitRotate.rotateRight(42, -5));
        assertTrue(exception.getMessage().contains("negative"));
    }

    // ===== Complementary Operations Test =====

    @Test
    public void testRotateLeftRightComposition() {
        // Rotating left then right by same amount should return original value
        int original = 0x12345678;
        int shift = 7;

        int leftRotated = BitRotate.rotateLeft(original, shift);
        int restored = BitRotate.rotateRight(leftRotated, shift);

        assertEquals(original, restored);
    }

    @Test
    public void testRotateRightLeftComposition() {
        // Rotating right then left by same amount should return original value
        int original = 0x9ABCDEF0;
        int shift = 13;

        int rightRotated = BitRotate.rotateRight(original, shift);
        int restored = BitRotate.rotateLeft(rightRotated, shift);

        assertEquals(original, restored);
    }

    @Test
    public void testRotateLeft31IsSameAsRotateRight1() {
        // Rotating left by 31 should be same as rotating right by 1
        int value = 0x55555555;
        assertEquals(BitRotate.rotateLeft(value, 31), BitRotate.rotateRight(value, 1));
    }

    @Test
    public void testTraversals() {
        // Test that methods don't throw exceptions
        assertDoesNotThrow(() -> BitRotate.rotateLeft(1, 1));
        assertDoesNotThrow(() -> BitRotate.rotateRight(1, 1));
    }
}
