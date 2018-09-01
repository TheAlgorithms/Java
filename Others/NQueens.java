/**
 * Program to implement N Queen problem of placing N queens on an NxN chessboard so that no two queens attack each other
 * 
 * @author nikhilbarar
 */

public class NQueens {
	public static void main(String[] args) {
		NQueens nQueens = new NQueens();
		nQueens.placeQueens(4); // Lets take example of 4*4
	}

	// Function to place and find the correct positions of the queens and print the board
	private void placeQueens(int gridSize) {
		// If Grid is 1*1 or 2*2 or 3*3 then solution is not possible as,
		// In 1*1 or 2*2 grid, Queen placed in 1st row at any position will attack queen
		// placed at all the positions in row 2.
		// In 3*3 grid, Queen placed in 1st row and 2nd row for all combinations
		// position will attack queen placed at all the positions in row 3.
		if (gridSize < 4) {
			System.out.println("No Solution available");
		} else {
			int[][] board = new int[gridSize][gridSize];
			placeAllQueens(board, 0);
			printBoard(board);
		}
	}

	// Function to place all queens on the board and find the correct positions
	private boolean placeAllQueens(int board[][], int row) {
		if (row >= board.length) {
			return true;
		}

		boolean isAllQueensPlaced = false;
		for (int j = 0; j < board.length; j++) {
			if (isSafe(board, row, j)) {
				board[row][j] = 1;
				isAllQueensPlaced = placeAllQueens(board, row + 1);
			}
			if (isAllQueensPlaced) {
				break;
			} else {
				board[row][j] = 0;
			}
		}
		return isAllQueensPlaced;
	}

	// Function to check if the position is safe
	private boolean isSafe(int board[][], int row, int col) {
		// Check Left Upper Diagonal
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		// Check Right Upper Diagonal
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		// Check in same Column
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col] == 1) {
				return false;
			}
		}

		return true;
	}

	// Function to print the chess board
	private void printBoard(int[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col] == 1) {
					System.out.print("Q ");
				} else {
					System.out.print("_ ");
				}
			}
			System.out.println();
		}
		System.out.println("");
	}
}