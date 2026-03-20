package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for Correlation class
 */
public class CorrelationTest {

    public static final double DELTA = 1e-9;

    public void testCorrelationFirst() {
        double[] x = {1, 2, 3, 4};
        double[] y = {7, 1, 4, 9};
        int n = 4;
        assertEquals(0.3319700011, Correlation.correlation(x, y, n), DELTA);
    }
	
    public void testCorrelationSecond() {
        double[] x = {1, 2, 3, 4};
        double[] y = {5, 0, 9, 2};
        int n = 4;
        assertEquals(0, Correlation.correlation(x, y, n), DELTA);
    }        

    public void testCorrelationConstant() {
        double[] x = {1, 2, 3};
        double[] y = {4, 4, 4};
        int n = 3;
        assertEquals(0, Correlation.correlation(x, y, n), DELTA);
	}

    public void testCorrelationLinearDependence() {
        double[] x = {1, 2, 3, 4};
        double[] y = {6, 8, 10, 12};
        int n = 4;
        assertEquals(1, Correlation.correlation(x, y, n), DELTA);
    }

    public void testCorrelationInverseLinearDependence() {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {18, 15, 12, 9, 6};
        int n = 5;
        assertEquals(-1, Correlation.correlation(x, y, n), DELTA);
    }
}
