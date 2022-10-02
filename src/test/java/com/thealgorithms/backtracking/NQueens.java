// https://en.wikipedia.org/wiki/Eight_queens_puzzle
// 1. Given a number n, the size of a chess board.
// 2. Place n number of queens in the n * n cells of board such that no queen can kill another.
// Note - Queens kill at distance in all 8 directions

package com.thealgorithms.backtracking;
import java.util.*;
public class NQueens {
    public static boolean isSafe(int [][] chess, int r, int c){
        for(int i=r-1, j=c+1; i>=0 && j<chess.length ;i--,j++){
            if (chess[i][j] == 1){
               return false; 
            }
        }
        for(int i = r-1, j=c; i>=0;i--){
            if (chess[i][j] == 1){
               return false; 
            }
        }
        for(int i = r-1, j=c-1; i>=0 && j>=0; i--,j--){
            if (chess[i][j] == 1){
               return false; 
            }
        }
        
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        scn.close();
        int [][] chess = new int[n][n];
        printNQueens(chess,"",0);
    }

    public static void printNQueens(int[][] chess, String qsf, int row) {
        if (row == chess.length){
            System.out.println(qsf+".");
            return;
        }
        for(int j=0; j<chess[0].length; j++){
            if (isSafe(chess,row,j)){
                chess[row][j] = 1;
                printNQueens(chess, qsf + row + '-' + j + ", "  , row+1);
                chess[row][j] = 0;
            }
        }
        
    }
}
