package com.thealgorithms.puzzlesandgames;

/**
 * Tic-Tac-Toe on a 3x3 board, with a perfect-play move finder based on the
 * negamax form of the minimax algorithm.
 *
 * <p>
 * Players alternate placing marks; the first to fill a row, column or
 * diagonal wins. If the board fills with no winner the game is a draw.
 * </p>
 *
 * <p>
 * Best-move search: Time Complexity O(b!) where b is the number of empty cells
 * (at most 9! = 362880 positions). Space Complexity: O(b) recursion depth.
 * </p>
 *
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Tic-tac-toe">Tic-tac-toe on Wikipedia</a>
 * and <a href="https://en.wikipedia.org/wiki/Minimax">Minimax on Wikipedia</a>.
 * </p>
 */
public final class TicTacToe {

    public static final char EMPTY = ' ';
    public static final char X = 'X';
    public static final char O = 'O';
    private static final int SIZE = 3;

    private final char[][] board = new char[SIZE][SIZE];
    private char currentPlayer = X;

    public TicTacToe() {
        for (char[] row : board) {
            java.util.Arrays.fill(row, EMPTY);
        }
    }

    /**
     * Places the current player's mark and passes the turn.
     *
     * @param row row index, 0-2
     * @param col column index, 0-2
     * @throws IllegalStateException    if the game is already over
     * @throws IllegalArgumentException if the cell is outside the board or occupied
     */
    public void placeMark(int row, int col) {
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over");
        }
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Cell is outside the board");
        }
        if (board[row][col] != EMPTY) {
            throw new IllegalArgumentException("Cell is already occupied");
        }
        board[row][col] = currentPlayer;
        currentPlayer = opponent(currentPlayer);
    }

    public char getWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (isLine(board[i][0], board[i][1], board[i][2])) {
                return board[i][0];
            }
            if (isLine(board[0][i], board[1][i], board[2][i])) {
                return board[0][i];
            }
        }
        if (isLine(board[0][0], board[1][1], board[2][2])) {
            return board[0][0];
        }
        if (isLine(board[0][2], board[1][1], board[2][0])) {
            return board[0][2];
        }
        return EMPTY;
    }

    /** @return true if the board is full and nobody has won */
    public boolean isDraw() {
        return getWinner() == EMPTY && isFull();
    }

    /** @return true if someone has won or the board is full */
    public boolean isGameOver() {
        return getWinner() != EMPTY || isFull();
    }

    /** @return the mark of the player whose turn it is */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /** @return a copy of the board, indexed {@code [row][col]} */
    public char[][] getBoard() {
        char[][] copy = new char[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    /**
     * Finds an optimal move for the current player using negamax. Among
     * equally good outcomes, quicker wins and slower losses are preferred.
     *
     * @return {@code {row, col}} of the best move
     * @throws IllegalStateException if the game is already over
     */
    public int[] bestMove() {
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over");
        }
        int bestScore = Integer.MIN_VALUE;
        int[] best = {-1, -1};
        char player = currentPlayer;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != EMPTY) {
                    continue;
                }
                board[row][col] = player;
                int score = -negamax(opponent(player));
                board[row][col] = EMPTY;
                if (score > bestScore) {
                    bestScore = score;
                    best = new int[] {row, col};
                }
            }
        }
        return best;
    }

    /** Score of the position from the point of view of {@code player}, who is to move. */
    private int negamax(char player) {
        if (getWinner() != EMPTY) {
            // The previous mover just won, so the player to move has lost.
            return -(countEmpty() + 1);
        }
        if (isFull()) {
            return 0;
        }
        int best = Integer.MIN_VALUE;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    board[row][col] = player;
                    best = Math.max(best, -negamax(opponent(player)));
                    board[row][col] = EMPTY;
                }
            }
        }
        return best;
    }

    private int countEmpty() {
        int count = 0;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == EMPTY) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isFull() {
        return countEmpty() == 0;
    }

    private static char opponent(char player) {
        return player == X ? O : X;
    }

    private static boolean isLine(char a, char b, char c) {
        return a != EMPTY && a == b && b == c;
    }
}
