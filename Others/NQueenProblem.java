package Others;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueenProblem {
    public static List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        solve(board, 0, res);
        return res;
    }

    public static void solve(char[][] board, int row, List<List<String>> res) {
        if(row == board.length) {
            List<String> sol = new ArrayList<>();

            for(int i=0; i<board.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j=0; j< board[0].length; j++) {
                    sb.append(board[i][j]);
                }
                sol.add(sb.toString());
            }
            res.add(sol);
            return;
        }

        for(int i=0; i<board[0].length; i++) {
            if(checkValid(board, row, i)) {
                board[row][i] = 'Q';
                solve(board, row+1, res);
                board[row][i] = '.';
            }
        }
    }

    public static boolean checkValid(char[][] board, int row, int col) {
        for(int i=0; i<row; i++) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }
        int k = col, l=col;
        for(int i=row-1; i>=0; i--) {
            if((k-1 >= 0 && board[i][k-1] == 'Q') || (l+1 < board[0].length && board[i][l+1] == 'Q')) {
                return false;
            }
            k = k-1;
            l = l+1;
        }
        return true;
    }
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of N for Queen's problem");
        int n = sc.nextInt();
        System.out.println(solveNQueens(n));
    }
}

