package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AbsoluteMaxTest {

    @Test
    void testGetMaxValue() {
        assertEquals(16, AbsoluteMax.getMaxValue(-2, 0, 16));
        assertEquals(-22, AbsoluteMax.getMaxValue(-3, -10, -22));
        assertEquals(-888, AbsoluteMax.getMaxValue(-888));
        assertEquals(-1, AbsoluteMax.getMaxValue(-1, -1, -1, -1, -1));
    }

    @Test
    void testGetMaxValueWithNoArguments() {
        assertThrows(IllegalArgumentException.class, AbsoluteMax::getMaxValue);
    }

    @Test
    void testGetMaxValueWithSameAbsoluteValues() {
        assertEquals(5, AbsoluteMax.getMaxValue(-5, 5));
        assertEquals(5, AbsoluteMax.getMaxValue(5, -5));
        assertEquals(12, AbsoluteMax.getMaxValue(-12, 9, 3, 12, 1));
        assertEquals(12, AbsoluteMax.getMaxValue(12, 9, 3, -12, 1));
    }
}
