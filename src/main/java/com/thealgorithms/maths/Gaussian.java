package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

public final class Gaussian {
    private Gaussian() {
    }

    public static ArrayList<Double> gaussian(int matSize, List<Double> matrix) {
        int i;
        int j = 0;

        double[][] mat = new double[matSize + 1][matSize + 1];
        double[][] x = new double[matSize][matSize + 1];

        // Values from arraylist to matrix
        for (i = 0; i < matSize; i++) {
            for (j = 0; j <= matSize; j++) {
                mat[i][j] = matrix.get(i);
            }
        }

        mat = gaussianElimination(matSize, i, mat);
        return valueOfGaussian(matSize, x, mat);
    }

    // Perform Gaussian elimination
    public static double[][] gaussianElimination(int matSize, int i, double[][] mat) {
        int step = 0;
        for (step = 0; step < matSize - 1; step++) {
            for (i = step; i < matSize - 1; i++) {
                double a = (mat[i + 1][step] / mat[step][step]);

                for (int j = step; j <= matSize; j++) {
                    mat[i + 1][j] = mat[i + 1][j] - (a * mat[step][j]);
                }
            }
        }
        return mat;
    }

    // calculate the x_1, x_2, ... values of the gaussian and save it in an arraylist.
    public static ArrayList<Double> valueOfGaussian(int matSize, double[][] x, double[][] mat) {
        ArrayList<Double> answerArray = new ArrayList<Double>();
        int i;
        int j;

        for (i = 0; i < matSize; i++) {
            for (j = 0; j <= matSize; j++) {
                x[i][j] = mat[i][j];
            }
        }

        for (i = matSize - 1; i >= 0; i--) {
            double sum = 0;
            for (j = matSize - 1; j > i; j--) {
                x[i][j] = x[j][j] * x[i][j];
                sum = x[i][j] + sum;
            }
            if (x[i][i] == 0) {
                x[i][i] = 0;
            } else {
                x[i][i] = (x[i][matSize] - sum) / (x[i][i]);
            }
            answerArray.add(x[i][j]);
        }
        return answerArray;
    }
}
