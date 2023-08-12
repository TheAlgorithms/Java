package com.thealgorithms.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitManipulationTest {

    @Test
    void isEven() {
        assertTrue(BitManipulation.isEven(10));
        assertFalse(BitManipulation.isEven(19));
        assertTrue(BitManipulation.isEven(776));
        assertFalse(BitManipulation.isEven(1001));
    }
}