package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Computes the minimum search cost of an optimal binary search tree.
 *
 * <p>The algorithm sorts the keys, preserves the corresponding search frequencies, and uses
 * dynamic programming with Knuth's optimization to compute the minimum weighted search cost.
 *
 * <p>Example: if keys = [10, 12] and frequencies = [34, 50], the best tree puts 12 at the root
 * and 10 as its left child. The total cost is 50 * 1 + 34 * 2 = 118.
 *
 * <p>Reference:
 * https://en.wikipedia.org/wiki/Optimal_binary_search_tree
 */
public final class OptimalBinarySearchTree {
    private OptimalBinarySearchTree() {
    }

    /**
     * Computes the minimum weighted search cost for the given keys and search frequencies.
     *
     * @param keys the BST keys
     * @param frequencies the search frequencies associated with the keys
     * @return the minimum search cost
     * @throws IllegalArgumentException if the input is invalid
     */
    public static long findOptimalCost(int[] keys, int[] frequencies) {
        validateInput(keys, frequencies);
        if (keys.length == 0) {
            return 0L;
        }

        int[][] sortedNodes = sortNodes(keys, frequencies);
        int nodeCount = sortedNodes.length;
        long[] prefixSums = buildPrefixSums(sortedNodes);
        long[][] optimalCost = new long[nodeCount][nodeCount];
        int[][] root = new int[nodeCount][nodeCount];

        // Small example:
        // keys        = [10, 12]
        // frequencies = [34, 50]
        // Choosing 12 as the root gives cost 50 * 1 + 34 * 2 = 118,
        // which is better than choosing 10 as the root.

        // Base case: a subtree containing one key has cost equal to its frequency,
        // because that key becomes the root of the subtree and is searched at depth 1.
        for (int index = 0; index < nodeCount; index++) {
            optimalCost[index][index] = sortedNodes[index][1];
            root[index][index] = index;
        }

        // Build solutions for longer and longer key ranges.
        // optimalCost[start][end] stores the minimum search cost for keys in that range.
        for (int length = 2; length <= nodeCount; length++) {
            for (int start = 0; start <= nodeCount - length; start++) {
                int end = start + length - 1;

                // Every key in this range moves one level deeper when we choose a root,
                // so the sum of frequencies is added once to the subtree cost.
                long frequencySum = prefixSums[end + 1] - prefixSums[start];
                optimalCost[start][end] = Long.MAX_VALUE;

                // Knuth's optimization:
                // the best root for [start, end] lies between the best roots of
                // [start, end - 1] and [start + 1, end], so we search only this interval.
                int leftBoundary = root[start][end - 1];
                int rightBoundary = root[start + 1][end];
                for (int currentRoot = leftBoundary; currentRoot <= rightBoundary; currentRoot++) {
                    long leftCost = currentRoot > start ? optimalCost[start][currentRoot - 1] : 0L;
                    long rightCost = currentRoot < end ? optimalCost[currentRoot + 1][end] : 0L;
                    long currentCost = frequencySum + leftCost + rightCost;

                    if (currentCost < optimalCost[start][end]) {
                        optimalCost[start][end] = currentCost;
                        root[start][end] = currentRoot;
                    }
                }
            }
        }

        return optimalCost[0][nodeCount - 1];
    }

    private static void validateInput(int[] keys, int[] frequencies) {
        if (keys == null || frequencies == null) {
            throw new IllegalArgumentException("Keys and frequencies cannot be null");
        }
        if (keys.length != frequencies.length) {
            throw new IllegalArgumentException("Keys and frequencies must have the same length");
        }

        for (int frequency : frequencies) {
            if (frequency < 0) {
                throw new IllegalArgumentException("Frequencies cannot be negative");
            }
        }
    }

    private static int[][] sortNodes(int[] keys, int[] frequencies) {
        int[][] sortedNodes = new int[keys.length][2];
        for (int index = 0; index < keys.length; index++) {
            sortedNodes[index][0] = keys[index];
            sortedNodes[index][1] = frequencies[index];
        }

        // Sort by key so the nodes can be treated as an in-order BST sequence.
        Arrays.sort(sortedNodes, Comparator.comparingInt(node -> node[0]));

        for (int index = 1; index < sortedNodes.length; index++) {
            if (sortedNodes[index - 1][0] == sortedNodes[index][0]) {
                throw new IllegalArgumentException("Keys must be distinct");
            }
        }

        return sortedNodes;
    }

    private static long[] buildPrefixSums(int[][] sortedNodes) {
        long[] prefixSums = new long[sortedNodes.length + 1];
        for (int index = 0; index < sortedNodes.length; index++) {
            // prefixSums[i] holds the total frequency of the first i sorted keys.
            // This lets us get the frequency sum of any range in O(1) time.
            prefixSums[index + 1] = prefixSums[index] + sortedNodes[index][1];
        }
        return prefixSums;
    }
}
