// Problem: Determinant of a Matrix
// 
// Implement a function to calculate the determinant of a square matrix.
//
// Requirements:
// - The function should work for any n x n matrix.
// - Return 0 if the matrix is singular (determinant = 0).
// - Only square matrices are allowed; handle error for non-square matrices.
//
// Example:
// double[][] matrix1 = {{1,2},{3,4}};
// determinant(matrix1); // returns -2
//
// double[][] matrix2 = {{2,0,1},{3,0,0},{5,1,1}};
// determinant(matrix2); // returns 3
//
// Notes:
// - You can use recursion or any other suitable algorithm.
// - Bonus: Optimize for larger matrices (optional).
package matrix;

public class MatrixDeterminant {

    public static double determinant(double[][] m) {
        int n = m.length;
        if (n == 1) return m[0][0];
        if (n == 2) return m[0][0]*m[1][1] - m[0][1]*m[1][0];

        double det = 0;
        for (int c = 0; c < n; c++) {
            det += Math.pow(-1, c) * m[0][c] * determinant(minor(m, 0, c));
        }
        return det;
    }

    private static double[][] minor(double[][] m, int row, int col) {
        int n = m.length;
        double[][] min = new double[n-1][n-1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                min[r][c++] = m[i][j];
            }
            r++;
        }
        return min;
    }

    public static void main(String[] args) {
        double[][] a = {{1,2},{3,4}};
        double[][] b = {{2,0,1},{3,0,0},{5,1,1}};
        System.out.println(determinant(a)); // -2
        System.out.println(determinant(b)); // 3
    }
}
