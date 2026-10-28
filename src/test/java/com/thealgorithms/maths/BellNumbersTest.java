package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BellNumbersTest {

    @Test
    void testStandardCases() {
        // Base cases and small numbers
        assertEquals(1, BellNumbers.compute(0));
        assertEquals(1, BellNumbers.compute(1));
        assertEquals(2, BellNumbers.compute(2));
        assertEquals(5, BellNumbers.compute(3));
        assertEquals(15, BellNumbers.compute(4));
        assertEquals(52, BellNumbers.compute(5));
    }

    @Test
    void testMediumNumber() {
        // B10 = 115,975
        assertEquals(115975, BellNumbers.compute(10));
        // B15 = 1,382,958,545
        assertEquals(1382958545L, BellNumbers.compute(15));
    }

    @Test
    void testLargeNumber() {
        // B20 = 51,724,158,235,372
        // We use the 'L' suffix to tell Java this is a long literal
        assertEquals(51724158235372L, BellNumbers.compute(20));
    }

    @Test
    void testMaxLongCapacity() {
        // B25 is the largest Bell number that fits in a Java long (signed 64-bit)
        // B25 = 4,638,590,332,229,999,353
        assertEquals(4638590332229999353L, BellNumbers.compute(25));
    }

    @Test
    void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> BellNumbers.compute(-1));
    }

    @Test
    void testOverflowProtection() {
        // We expect an exception if the user asks for the impossible
        assertThrows(IllegalArgumentException.class, () -> BellNumbers.compute(26));
    }
}
