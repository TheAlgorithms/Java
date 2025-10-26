package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ChebyshevIterationTest {

    // --- Constants for testSolveSimple2x2System ---
    private static final double M1_A11 = 4.0;
    private static final double M1_A12 = 1.0;
    private static final double M1_A21 = 1.0;
    private static final double M1_A22 = 3.0;
    private static final double[][] M1_A = { { M1_A11, M1_A12 }, { M1_A21, M1_A22 } };

    private static final double M1_B1 = 1.0;
    private static final double M1_B2 = 2.0;
    private static final double[] M1_B = { M1_B1, M1_B2 };
    private static final double[] M1_X0 = { 0.0, 0.0 };

    // Eigenvalues are (7 +/- sqrt(5)) / 2
    private static final double M1_LAMBDA_MIN = (7.0 - Math.sqrt(5.0)) / 2.0;
    private static final double M1_LAMBDA_MAX = (7.0 + Math.sqrt(5.0)) / 2.0;
    private static final double M1_EXPECTED_X1 = 1.0 / 11.0;
    private static final double M1_EXPECTED_X2 = 7.0 / 11.0;
    private static final double[] M1_EXPECTED = { M1_EXPECTED_X1, M1_EXPECTED_X2 };

    // --- Constants for testSolve3x3System ---
    private static final double[][] M2_A = { { 5.0, 0.0, 0.0 }, { 0.0, 2.0, 0.0 }, { 0.0, 0.0, 8.0 } };
    private static final double[] M2_B = { 10.0, -4.0, 24.0 };
    private static final double[] M2_X0 = { 0.0, 0.0, 0.0 };
    private static final double[] M2_EXPECTED = { 2.0, -2.0, 3.0 };
    private static final double M2_LAMBDA_MIN = 2.0;
    private static final double M2_LAMBDA_MAX = 8.0;

    // --- Constants for testAlreadyConverged ---
    private static final double M3_LAMBDA_MIN = 2.38;
    private static final double M3_LAMBDA_MAX = 4.62;

    // --- General Constants ---
    private static final int MAX_ITERATIONS = 100;
    private static final double TOLERANCE = 1e-10;
    private static final double ASSERT_TOLERANCE = 1e-9;
    private static final int TEST_ITERATIONS = 10;
    private static final double TEST_TOLERANCE = 1e-5;

    @Test
    void testSolveSimple2x2System() {
        double[] solution = ChebyshevIteration.solve(
            M1_A,
            M1_B,
            M1_X0,
            M1_LAMBDA_MIN,
            M1_LAMBDA_MAX,
            MAX_ITERATIONS,
            TOLERANCE
        );
        assertArrayEquals(M1_EXPECTED, solution, ASSERT_TOLERANCE);
    }

    @Test
    void testSolve3x3System() {
        double[] solution = ChebyshevIteration.solve(
            M2_A,
            M2_B,
            M2_X0,
            M2_LAMBDA_MIN,
            M2_LAMBDA_MAX,
            MAX_ITERATIONS,
            TOLERANCE
        );
        assertArrayEquals(M2_EXPECTED, solution, ASSERT_TOLERANCE);
    }

    @Test
    void testAlreadyConverged() {
        // Test case where the initial guess is already the solution
        double[] solution = ChebyshevIteration.solve(
            M1_A,
            M1_B,
            M1_EXPECTED, // Use expected solution as initial guess
            M3_LAMBDA_MIN, // Use approximate eigenvalues
            M3_LAMBDA_MAX,
            MAX_ITERATIONS,
            TOLERANCE
        );
        assertArrayEquals(M1_EXPECTED, solution, ASSERT_TOLERANCE);
    }

    @Test
    void testInvalidEigenvalues() {
        double[][] A = { { 1.0, 0.0 }, { 0.0, 1.0 } };
        double[] b = { 1.0, 1.0 };
        double[] x0 = { 0.0, 0.0 };

        // lambdaMin >= lambdaMax
        assertThrows(
            IllegalArgumentException.class,
            () ->
                ChebyshevIteration.solve(
                    A,
                    b,
                    x0,
                    2.0,
                    1.0,
                    TEST_ITERATIONS,
                    TEST_TOLERANCE
                )
        );
        // lambdaMin <= 0
        assertThrows(
            IllegalArgumentException.class,
            () ->
                ChebyshevIteration.solve(
                    A,
                    b,
                    x0,
                    0.0,
                    2.0,
                    TEST_ITERATIONS,
                    TEST_TOLERANCE
                )
        );
    }

    @Test
    void testMismatchedDimensions() {
        double[][] A = { { 1.0, 0.0 }, { 0.0, 1.0 } };
        double[] b = { 1.0, 1.0, 1.0 }; // b.length = 3
        double[] x0 = { 0.0, 0.0 }; // x0.length = 2

        assertThrows(
            IllegalArgumentException.class,
            () ->
                ChebyshevIteration.solve(
                    A,
                    b,
                    x0,
                    0.5,
                    1.5,
                    TEST_ITERATIONS,
                    TEST_TOLERANCE
                )
        );
    }

    @Test
    void testNonSquareMatrix() {
        double[][] A = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 } }; // 2x3 matrix
        double[] b = { 1.0, 1.0 };
        double[] x0 = { 0.0, 0.0 };

        assertThrows(
            IllegalArgumentException.class,
            () ->
                ChebyshevIteration.solve(
                    A,
                    b,
                    x0,
                    0.5,
                    1.5,
                    TEST_ITERATIONS,
                    TEST_TOLERANCE
                )
        );
    }
}