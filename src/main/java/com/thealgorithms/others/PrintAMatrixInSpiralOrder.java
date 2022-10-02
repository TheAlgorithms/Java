package com.thealgorithms.others;

public class PrintAMatrixInSpiralOrder {
    /**
     * Search a key in row and column wise sorted matrix
     *
     * @param matrix matrix to be searched
     * @param row    number of rows matrix has
     * @param col    number of columns matrix has
     * @author Sadiul Hakim : https://github.com/sadiul-hakim
     */

    public void print(int[][] matrix, int row, int col) {

        //r traverses matrix row wise from first
        int r = 0;
        //c traverses matrix column wise from first
        int c = 0;
        int i;

        while (r < row && c < col) {
            //print first row of matrix
            for (i = c; i < col; i++) {
                System.out.print(matrix[r][i] + " ");

            }

            //increase r by one because first row printed
            r++;

            //print last column
            for (i = r; i < row; i++) {
                System.out.print(matrix[i][col - 1] + " ");
            }

            //decrease col by one because last column has been printed
            col--;

            //print rows from last except printed elements
            if (r < row) {
                for (i = col - 1; i >= c; i--) {
                    System.out.print(matrix[row - 1][i] + " ");
                }

                row--;

            }

            //print columns from first except printed elements
            if (c < col) {
                for (i = row - 1; i >= r; i--) {
                    System.out.print(matrix[i][c] + " ");
                }
                c++;
            }

        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 3, 4, 5, 6, 7 },
                { 8, 9, 10, 11, 12 },
                { 14, 15, 16, 17, 18 },
                { 23, 24, 25, 26, 27 },
                { 30, 31, 32, 33, 34 }
        };

        var printer = new PrintAMatrixInSpiralOrder();
        printer.print(matrix, matrix.length, matrix[0].length);

    }
}
