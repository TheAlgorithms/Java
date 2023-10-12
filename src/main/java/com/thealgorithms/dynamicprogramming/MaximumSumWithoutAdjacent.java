package com.thealgorithms.dynamicprogramming;

/**
 * Problem Statement:
 * Find maximum sum of values(2 or more) in a 2D array where no elements can be
 * adjacent(horizontal or vertical or diagonal) to each other.
 */
public class MaximumSumWithoutAdjacent {
    public static void main(String[] args) {
        int[][] input = new int[][]{{1,2,4,3,2},{4,2,1,3,2}};
        System.out.println(findMaxSumWithoutAdjacent(input));
    }

    //We approach this problem by using dynamic programming where we are computing max
    //sum till each column index in the dp array of length equal to number of columns.
    // To avoid using extra memory we are reusing the first row of input 2D array.
    public static int findMaxSumWithoutAdjacent(int[][] input) {
        //Without Using extra space
        input[0][0] = Math.max(input[0][0], input[1][0]);
        input[0][1] = Math.max(Math.max(input[0][1], input[1][1]),input[0][0]);
        for(int i=2;i<input[0].length;i++){
            input[0][i] = Math.max(Math.max(input[0][i], input[1][i])+ input[0][i-2], input[0][i-1]);
        }
        return input[0][input[0].length-1];
    }
}
