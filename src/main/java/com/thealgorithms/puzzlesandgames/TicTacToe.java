package com.thealgorithms.puzzlesandgames;

import java.util.Scanner;

/**
 * The {@code TicTacToe} class provides a console-based 2-player Tic-Tac-Toe game.
 * Players take turns marking a 3x3 grid until one wins or the game ends in a tie.
 *
 * <p>
 * This implementation demonstrates basic programming concepts in Java:
 * 1. 2D arrays for the game board
 * 2. Loops and conditionals for game logic
 * 3. Input handling with {@link Scanner}
 * </p>
 *
 * <p>
 * The {@code play} method runs the game loop, {@code printBoard} displays
 * the board, and {@code checkWin} determines if a player has won.
 * </p>
 *
 * <p>
 * Time Complexity: O(1) for each move (constant operations per turn)  
 * Space Complexity: O(1) additional space besides the board
 * </p>
 */
/**
 * TicTacToe.java
 * 
 * A console-based 2-player Tic-Tac-Toe game.
 */
public class TicTacToe {
    static char[][] board = {{'1','2','3'}, {'4','5','6'}, {'7','8','9'}};
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';
        int moves = 0;
        boolean won = false;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (moves < 9 && !won) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", enter a position (1-9): ");
            int pos = scanner.nextInt();
            int row = (pos-1)/3;
            int col = (pos-1)%3;

            if (board[row][col] != 'X' && board[row][col] != 'O') {
                board[row][col] = currentPlayer;
                moves++;
                won = checkWin(currentPlayer);
                if (!won) currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Position already taken. Try again.");
            }
        }

        printBoard();
        if (won) System.out.println("Player " + currentPlayer + " wins!");
        else System.out.println("It's a tie!");

        scanner.close();
    }

    static void printBoard() {
        System.out.println();
        for (char[] row : board) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
        System.out.println();
    }

    static boolean checkWin(char player) {
        // Rows, columns, diagonals
        for (int i=0; i<3; i++)
            if ((board[i][0]==player && board[i][1]==player && board[i][2]==player) ||
                (board[0][i]==player && board[1][i]==player && board[2][i]==player))
                return true;

        return (board[0][0]==player && board[1][1]==player && board[2][2]==player) ||
               (board[0][2]==player && board[1][1]==player && board[2][0]==player);
    }
}