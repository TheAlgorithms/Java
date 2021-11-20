/**
 * \file
 * \brief [Gaussian elimination
 * method](https://en.wikipedia.org/wiki/Gaussian_elimination)
 * @author [Sachwin Kohli](https://github.com/Sachwin-Kohli)
 */
package com.thealgorithms.maths;

import java.util.*;

/**
 * Main function
 */
public class Gaussian {

    public static void main(String[] args) {
        int mat_size, i, j, step;
        Scanner sc = new Scanner(System.in);

        System.out.println("Matrix Size : ");
        mat_size = sc.nextInt();

        double[][] mat = new double[mat_size + 1][mat_size + 1];
        double[][] x = new double[mat_size][mat_size + 1];

        System.out.println("Enter value of the matrix");
        System.out.println(' ');
        for (i = 0; i < mat_size; i++) {
            for (j = 0; j <= mat_size; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        // perform Gaussian elimination
        for (step = 0; step < mat_size - 1; step++) {
            for (i = step; i < mat_size - 1; i++) {
                double a = (mat[i + 1][step] / mat[step][step]);

                for (j = step; j <= mat_size; j++) {
                    mat[i + 1][j] = mat[i + 1][j] - (a * mat[step][j]);
                }
            }
        }

        System.out.println("Matrix using Gaussian Elimination method: ");
        System.out.println(" ");
        for (i = 0; i < mat_size; i++) {
            for (j = 0; j <= mat_size; j++) {
                x[i][j] = mat[i][j];
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Value of the Gaussian Elimination method: ");
        System.out.println(" ");

        for (i = mat_size - 1; i >= 0; i--) {
            double sum = 0;
            for (j = mat_size - 1; j > i; j--) {
                x[i][j] = x[j][j] * x[i][j];
                sum = x[i][j] + sum;
            }
            if (x[i][i] == 0) {
                x[i][i] = 0;
            } else {
                x[i][i] = (x[i][mat_size] - sum) / (x[i][i]);
            }
            System.out.print("x" + i + "=" + x[i][i]);
            System.out.println(" ");
        }
    }
}
