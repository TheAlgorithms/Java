package com.thealgorithms.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class QRDecompositionTest {

    private static final double DELTA = 1e-9;

    @Test
    public void testQRDecomposition2x2() {
        double[][] matrix = {{12, -51}, {6, 167}};
        QRDecomposition.QR qr = QRDecomposition.decompose(matrix);
        double[][] q = qr.getQ();
        double[][] r = qr.getR();

        double[][] reconstructed = multiplyMatrices(q, r);
        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(matrix[i], reconstructed[i], DELTA);
        }
    }

    @Test
    public void testQRDecomposition3x3() {
        double[][] matrix = {{1, 1, 0}, {1, 0, 1}, {0, 1, 1}};
        QRDecomposition.QR qr = QRDecomposition.decompose(matrix);
        double[][] q = qr.getQ();
        double[][] r = qr.getR();

        double[][] reconstructed = multiplyMatrices(q, r);
        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(matrix[i], reconstructed[i], DELTA);
        }
    }

    @Test
    public void testQROrthogonalColumns() {
        double[][] matrix = {{1, 1, 0}, {1, 0, 1}, {0, 1, 1}};
        QRDecomposition.QR qr = QRDecomposition.decompose(matrix);
        double[][] q = qr.getQ();

        for (int i = 0; i < q[0].length; i++) {
            for (int j = i; j < q[0].length; j++) {
                double dot = 0;
                for (int k = 0; k < q.length; k++) {
                    dot += q[k][i] * q[k][j];
                }
                if (i == j) {
                    assertArrayEquals(new double[] {1.0}, new double[] {dot}, DELTA);
                } else {
                    assertArrayEquals(new double[] {0.0}, new double[] {dot}, DELTA);
                }
            }
        }
    }

    @Test
    public void testRIsUpperTriangular() {
        double[][] matrix = {{12, -51}, {6, 167}};
        QRDecomposition.QR qr = QRDecomposition.decompose(matrix);
        double[][] r = qr.getR();

        for (int i = 1; i < r.length; i++) {
            for (int j = 0; j < i; j++) {
                assertArrayEquals(new double[] {0.0}, new double[] {r[i][j]}, DELTA);
            }
        }
    }

    @Test
    public void testQRDecompositionIdentityMatrix() {
        double[][] matrix = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        QRDecomposition.QR qr = QRDecomposition.decompose(matrix);
        double[][] q = qr.getQ();
        double[][] r = qr.getR();

        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(matrix[i], q[i], DELTA);
            assertArrayEquals(matrix[i], r[i], DELTA);
        }
    }

    @Test
    public void testQRDecompositionRankDeficientThrows() {
        double[][] matrix = {{1, 2}, {2, 4}};
        assertThrows(ArithmeticException.class, () -> QRDecomposition.decompose(matrix));
    }

    @Test
    public void testQRDecompositionNullMatrixThrows() {
        assertThrows(IllegalArgumentException.class, () -> QRDecomposition.decompose(null));
    }

    @Test
    public void testQRDecompositionEmptyMatrixThrows() {
        assertThrows(IllegalArgumentException.class, () -> QRDecomposition.decompose(new double[0][0]));
    }

    private static double[][] multiplyMatrices(double[][] a, double[][] b) {
        int m = a.length;
        int n = b[0].length;
        int k = a[0].length;
        double[][] result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < k; p++) {
                    result[i][j] += a[i][p] * b[p][j];
                }
            }
        }
        return result;
    }
}
