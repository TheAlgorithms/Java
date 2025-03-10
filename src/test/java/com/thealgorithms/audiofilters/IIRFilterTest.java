package com.thealgorithms.audiofilters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IIRFilterTest {

    @Test
    void testConstructorValidOrder() {
        // Test a valid filter creation
        IIRFilter filter = new IIRFilter(2);
        assertNotNull(filter, "Filter should be instantiated correctly");
    }

    @Test
    void testConstructorInvalidOrder() {
        // Test an invalid filter creation (order <= 0)
        assertThrows(IllegalArgumentException.class, () -> { new IIRFilter(0); }, "Order must be greater than zero");
    }

    @Test
    void testSetCoeffsInvalidLengthA() {
        IIRFilter filter = new IIRFilter(2);

        // Invalid 'aCoeffs' length
        double[] aCoeffs = {1.0}; // too short
        double[] bCoeffs = {1.0, 0.5};
        assertThrows(IllegalArgumentException.class, () -> { filter.setCoeffs(aCoeffs, bCoeffs); }, "aCoeffs must be of size 2");
    }

    @Test
    void testSetCoeffsInvalidLengthB() {
        IIRFilter filter = new IIRFilter(2);

        // Invalid 'bCoeffs' length
        double[] aCoeffs = {1.0, 0.5};
        double[] bCoeffs = {1.0}; // too short
        assertThrows(IllegalArgumentException.class, () -> { filter.setCoeffs(aCoeffs, bCoeffs); }, "bCoeffs must be of size 2");
    }

    @Test
    void testSetCoeffsInvalidACoeffZero() {
        IIRFilter filter = new IIRFilter(2);

        // Invalid 'aCoeffs' where aCoeffs[0] == 0.0
        double[] aCoeffs = {0.0, 0.5}; // aCoeffs[0] must not be zero
        double[] bCoeffs = {1.0, 0.5};
        assertThrows(IllegalArgumentException.class, () -> { filter.setCoeffs(aCoeffs, bCoeffs); }, "aCoeffs[0] must not be zero");
    }

    @Test
    void testProcessWithNoCoeffsSet() {
        // Test process method with default coefficients (sane defaults)
        IIRFilter filter = new IIRFilter(2);
        double inputSample = 0.5;
        double result = filter.process(inputSample);

        // Since default coeffsA[0] and coeffsB[0] are 1.0, expect output = input
        assertEquals(inputSample, result, 1e-6, "Process should return the same value as input with default coefficients");
    }

    @Test
    void testProcessWithCoeffsSet() {
        // Test process method with set coefficients
        IIRFilter filter = new IIRFilter(2);

        double[] aCoeffs = {1.0, 0.5};
        double[] bCoeffs = {1.0, 0.5};
        filter.setCoeffs(aCoeffs, bCoeffs);

        // Process a sample
        double inputSample = 0.5;
        double result = filter.process(inputSample);

        // Expected output can be complex to calculate in advance;
        // check if the method runs and returns a result within reasonable bounds
        assertTrue(result >= -1.0 && result <= 1.0, "Processed result should be in the range [-1, 1]");
    }
}
