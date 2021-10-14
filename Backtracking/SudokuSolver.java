package challengesBackTracking;

import java.util.Scanner;

public class SudokuSolver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		solve(a, 0, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean solve(int[][] a, int row, int col) {
		if (row == 8 && col == 9) {
			return true;
		}
		if (col == 9) {
			row++;
			col = 0;
		}
		if (a[row][col] != 0) {
			return solve(a, row, col + 1);
		}
		for (int i = 1; i <= 9; i++) {
			if (isSafe(a, row, col, i)) {
				a[row][col] = i;
				if (solve(a, row, col + 1)) {
					return true;
				}
			}
			a[row][col] = 0;
		}
		return false;
	}

	public static boolean isSafe(int[][] a, int row, int col, int i) {
		for (int j = 0; j <= 8; j++) {
			if (a[row][j] == i) {
				return false;
			}
		}
		for (int j = 0; j <= 8; j++) {
			if (a[j][col] == i) {
				return false;
			}
		}
		int sr = row - row % 3;
		int sc = col - col % 3;
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (a[j + sr][k + sc] == i) {
					return false;
				}
			}
		}
		return true;
	}

}
