package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ArmstrongNumberTest {
    @Test
    void testSingleDigitNumbers() {
        for (int i = 0; i <= 9; i++) {
            assertTrue(ArmstrongNumber.isArmstrong(i));
        }
    }

    @Test
    void testThreeDigitArmstrongNumbers() {
        assertTrue(ArmstrongNumber.isArmstrong(153));
        assertTrue(ArmstrongNumber.isArmstrong(370));
        assertTrue(ArmstrongNumber.isArmstrong(371));
        assertTrue(ArmstrongNumber.isArmstrong(407));
    }

    @Test
    void testFourDigitArmstrongNumbers() {
        assertTrue(ArmstrongNumber.isArmstrong(1634));
        assertTrue(ArmstrongNumber.isArmstrong(8208));
        assertTrue(ArmstrongNumber.isArmstrong(9474));
    }

    @Test
    void testNonArmstrongNumbers() {
        assertFalse(ArmstrongNumber.isArmstrong(10));
        assertFalse(ArmstrongNumber.isArmstrong(100));
        assertFalse(ArmstrongNumber.isArmstrong(123));
        assertFalse(ArmstrongNumber.isArmstrong(999));
    }

    @Test
    void testNegativeNumbers() {
        assertFalse(ArmstrongNumber.isArmstrong(-1));
        assertFalse(ArmstrongNumber.isArmstrong(-153));
    }

    @Test
    void testZero() {
        assertTrue(ArmstrongNumber.isArmstrong(0));
    }
}
