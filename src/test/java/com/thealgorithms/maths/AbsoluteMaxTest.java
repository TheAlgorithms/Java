package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AbsoluteMaxTest {

    @Test
    void testGetMaxValue() {
        assertEquals(16, AbsoluteMax.getMaxValue(-2, 0, 16));
        assertEquals(-10, AbsoluteMax.getMaxValue(3, -10, -2));
    }

    @Test
    void testGetMaxValueWithNoArguments() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> AbsoluteMax.getMaxValue()
        );
        assertEquals("Numbers array cannot be empty", exception.getMessage());
    }
}
