/**
 * Program for Knight Tour problem
 * 
 * @author nikhilbarar
 */
public class KnightsTour {
	private static final int GRIDSIZE = 8;

	/**
	 * A utility function to check if i,j are valid indexes for N*N chessboard
	 * 
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @param sol The Board
	 * @return true if x,y are valid
	 */
	private boolean isSafe(int x, int y, int[][] sol) {
		return (x >= 0 && x < GRIDSIZE && y >= 0 && y < GRIDSIZE && sol[x][y] == -1);
	}

	/**
	 * A utility function to print solution matrix sol[N][N]
	 * 
	 * @param sol The board
	 */
	private void printSolution(int[][] sol) {
		for (int x = 0; x < GRIDSIZE; x++) {
			for (int y = 0; y < GRIDSIZE; y++)
				System.out.print(sol[x][y] + " ");
			System.out.println();
		}
	}

	/**
	 * This function solves the Knight Tour problem using Backtracking. This
	 * function mainly uses solveKTUtil() to solve the problem. It returns false if
	 * no complete tour is possible, otherwise return true and prints the tour.
	 * Please note that there may be more than one solutions, this function prints
	 * one of the feasible solutions.
	 * 
	 * @return true if solution exists
	 */
	private boolean solveKT() {
		int[][] sol = new int[8][8];

		/* Initialization of solution matrix */
		for (int x = 0; x < GRIDSIZE; x++)
			for (int y = 0; y < GRIDSIZE; y++)
				sol[x][y] = -1;

		/*
		 * xMove[] and yMove[] define next move of Knight. xMove[] is for next value of
		 * x coordinate yMove[] is for next value of y coordinate
		 */
		int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };

		// Since the Knight is initially at the first block
		sol[0][0] = 0;

		/*
		 * Start from 0,0 and explore all tours using solveKTUtil()
		 */
		if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
			System.out.println("Solution does not exist");
			return false;
		} else
			printSolution(sol);

		return true;
	}

	/**
	 * A recursive utility function to solve Knight Tour problem
	 * 
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @param movei Current position on board
	 * @param sol The Board
	 * @param xMove Next Moves of knight in X direction
	 * @param yMove Next Moves of knight in Y direction
	 * @return true if solution exists
	 */
	private boolean solveKTUtil(int x, int y, int movei, int[][] sol, int[] xMove, int[] yMove) {
		int k;
		int nextX;
		int nextY;
		if (movei == GRIDSIZE * GRIDSIZE)
			return true;

		/*
		 * Try all next moves from the current coordinate x, y
		 */
		for (k = 0; k < 8; k++) {
			nextX = x + xMove[k];
			nextY = y + yMove[k];
			if (isSafe(nextX, nextY, sol)) {
				sol[nextX][nextY] = movei;
				if (solveKTUtil(nextX, nextY, movei + 1, sol, xMove, yMove))
					return true;
				else
					sol[nextX][nextY] = -1;// backtracking
			}
		}

		return false;
	}

	/**
	 * The main method
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		KnightsTour knightsTour = new KnightsTour();
		knightsTour.solveKT();
	}
}