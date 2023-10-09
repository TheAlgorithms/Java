package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeficientNumberTest {
    @Test
    public void testNumbersAreDeficientNumber() {
        Assertions.assertTrue(DeficientNumber.isDeficientNumber(8));
        Assertions.assertTrue(DeficientNumber.isDeficientNumber(7));
        Assertions.assertTrue(DeficientNumber.isDeficientNumber(25));
    }

    @Test
    public void testNumbersAreNotDeficientNumber() {
        Assertions.assertTrue(DeficientNumber.isDeficientNumber(6));
        Assertions.assertTrue(DeficientNumber.isDeficientNumber(12));
        Assertions.assertTrue(DeficientNumber.isDeficientNumber(100));
    }

    @Test
    public void testIfNegativeInputThenExceptionExpected() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> DeficientNumber.isDeficientNumber(-1));
        Assertions.assertEquals(exception.getMessage(), "Input parameter must not be negative!");
    }
}
