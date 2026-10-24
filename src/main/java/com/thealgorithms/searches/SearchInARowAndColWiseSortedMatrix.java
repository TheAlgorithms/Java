package com.thealgorithms.searches;

public final class SearchInARowAndColWiseSortedMatrix {
    /**
     * Search a key in row and column wise sorted matrix
     *
     * @param matrix matrix to be searched
     * @param value  Key being searched for
     * @return row and column indices if found, otherwise {-1, -1}
     * @author Sadiul Hakim : https://github.com/sadiul-hakim
     */
    public int[] search(int[][] matrix, int value) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        // This variable iterates over rows
        int i = 0;
        // This variable iterates over columns, starting from top-right corner
        int j = cols - 1;
        int[] result = {-1, -1};
        while (i < rows && j >= 0) {
            if (matrix[i] == null || j >= matrix[i].length) {
                i++;
                continue;
            }
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
}
