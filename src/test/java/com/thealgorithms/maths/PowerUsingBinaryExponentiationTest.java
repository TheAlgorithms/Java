package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
/**
 * Test cases for Power using binary Exponenetiation method.
 * @author Razat Aggarwal (https://github.com/razat-thapar)
 */
public class PowerUsingBinaryExponentiationTest {
    @Test
    public void testPowMethodWithPositiveBase() {
        assertEquals(1024L, PowerUsingBinaryExponentiation.pow(2, 10, 1000000007));
        assertEquals(125L, PowerUsingBinaryExponentiation.pow(5, 3, 1000000007));
        assertEquals(294967268L, PowerUsingBinaryExponentiation.pow(2, 32, 1000000007));
    }
    @Test
    public void testPowMethodForBigPowers() {
        assertEquals(754573817L, PowerUsingBinaryExponentiation.pow(5, 100000, 1000000007));
    }
    @Test
    public void testPowMethodWithNegativeBase() {
        assertEquals(1024L, PowerUsingBinaryExponentiation.pow(-2, 10, 1000000007));
        assertEquals(999999882L, PowerUsingBinaryExponentiation.pow(-5, 3, 1000000007));
    }
    @Test
    public void testPowMethodWithIllegalArguments() {
        assertThrowsExactly(IllegalArgumentException.class, () -> PowerUsingBinaryExponentiation.pow(2, -10, 1000000007), "exponent and mod can't be negative!");
        assertThrowsExactly(IllegalArgumentException.class, () -> PowerUsingBinaryExponentiation.pow(2, 10, -1000000007), "exponent and mod can't be negative!");
    }
}
