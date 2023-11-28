import java.util.Scanner;

public class NQueensSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the chessboard (N): ");
        int n = scanner.nextInt();
        scanner.close();

        int[] queens = new int[n];
        int solutionsCount = solveNQueens(queens, 0, n);

        System.out.println("Number of solutions: " + solutionsCount);
    }

    // Solves the N-Queens problem using backtracking and returns the number of solutions
    private static int solveNQueens(int[] queens, int row, int n) {
        if (row == n) {
            return 1;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (isSafe(queens, row, i)) {
                queens[row] = i;
                count += solveNQueens(queens, row + 1, n);
            }
        }

        return count;
    }

    // Checks if placing a queen at a given position is safe
    private static boolean isSafe(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }
}
