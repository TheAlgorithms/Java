package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * Tournament Sort algorithm implementation.
 *
 * Tournament sort builds a winner tree (a complete binary tree storing the index
 * of the smallest element in each subtree). It then repeatedly extracts the
 * winner (minimum) and updates the path from the removed leaf to the root.
 *
 * Time Complexity:
 * - Best case: O(n log n)
 * - Average case: O(n log n)
 * - Worst case: O(n log n)
 *
 * Space Complexity: O(n) – additional winner-tree storage
 *
 * @see <a href="https://en.wikipedia.org/wiki/Tournament_sort">Tournament Sort Algorithm</a>
 * @see SortAlgorithm
 */
public class TournamentSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        final int n = array.length;
        final int leafCount = nextPowerOfTwo(n);

        // Winner tree represented as an array:
        // - Leaves live at [leafCount .. 2*leafCount)
        // - Internal nodes live at [1 .. leafCount)
        // Each node stores an index into the original array or -1 for "empty".
        final int[] tree = new int[2 * leafCount];
        Arrays.fill(tree, -1);

        for (int i = 0; i < n; i++) {
            tree[leafCount + i] = i;
        }

        for (int node = leafCount - 1; node >= 1; node--) {
            tree[node] = winnerIndex(array, tree[node * 2], tree[node * 2 + 1]);
        }

        final T[] result = array.clone();
        for (int out = 0; out < n; out++) {
            final int winner = tree[1];
            result[out] = array[winner];

            int node = leafCount + winner;
            tree[node] = -1;

            for (node /= 2; node >= 1; node /= 2) {
                tree[node] = winnerIndex(array, tree[node * 2], tree[node * 2 + 1]);
            }
        }

        System.arraycopy(result, 0, array, 0, n);
        return array;
    }

    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power <<= 1;
        }
        return power;
    }

    private static <T extends Comparable<T>> int winnerIndex(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex == -1) {
            return rightIndex;
        }
        if (rightIndex == -1) {
            return leftIndex;
        }

        // If equal, prefer the left element to keep ordering deterministic.
        return SortUtils.less(array[rightIndex], array[leftIndex]) ? rightIndex : leftIndex;
    }
}
