package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ReturnOnInvestmentTest {

    @Test
    void testPositiveROI() {
        assertEquals(100.0, ReturnOnInvestment.returnOnInvestment(1000, 500));
    }

    @Test
    void testZeroROI() {
        assertEquals(0.0, ReturnOnInvestment.returnOnInvestment(500, 500));
    }

    @Test
    void testNegativeROI() {
        assertEquals(-60.0, ReturnOnInvestment.returnOnInvestment(200, 500));
    }

    @Test
    void testTotalLoss() {
        assertEquals(-100.0, ReturnOnInvestment.returnOnInvestment(0, 500));
    }

    @Test
    void testZeroCostThrows() {
        assertThrows(IllegalArgumentException.class, () -> ReturnOnInvestment.returnOnInvestment(1000, 0));
    }

    @Test
    void testNegativeCostThrows() {
        assertThrows(IllegalArgumentException.class, () -> ReturnOnInvestment.returnOnInvestment(1000, -100));
    }
}
