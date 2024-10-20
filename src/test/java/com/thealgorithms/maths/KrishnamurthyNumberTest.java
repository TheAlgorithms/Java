package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the KrishnamurthyNumber class.
 */
public class KrishnamurthyNumberTest {

    /**
     * Test the isKrishnamurthy method with a known Krishnamurthy number.
     */
    @Test
    public void testIsKrishnamurthyTrue() {
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(145));
    }

    /**
     * Test the isKrishnamurthy method with a number that is not a Krishnamurthy number.
     */
    @Test
    public void testIsKrishnamurthyFalse() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(123));
    }

    /**
     * Test the isKrishnamurthy method with zero.
     */
    @Test
    public void testIsKrishnamurthyZero() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(0));
    }

    /**
     * Test the isKrishnamurthy method with a negative number.
     */
    @Test
    public void testIsKrishnamurthyNegative() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(-145));
    }

    /**
     * Test the isKrishnamurthy method with a single-digit Krishnamurthy number.
     */
    @Test
    public void testIsKrishnamurthySingleDigitTrue() {
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(1));
        assertTrue(KrishnamurthyNumber.isKrishnamurthy(2));
    }

    /**
     * Test the isKrishnamurthy method with a single-digit number that is not a Krishnamurthy number.
     */
    @Test
    public void testIsKrishnamurthySingleDigitFalse() {
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(3));
        assertFalse(KrishnamurthyNumber.isKrishnamurthy(4));
    }
}
