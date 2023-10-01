import java.util.Arrays;

public class Sudoku {
    public static void main(String[] args) {
        int[][] board ={ {0,2,9,0,0,3,0,0,5},
                {5,0,7,0,0,0,0,9,0},
                {6,0,0,0,0,9,4,2,0},
                {3,0,2,0,0,4,0,0,0},
                {0,0,5,0,3,0,7,0,0},
                {0,0,0,5,0,0,6,0,2},
                {0,9,8,4,0,0,0,0,3},
                {0,3,0,0,0,0,1,0,6},
                {2,0,0,3,0,0,9,4,0}
        };
        printsudoku(board);
    }

    public static void printsudoku(int[][] board) {
        if (sudoku(board, 0, 0)) {
            System.out.println("solution exits");
            for (int[] arr : board) {
                System.out.println(Arrays.toString(arr));
            }
        }
        else {
            System.out.println("solution does not exists");
        }
    }

    public static boolean sudoku(int[][] board, int row, int col){
        // base condition
        if (row == 9) {
            return true;
        }

        // recursion
        int nextRow = row, nextCol = col+1;
        if (col+1 == 9) {
            nextRow = row+1;
            nextCol = 0;
        }
        if (board[row][col] != 0) {
            return sudoku(board, nextRow, nextCol);
        }
        for (int digit = 1; digit <= 9; digit++) {
            if (isSafe(digit, row, col, board)) {
                board[row][col] = digit;
                if (sudoku(board, nextRow, nextCol)){
                    return true;
                }
                board[row][col] = 0; // backtracking step
            }
        }
        return false;
    }

    public static boolean isSafe(int digit, int row, int col, int[][] board) {
        // checking if the current coloum is safe or not
        for (int i = 0; i < 9 ; i++) {
            if (board[i][col] == digit) {
                return false;
            }
        }
        // checking if current row is safe or not
        for (int j = 0; j < 9 ; j++) {
            if (board[row][j] == digit) {
                return false;
            }
        }
        // checking if the grid is safe or not
        // starting row and starting coloum is required
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i <sr+3 ; i++) {
            for (int j = sc; j <sc+3 ; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }
}
