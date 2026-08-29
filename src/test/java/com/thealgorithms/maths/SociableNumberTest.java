package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SociableNumber}.
 *
 * @author Vraj Prajapati (@Rosander0)
 */
public class SociableNumberTest {

    @Test
    public void testSumOfProperDivisorsEdgeCases() {
        assertEquals(0, SociableNumber.sumOfProperDivisors(0));
        assertEquals(0, SociableNumber.sumOfProperDivisors(-5));
        assertEquals(0, SociableNumber.sumOfProperDivisors(1));
        assertEquals(1, SociableNumber.sumOfProperDivisors(2));
    }

    @Test
    public void testSociableCycleOfLengthFive() {
        assertTrue(SociableNumber.isSociable(12496, 5));
    }

    @Test
    public void testAmicableNumbersAreSociableOfLengthTwo() {
        assertTrue(SociableNumber.isSociable(220, 2));
        assertTrue(SociableNumber.isSociable(284, 2));
    }

    @Test
    public void testNonSociableNumbers() {
        assertFalse(SociableNumber.isSociable(12, 5));
        assertFalse(SociableNumber.isSociable(10, 3));
    }

    @Test
    public void testEarlyCycleReturn() {
        // 220 has cycle length 2; requesting a different length should return
        // false because it returns to the start too early.
        assertFalse(SociableNumber.isSociable(220, 3));
        assertFalse(SociableNumber.isSociable(284, 4));
        assertFalse(SociableNumber.isSociable(12496, 3));
    }

    @Test
    public void testInvalidInputs() {
        assertFalse(SociableNumber.isSociable(0, 5));
        assertFalse(SociableNumber.isSociable(-1, 5));
        assertFalse(SociableNumber.isSociable(220, 1));
    }
}
