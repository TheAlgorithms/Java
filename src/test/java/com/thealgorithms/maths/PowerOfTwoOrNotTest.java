package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PowerOfTwoOrNotTest {
    @Test
    public void testPowerOfTwoOrNotForPowersOfTwo() {
        final var powersOfTwo = new int[] {1, 2, 4, 8, 16, 32, 64};
        for (final var n : powersOfTwo) {
            assertTrue(PowerOfTwoOrNot.checkIfPowerOfTwoOrNot(n));
        }
    }

    @Test
    public void testPowerOfTwoOrNotForNotPowersOfTwo() {
        final var notPowersOfTwo = new int[] {-16, -8, -6, -5, -4, -3, -2, -1, 0, 3, 5, 6, 7, 9, 10, 11, 33, 63, 65, 1000, 9999};
        for (final var n : notPowersOfTwo) {
            assertFalse(PowerOfTwoOrNot.checkIfPowerOfTwoOrNot(n));
        }
    }
}
