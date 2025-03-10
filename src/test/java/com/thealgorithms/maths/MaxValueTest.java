package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxValueTest {
    @Test
    public void maxTest() {
        assertEquals(-1, MaxValue.max(-1, -3));
        assertEquals(3, MaxValue.max(3, 2));
        assertEquals(5, MaxValue.max(5, 5));
    }
}
