package com.thealgorithms.maths;

public class MatrixDiag2x2 {

    /*
     * Algorithm for computing blah
     *
     * Equations referenced for algorithm:
     * https://people.math.harvard.edu/~knill/teaching/math21b2004/exhibits/2dmatrices/index.html
     *
     * Condition for diagonalization
     * https://en.wikipedia.org/wiki/Diagonalizable_matrix
     * basic premise is if all eigenvalues are distinct, then diagonal matrix entries are the eigenvalues
     *
     * eigenvalues must be real numbers (no complex)
     * @author msoud25
     */

    // get trace and determinant of matrix to calculate eigenvalues
    public static double MatrixTrace2x2(double[][] mat) {
        return mat[0][0] + mat[1][1];
    }

    public static double MatrixDet2x2(double[][] mat) {
        return (mat[0][0] * mat[1][1]) - (mat[0][1] * mat[1][0]);
    }

    // get eigenvalues
    public static double eigenvalue1(double[][] mat) {
        return (MatrixTrace2x2(mat) / 2) + Math.pow((Math.pow(MatrixTrace2x2(mat), 2) / 4) - MatrixDet2x2(mat), 0.5);
    }

    public static double eigenvalue2(double[][] mat) {
        return (MatrixTrace2x2(mat) / 2) - Math.pow((Math.pow(MatrixTrace2x2(mat), 2) / 4) - MatrixDet2x2(mat), 0.5);
    }
    // get eigenvectors

    public static double[] eigenvector1(double[][] mat) {
        double[] eVector1 = new double[2];

        if (mat[1][0] != 0) {
            eVector1[0] = eigenvalue1(mat) - mat[1][1];
            eVector1[1] = mat[1][0];
        } else if (mat[0][1] != 0) {
            eVector1[0] = mat[0][1];
            eVector1[1] = eigenvalue1(mat) - mat[0][0];
        } else if (mat[0][1] == 0 && mat[1][0] == 0) {
            eVector1[0] = 1;
            eVector1[1] = 0;
        }

        return eVector1;
    }

    public static double[] eigenvector2(double[][] mat) {
        double[] eVector2 = new double[2];

        if (mat[1][0] != 0) {
            eVector2[0] = eigenvalue2(mat) - mat[1][1];
            eVector2[1] = mat[1][0];
        } else if (mat[0][1] != 0) {
            eVector2[0] = mat[0][1];
            eVector2[1] = eigenvalue2(mat) - mat[0][0];
        } else if (mat[0][1] == 0 && mat[1][0] == 0) {
            eVector2[0] = 0;
            eVector2[1] = 1;
        }

        return eVector2;
    }

    // check if matrix is diagonalizable, then find the diagonal matrix

    public static boolean isDiagonalizable(double[][] mat) {
        if (eigenvalue1(mat) != eigenvalue2(mat)) {
            return true;
        } else {
            return false;
        }
    }

    public static double[][] getDiagonalMatrix(double[][] mat) throws Exception {
        double[][] diagMat = new double[2][2];
        if (isDiagonalizable(mat)) {
            diagMat[0][0] = eigenvalue1(mat);
            diagMat[0][1] = 0;
            diagMat[1][0] = 0;
            diagMat[1][1] = eigenvalue2(mat);
            return (diagMat);
        } else {
            throw new Exception("Matrix is not diagonalizable");
        }
    }
}
