package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

public class EulerMethodTest {

    @Test
    public void testEulerStepBasic() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        double result = EulerMethod.eulerStep(0.0, 0.1, 1.0, equation);
        assertEquals(1.1, result, 1e-9, "Euler step failed for basic equation.");
    }

    @Test
    public void testEulerStepNegativeStepSize() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        assertThrows(IllegalArgumentException.class, () -> EulerMethod.eulerStep(0.0, -0.1, 1.0, equation), "Expected IllegalArgumentException for negative step size.");
    }

    @Test
    public void testEulerStepZeroStepSize() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        assertThrows(IllegalArgumentException.class, () -> EulerMethod.eulerStep(0.0, 0.0, 1.0, equation), "Expected IllegalArgumentException for zero step size.");
    }

    @Test
    public void testEulerFullBasic() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x;
        ArrayList<double[]> result = EulerMethod.eulerFull(0, 1, 0.5, 0, equation);

        assertEquals(3, result.size(), "Incorrect number of points in the result.");
        assertArrayEquals(new double[] {0.0, 0.0}, result.get(0), 1e-9, "Incorrect first point.");
        assertArrayEquals(new double[] {0.5, 0.0}, result.get(1), 1e-9, "Incorrect second point.");
        assertArrayEquals(new double[] {1.0, 0.25}, result.get(2), 1e-9, "Incorrect third point.");
    }

    @Test
    public void testEulerFullWithExponentialEquation() {
        BiFunction<Double, Double, Double> equation = (x, y) -> y;
        ArrayList<double[]> result = EulerMethod.eulerFull(0, 1, 0.1, 1, equation);

        assertEquals(12, result.size(), "Incorrect number of points in the result.");
        double[] lastPoint = result.get(result.size() - 1);
        assertEquals(1.0999999999999999, lastPoint[0], 1e-9, "Incorrect x-value of the last point.");
        assertEquals(2.8531167061100002, lastPoint[1], 1e-9, "Incorrect y-value of the last point.");
    }

    @Test
    public void testEulerFullInvalidRange() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        assertThrows(IllegalArgumentException.class, () -> EulerMethod.eulerFull(1, 0, 0.1, 1, equation), "Expected IllegalArgumentException for invalid range (xStart >= xEnd).");
    }

    @Test
    public void testEulerFullZeroStepSize() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        assertThrows(IllegalArgumentException.class, () -> EulerMethod.eulerFull(0, 1, 0.0, 1, equation), "Expected IllegalArgumentException for zero step size.");
    }

    @Test
    public void testEulerFullNegativeStepSize() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        assertThrows(IllegalArgumentException.class, () -> EulerMethod.eulerFull(0, 1, -0.1, 1, equation), "Expected IllegalArgumentException for negative step size.");
    }

    @Test
    public void testEulerFullSingleStep() {
        BiFunction<Double, Double, Double> equation = (x, y) -> x + y;
        ArrayList<double[]> result = EulerMethod.eulerFull(0, 0.1, 0.1, 1, equation);
        assertEquals(2, result.size(), "Incorrect number of points for single step.");
        assertArrayEquals(new double[] {0.0, 1.0}, result.get(0), 1e-9, "Incorrect first point.");
        assertArrayEquals(new double[] {0.1, 1.1}, result.get(1), 1e-9, "Incorrect second point.");
    }
}
