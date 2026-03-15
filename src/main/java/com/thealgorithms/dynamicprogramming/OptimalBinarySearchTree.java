package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Computes the minimum search cost of an optimal binary search tree.
 *
 * <p>The algorithm sorts the keys, preserves the corresponding search frequencies, and uses
 * dynamic programming with Knuth's optimization to compute the minimum weighted search cost.
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

        for (int index = 0; index < nodeCount; index++) {
            optimalCost[index][index] = sortedNodes[index][1];
            root[index][index] = index;
        }

        for (int length = 2; length <= nodeCount; length++) {
            for (int start = 0; start <= nodeCount - length; start++) {
                int end = start + length - 1;
                long frequencySum = prefixSums[end + 1] - prefixSums[start];
                optimalCost[start][end] = Long.MAX_VALUE;

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
            prefixSums[index + 1] = prefixSums[index] + sortedNodes[index][1];
        }
        return prefixSums;
    }
}
