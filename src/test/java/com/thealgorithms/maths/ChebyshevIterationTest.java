package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for ChebyshevIteration class
 */
class ChebyshevIterationTest {

    private static final double[][] M1_A = {{4.0, 1.0}, {1.0, 3.0}};
    private static final double[] M1_B = {1.0, 2.0};
    private static final double[] M1_X0 = {0.0, 0.0};
    private static final double M1_LAMBDA_MIN = (7.0 - Math.sqrt(5.0)) / 2.0;
    private static final double M1_LAMBDA_MAX = (7.0 + Math.sqrt(5.0)) / 2.0;
    private static final double[] M1_EXPECTED = {1.0 / 11.0, 7.0 / 11.0};

    private static final double[][] M2_A = {{5.0, 0.0, 0.0}, {0.0, 2.0, 0.0}, {0.0, 0.0, 8.0}};
    private static final double[] M2_B = {10.0, -4.0, 24.0};
    private static final double[] M2_X0 = {0.0, 0.0, 0.0};
    private static final double[] M2_EXPECTED = {2.0, -2.0, 3.0};
    private static final double M2_LAMBDA_MIN = 2.0;
    private static final double M2_LAMBDA_MAX = 8.0;

    private static final int MAX_ITERATIONS = 1000;
    private static final double TOLERANCE = 1e-10;
    private static final double ASSERT_TOLERANCE = 1e-9;

    @Test
    void testSolve2x2System() {
        double[] solution = ChebyshevIteration.solve(
                M1_A, M1_B, M1_X0, M1_LAMBDA_MIN, M1_LAMBDA_MAX, MAX_ITERATIONS, TOLERANCE);
        assertArrayEquals(M1_EXPECTED, solution, ASSERT_TOLERANCE);
    }

    @Test
    void testSolve3x3System() {
        double[] solution = ChebyshevIteration.solve(
                M2_A, M2_B, M2_X0, M2_LAMBDA_MIN, M2_LAMBDA_MAX, MAX_ITERATIONS, TOLERANCE);
        assertArrayEquals(M2_EXPECTED, solution, ASSERT_TOLERANCE);
    }

    @Test
    void testAlreadyConverged() {
        double[] solution = ChebyshevIteration.solve(
                M1_A, M1_B, M1_EXPECTED, M1_LAMBDA_MIN, M1_LAMBDA_MAX, MAX_ITERATIONS, TOLERANCE);
        assertArrayEquals(M1_EXPECTED, solution, ASSERT_TOLERANCE);
    }

    @Test
    void testInvalidEigenvalues() {
        assertThrows(IllegalArgumentException.class, () ->
                ChebyshevIteration.solve(M1_A, M1_B, M1_X0, 2.0, 1.0, 10, 1e-5));
        assertThrows(IllegalArgumentException.class, () ->
                ChebyshevIteration.solve(M1_A, M1_B, M1_X0, 0.0, 2.0, 10, 1e-5));
    }
}
