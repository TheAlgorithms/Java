package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author SirFixalot16
 * @since 01/06/22
 */
public class SumOfDigitsTest {
    @Test
    void isSumOf2Digits() {
        SumOfDigits sum = new SumOfDigits();
        assertEquals(11, sum.sumOfDigits(56));
    }
    void isSumOf3Digits() {
        SumOfDigits sum = new SumOfDigits();
        assertEquals(12, sum.sumOfDigits(192));
    }
    void isSumOf4Digits() {
        SumOfDigits sum = new SumOfDigits();
        assertEquals(25, sum.sumOfDigits(8962));
    }
}
