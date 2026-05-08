package com.thealgorithms.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem statement: Given a N x N chess board. Return all arrangements in
 * which N queens can be placed on the board such no two queens attack each
 * other. Ex. N = 6 Solution= There are 4 possible ways Arrangement: 1 ".Q....",
 * "...Q..", ".....Q", "Q.....", "..Q...", "....Q."
 *
 * Arrangement: 2 "..Q...", ".....Q", ".Q....", "....Q.", "Q.....", "...Q.."
 *
 * Arrangement: 3 "...Q..", "Q.....", "....Q.", ".Q....", ".....Q", "..Q..."
 *
 * Arrangement: 4 "....Q.", "..Q...", "Q.....", ".....Q", "...Q..", ".Q...."
 *
 * Solution: Brute Force approach:
 *
 * Generate all possible arrangement to place N queens on N*N board. Check each
 * board if queens are placed safely. If it is safe, include arrangement in
 * solution set. Otherwise, ignore it
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

/*
 * Time Complexity: O(N!)
 * space Complexity: O(N)
 */
public final class NQueens {

    // Store occupied rows for constant time safety check
    private static final Set<Integer> OCROWS = new HashSet<>();

    // Store occupied main diagonals (row - column)
    private static final Set<Integer> OCDIAG = new HashSet<>();

    // Store occupied anti-diagonals (row + columns)
    private static final Set<Integer> OCANTIDIAG = new HashSet<>();

    private NQueens() {
    }

    public static List<List<String>> getNQueensArrangements(int queens) {
        List<List<String>> arrangements = new ArrayList<>();
        getSolution(queens, arrangements, new int[queens], 0);
        return arrangements;
    }

    public static void placeQueens(final int queens) {
        List<List<String>> arrangements = new ArrayList<>();
        getSolution(queens, arrangements, new int[queens], 0);
        if (arrangements.isEmpty()) {
            System.out.println(" no way to place " + queens + " queens on board of size " + queens + "x" + queens);
        } else {
            System.out.println("Arrangement for placing " + queens + " queens");
        }
        for (List<String> arrangement : arrangements) {
            arrangement.forEach(System.out::println);
            System.out.println();
        }
    }

    /**
     * This is backtracking function which tries to place queen recursively
     *
     * @param boardSize:   size of chess board
     * @param solutions:   this holds all possible arrangements
     * @param columns:     columns[i] = rowId where queen is placed in ith column.
     * @param columnIndex: This is the column in which queen is being placed
     */
    private static void getSolution(int boardSize, List<List<String>> solutions, int[] columns, int columnIndex) {
        if (columnIndex == boardSize) {
            // this means that all queens have been placed
            List<String> sol = new ArrayList<>();
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

            // Skip current position if row or diagonal is already occupied
            boolean isROp = OCROWS.contains(rowIndex);

            boolean isDOp = OCDIAG.contains(rowIndex - columnIndex) || OCANTIDIAG.contains(rowIndex + columnIndex);

            if (isROp || isDOp) {
                continue;
            }

            // Mark current row and diagonal as occupied
            OCROWS.add(rowIndex);
            OCDIAG.add(rowIndex - columnIndex);
            OCANTIDIAG.add(rowIndex + columnIndex);

            // Move to the next column after placing current queen
            getSolution(boardSize, solutions, columns, columnIndex + 1);

            // Backtrack by removing current queen

            OCROWS.remove(rowIndex);
            OCDIAG.remove(rowIndex - columnIndex);
            OCANTIDIAG.remove(rowIndex + columnIndex);
        }
    }
}
