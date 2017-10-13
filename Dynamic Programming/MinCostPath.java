import java.util.Scanner;

public class MinCostPath {

	public static int minCostPathDP(int arr[][],int row, int column) {
		int m = row;
		int n = column;
		int storage[][] = new int[m][n];

		storage[m - 1][n - 1] = arr[m - 1][n - 1];
		// Last Row
		for (int j = n - 2; j >= 0; j--) {
			storage[m - 1][j] = storage[m - 1][j + 1] + arr[m - 1][j];
		}
		// Last Column
		for (int i = m - 2; i >= 0; i--) {
			storage[i][n - 1] = storage[i + 1][n - 1] + arr[i][n - 1];
		}

		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				storage[i][j] = arr[i][j]
						+ Math.min(storage[i][j + 1], Math.min(
								storage[i + 1][j + 1], storage[i + 1][j]));
			}
		}
		return storage[0][0]+arr[row][column];
	}

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int rows = s.nextInt();
		int columns = s.nextInt();
		int arr[][] = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				arr[i][j] = s.nextInt();
			}
		}
		int row = s.nextInt();
		int column = s.nextInt();
		System.out.println(minCostPathDP(arr,row,column));
		s.close();
	}
}

