package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LUDecompositionTest {

    private static final double DELTA = 1e-9;

    @Test
    public void testDecomposeSimpleMatrix() {
        double[][] matrix = {{2, 1, 1}, {4, 3, 3}, {8, 7, 9}};
        double[][] lu = LUDecomposition.decompose(matrix);

        double[][] lower = LUDecomposition.getLowerMatrix(lu);
        double[][] upper = LUDecomposition.getUpperMatrix(lu);

        assertArrayEquals(new double[] {1, 1, 1}, new double[] {lower[0][0], lower[1][1], lower[2][2]}, DELTA);

        double[][] product = multiply(lower, upper);
        assertArrayEquals(new double[] {2, 1, 1, 4, 3, 3, 8, 7, 9}, flatten(product), DELTA);
    }

    @Test
    public void testDecomposeTwoByTwo() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] lu = LUDecomposition.decompose(matrix);

        double[][] lower = LUDecomposition.getLowerMatrix(lu);
        double[][] upper = LUDecomposition.getUpperMatrix(lu);

        double[][] product = multiply(lower, upper);
        assertArrayEquals(new double[] {1, 2, 3, 4}, flatten(product), DELTA);
    }

    @Test
    public void testDecomposeIdentityMatrix() {
        double[][] matrix = {{1, 0}, {0, 1}};
        double[][] lu = LUDecomposition.decompose(matrix);

        double[][] lower = LUDecomposition.getLowerMatrix(lu);
        double[][] upper = LUDecomposition.getUpperMatrix(lu);

        assertArrayEquals(new double[] {1, 0, 0, 1}, flatten(lower), DELTA);
        assertArrayEquals(new double[] {1, 0, 0, 1}, flatten(upper), DELTA);
    }

    @Test
    public void testDecomposeNonSquareMatrixThrows() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        assertThrows(IllegalArgumentException.class, () -> LUDecomposition.decompose(matrix));
    }

    @Test
    public void testDecomposeSingularMatrixThrows() {
        double[][] matrix = {{0, 1}, {1, 0}};
        assertThrows(ArithmeticException.class, () -> LUDecomposition.decompose(matrix));
    }

    @Test
    public void testSolveLinearSystem() {
        double[][] matrix = {{2, 1, 1}, {4, 3, 3}, {8, 7, 9}};
        double[] b = {8, 20, 46};
        double[][] lu = LUDecomposition.decompose(matrix);
        double[] solution = LUDecomposition.solve(lu, b);

        assertArrayEquals(new double[] {1, 3, 3}, solution, DELTA);
    }

    @Test
    public void testSolveTwoByTwoSystem() {
        double[][] matrix = {{2, 1}, {1, 3}};
        double[] b = {5, 7};
        double[][] lu = LUDecomposition.decompose(matrix);
        double[] solution = LUDecomposition.solve(lu, b);

        assertArrayEquals(new double[] {1.6, 1.8}, solution, DELTA);
    }

    private static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    private static double[] flatten(double[][] matrix) {
        int n = matrix.length;
        double[] result = new double[n * n];
        int idx = 0;
        for (double[] row : matrix) {
            for (double val : row) {
                result[idx++] = val;
            }
        }
        return result;
    }
}
