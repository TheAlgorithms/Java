package com.thealgorithms.greedyalgorithms;

import java.util.PriorityQueue;

/**
 * Class to solve the Optimal File Merging Problem.
 * The goal is to minimize the cost of merging files, where the cost of merging two files is the sum of their sizes.
 * The cost of merging all files is the sum of the costs of merging each pair of files.
 * Example:
 * files = [4, 3, 2, 6]
 * The minimum cost to merge all files is 29.
 * Steps:
 * 1. Merge files 2 and 3 (cost = 2 + 3 = 5). New files = [4, 5, 6]
 * 2. Merge files 4 and 5 (cost = 4 + 5 = 9). New files = [6, 9]
 * 3. Merge files 6 and 9 (cost = 6 + 9 = 15). New files = [15]
 * Total cost = 5 + 9 + 15 = 29
 *
 * @author Hardvan
 */
public final class OptimalFileMerging {
    private OptimalFileMerging() {
    }

    /**
     * Calculates the minimum cost to merge all files.
     * Steps:
     * 1. Add all files to a min heap.
     * 2. Remove the two smallest files from the heap, merge them, and add the result back to the heap.
     * 3. Repeat step 2 until there is only one file left in the heap.
     * 4. The total cost is the sum of all the costs of merging the files.
     *
     * @param files array of file sizes
     * @return the minimum cost to merge the files
     */
    public static int minMergeCost(int[] files) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int file : files) {
            minHeap.add(file);
        }

        int totalCost = 0;
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int cost = first + second;
            totalCost += cost;

            minHeap.add(cost);
        }
        return totalCost;
    }
}
