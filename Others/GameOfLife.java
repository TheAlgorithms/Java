package Others;

import java.util.Random;

/**
 * a simple implementation of Conway's Game of Life
 * (https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
 * <p>
 * Rules:
 * 1. Any live cell with two or three live neighbours survives.
 * 2. Any dead cell with three live neighbours becomes a live cell.
 * 3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
 */
public class GameOfLife {

    public static void main(String[] args) {
        simulateGame(15, 10);
    }

    /**
     * @param boardSize   the length of the board's sides
     * @param generations the number of generations that should be simulated
     */
    public static void simulateGame(int boardSize, int generations) {
        // Initialize the board
        boolean[][] board = new boolean[boardSize][boardSize];
        // Populate it with random cells
        Random random = new Random();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (random.nextInt(3) == 0) {
                    board[i][j] = true;
                }
            }
        }
        System.out.println("Original generation: ");
        displayBoard(board);

        for (int generation = 1; generation <= generations; generation++) {
            board = applyRules(board);
            System.out.println("Generation " + generation + ":");
            displayBoard(board);
        }
    }

    private static boolean[][] applyRules(boolean[][] board) {
        int size = board.length;
        boolean[][] newBoard = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean cellStatus = board[i][j];
                // Calculate the number of alive neighbour cells
                int neighbourCount = 0;
                for (int x = i - 1; x < size && x <= i + 1; x++) {
                    for (int y = j - 1; y < size && y <= j + 1; y++) {
                        if (x < 0 || y < 0) {
                            continue;
                        }
                        if (x == i && y == j) {
                            // We don't want to count the cell we're currently handling.
                            continue;
                        }
                        if (board[x][y]) {
                            neighbourCount += 1;
                        }
                    }
                }
                // Any live cell with two or three live neighbours survives.
                if (cellStatus && (2 <= neighbourCount && neighbourCount <= 3)) {
                    newBoard[i][j] = true;
                    continue;
                }
                // Any dead cell with three live neighbours becomes a live cell.
                if (!cellStatus && neighbourCount == 3) {
                    newBoard[i][j] = true;
                    continue;
                }
                // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                newBoard[i][j] = false;
            }
        }
        return newBoard;
    }

    private static void displayBoard(boolean[][] board) {
        for (boolean[] rows : board) {
            for (boolean cell : rows) {
                if (cell) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
