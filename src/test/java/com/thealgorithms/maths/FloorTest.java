package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class FloorTest {
    @Test
    public void testFloorWholeNumber() {
        assertEquals(0, Floor.floor(0));
        assertEquals(1, Floor.floor(1));
        assertEquals(-1, Floor.floor(-1));
        assertEquals(42, Floor.floor(42));
        assertEquals(-42, Floor.floor(-42));
    }

    @Test
    public void testFloorDoubleNumber() {
        assertEquals(0, Floor.floor(0.1));
        assertEquals(1, Floor.floor(1.9));
        assertEquals(-2, Floor.floor(-1.1));
        assertEquals(-43, Floor.floor(-42.7));
    }

    @Test
    public void testFloorNegativeZero() {
        assertEquals(-0.0, Floor.floor(-0.0));
    }
}
