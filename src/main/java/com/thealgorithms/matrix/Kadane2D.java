package com.thealgorithms.matrix;


/**
 * Kadane's Algorithm for 2D arrays (Maximum Sum Rectangle in a 2D Matrix)
 * 
 * This algorithm finds the maximum sum of elements in a rectangular submatrix
 * of a given 2D matrix. It uses Kadane's algorithm for 1D arrays extended to 2D.
 * 
 * Time Complexity: O(n^2 * m) where n is the number of columns and m is the number of rows
 * Space Complexity: O(m) for the temporary array
 * 
 * @author Adarsh Singh
 */
public final class Kadane2D {
    
    
    
    /**
     * Finds the maximum sum rectangle in a 2D matrix
     * 
     * @param matrix The input 2D matrix
     * @return The maximum sum found in any rectangular submatrix
     * @throws IllegalArgumentException if matrix is null or empty
     */
    public static int maxSumRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        
        // Fix the left column
        for (int left = 0; left < cols; left++) {
            int[] temp = new int[rows];
            
            // Calculate sum between left and right columns
            for (int right = left; right < cols; right++) {
                // Add elements of current column to temp array
                for (int i = 0; i < rows; i++) {
                    temp[i] += matrix[i][right];
                }
                
                // Apply Kadane's 1D algorithm on temp array
                int currentMax = kadane1D(temp);
                maxSum = Math.max(maxSum, currentMax);
            }
        }
        
        return maxSum;
    }
    
    /**
     * Kadane's algorithm for 1D array
     * Finds maximum sum of contiguous subarray
     * 
     * @param arr The input array
     * @return Maximum sum of contiguous subarray
     */
    private static int kadane1D(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * Result class to store coordinates along with maximum sum
     */
    public static class Result {
        public final int maxSum;
        public final int topRow;
        public final int leftCol;
        public final int bottomRow;
        public final int rightCol;
        
        public Result(int maxSum, int topRow, int leftCol, int bottomRow, int rightCol) {
            this.maxSum = maxSum;
            this.topRow = topRow;
            this.leftCol = leftCol;
            this.bottomRow = bottomRow;
            this.rightCol = rightCol;
        }
        
        @Override
        public String toString() {
            return String.format("Max Sum: %d, Top-Left: (%d, %d), Bottom-Right: (%d, %d)",
                    maxSum, topRow, leftCol, bottomRow, rightCol);
        }
    }
    
    /**
     * Finds the maximum sum rectangle along with its coordinates
     * 
     * @param matrix The input 2D matrix
     * @return Result object containing maximum sum and rectangle coordinates
     * @throws IllegalArgumentException if matrix is null or empty
     */
    public static Result maxSumRectangleWithCoordinates(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        int finalLeft = 0, finalRight = 0, finalTop = 0, finalBottom = 0;
        
        for (int left = 0; left < cols; left++) {
            int[] temp = new int[rows];
            
            for (int right = left; right < cols; right++) {
                for (int i = 0; i < rows; i++) {
                    temp[i] += matrix[i][right];
                }
                
                // Modified Kadane's to track row positions
                int currentSum = temp[0];
                int maxTemp = temp[0];
                int start = 0, end = 0, s = 0;
                
                for (int i = 1; i < rows; i++) {
                    if (currentSum < 0) {
                        currentSum = temp[i];
                        s = i;
                    } else {
                        currentSum += temp[i];
                    }
                    
                    if (currentSum > maxTemp) {
                        maxTemp = currentSum;
                        start = s;
                        end = i;
                    }
                }
                
                if (maxTemp > maxSum) {
                    maxSum = maxTemp;
                    finalLeft = left;
                    finalRight = right;
                    finalTop = start;
                    finalBottom = end;
                }
            }
        }
        
        return new Result(maxSum, finalTop, finalLeft, finalBottom, finalRight);
    }
}
