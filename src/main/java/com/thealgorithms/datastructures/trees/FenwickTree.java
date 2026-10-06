package com.thealgorithms.datastructures.trees;

/** Fenwick Tree for point updates and prefix-sum queries in O(log n). */
public class FenwickTree {

    private final int n;
    private final int[] fenTree;

    /** Creates a Fenwick tree with n elements, all initialized to zero. */
    public FenwickTree(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Size must be non-negative");
        }
        this.n = n;
        this.fenTree = new int[n + 1];
    }

    /** Adds val to the element at index i. */
    public void update(int i, int val) {
        checkIndex(i);
        i += 1; // Convert to the internal 1-based index
        while (i <= n) {
            fenTree[i] += val;
            i += i & (-i);
        }
    }

    /** Returns the sum of elements from index 0 to i. */
    public int query(int i) {
        checkIndex(i);
        i += 1;
        int cumSum = 0;
        while (i > 0) {
            cumSum += fenTree[i];
            i -= i & (-i);
        }
        return cumSum;
    }

    // Check that the index is within the valid range.
    private void checkIndex(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds for size " + n);
        }
    }
}
