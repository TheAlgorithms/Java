package com.thealgorithms.maths;

/**
 * To Check whether the given Sudoku Configuration is Valid or not.
 * @author Yash Jain (https://github.com/Yashjain1602)
 */

import java.io.*;
import java.util.*;
public class CheckValidityOfSudokuBoardConfiguration {
        // Checks whether there is any duplicate in the current row or not
        public static boolean notInRow(char arr[][], int row) {
            // Set to store characters seen so far.
            HashSet<Character> st = new HashSet<>();
    
            for (int i = 0; i < 9; i++) {
                // If already encountered before, return false
                if (st.contains(arr[row][i])) return false;
    
                // If it is not an empty cell, insert value at the current cell in the set
                if (arr[row][i] != '.') st.add(arr[row][i]);
            }
            return true;
        }
    
        // Checks whether there is any duplicate in the current column or not.
        public static boolean notInCol(char arr[][], int col) {
            HashSet<Character> st = new HashSet<>();
    
            for (int i = 0; i < 9; i++) {
                // If already encountered before, return false
                if (st.contains(arr[i][col])) return false;
    
                // If it is not an empty cell, insert value at the current cell in the set
                if (arr[i][col] != '.') st.add(arr[i][col]);
            }
            return true;
        }
    
        // Checks whether there is any duplicate in the current 3x3 box or not.
        public static boolean notInBox(char arr[][], int startRow, int startCol) {
            HashSet<Character> st = new HashSet<>();
    
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char curr = arr[row + startRow][col + startCol];
    
                    // If already encountered before, return false
                    if (st.contains(curr)) return false;
    
                    // If it is not an empty cell, insert value at current cell in set
                    if (curr != '.') st.add(curr);
                }
            }
            return true;
        }
    
        // Checks whether the current row and current column and current 3x3 box is valid or not
        public static boolean isValid(char arr[][], int row, int col) {
            return notInRow(arr, row) && notInCol(arr, col)
                    && notInBox(arr, row - row % 3, col - col % 3);
        }
    
        // Function to check if the entire sudoku configuration is valid
        public static boolean isValidConfig(char arr[][], int n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // If current row or current column or current 3x3 box is not valid, return false
                    if (!isValid(arr, i, j)) return false;
                }
            }
            return true;
     }

    // Driver's code
    public static void main(String[] args) {
        char[][] board = new char[][] {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        // Function call to check if the configuration is valid
        System.out.println((isValidConfig(board, 9) ? "YES" : "NO"));
    }
}
