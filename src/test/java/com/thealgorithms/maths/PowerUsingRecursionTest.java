package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test case for Power using Recursion
 * @author Vinayak (https://github.com/Vinayak-v12)
 */

class PowerUsingRecursionTest {

    @Test
    void testPowerUsingRecursion() {
        // exponent = 0
        assertEquals(1.0, PowerUsingRecursion.power(5.0, 0));

        // exponent = 1
        assertEquals(5.0, PowerUsingRecursion.power(5.0, 1));

        // negative exponent
        assertEquals(0.25, PowerUsingRecursion.power(2.0, -2));

        // another negative exponent
        assertEquals(0.5, PowerUsingRecursion.power(2.0, -1));
    }

}
