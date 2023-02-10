package com.thealgorithms.misc;

import java.util.*;

/*
 *A matrix is sparse if many of its coefficients are zero (In general if 2/3rd of matrix elements are 0, it is considered as sparse).
 *The interest in sparsity arises because its exploitation can lead to enormous computational savings and because many large matrix problems that occur in practice are sparse.
 *
 * @author Ojasva Jain
 */

class Sparcity {

    /*
     * @return Sparcity of matrix
     *
     * where sparcity = number of zeroes/total elements in matrix
     *
     */
    static double sparcity(double[][] mat) {
        int zero = 0;
        //Traversing the matrix to count number of zeroes
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    zero++;
                }
            }
        }
        //return sparcity
        return ((double) zero / (mat.length * mat[1].length));
    }

    //Driver method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of rows in matrix: ");
        int n = in.nextInt();
        System.out.println("Enter number of Columns in matrix: ");
        int m = in.nextInt();

        System.out.println("Enter Matrix elements: ");
        double[][] mat = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = in.nextDouble();
            }
        }
        System.out.println("Sparcity of matrix is: " + sparcity(mat));
    }
}
