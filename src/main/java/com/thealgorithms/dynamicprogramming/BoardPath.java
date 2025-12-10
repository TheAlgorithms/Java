package com.thealgorithms.dynamicprogramming;

public final class BoardPath {
    private BoardPath() {
    }

    /**
     * Recursive solution without memoization
     *
     * @param start - the current position
     * @param end   - the target position
     * @return the number of ways to reach the end from the start
     */
    public static int bpR(int start, int end) {
        if (start == end) {
            return 1;
        } else if (start > end) {
            return 0;
        }
        int count = 0;
        for (int dice = 1; dice <= 6; dice++) {
            count += bpR(start + dice, end);
        }
        return count;
    }

    /**
     * Recursive solution with memoization
     *
     * @param curr - the current position
     * @param end  - the target position
     * @param strg - memoization array
     * @return the number of ways to reach the end from the start
     */
    public static int bpRS(int curr, int end, int[] strg) {
        if (curr == end) {
            return 1;
        } else if (curr > end) {
            return 0;
        }
        if (strg[curr] != 0) {
            return strg[curr];
        }
        int count = 0;
        for (int dice = 1; dice <= 6; dice++) {
            count += bpRS(curr + dice, end, strg);
        }
        strg[curr] = count;
        return count;
    }

    /**
     * Iterative solution with tabulation
     *
     * @param curr - the current position (always starts from 0)
     * @param end  - the target position
     * @param strg - memoization array
     * @return the number of ways to reach the end from the start
     */
    public static int bpIS(int curr, int end, int[] strg) {
        strg[end] = 1;
        for (int i = end - 1; i >= 0; i--) {
            int count = 0;
            for (int dice = 1; dice <= 6 && dice + i <= end; dice++) {
                count += strg[i + dice];
            }
            strg[i] = count;
        }
        return strg[curr];
    }
}
