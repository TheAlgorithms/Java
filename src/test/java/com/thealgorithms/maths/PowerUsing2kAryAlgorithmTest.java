package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Test case for Power using 2k-ary algorithm
 * @author Razat Aggarwal (https://github.com/razat-thapar)
 */
class PowerUsing2kAryAlgorithmTest {
	
    @Test
    void testPowerUsing2kAryAlgorithmTest() {
        assertEquals(32L, PowerUsing2kAryAlgorithm.pow(2, 5, 1000000007));
        assertEquals(754573817L, PowerUsing2kAryAlgorithm.pow(5, 100000, 1000000007));
        assertEquals(1L, PowerUsing2kAryAlgorithm.pow(5, 0, 1000000007));
    }
	
    @Test
    void testPowerUsing2kAryAlgorithmTestForWrongInputs() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> PowerUsing2kAryAlgorithm.pow(5, -2, 1000000007));
         assertEquals("exponent and mod can't be negative!", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> PowerUsing2kAryAlgorithm.pow(5, 2, -1000000007));
         assertEquals("exponent and mod can't be negative!", exception2.getMessage());

    }
	
}
