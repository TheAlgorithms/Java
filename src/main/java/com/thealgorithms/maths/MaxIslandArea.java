package com.thealgorithms.maths;
/**
 * Given an two dimensional binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges
 * of the grid are surrounded by water.  The area of an island is the number of cells with
 * a value 1 in the island. Return the maximum area of an island in a grid. If there is no island, return 0.
 * @author Akanksha Singh (https://github.com/singhakanksha03)
 */
public class MaxIslandArea {
    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(findMaxIslandArea(matrix)); // Output: 6
    }
    // Check if the given row and column are within the matrix bounds
    public static boolean isSafe(int row, int col, int rows, int cols) {
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }
    // Perform a depth-first search to calculate the area of the island
    public static int depthFirstSearch(int row, int col, boolean[][] visited, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (isSafe(row, col, rows, cols) && !visited[row][col] && matrix[row][col] == 1) {
            visited[row][col] = true;
            int area = 1;
            area += depthFirstSearch(row + 1, col, visited, matrix);
            area += depthFirstSearch(row - 1, col, visited, matrix);
            area += depthFirstSearch(row, col + 1, visited, matrix);
            area += depthFirstSearch(row, col - 1, visited, matrix);
            return area;
        }
        return 0;
    }
    // Find the maximum area of an island in the binary matrix
    public static int findMaxIslandArea(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxArea = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    int currentArea = depthFirstSearch(row, col, visited, matrix);
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        return maxArea;
    }
}
