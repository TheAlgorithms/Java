package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ChebyshevTest {

    @Test
    public void testSolveSimple2x2Diagonal() {
        // A = [[2, 0], [0, 1]] (SPD)
        // Eigenvalues: m=1, M=2
        // b = [2, 2]
        // Exact solution: x = [1, 2]
        double[][] a = { { 2, 0 }, { 0, 1 } };
        double[] b = { 2, 2 };
        double[] x0 = { 0, 0 };
        double minEig = 1.0;
        double maxEig = 2.0;
        int maxIter = 50;
        double tol = 1e-9;
        double[] expected = { 1.0, 2.0 };

        double[] result = Chebyshev.solve(a, b, x0, minEig, maxEig, maxIter, tol);
        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testSolve2x2Symmetric() {
        // A = [[4, 1], [1, 3]] (SPD)
        // Eigenvalues = (7 +/- sqrt(5)) / 2
        // m ≈ 2.3819, M ≈ 4.6180
        // b = [1, 2]
        // Exact solution: x = [1/11, 7/11]
        double[][] a = { { 4, 1 }, { 1, 3 } };
        double[] b = { 1, 2 };
        double[] x0 = { 0, 0 };
        double minEig = (7.0 - Math.sqrt(5.0)) / 2.0;
        double maxEig = (7.0 + Math.sqrt(5.0)) / 2.0;
        int maxIter = 100;
        double tol = 1e-10;
        double[] expected = { 1.0 / 11.0, 7.0 / 11.0 };

        double[] result = Chebyshev.solve(a, b, x0, minEig, maxEig, maxIter, tol);
        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    public void testAlreadyAtSolution() {
        // Test if the initial guess is already the solution
        double[][] a = { { 2, 0 }, { 0, 1 } };
        double[] b = { 2, 2 };
        double[] x0 = { 1, 2 }; // Initial guess is the solution
        double minEig = 1.0;
        double maxEig = 2.0;
        int maxIter = 10;
        double tol = 1e-5;
        double[] expected = { 1.0, 2.0 };

        double[] result = Chebyshev.solve(a, b, x0, minEig, maxEig, maxIter, tol);
        assertArrayEquals(expected, result, 0.0); // Should be exact
    }

    @Test
    public void testMismatchedDimensionsAB() {
        double[][] a = { { 1, 0 }, { 0, 1 } };
        double[] b = { 1 }; // Should be length 2
        double[] x0 = { 0, 0 };
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 1, 2, 10, 1e-5));
    }

    @Test
    public void testMismatchedDimensionsAX() {
        double[][] a = { { 1, 0 }, { 0, 1 } };
        double[] b = { 1, 1 };
        double[] x0 = { 0 }; // Should be length 2
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 1, 2, 10, 1e-5));
    }

    @Test
    public void testNonSquareMatrix() {
        double[][] a = { { 1, 0, 0 }, { 0, 1, 0 } }; // 2x3
        double[] b = { 1, 1 };
        double[] x0 = { 0, 0 }; // This check will fail first
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 1, 2, 10, 1e-5));
    }

    @Test
    public void testInvalidEigenvalues() {
        double[][] a = { { 1, 0 }, { 0, 1 } };
        double[] b = { 1, 1 };
        double[] x0 = { 0, 0 };
        // min > max
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 2, 1, 10, 1e-5));
        // min == max
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 1, 1, 10, 1e-5));
    }

    @Test
    public void testNonPositiveDefinite() {
        double[][] a = { { 1, 0 }, { 0, 1 } };
        double[] b = { 1, 1 };
        double[] x0 = { 0, 0 };
        // min = 0
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 0, 1, 10, 1e-5));
        // min < 0
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, -1, 1, 10, 1e-5));
    }

    @Test
    public void testInvalidIterationCount() {
        double[][] a = { { 1, 0 }, { 0, 1 } };
        double[] b = { 1, 1 };
        double[] x0 = { 0, 0 };
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 1, 2, 0, 1e-5));
        assertThrows(IllegalArgumentException.class, () -> Chebyshev.solve(a, b, x0, 1, 2, -1, 1e-5));
    }
}
