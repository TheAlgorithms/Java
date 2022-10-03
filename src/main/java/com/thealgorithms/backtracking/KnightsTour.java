package com.thealgorithms.backtracking;

import java.util.*;

/*
    * Problem Statement: -
    
    Given a N*N board with the Knight placed on the first block of an empty board. Moving according to the rules of
    chess knight must visit each square exactly once. Print the order of each cell in which they are visited.

    Example: -

    Input : N = 8

    Output:
        0  59  38  33  30  17   8  63
        37  34  31  60   9  62  29  16
        58   1  36  39  32  27  18   7
        35  48  41  26  61  10  15  28
        42  57   2  49  40  23   6  19
        47  50  45  54  25  20  11  14
        56  43  52   3  22  13  24   5
        51  46  55  44  53   4  21  12

 */
public class KnightsTour {

    private static final int base = 12;
    private static final int[][] moves = {
        { 1, -2 },
        { 2, -1 },
        { 2, 1 },
        { 1, 2 },
        { -1, 2 },
        { -2, 1 },
        { -2, -1 },
        { -1, -2 },
    }; // Possible moves by knight on chess
    private static int[][] grid; // chess grid
    private static int total; // total squares in chess

    public static void main(String[] args) {
        grid = new int[base][base];
        total = (base - 4) * (base - 4);

        for (int r = 0; r < base; r++) {
            for (int c = 0; c < base; c++) {
                if (r < 2 || r > base - 3 || c < 2 || c > base - 3) {
                    grid[r][c] = -1;
                }
            }
        }

        int row = 2 + (int) (Math.random() * (base - 4));
        int col = 2 + (int) (Math.random() * (base - 4));

        grid[row][col] = 1;

        if (solve(row, col, 2)) {
            printResult();
        } else {
            System.out.println("no result");
        }
    }

    // Return True when solvable
    private static boolean solve(int row, int column, int count) {
        if (count > total) {
            return true;
        }

        List<int[]> neighbor = neighbors(row, column);

        if (neighbor.isEmpty() && count != total) {
            return false;
        }

        Collections.sort(
            neighbor,
            new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[2] - b[2];
                }
            }
        );

        for (int[] nb : neighbor) {
            row = nb[0];
            column = nb[1];
            grid[row][column] = count;
            if (
                !orphanDetected(count, row, column) &&
                solve(row, column, count + 1)
            ) {
                return true;
            }
            grid[row][column] = 0;
        }

        return false;
    }

    // Returns List of neighbours
    private static List<int[]> neighbors(int row, int column) {
        List<int[]> neighbour = new ArrayList<>();

        for (int[] m : moves) {
            int x = m[0];
            int y = m[1];
            if (grid[row + y][column + x] == 0) {
                int num = countNeighbors(row + y, column + x);
                neighbour.add(new int[] { row + y, column + x, num });
            }
        }
        return neighbour;
    }

    // Returns the total count of neighbors
    private static int countNeighbors(int row, int column) {
        int num = 0;
        for (int[] m : moves) {
            if (grid[row + m[1]][column + m[0]] == 0) {
                num++;
            }
        }
        return num;
    }

    // Returns true if it is orphan
    private static boolean orphanDetected(int count, int row, int column) {
        if (count < total - 1) {
            List<int[]> neighbor = neighbors(row, column);
            for (int[] nb : neighbor) {
                if (countNeighbors(nb[0], nb[1]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Prints the result grid
    private static void printResult() {
        for (int[] row : grid) {
            for (int i : row) {
                if (i == -1) {
                    continue;
                }
                System.out.printf("%2d ", i);
            }
            System.out.println();
        }
    }
}
