package com.thealgorithms.searches;

public class SearchInARowAndColWiseSortedMatrix {
    /**
     * Search a key in row and column wise sorted matrix
     *
     * @param matrix matrix to be searched
     * @param value  Key being searched for
     * @author Sadiul Hakim : https://github.com/sadiul-hakim
     */

    public void search(int[][] matrix, int value) {
        int n = matrix.length;
        // This variable iterates over rows
        int i = 0;
        // This variable iterates over columns
        int j = n - 1;

        while (i < n && j >= 0) {

            if (matrix[i][j] == value) {
                System.out.println(value + " found at position row : " + i + " ,column :" + j);
                return;
            }
            if (value > matrix[i][j]) {

                i++;

            } else {
                j--;
            }

        }
        System.out.println(value + "Not Found.");
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 3, 4, 5, 6, 7 },
                { 8, 9, 10, 11, 12 },
                { 14, 15, 16, 17, 18 },
                { 23, 24, 25, 26, 27 },
                { 30, 31, 32, 33, 34 }
        };

        var search = new SearchInARowAndColWiseSortedMatrix();
        search.search(matrix, 26);
    }
}
