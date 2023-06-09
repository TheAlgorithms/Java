package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem statement: Given a N x N chess board. Return all arrangements in
 * which N queens can be placed on the board such no two queens attack each
 * other. Ex. N = 6 Solution= There are 4 possible ways Arrangement: 1 ".Q....",
 * "...Q..", ".....Q", "Q.....", "..Q...", "....Q."
 * <p>
 * Arrangement: 2 "..Q...", ".....Q", ".Q....", "....Q.", "Q.....", "...Q.."
 * <p>
 * Arrangement: 3 "...Q..", "Q.....", "....Q.", ".Q....", ".....Q", "..Q..."
 * <p>
 * Arrangement: 4 "....Q.", "..Q...", "Q.....", ".....Q", "...Q..", ".Q...."
 *
 * Solution: Brute Force approach:
 *
 * Generate all possible arrangement to place N queens on N*N board. Check each
 * board if queens are placed safely. If it is safe, include arrangement in
 * solution set. Otherwise ignore it
 *
 * Optimized solution: This can be solved using backtracking in below steps
 *
 * Start with first column and place queen on first row Try placing queen in a
 * row on second column If placing second queen in second column attacks any of
 * the previous queens, change the row in second column otherwise move to next
 * column and try to place next queen In case if there is no rows where a queen
 * can be placed such that it doesn't attack previous queens, then go back to
 * previous column and change row of previous queen. Keep doing this until last
 * queen is not placed safely. If there is no such way then return an empty list
 * as solution
 */
public class NQueens {

    public static void main(String[] args) {
        placeQueens(1);
        placeQueens(2);
        placeQueens(3);
        placeQueens(4);
        placeQueens(5);
        placeQueens(6);
    }

    public static void placeQueens(final int queens) {
        List<List<String>> arrangements = new ArrayList<List<String>>();
        getSolution(queens, arrangements, new int[queens], 0);
        if (arrangements.isEmpty()) {
            System.out.println("There is no way to place " + queens + " queens on board of size " + queens + "x" + queens);
        } else {
            System.out.println("Arrangement for placing " + queens + " queens");
        }
        arrangements.forEach(arrangement -> {
            arrangement.forEach(row -> System.out.println(row));
            System.out.println();
        });
    }

    /**
     * This is backtracking function which tries to place queen recursively
     *
     * @param boardSize: size of chess board
     * @param solutions: this holds all possible arrangements
     * @param columns: columns[i] = rowId where queen is placed in ith column.
     * @param columnIndex: This is the column in which queen is being placed
     */
    private static void getSolution(int boardSize, List<List<String>> solutions, int[] columns, int columnIndex) {
        if (columnIndex == boardSize) {
            // this means that all queens have been placed
            List<String> sol = new ArrayList<String>();
            for (int i = 0; i < boardSize; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < boardSize; j++) {
                    sb.append(j == columns[i] ? "Q" : ".");
                }
                sol.add(sb.toString());
            }
            solutions.add(sol);
            return;
        }

        // This loop tries to place queen in a row one by one
        for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
            columns[columnIndex] = rowIndex;
            if (isPlacedCorrectly(columns, rowIndex, columnIndex)) {
                // If queen is placed successfully at rowIndex in column=columnIndex then try
                // placing queen in next column
                getSolution(boardSize, solutions, columns, columnIndex + 1);
            }
        }
    }

    /**
     * This function checks if queen can be placed at row = rowIndex in column =
     * columnIndex safely
     *
     * @param columns: columns[i] = rowId where queen is placed in ith column.
     * @param rowIndex: row in which queen has to be placed
     * @param columnIndex: column in which queen is being placed
     * @return true: if queen can be placed safely false: otherwise
     */
    private static boolean isPlacedCorrectly(int[] columns, int rowIndex, int columnIndex) {
        for (int i = 0; i < columnIndex; i++) {
            int diff = Math.abs(columns[i] - rowIndex);
            if (diff == 0 || columnIndex - i == diff) {
                return false;
            }
        }
        return true;
    }
}
