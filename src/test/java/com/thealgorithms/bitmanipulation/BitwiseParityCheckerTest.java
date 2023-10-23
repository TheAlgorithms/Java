package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BitwiseParityCheckerTest {
    @Test
    public void testCheckEvenParityWithEvenParity() {
        boolean isEvenParity = BitwiseParityChecker.checkEvenParity(0b1101100);
        assertEquals(true, isEvenParity);
    }

    @Test
    public void testCheckEvenParityWithOddParity() {
        boolean isEvenParity = BitwiseParityChecker.checkEvenParity(0b11011010);
        assertEquals(false, isEvenParity);
    }

    @Test
    public void testCheckOddParityWithEvenParity() {
        boolean isOddParity = BitwiseParityChecker.checkOddParity(0b1101001);
        assertEquals(false, isOddParity);
    }

    @Test
    public void testCheckOddParityWithOddParity() {
        boolean isOddParity = BitwiseParityChecker.checkOddParity(0b11001011);
        assertEquals(true, isOddParity);
    }
}
