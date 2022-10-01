package com.thealgorithms.AI;

import java.util.Scanner;

public class TicTacToe {

    static Scanner in = new Scanner(System.in);

    public static class moves {
        int row;
        int col;
    }

    static char Comp = 'x';
    static char Human = 'o';

    static boolean movesLeft(char[][] B) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (B[i][j] == '_') {
                    return true;
                }
            }

        return false;
    }

    public static int ELine(int R1, int C1, int R2, int C2, int R3, int C3, char[][] B) {
        int score = 0;

        if (B[R1][C1] == Comp) {
            score = 1;
        } else if (B[R1][C1] == Human) {
            score = -1;
        }

        // Second cell
        if (B[R2][C2] == Comp) {
            if (score == 1) {
                score = 10;
            } else if (score == -1) {
                return 0;
            } else {
                score = 1;
            }
        } else if (B[R2][C2] == Human) {
            if (score == -1) {
                score = -10;
            } else if (score == 1) {
                return 0;
            } else {
                score = -1;
            }
        }
        // Third cell
        if (B[R3][C3] == Comp) {
            if (score > 0) {
                score *= 10;
            } else if (score < 0) {
                return 0;
            } else {
                score = 1;
            }
        } else if (B[R3][C3] == Human) {
            if (score < 0) {
                score *= 10;
            } else if (score > 1) {
                return 0;
            } else {
                score = -1;
            }
        }

        return score;
    }

    public static int evaluate(char[][] B) {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += ELine(0, 0, 0, 1, 0, 2, B); // row 0
        score += ELine(1, 0, 1, 1, 1, 2, B); // row 1
        score += ELine(2, 0, 2, 1, 2, 2, B); // row 2
        score += ELine(0, 0, 1, 0, 2, 0, B); // col 0
        score += ELine(0, 1, 1, 1, 2, 1, B); // col 1
        score += ELine(0, 2, 1, 2, 2, 2, B); // col 2
        score += ELine(0, 0, 1, 1, 2, 2, B); // dia 1
        score += ELine(0, 2, 1, 1, 2, 0, B); // dia 2
        return score;
    }

    static int minimax(char[][] b, int depth, boolean isMax) {
        int score = evaluate(b);
        if (depth == 1) {
            printBoard(b);
            System.out.printf("Board Score is: %d", score);
            return score;
        }

        if (score >= 80 || score <= -80) {
            return score;
        }

        if (!movesLeft(b)) {
            return 0;
        }

        if (isMax) {
            int bestValue = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (b[i][j] == '_') {
                        b[i][j] = Comp;
                        bestValue = Math.max(bestValue, minimax(b, depth + 1, !isMax));
                        // show board and its score for depth =1 and depth = 2
                        b[i][j] = '_';
                    }
                }
                return bestValue;
            }
        } else {
            int bestValue = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (b[i][j] == '_') {
                        b[i][j] = Human;
                        bestValue = Math.min(bestValue, minimax(b, depth + 1, !isMax));
                        // shows board and its score for depth =1 and depth = 2
                        b[i][j] = '_';
                    }
                }
            }
            return bestValue;
        }
        return -1;
    }

    static moves bestMove(char[][] b) {
        int bestValue = -1000;
        moves bMove = new moves();
        bMove.row = -1;
        bMove.col = -1;
        System.out.println("Combinations");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == '_') {
                    b[i][j] = Comp;
                    int value = minimax(b, 0, false);
                    System.out.printf("\nNode score is: %d\n", value);
                    if (value > bestValue) {
                        bMove.row = i;
                        bMove.col = j;
                        bestValue = value;
                    }
                    b[i][j] = '_';
                }
            }
        }
        System.out.printf("The best move is : %d", bMove.row, " %d\n", bMove.col);
        return bMove;
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void playerInput(char[][] board) {
        boolean userStep = true;
        while (userStep) {
            System.out.print("ROW: ");
            int row = in.nextInt();
            System.out.print("COl: ");
            int col = in.nextInt();

            if (row > 2 || col > 2) {
                System.out.println("Invalid Input !!!");
                continue;
            }

            if (board[row][col] == '_') {
                board[row][col] = Human;
                userStep = false;
            } else {
                System.out.println("Position is taken..Try again :)");
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                { '_', '_', '_' },
                { '_', '_', '_' },
                { '_', '_', '_' }
        };
        printBoard(board);

        boolean humanTurn = false;

        int score = evaluate(board);
        System.out.printf("Score: %d\n", score);

        while (score < 80 && score > -80 && movesLeft(board)) {
            if (humanTurn) {
                playerInput(board);
                humanTurn = false;
                score = evaluate(board);
                if (score <= 80) {
                    System.out.printf("\nBoard: \n");
                    printBoard(board);
                    System.out.printf("Board score: %d\n", score, "User is winner");
                }
            } else {
                moves bMove = bestMove(board);
                board[bMove.row][bMove.col] = Comp;
                humanTurn = true;

                score = evaluate(board);
                if (score >= 80) {
                    System.out.printf("\nBoard: ");
                    printBoard(board);
                    System.out.printf("Board score: %d\n", score, "Computer is winner");
                }
            }
            score = evaluate(board);
            System.out.printf("\nBoard: ");
            printBoard(board);
            System.out.printf("Board score: %d\n", score);
        }
        System.out.println("Game Over");
    }
}
