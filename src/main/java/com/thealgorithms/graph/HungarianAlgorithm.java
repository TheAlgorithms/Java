package com.thealgorithms.graph;

import java.util.Arrays;

/**
 * Hungarian algorithm (a.k.a. Kuhn–Munkres) for the Assignment Problem.
 *
 * <p>Given an n x m cost matrix (n tasks, m workers), finds a minimum-cost
 * one-to-one assignment. If the matrix is rectangular, the algorithm pads to a
 * square internally. Costs must be finite non-negative integers.
 *
 * <p>Time complexity: O(n^3) with n = max(rows, cols).
 *
 * <p>API returns the assignment as an array where {@code assignment[i]} is the
 * column chosen for row i (or -1 if unassigned when rows != cols), and a total
 * minimal cost.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Hungarian_algorithm">Wikipedia: Hungarian algorithm</a>
 */
public final class HungarianAlgorithm {

    private HungarianAlgorithm() {
    }

    /** Result holder for the Hungarian algorithm. */
    public static final class Result {
        public final int[] assignment; // assignment[row] = col or -1
        public final int minCost;

        public Result(int[] assignment, int minCost) {
            this.assignment = assignment;
            this.minCost = minCost;
        }
    }

    /**
     * Solves the assignment problem for a non-negative cost matrix.
     *
     * @param cost an r x c matrix of non-negative costs
     * @return Result with row-to-column assignment and minimal total cost
     * @throws IllegalArgumentException for null/empty or negative costs
     */
    public static Result solve(int[][] cost) {
        validate(cost);
        int rows = cost.length;
        int cols = cost[0].length;
        int n = Math.max(rows, cols);

        // Build square matrix with padding 0 for missing cells
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            if (i < rows) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = (j < cols) ? cost[i][j] : 0;
                }
            } else {
                Arrays.fill(a[i], 0);
            }
        }

        // Potentials and matching arrays
        int[] u = new int[n + 1];
        int[] v = new int[n + 1];
        int[] p = new int[n + 1];
        int[] way = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[0] = i;
            int j0 = 0;
            int[] minv = new int[n + 1];
            boolean[] used = new boolean[n + 1];
            Arrays.fill(minv, Integer.MAX_VALUE);
            Arrays.fill(used, false);
            do {
                used[j0] = true;
                int i0 = p[j0];
                int delta = Integer.MAX_VALUE;
                int j1 = 0;
                for (int j = 1; j <= n; j++) {
                    if (!used[j]) {
                        int cur = a[i0 - 1][j - 1] - u[i0] - v[j];
                        if (cur < minv[j]) {
                            minv[j] = cur;
                            way[j] = j0;
                        }
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j;
                        }
                    }
                }
                for (int j = 0; j <= n; j++) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }
                j0 = j1;
            } while (p[j0] != 0);
            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        int[] matchColForRow = new int[n];
        Arrays.fill(matchColForRow, -1);
        for (int j = 1; j <= n; j++) {
            if (p[j] != 0) {
                matchColForRow[p[j] - 1] = j - 1;
            }
        }

        // Build assignment for original rows only, ignore padded rows
        int[] assignment = new int[rows];
        Arrays.fill(assignment, -1);
        int total = 0;
        for (int i = 0; i < rows; i++) {
            int j = matchColForRow[i];
            if (j >= 0 && j < cols) {
                assignment[i] = j;
                total += cost[i][j];
            }
        }
        return new Result(assignment, total);
    }

    private static void validate(int[][] cost) {
        if (cost == null || cost.length == 0) {
            throw new IllegalArgumentException("Cost matrix must not be null or empty");
        }
        int c = cost[0].length;
        if (c == 0) {
            throw new IllegalArgumentException("Cost matrix must have at least 1 column");
        }
        for (int i = 0; i < cost.length; i++) {
            if (cost[i] == null || cost[i].length != c) {
                throw new IllegalArgumentException("Cost matrix must be rectangular with equal row lengths");
            }
            for (int j = 0; j < c; j++) {
                if (cost[i][j] < 0) {
                    throw new IllegalArgumentException("Costs must be non-negative");
                }
            }
        }
    }
}
