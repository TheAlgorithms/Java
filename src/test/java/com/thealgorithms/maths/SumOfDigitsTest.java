package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SumOfDigitsTest {

    SumOfDigits SoD = new SumOfDigits();

    @Test
    void testZero() {
        assertEquals(0, SoD.sumOfDigits(0));
        assertEquals(0, SoD.sumOfDigitsRecursion(0));
        assertEquals(0, SoD.sumOfDigitsFast(0));
    }

    @Test
    void testPositive() {
        assertEquals(15, SoD.sumOfDigits(12345));
        assertEquals(15, SoD.sumOfDigitsRecursion(12345));
        assertEquals(15, SoD.sumOfDigitsFast(12345));
    }

    @Test
    void testNegative() {
        assertEquals(6, SoD.sumOfDigits(-123));
        assertEquals(6, SoD.sumOfDigitsRecursion(-123));
        assertEquals(6, SoD.sumOfDigitsFast(-123));
    }
}
