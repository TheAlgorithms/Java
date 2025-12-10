package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AbsoluteMinTest {

    @Test
    void testGetMinValue() {
        assertEquals(0, AbsoluteMin.getMinValue(4, 0, 16));
        assertEquals(-2, AbsoluteMin.getMinValue(3, -10, -2));
    }

    @Test
    void testGetMinValueWithNoArguments() {
        Exception exception = assertThrows(IllegalArgumentException.class, AbsoluteMin::getMinValue);
        assertEquals("Numbers array cannot be empty", exception.getMessage());
    }

    @Test
    void testGetMinValueWithSameAbsoluteValues() {
        assertEquals(-5, AbsoluteMin.getMinValue(-5, 5));
        assertEquals(-5, AbsoluteMin.getMinValue(5, -5));
    }
}
