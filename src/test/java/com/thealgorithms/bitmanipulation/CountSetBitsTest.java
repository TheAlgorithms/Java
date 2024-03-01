// CountSetBitsTest.java
package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSetBitsTest {
    @Test
    void testCountSetBits() {
        assertEquals(0, CountSetBits.countSetBits(0));
        assertEquals(1, CountSetBits.countSetBits(1));
        assertEquals(2, CountSetBits.countSetBits(5));
        assertEquals(3, CountSetBits.countSetBits(7));
        assertEquals(4, CountSetBits.countSetBits(15));
    }
}
