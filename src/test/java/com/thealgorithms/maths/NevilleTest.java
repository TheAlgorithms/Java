package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NevilleTest {

    @Test
    public void testInterpolateLinear() {
        // Test with a simple linear function y = 2x + 1
        // Points (0, 1) and (2, 5)
        double[] x = {0, 2};
        double[] y = {1, 5};
        // We want to find y when x = 1, which should be 3
        double target = 1;
        double expected = 3.0;
        assertEquals(expected, Neville.interpolate(x, y, target), 1e-9);
    }

    @Test
    public void testInterpolateQuadratic() {
        // Test with a quadratic function y = x^2
        // Points (0, 0), (1, 1), (3, 9)
        double[] x = {0, 1, 3};
        double[] y = {0, 1, 9};
        // We want to find y when x = 2, which should be 4
        double target = 2;
        double expected = 4.0;
        assertEquals(expected, Neville.interpolate(x, y, target), 1e-9);
    }

    @Test
    public void testInterpolateWithNegativeNumbers() {
        // Test with y = x^2 - 2x + 1
        // Points (-1, 4), (0, 1), (2, 1)
        double[] x = {-1, 0, 2};
        double[] y = {4, 1, 1};
        // We want to find y when x = 1, which should be 0
        double target = 1;
        double expected = 0.0;
        assertEquals(expected, Neville.interpolate(x, y, target), 1e-9);
    }

    @Test
    public void testMismatchedArrayLengths() {
        double[] x = {1, 2};
        double[] y = {1};
        double target = 1.5;
        assertThrows(IllegalArgumentException.class, () -> Neville.interpolate(x, y, target));
    }

    @Test
    public void testEmptyArrays() {
        double[] x = {};
        double[] y = {};
        double target = 1;
        assertThrows(IllegalArgumentException.class, () -> Neville.interpolate(x, y, target));
    }

    @Test
    public void testDuplicateXCoordinates() {
        double[] x = {1, 2, 1};
        double[] y = {5, 8, 3};
        double target = 1.5;
        assertThrows(IllegalArgumentException.class, () -> Neville.interpolate(x, y, target));
    }
}
