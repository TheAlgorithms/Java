package com.thealgorithms.randomized;

import static com.thealgorithms.randomized.MonteCarloIntegration.approximate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Function;
import org.junit.jupiter.api.Test;

class MonteCarloIntegrationTest {

    private static final double EPSILON = 0.03; // Allow 3% error margin

    @Test
    void testConstantFunction() {
        // Integral of f(x) = 2 from 0 to 1 is 2
        Function<Double, Double> constant = x -> 2.0;
        double result = approximate(constant, 0, 1, 10000);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testLinearFunction() {
        // Integral of f(x) = x from 0 to 1 is 0.5
        Function<Double, Double> linear = Function.identity();
        double result = approximate(linear, 0, 1, 10000);
        assertEquals(0.5, result, EPSILON);
    }

    @Test
    void testQuadraticFunction() {
        // Integral of f(x) = x^2 from 0 to 1 is 1/3
        Function<Double, Double> quadratic = x -> x * x;
        double result = approximate(quadratic, 0, 1, 10000);
        assertEquals(1.0 / 3.0, result, EPSILON);
    }

    @Test
    void testLargeSampleSize() {
        // Integral of f(x) = x^2 from 0 to 1 is 1/3
        Function<Double, Double> quadratic = x -> x * x;
        double result = approximate(quadratic, 0, 1, 50000000);
        assertEquals(1.0 / 3.0, result, EPSILON / 2); // Larger sample size, smaller error margin
    }

    @Test
    void testReproducibility() {
        Function<Double, Double> linear = Function.identity();
        double result1 = approximate(linear, 0, 1, 10000, 42L);
        double result2 = approximate(linear, 0, 1, 10000, 42L);
        assertEquals(result1, result2, 0.0); // Exactly equal
    }

    @Test
    void testNegativeInterval() {
        // Integral of f(x) = x from -1 to 1 is 0
        Function<Double, Double> linear = Function.identity();
        double result = approximate(linear, -1, 1, 10000);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testNullFunction() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> approximate(null, 0, 1, 1000));
        assertNotNull(exception);
    }

    @Test
    void testInvalidInterval() {
        Function<Double, Double> linear = Function.identity();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            approximate(linear, 2, 1, 1000); // b <= a
        });
        assertNotNull(exception);
    }

    @Test
    void testZeroSampleSize() {
        Function<Double, Double> linear = Function.identity();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> approximate(linear, 0, 1, 0));
        assertNotNull(exception);
    }

    @Test
    void testNegativeSampleSize() {
        Function<Double, Double> linear = Function.identity();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> approximate(linear, 0, 1, -100));
        assertNotNull(exception);
    }
}
