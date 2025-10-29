package com.thealgorithms.bitmanipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PowerOfFour}.
 */
public final class PowerOfFourTest {

    @Test
    void testPowerOfFourTrueCases() {
        Assertions.assertTrue(PowerOfFour.isPowerOfFour(1));
        Assertions.assertTrue(PowerOfFour.isPowerOfFour(4));
        Assertions.assertTrue(PowerOfFour.isPowerOfFour(16));
        Assertions.assertTrue(PowerOfFour.isPowerOfFour(64));
    }

    @Test
    void testPowerOfFourFalseCases() {
        Assertions.assertFalse(PowerOfFour.isPowerOfFour(0));
        Assertions.assertFalse(PowerOfFour.isPowerOfFour(2));
        Assertions.assertFalse(PowerOfFour.isPowerOfFour(8));
        Assertions.assertFalse(PowerOfFour.isPowerOfFour(12));
    }

    @Test
    void testNegativeNumbers() {
        Assertions.assertFalse(PowerOfFour.isPowerOfFour(-4));
        Assertions.assertFalse(PowerOfFour.isPowerOfFour(-16));
    }
}
