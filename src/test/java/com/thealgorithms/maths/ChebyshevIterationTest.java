package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ChebyshevIterationTest {

    @Test
    public void testSolveSimple2x2Diagonal() {
        double[][] a = {{2, 0}, {0, 1}};
        double[] b = {2, 2};
        double[] x0 = {0, 0};
        double minEig = 1.0;
        double maxEig = 2.0;
        int maxIter = 50;
        double tol = 1e-9;
        double[] expected = {1.0, 2.0};

        double[] result = ChebyshevIteration.solve(a, b, x0, minEig, maxEig, maxIter, tol);
        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testSolve2x2Symmetric() {
        double[][] a = {{4, 1}, {1, 3}};
        double[] b = {1, 2};
        double[] x0 = {0, 0};
        double minEig = (7.0 - Math.sqrt(5.0)) / 2.0;
        double maxEig = (7.0 + Math.sqrt(5.0)) / 2.0;
        int maxIter = 100;
        double tol = 1e-10;
        double[] expected = {1.0 / 11.0, 7.0 / 11.0};

        double[] result = ChebyshevIteration.solve(a, b, x0, minEig, maxEig, maxIter, tol);
        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testAlreadyAtSolution() {
        double[][] a = {{2, 0}, {0, 1}};
        double[] b = {2, 2};
        double[] x0 = {1, 2};
        double minEig = 1.0;
        double maxEig = 2.0;
        int maxIter = 10;
        double tol = 1e-5;
        double[] expected = {1.0, 2.0};

        double[] result = ChebyshevIteration.solve(a, b, x0, minEig, maxEig, maxIter, tol);
        assertArrayEquals(expected, result, 0.0);
    }

    @Test
    public void testMismatchedDimensionsAB() {
        double[][] a = {{1, 0}, {0, 1}};
        double[] b = {1};
        double[] x0 = {0, 0};
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 1, 2, 10, 1e-5));
    }

    @Test
    public void testMismatchedDimensionsAX() {
        double[][] a = {{1, 0}, {0, 1}};
        double[] b = {1, 1};
        double[] x0 = {0};
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 1, 2, 10, 1e-5));
    }

    @Test
    public void testNonSquareMatrix() {
        double[][] a = {{1, 0, 0}, {0, 1, 0}};
        double[] b = {1, 1};
        double[] x0 = {0, 0};
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 1, 2, 10, 1e-5));
    }

    @Test
    public void testInvalidEigenvalues() {
        double[][] a = {{1, 0}, {0, 1}};
        double[] b = {1, 1};
        double[] x0 = {0, 0};
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 2, 1, 10, 1e-5));
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 1, 1, 10, 1e-5));
    }

    @Test
    public void testNonPositiveDefinite() {
        double[][] a = {{1, 0}, {0, 1}};
        double[] b = {1, 1};
        double[] x0 = {0, 0};
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 0, 1, 10, 1e-5));
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, -1, 1, 10, 1e-5));
    }

    @Test
    public void testInvalidIterationCount() {
        double[][] a = {{1, 0}, {0, 1}};
        double[] b = {1, 1};
        double[] x0 = {0, 0};
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 1, 2, 0, 1e-5));
        assertThrows(IllegalArgumentException.class, () -> ChebyshevIteration.solve(a, b, x0, 1, 2, -1, 1e-5));
    }
}
