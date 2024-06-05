package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test case for Power using Recursion
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

class PowerUsingRecursionTest {

    @Test
    void testPowerUsingRecursion() {
        assertEquals(32.0, PowerUsingRecursion.power(2.0, 5));
        assertEquals(97.65625, PowerUsingRecursion.power(2.5, 5));
        assertEquals(81, PowerUsingRecursion.power(3, 4));
    }
}
