package com.thealgorithms.datastructures.trees;

/**
 * 2D Segment Tree (Tree of Trees) implementation.
 * This data structure supports point updates and submatrix sum queries
 * in a 2D grid. It achieves this by nesting 1D Segment Trees within a 1D Segment Tree.
 *
 * Time Complexity:
 * - Build/Initialization: O(N * M)
 * - Point Update: O(log N * log M)
 * - Submatrix Query: O(log N * log M)
 *
 * @see <a href="https://cp-algorithms.com/data_structures/segment_tree.html#2d-segment-tree">2D Segment Tree</a>
 */
public class SegmentTree2D {

    /**
     * Represents a 1D Segment Tree.
     * This is equivalent to your 'Sagara' struct. It manages the columns (X-axis).
     */
    public static class SegmentTree1D {
        private int n;
        private final int[] tree;

        /**
         * Initializes the 1D Segment Tree with the nearest power of 2.
         *
         * @param size The expected number of elements (columns).
         */
        public SegmentTree1D(int size) {
            n = 1;
            while (n < size) {
                n *= 2;
            }
            tree = new int[n * 2];
        }

        /**
         * Recursively updates a point in the 1D tree.
         */
        private void update(int index, int val, int node, int lx, int rx) {
            if (rx - lx == 1) {
                tree[node] = val;
                return;
            }

            int mid = lx + (rx - lx) / 2;
            int leftChild = node * 2 + 1;
            int rightChild = node * 2 + 2;

            if (index < mid) {
                update(index, val, leftChild, lx, mid);
            } else {
                update(index, val, rightChild, mid, rx);
            }

            tree[node] = tree[leftChild] + tree[rightChild];
        }

        /**
         * Public wrapper to update a specific index.
         *
         * @param index The column index to update.
         * @param val   The new value.
         */
        public void update(int index, int val) {
            update(index, val, 0, 0, n);
        }

        /**
         * Retrieves the exact value at a specific leaf node.
         *
         * @param index The column index.
         * @return The value at the given index.
         */
        public int get(int index) {
            return query(index, index + 1, 0, 0, n);
        }

        /**
         * Recursively queries the sum in a 1D range.
         */
        private int query(int l, int r, int node, int lx, int rx) {
            if (lx >= r || rx <= l) {
                return 0; // Out of bounds
            }
            if (lx >= l && rx <= r) {
                return tree[node]; // Fully inside
            }

            int mid = lx + (rx - lx) / 2;
            int leftSum = query(l, r, node * 2 + 1, lx, mid);
            int rightSum = query(l, r, node * 2 + 2, mid, rx);

            return leftSum + rightSum;
        }

        /**
         * Public wrapper to query the sum in the range [l, r).
         *
         * @param l Left boundary (inclusive).
         * @param r Right boundary (exclusive).
         * @return The sum of the range.
         */
        public int query(int l, int r) {
            return query(l, r, 0, 0, n);
        }
    }

    // --- Start of 2D Segment Tree (equivalent to 'Sagara2D') ---

    private int n;
    private final SegmentTree1D[] tree;

    /**
     * Initializes the 2D Segment Tree.
     *
     * @param rows The number of rows in the matrix.
     * @param cols The number of columns in the matrix.
     */
    public SegmentTree2D(int rows, int cols) {
        n = 1;
        while (n < rows) {
            n *= 2;
        }
        tree = new SegmentTree1D[n * 2];
        for (int i = 0; i < n * 2; i++) {
            // Every node in the outer tree is a full 1D tree!
            tree[i] = new SegmentTree1D(cols);
        }
    }

    /**
     * Recursively updates a point in the 2D grid.
     */
    private void update(int row, int col, int val, int node, int lx, int rx) {
        if (rx - lx == 1) {
            tree[node].update(col, val);
            return;
        }

        int mid = lx + (rx - lx) / 2;
        int leftChild = node * 2 + 1;
        int rightChild = node * 2 + 2;

        if (row < mid) {
            update(row, col, val, leftChild, lx, mid);
        } else {
            update(row, col, val, rightChild, mid, rx);
        }

        // The value of the current node's column is the sum of its children's column values
        int leftVal = tree[leftChild].get(col);
        int rightVal = tree[rightChild].get(col);
        tree[node].update(col, leftVal + rightVal);
    }

    /**
     * Public wrapper to update a specific point (row, col).
     *
     * @param row The row index.
     * @param col The column index.
     * @param val The new value.
     */
    public void update(int row, int col, int val) {
        update(row, col, val, 0, 0, n);
    }

    /**
     * Recursively queries the sum in a submatrix.
     */
    private int query(int top, int bottom, int left, int right, int node, int lx, int rx) {
        if (lx >= bottom || rx <= top) {
            return 0; // Out of bounds
        }
        if (lx >= top && rx <= bottom) {
            // Fully inside the row range, so delegate the column query to the 1D tree
            return tree[node].query(left, right);
        }

        int mid = lx + (rx - lx) / 2;
        int leftSum = query(top, bottom, left, right, node * 2 + 1, lx, mid);
        int rightSum = query(top, bottom, left, right, node * 2 + 2, mid, rx);

        return leftSum + rightSum;
    }

    /**
     * Public wrapper to query the sum of a submatrix.
     * Note: boundaries are [top, bottom) and [left, right).
     *
     * @param top    Top row index (inclusive).
     * @param bottom Bottom row index (exclusive).
     * @param left   Left column index (inclusive).
     * @param right  Right column index (exclusive).
     * @return The sum of the submatrix.
     */
    public int query(int top, int bottom, int left, int right) {
        return query(top, bottom, left, right, 0, 0, n);
    }
}
