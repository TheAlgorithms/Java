package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AbsoluteMinTest {

    @Test
    void testGetMinValue() {
        assertEquals(0, AbsoluteMin.getMinValue(4, 0, 16));
        assertEquals(-2, AbsoluteMin.getMinValue(3, -10, -2));
        assertEquals(-2, AbsoluteMin.getMinValue(-3, -10, -2));
        assertEquals(2, AbsoluteMin.getMinValue(-3, -10, 2));
        assertEquals(2, AbsoluteMin.getMinValue(-5, 2));
        assertEquals(2, AbsoluteMin.getMinValue(2, -5));
    }

    @Test
    void testGetMinValueWithNoArguments() {
        assertThrows(IllegalArgumentException.class, AbsoluteMin::getMinValue);
        assertThrows(IllegalArgumentException.class, () -> AbsoluteMin.getMinValue((int[]) null));
    }

    @Test
    void testGetMinValueWithSameAbsoluteValues() {
        assertEquals(-5, AbsoluteMin.getMinValue(-5, 5));
        assertEquals(-5, AbsoluteMin.getMinValue(5, -5));
    }
}
