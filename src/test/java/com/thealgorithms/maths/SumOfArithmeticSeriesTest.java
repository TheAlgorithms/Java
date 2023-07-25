package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SumOfArithmeticSeriesTest {
    @Test
    public void testSumFrom1To10() {
        assertEquals(55.0, SumOfArithmeticSeries.sumOfSeries(1.0, 1.0, 10));
    }

    @Test
    public void testSumOfOddNumbers1To19() {
        assertEquals(100.0, SumOfArithmeticSeries.sumOfSeries(1.0, 2.0, 10));
    }

    @Test
    public void testA() {
        assertEquals(460.0, SumOfArithmeticSeries.sumOfSeries(1.0, 10.0, 10));
    }

    @Test
    public void testB() {
        assertEquals(5.5, SumOfArithmeticSeries.sumOfSeries(0.1, 0.1, 10));
    }

    @Test
    public void testC() {
        assertEquals(49600.0, SumOfArithmeticSeries.sumOfSeries(1, 10, 100));
    }

    @Test
    public void testForZeroTerms() {
        assertEquals(0.0, SumOfArithmeticSeries.sumOfSeries(1.0, 100.0, 0), 0.00001);
    }

    @Test
    public void testIfThrowsExceptionForNegativeNumberOfTerms() {
        assertThrows(IllegalArgumentException.class, () -> SumOfArithmeticSeries.sumOfSeries(1.0, 1.0, -1));
    }

    @Test
    public void testWithSingleTerm() {
        assertEquals(123.0, SumOfArithmeticSeries.sumOfSeries(123.0, 5.0, 1));
    }

    @Test
    public void testWithZeroCommonDiff() {
        assertEquals(100.0, SumOfArithmeticSeries.sumOfSeries(1.0, 0.0, 100));
    }
}
