package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountLeadingZerosTest {

    @Test
    public void testCountLeadingZeros() {
        assertEquals(29, CountLeadingZeros.countLeadingZeros(5)); // 000...0101 has 29 leading zeros
        assertEquals(32, CountLeadingZeros.countLeadingZeros(0)); // 000...0000 has 32 leading zeros
        assertEquals(31, CountLeadingZeros.countLeadingZeros(1)); // 000...0001 has 31 leading zeros
        assertEquals(0, CountLeadingZeros.countLeadingZeros(-1)); // No leading zeros in negative number (-1)
    }
}
