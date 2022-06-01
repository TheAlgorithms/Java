package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author SirFixalot16
 * @since 01/06/22
 */
public class SumOfDigitsTest {
    @Test
    void isSumOfDigits() {
        SumOfDigits sum = new SumOfDigits();
        assertEquals(11, sum.sumOfDigits(56));
        assertEquals(12, sum.sumOfDigits(192));
        assertEquals(25, sum.sumOfDigits(8962));
    }
}
