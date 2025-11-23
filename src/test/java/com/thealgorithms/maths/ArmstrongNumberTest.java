package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ArmstrongNumberTest {

    @Test
    void testArmstrongNumbers() {
        // Test known Armstrong numbers
        assertTrue(ArmstrongNumber.isArmstrong(0));
        assertTrue(ArmstrongNumber.isArmstrong(1));
        assertTrue(ArmstrongNumber.isArmstrong(153));
        assertTrue(ArmstrongNumber.isArmstrong(370));
        assertTrue(ArmstrongNumber.isArmstrong(371));
        assertTrue(ArmstrongNumber.isArmstrong(407));
        assertTrue(ArmstrongNumber.isArmstrong(1634));
    }

    @Test
    void testNonArmstrongNumbers() {
        // Test numbers that are not Armstrong numbers
        assertFalse(ArmstrongNumber.isArmstrong(10));
        assertFalse(ArmstrongNumber.isArmstrong(100));
        assertFalse(ArmstrongNumber.isArmstrong(152));
        assertFalse(ArmstrongNumber.isArmstrong(200));
    }

    @Test
    void testNegativeNumbers() {
        // Negative numbers cannot be Armstrong numbers
        assertFalse(ArmstrongNumber.isArmstrong(-1));
        assertFalse(ArmstrongNumber.isArmstrong(-153));
    }
}
