package com.thealgorithms.searches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Union-Find data structure, also known as Disjoint Set Union (DSU),
 * is a data structure that tracks a set of elements partitioned into
 * disjoint (non-overlapping) subsets. It supports two main operations:
 *
 * 1. **Find**: Determine which subset a particular element is in.
 * 2. **Union**: Join two subsets into a single subset.
 *
 * This implementation uses path compression in the `find` operation
 * and union by rank in the `union` operation for efficiency.
 */
public class UnionFind {

    private final int[] p; // Parent array
    private final int[] r; // Rank array

    /**
     * Initializes a Union-Find data structure with n elements.
     * Each element is its own parent initially.
     *
     * @param n the number of elements
     */
    public UnionFind(int n) {
        p = new int[n];
        r = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    /**
     * Finds the root of the set containing the element i.
     * Uses path compression to flatten the structure.
     *
     * @param i the element to find
     * @return the root of the set
     */
    public int find(int i) {
        int parent = p[i];

        if (i == parent) {
            return i;
        }

        // Path compression
        final int result = find(parent);
        p[i] = result;
        return result;
    }

    /**
     * Unites the sets containing elements x and y.
     * Uses union by rank to attach the smaller tree under the larger tree.
     *
     * @param x the first element
     * @param y the second element
     */
    public void union(int x, int y) {
        int r0 = find(x);
        int r1 = find(y);

        if (r1 == r0) {
            return;
        }

        // Union by rank
        if (r[r0] > r[r1]) {
            p[r1] = r0;
        } else if (r[r1] > r[r0]) {
            p[r0] = r1;
        } else {
            p[r1] = r0;
            r[r0]++;
        }
    }

    /**
     * Counts the number of disjoint sets.
     *
     * @return the number of disjoint sets
     */
    public int count() {
        List<Integer> parents = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            int root = find(i);
            if (!parents.contains(root)) {
                parents.add(root);
            }
        }
        return parents.size();
    }

    @Override
    public String toString() {
        return "p " + Arrays.toString(p) + " r " + Arrays.toString(r) + "\n";
    }
}
