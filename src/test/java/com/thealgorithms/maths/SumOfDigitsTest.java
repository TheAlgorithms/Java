package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public  class SumOfDigitsTest {

    @Test
    void testZero() {
        assertEquals(0, SumOfDigits.sumOfDigits(0));
        assertEquals(0, SumOfDigits.sumOfDigitsRecursion(0));
        assertEquals(0, SumOfDigits.sumOfDigitsFast(0));
}

    @Test
    void testPositive() {
        assertEquals(15, SumOfDigits.sumOfDigits(12345));
        assertEquals(15, SumOfDigits.sumOfDigitsRecursion(12345));
        assertEquals(15, SumOfDigits.sumOfDigitsFast(12345));
    }

    @Test
    void testNegative() {
        assertEquals(6, SumOfDigits.sumOfDigits(-123));
        assertEquals(6, SumOfDigits.sumOfDigitsRecursion(-123));
        assertEquals(6, SumOfDigits.sumOfDigitsFast(-123));
    }
}