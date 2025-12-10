package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class FrizzyNumberTest {
    @Test
    public void testFrizziesForBase2() {
        assertEquals(1, FrizzyNumber.getNthFrizzy(2, 1));
        assertEquals(3, FrizzyNumber.getNthFrizzy(2, 3));
        assertEquals(1000, FrizzyNumber.getNthFrizzy(2, 1000));
    }

    @Test
    public void testFrizziesForBase3() {
        assertEquals(1, FrizzyNumber.getNthFrizzy(3, 1));
        assertEquals(3, FrizzyNumber.getNthFrizzy(3, 2));
        assertEquals(29430, FrizzyNumber.getNthFrizzy(3, 1000));
    }

    @Test
    public void testFrizziesForBase69() {
        assertEquals(1, FrizzyNumber.getNthFrizzy(69, 1));
        assertEquals(69, FrizzyNumber.getNthFrizzy(69, 2));
        assertEquals(328510, FrizzyNumber.getNthFrizzy(69, 9));
        assertEquals(333340, FrizzyNumber.getNthFrizzy(69, 15));
    }
}
