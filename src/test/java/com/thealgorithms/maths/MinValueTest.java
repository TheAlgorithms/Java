package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinValueTest {
    @Test
    public void minTest() {
        assertEquals(-1, MinValue.min(-1, 3));
        assertEquals(2, MinValue.min(3, 2));
        assertEquals(5, MinValue.min(5, 5));
    }
}
