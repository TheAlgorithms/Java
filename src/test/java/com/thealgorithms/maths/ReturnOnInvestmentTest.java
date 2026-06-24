package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ReturnOnInvestmentTest {

    private static final double DELTA = 1e-9;

    // --- Simple ROI ---

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

    // --- Annualized ROI ---

    @Test
    void testAnnualizedROIOneYear() {
        // Over exactly 1 year, annualized ROI == simple ROI
        assertEquals(100.0, ReturnOnInvestment.annualizedReturnOnInvestment(1000, 500, 1), DELTA);
    }

    @Test
    void testAnnualizedROITwoYears() {
        // Simple ROI = 100% over 2 years → annualized = (sqrt(2) - 1) * 100 ≈ 41.42%
        double expected = (Math.pow(2.0, 0.5) - 1.0) * 100.0;
        assertEquals(expected, ReturnOnInvestment.annualizedReturnOnInvestment(1000, 500, 2), DELTA);
    }

    @Test
    void testAnnualizedROIFractionalYear() {
        // 6 months (0.5 years): annualizes to a higher rate than the simple ROI
        double expected = (Math.pow(2.0, 2.0) - 1.0) * 100.0; // (1+1)^2 - 1 = 300%
        assertEquals(expected, ReturnOnInvestment.annualizedReturnOnInvestment(1000, 500, 0.5), DELTA);
    }

    @Test
    void testAnnualizedZeroROI() {
        // If gain == cost, ROI is 0 regardless of holding period
        assertEquals(0.0, ReturnOnInvestment.annualizedReturnOnInvestment(500, 500, 5), DELTA);
    }

    @Test
    void testAnnualizedNegativeROI() {
        // Loss of 50% over 2 years: annualized = (sqrt(0.5) - 1) * 100 ≈ -29.29%
        double expected = (Math.pow(0.5, 0.5) - 1.0) * 100.0;
        assertEquals(expected, ReturnOnInvestment.annualizedReturnOnInvestment(500, 1000, 2), DELTA);
    }

    @Test
    void testAnnualizedZeroYearsThrows() {
        assertThrows(IllegalArgumentException.class, () -> ReturnOnInvestment.annualizedReturnOnInvestment(1000, 500, 0));
    }

    @Test
    void testAnnualizedNegativeYearsThrows() {
        assertThrows(IllegalArgumentException.class, () -> ReturnOnInvestment.annualizedReturnOnInvestment(1000, 500, -3));
    }

    @Test
    void testAnnualizedZeroCostThrows() {
        assertThrows(IllegalArgumentException.class, () -> ReturnOnInvestment.annualizedReturnOnInvestment(1000, 0, 2));
    }
}
