package com.thealgorithms.searches;

import java.util.Arrays;

public class SearchInARowAndColWiseSortedMatrix {
    /**
     * Search a key in row and column wise sorted matrix
     *
     * @param matrix matrix to be searched
     * @param value  Key being searched for
     * @author Sadiul Hakim : https://github.com/sadiul-hakim
     */

    public int[] search(int[][] matrix, int value) {
        int n = matrix.length;
        // This variable iterates over rows
        int i = 0;
        // This variable iterates over columns
        int j = n - 1;
        int[] result = { -1, -1 };

        while (i < n && j >= 0) {

            if (matrix[i][j] == value) {
                result[0] = i;
                result[1] = j;
                return result;
            }
            if (value > matrix[i][j]) {

                i++;

            } else {
                j--;
            }

        }
        return result;
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
        int[] res = search.search(matrix, 26);
        System.out.println(Arrays.toString(res));
    }
}
