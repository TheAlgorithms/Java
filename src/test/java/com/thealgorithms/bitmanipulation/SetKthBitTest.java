package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SetKthBitTest {

    @Test
    void testSetKthBit() {
        // Test case: Setting the 0th bit in 5 (binary 101)
        assertEquals(5, SetKthBit.setKthBit(5, 0));

        // Test case: Setting the 2nd bit in 10 (binary 1010)
        assertEquals(14, SetKthBit.setKthBit(10, 2));

        // Test case: Setting the 3rd bit in 15 (binary 1111)
        assertEquals(15, SetKthBit.setKthBit(15, 3));

        // Test case: Setting the 1st bit in 0 (binary 0)
        assertEquals(2, SetKthBit.setKthBit(0, 1));
    }
}
