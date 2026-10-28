package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Correlation class
 */
public class CorrelationTest {

    public static final double DELTA = 1e-9;

    // Regular correlation test
    public void testCorrelationFirst() {
        double[] x = {1, 2, 3, 4};
        double[] y = {7, 1, 4, 9};
        int n = 4;
        assertEquals(0.3319700011, Correlation.correlation(x, y, n), DELTA);
    }

    // Regular correlation test (zero correlation)
    public void testCorrelationSecond() {
        double[] x = {1, 2, 3, 4};
        double[] y = {5, 0, 9, 2};
        int n = 4;
        assertEquals(0, Correlation.correlation(x, y, n), DELTA);
    }

    // Correlation with a constant variable is taken to be zero
    public void testCorrelationConstant() {
        double[] x = {1, 2, 3};
        double[] y = {4, 4, 4};
        int n = 3;
        assertEquals(0, Correlation.correlation(x, y, n), DELTA);
    }

    // Linear dependence gives correlation 1
    public void testCorrelationLinearDependence() {
        double[] x = {1, 2, 3, 4};
        double[] y = {6, 8, 10, 12};
        int n = 4;
        assertEquals(1, Correlation.correlation(x, y, n), DELTA);
    }

    // Inverse linear dependence gives correlation -1
    public void testCorrelationInverseLinearDependence() {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {18, 15, 12, 9, 6};
        int n = 5;
        assertEquals(-1, Correlation.correlation(x, y, n), DELTA);
    }
}
