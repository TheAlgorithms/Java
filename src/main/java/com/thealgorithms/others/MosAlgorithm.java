package com.thealgorithms.others;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Mo's Algorithm (Square Root Decomposition) for offline range queries
 *
 * Mo's Algorithm is used to answer range queries efficiently when:
 * 1. Queries can be processed offline (all queries known beforehand)
 * 2. We can efficiently add/remove elements from current range
 * 3. The problem has optimal substructure for range operations
 *
 * Time Complexity: O((N + Q) * sqrt(N)) where N = array size, Q = number of queries
 * Space Complexity: O(N + Q)
 *
 * @see <a href="https://www.geeksforgeeks.org/dsa/mos-algorithm-query-square-root-decomposition-set-1-introduction/">Mo's Algorithm</a>
 * @author BEASTSHRIRAM
 */
public final class MosAlgorithm {

    /**
     * Query structure to store range queries
     */
    public static class Query {
        public final int left;
        public final int right;
        public final int index; // Original index of query
        public int result; // Result of the query

        public Query(int left, int right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
            this.result = 0;
        }
    }

    private MosAlgorithm() {
        // Utility class
    }

    /**
     * Solves range sum queries using Mo's Algorithm
     *
     * @param arr the input array
     * @param queries array of queries to process
     * @return array of results corresponding to each query
     */
    public static int[] solveRangeSumQueries(int[] arr, Query[] queries) {
        if (arr == null || queries == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int blockSize = (int) Math.sqrt(n);

        // Sort queries using Mo's ordering
        Arrays.sort(queries, new MoComparator(blockSize));

        // Initialize variables for current range
        int currentLeft = 0;
        int currentRight = -1;
        int currentSum = 0;

        // Process each query
        for (Query query : queries) {
            // Expand or shrink the current range to match query range

            // Expand right boundary
            while (currentRight < query.right) {
                currentRight++;
                currentSum += arr[currentRight];
            }

            // Shrink right boundary
            while (currentRight > query.right) {
                currentSum -= arr[currentRight];
                currentRight--;
            }

            // Expand left boundary
            while (currentLeft < query.left) {
                currentSum -= arr[currentLeft];
                currentLeft++;
            }

            // Shrink left boundary
            while (currentLeft > query.left) {
                currentLeft--;
                currentSum += arr[currentLeft];
            }

            // Store the result
            query.result = currentSum;
        }

        // Extract results in original query order
        int[] results = new int[queries.length];
        for (Query query : queries) {
            results[query.index] = query.result;
        }

        return results;
    }

    /**
     * Solves range frequency queries using Mo's Algorithm
     * Example: Count occurrences of a specific value in range [L, R]
     *
     * @param arr the input array
     * @param queries array of queries to process
     * @param targetValue the value to count in each range
     * @return array of results corresponding to each query
     */
    public static int[] solveRangeFrequencyQueries(int[] arr, Query[] queries, int targetValue) {
        if (arr == null || queries == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int blockSize = (int) Math.sqrt(n);

        // Sort queries using Mo's ordering
        Arrays.sort(queries, new MoComparator(blockSize));

        // Initialize variables for current range
        int currentLeft = 0;
        int currentRight = -1;
        int currentCount = 0;

        // Process each query
        for (Query query : queries) {
            // Expand right boundary
            while (currentRight < query.right) {
                currentRight++;
                if (arr[currentRight] == targetValue) {
                    currentCount++;
                }
            }

            // Shrink right boundary
            while (currentRight > query.right) {
                if (arr[currentRight] == targetValue) {
                    currentCount--;
                }
                currentRight--;
            }

            // Expand left boundary
            while (currentLeft < query.left) {
                if (arr[currentLeft] == targetValue) {
                    currentCount--;
                }
                currentLeft++;
            }

            // Shrink left boundary
            while (currentLeft > query.left) {
                currentLeft--;
                if (arr[currentLeft] == targetValue) {
                    currentCount++;
                }
            }

            // Store the result
            query.result = currentCount;
        }

        // Extract results in original query order
        int[] results = new int[queries.length];
        for (Query query : queries) {
            results[query.index] = query.result;
        }

        return results;
    }

    /**
     * Comparator for Mo's Algorithm query ordering
     * Queries are sorted by block of left endpoint, then by right endpoint
     */
    private static class MoComparator implements Comparator<Query> {
        private final int blockSize;

        MoComparator(int blockSize) {
            this.blockSize = blockSize;
        }

        @Override
        public int compare(Query a, Query b) {
            int blockA = a.left / blockSize;
            int blockB = b.left / blockSize;

            if (blockA != blockB) {
                return Integer.compare(blockA, blockB);
            }

            // For odd blocks, sort right in ascending order
            // For even blocks, sort right in descending order (optimization)
            if ((blockA & 1) == 1) {
                return Integer.compare(a.right, b.right);
            } else {
                return Integer.compare(b.right, a.right);
            }
        }
    }

    /**
     * Demo method showing usage of Mo's Algorithm
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Example: Range sum queries
        int[] arr = {1, 3, 5, 2, 7, 6, 3, 1, 4, 8};

        Query[] queries = {
            new Query(0, 2, 0), // Sum of elements from index 0 to 2: 1+3+5 = 9
            new Query(1, 4, 1), // Sum of elements from index 1 to 4: 3+5+2+7 = 17
            new Query(2, 6, 2), // Sum of elements from index 2 to 6: 5+2+7+6+3 = 23
            new Query(3, 8, 3) // Sum of elements from index 3 to 8: 2+7+6+3+1+4 = 23
        };

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Range Sum Queries:");

        // Store original queries for display
        Query[] originalQueries = new Query[queries.length];
        for (int i = 0; i < queries.length; i++) {
            originalQueries[i] = new Query(queries[i].left, queries[i].right, queries[i].index);
        }

        int[] results = solveRangeSumQueries(arr, queries);

        for (int i = 0; i < originalQueries.length; i++) {
            System.out.printf("Query %d: Sum of range [%d, %d] = %d%n", i, originalQueries[i].left, originalQueries[i].right, results[i]);
        }

        // Example: Range frequency queries
        System.out.println("\nRange Frequency Queries (count of value 3):");
        Query[] freqQueries = {
            new Query(0, 5, 0), // Count of 3 in range [0, 5]: 1 occurrence
            new Query(2, 8, 1), // Count of 3 in range [2, 8]: 2 occurrences
            new Query(6, 9, 2) // Count of 3 in range [6, 9]: 1 occurrence
        };

        // Store original frequency queries for display
        Query[] originalFreqQueries = new Query[freqQueries.length];
        for (int i = 0; i < freqQueries.length; i++) {
            originalFreqQueries[i] = new Query(freqQueries[i].left, freqQueries[i].right, freqQueries[i].index);
        }

        int[] freqResults = solveRangeFrequencyQueries(arr, freqQueries, 3);

        for (int i = 0; i < originalFreqQueries.length; i++) {
            System.out.printf("Query %d: Count of 3 in range [%d, %d] = %d%n", i, originalFreqQueries[i].left, originalFreqQueries[i].right, freqResults[i]);
        }
    }
}
