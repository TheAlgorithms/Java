package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParityCheckTest {
    @Test
    public void testIsEvenParity() {
        assertTrue(ParityCheck.checkParity(0)); // 0 -> 0 ones
        assertTrue(ParityCheck.checkParity(3)); // 11 -> 2 ones
        assertTrue(ParityCheck.checkParity(5)); // 101 -> 2 ones
        assertTrue(ParityCheck.checkParity(10)); // 1010 -> 2 ones
        assertTrue(ParityCheck.checkParity(15)); // 1111 -> 4 ones
        assertTrue(ParityCheck.checkParity(1023)); // 10 ones
    }

    @Test
    public void testIsOddParity() {
        assertFalse(ParityCheck.checkParity(1)); // 1 -> 1 one
        assertFalse(ParityCheck.checkParity(2)); // 10 -> 1 one
        assertFalse(ParityCheck.checkParity(7)); // 111 -> 3 ones
        assertFalse(ParityCheck.checkParity(8)); // 1000 -> 1 one
        assertFalse(ParityCheck.checkParity(11)); // 1011 -> 3 ones
        assertFalse(ParityCheck.checkParity(31)); // 11111 -> 5 ones
    }

    @Test
    public void testLargeNumbers() {
        assertTrue(ParityCheck.checkParity(0b10101010)); // 4 ones
        assertFalse(ParityCheck.checkParity(0b100000000)); // 1 one
        assertTrue(ParityCheck.checkParity(0xAAAAAAAA)); // Alternating bits, 16 ones
    }
}
