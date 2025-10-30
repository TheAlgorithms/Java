/*
 * TheAlgorithms (https://github.com/TheAlgorithms/Java)
 * Author: Shewale41
 * This file is licensed under the MIT License.
 */

package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PowerOfFour}.
 */
public class PowerOfFourTest {

    @Test
    void testPowerOfFourTrueCases() {
        assertTrue(PowerOfFour.isPowerOfFour(1));
        assertTrue(PowerOfFour.isPowerOfFour(4));
        assertTrue(PowerOfFour.isPowerOfFour(16));
        assertTrue(PowerOfFour.isPowerOfFour(64));
        assertTrue(PowerOfFour.isPowerOfFour(256));
    }

    @Test
    void testPowerOfFourFalseCases() {
        assertFalse(PowerOfFour.isPowerOfFour(0));
        assertFalse(PowerOfFour.isPowerOfFour(2));
        assertFalse(PowerOfFour.isPowerOfFour(8));
        assertFalse(PowerOfFour.isPowerOfFour(12));
        assertFalse(PowerOfFour.isPowerOfFour(-4));
    }
}
