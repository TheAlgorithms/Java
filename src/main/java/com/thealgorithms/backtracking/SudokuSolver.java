package com.thealgorithms.backtracking;


// [Sudoku Solving Algorithms] (https://en.wikipedia.org/wiki/Sudoku_solving_algorithms)

/*
Write a program to solve a Sudoku puzzle by filling the empty cells which are denoted by 0.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
*/


public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board =  { {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0} };
        if (solve(board))
        {
            display(board);
            System.out.println();
        }
        else
        {
            System.out.println("Can not solve sudoku");
        }
    }

    static boolean solve(int[][] board ) {
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;

        // replacing r,c from arguments
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == 0)
                {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            //if u found some empty element in a row then break
            if(emptyLeft==false)
            {
                break;
            }
        }

        // as no empty item is found sudoku is solved
        if(emptyLeft==true)
        {
            return true;
        }

        //backtrack
        for (int number = 1; number <= n; number++) {
            if(isSafe(board, row, col, number))
            {
                board[row][col] = number;
                if (solve(board))
                {

                    return true;
                }
                else {
                    //backtrack
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    static boolean isSafe(int[][] board, int row, int col, int num)
    {
        //check the row
        for (int i = 0; i < board.length; i++) {
            //check if no is allready in row or not
            if(board[row][i]==num)
            {
                return false;
            }
        }

        //check the col
        for (int i = 0; i < board.length; i++) {
            //check if no is allready in col or not
            if(board[i][col]==num)
            {
                return false;
            }
        }

        //for the square matrix
        int sqrt = (int)(Math.sqrt(board.length));
        int rowStart = row - row % sqrt;
        int  colStart = col - col % sqrt;

        for (int r = rowStart; r < rowStart + sqrt ; r++) {
            for (int c = colStart; c < colStart + sqrt ; c++) {
                if(board[r][c]==num)
                {
                    return false;
                }
            }
        }
        return true;
    }

    static void display(int[][] board)
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
