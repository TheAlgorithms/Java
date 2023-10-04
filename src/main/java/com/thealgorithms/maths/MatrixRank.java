package com.thealgorithms.maths;


import java.util.ArrayList;
import java.util.Scanner;

/**
 *Rank of the Matrix:It is a fundamental concept in Linear ALgebra which measures
 * the maximum number of linealy independent rows or columns in any matrix.
 *
 * Given is the code that computes rank of matrix,the matrix is given by the user
 * and using the computeRank(input matrix) we find the rank of the matrix.
 * Time Complexity: O(MNrank), where M = Noof(rows) ,N= Noof(column)
 * Space Complexity:O(N), space required to store one row at a time when performing row operation
 **/
public class MatrixRank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of columns:");
        int cols = scanner.nextInt();

        double[][] matrix = new double[rows][cols];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        int rank = computeRank(matrix);
        System.out.println("Rank of the matrix: " + rank);
    }

    public static int computeRank(double[][] A) {
        int n = A.length;
        int m = A[0].length;
        int rank = 0;
        boolean[] row_selected = new boolean[n];
        for (int i = 0; i < m; ++i) {
            int j;
            for (j = 0; j < n; ++j) {
                if (!row_selected[j] && Math.abs(A[j][i]) > 1E-9)
                    break;
            }
            if (j != n) {
                ++rank;
                row_selected[j] = true;
                for (int p = i + 1; p < m; ++p)
                    A[j][p] /= A[j][i];
                for (int k = 0; k < n; ++k) {
                    if (k != j && Math.abs(A[k][i]) > 1E-9) {
                        for (int p = i + 1; p < m; ++p)
                            A[k][p] -= A[j][p] * A[k][i];
                    }
                }
            }
        }
        return rank;
    }
}

/** The code is also implement using Arraylist ,
 * Time Complexity:O(MNrank) ,M=Noof(rows) ,N=Noof(columns)
 * Space Complexity:O(MN), we are storing entire matrix in an Arraylist of Arraylists
 * **/
//
//public class MatrixRank {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the number of rows:");
//        int rows = scanner.nextInt();
//        System.out.println("Enter the number of columns:");
//        int cols = scanner.nextInt();
//
//        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
//        System.out.println("Enter the elements of the matrix:");
//        for (int i = 0; i < rows; i++) {
//            ArrayList<Double> row = new ArrayList<>();
//            for (int j = 0; j < cols; j++) {
//                row.add(scanner.nextDouble());
//            }
//            matrix.add(row);
//        }
//
//        int rank = computeRank(matrix);
//        System.out.println("Rank of the matrix: " + rank);
//    }
//
//    public static int computeRank(ArrayList<ArrayList<Double>> A) {
//        int n = A.size();
//        int m = A.get(0).size();
//        int rank = 0;
//        boolean[] row_selected = new boolean[n];
//        for (int i = 0; i < m; ++i) {
//            int j;
//            for (j = 0; j < n; ++j) {
//                if (!row_selected[j] && Math.abs(A.get(j).get(i)) > 1E-9)
//                    break;
//            }
//            if (j != n) {
//                ++rank;
//                row_selected[j] = true;
//                for (int p = i + 1; p < m; ++p)
//                    A.get(j).set(p, A.get(j).get(p) / A.get(j).get(i));
//                for (int k = 0; k < n; ++k) {
//                    if (k != j && Math.abs(A.get(k).get(i)) > 1E-9) {
//                        for (int p = i + 1; p < m; ++p)
//                            A.get(k).set(p, A.get(k).get(p) - A.get(j).get(p) * A.get(k).get(i));
//                    }
//                }
//            }
//        }
//        return rank;
//    }
//}


/**
 {0,0,0},
 {0,0,0},
 {0, 0,0} Rank is 0

 {5,6},
 {7,8}   Rank is 2

 {1,-1},
 {3,-6}  Rank is 2

 {1,4},
 {2,8}   Rank is 1

 {2,-1,1},
 {2,1,-5},
 {1,1,1}   Rank is 3

 {-1, 2, -2},
 {4, -3, 4},
 {-2, 4, -4}  Rank is 2

 {1, 2, -1, 3},
 {2, 4, 1, -2},
 {3, 6, 3, -7}  Rank is 2

 {3, 1, -5, -1},
 {1, -2, 1, -5},
 {1, 5, -7, 2}  Rank is 3


 **/