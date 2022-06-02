public package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumOfDigitsTest {

    @Test
    void sumOfDigits() {
//        var sumOfDigits = new SumOfDigits();
        assertEquals(0, SumOfDigits.sumOfDigits(0));
        assertEquals(0, SumOfDigits.sumOfDigitsRecursion(0));
        assertEquals(0, SumOfDigits.sumOfDigitsFast(0));
        assertEquals(15, SumOfDigits.sumOfDigits(12345));
        assertEquals(15, SumOfDigits.sumOfDigitsRecursion(12345));
        assertEquals(15, SumOfDigits.sumOfDigitsFast(12345));
    }
} {
    
}
