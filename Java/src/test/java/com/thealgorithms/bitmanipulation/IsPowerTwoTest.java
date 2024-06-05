package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test case for IsPowerTwo class
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsPowerTwoTest {
    @Test
    public void testIsPowerTwo() {
        // test some positive powers of 2
        assertTrue(IsPowerTwo.isPowerTwo(1));
        assertTrue(IsPowerTwo.isPowerTwo(2));
        assertTrue(IsPowerTwo.isPowerTwo(4));
        assertTrue(IsPowerTwo.isPowerTwo(16));
        assertTrue(IsPowerTwo.isPowerTwo(1024));

        // test some negative numbers
        assertFalse(IsPowerTwo.isPowerTwo(-1));
        assertFalse(IsPowerTwo.isPowerTwo(-2));
        assertFalse(IsPowerTwo.isPowerTwo(-4));

        // test some numbers that are not powers of 2
        assertFalse(IsPowerTwo.isPowerTwo(0));
        assertFalse(IsPowerTwo.isPowerTwo(3));
        assertFalse(IsPowerTwo.isPowerTwo(5));
        assertFalse(IsPowerTwo.isPowerTwo(15));
        assertFalse(IsPowerTwo.isPowerTwo(1000));
    }
}
