import java.util.Scanner;

public class NQueensProblem {
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n:");
		int n = sc.nextInt();
		boolean b[][] = new boolean[n][n];
		System.out.println("***********************");
		System.out.println("The number of ways " + n + " queens can be arranged on a " + n + "X" + n + " board: "+ countNQueens(b, 0));
		System.out.println("***********************");
		System.out.println("These arrangements are:");
		displayNQueens(b, 0, "");
	}

	public static int countNQueens(boolean board[][], int row) {
		if (row == board.length)
			return 1;
		int count = 0;
		for (int col = 0; col < board[row].length; col++) {
			if (isItSafe(board, row, col)) {
				board[row][col] = true;
				count += countNQueens(board, row + 1);
				board[row][col] = false;
			}
		}
		return count;
	}

	public static boolean isItSafe(boolean b[][], int r, int c) {

		for (int i = r; i >= 0; i--) {
			if (b[i][c] == true)
				return false;
		}
		for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
			if (b[i][j] == true)
				return false;
		}
		for (int i = r, j = c; i >= 0 && j < b[r].length; i--, j++) {
			if (b[i][j] == true)
				return false;
		}

		return true;
	}

	public static void displayNQueens(boolean board[][], int row, String ans) {
		if (row == board.length) {
			System.out.println(ans+";");
			return;
		}

		for (int col = 0; col < board[row].length; col++) {
			if (isItSafe(board, row, col)) {
				board[row][col] = true;
				displayNQueens(board, row + 1, ans + "{" + (row + 1) + "-" + (col + 1) + "},");
				board[row][col] = false;

			}
		}

	}

}
