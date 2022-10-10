package com.thealgorithms.backtracking;

public class Sudoku {

    public static boolean isSafe(int sudoku[][], int row, int col, int digit){
        //column
        for(int i=0; i<=8; i++){
            if(sudoku[i][col] == digit){
                return false;
            }
        }
        
        //row
        for(int j=0; j<=8; j++){
            if(sudoku[row][j] == digit){
                return false;
            }
        }

        //grid
        int sr = (row/3) * 3;
        int sc = (col/3) * 3;

        for(int i=sr; i<sr+3; i++){
            for(int j=sc; j<sc+3; j++){
                if(sudoku[i][j] == digit){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudokuSolver(int sudoku[][], int row, int col){
        // Base Case
        if(row == 9){
            return true;
        }

        //Recursion
        int nextRow = row, nextCol = col+1;
        if(col+1 == 9){
            nextRow = row+1;
            nextCol = 0;
        }

        if(sudoku[row][col] != 0){
            return sudokuSolver(sudoku, nextRow, nextCol);
        }

        for(int digit=1; digit<=9; digit++){
            if(isSafe(sudoku, row, col, digit)){
                sudoku[row][col] = digit;
                if(sudokuSolver(sudoku, nextRow, nextCol)){ // Solution Exists
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    public static void printlnSudoku(int sudoku[][]){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
