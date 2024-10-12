package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ToggleNthBit class.
 */
public class ToggleNthBitTest {

    /**
     * Test the toggleNthBit method with a positive number.
     */
    @Test
    public void testToggleNthBitPositive() {
        int result = ToggleNthBit.toggleNthBit(5, 2);
        assertEquals(1, result); // 5 (0101) with 2nd bit toggled becomes 1 (0001)
    }

    /**
     * Test the toggleNthBit method with a negative number.
     */
    @Test
    public void testToggleNthBitNegative() {
        int result = ToggleNthBit.toggleNthBit(-5, 2);
        assertEquals(-1, result); // -5 (1111...1011) with 2nd bit toggled becomes -1 (1111...1111)
    }

    /**
     * Test the toggleNthBit method with zero.
     */
    @Test
    public void testToggleNthBitZero() {
        int result = ToggleNthBit.toggleNthBit(0, 2);
        assertEquals(4, result); // 0 (0000) with 2nd bit toggled becomes 4 (0100)
    }

    /**
     * Test the toggleNthBit method with the same bit toggled twice.
     */
    @Test
    public void testToggleNthBitTwice() {
        int result = ToggleNthBit.toggleNthBit(5, 2);
        result = ToggleNthBit.toggleNthBit(result, 2);
        assertEquals(5, result); // Toggling the same bit twice should return the original number
    }
}
