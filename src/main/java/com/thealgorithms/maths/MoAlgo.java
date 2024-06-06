package com.thealgorithms.maths;

import java.lang.reflect.Array;
import java.util.*;

class Query implements Comparable<Query> {
    int L, R, index;

    public Query(int L, int R, int index) {
        this.L = L;
        this.R = R;
        this.index = index;
    }

    @Override
    public int compareTo(Query other) {
        int blockSize = (int) Math.sqrt(Array.getLength(null));
        int blockNumber1 = this.L / blockSize;
        int blockNumber2 = other.L / blockSize;
        if (blockNumber1 != blockNumber2)
            return Integer.compare(blockNumber1, blockNumber2);
        return Integer.compare(this.R, other.R);
    }
}

public class MoAlgo {
    public static int[] moAlgorithm(int[] arr, Query[] queries) {
        Arrays.sort(queries, Comparator.comparingInt((Query q) -> q.L / (int) Math.sqrt(arr.length))
                                       .thenComparingInt(q -> q.R));

        int currentL = 0, currentR = -1;
        int currentSum = 0;
        int[] results = new int[queries.length];

        for (Query query : queries) {
            int L = query.L, R = query.R;

            while (currentR < R) {
                currentR++;
                currentSum += arr[currentR];
            }

            while (currentR > R) {
                currentSum -= arr[currentR];
                currentR--;
            }

            while (currentL < L) {
                currentSum -= arr[currentL];
                currentL++;
            }

            while (currentL > L) {
                currentL--;
                currentSum += arr[currentL];
            }

            results[query.index] = currentSum;
        }

        return results;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 7, 6, 4, 8};
        Query[] queries = {
            new Query(0, 3, 0),  // Query 1: Sum of elements from index 0 to 3
            new Query(1, 5, 1),  // Query 2: Sum of elements from index 1 to 5
            new Query(2, 6, 2)   // Query 3: Sum of elements from index 2 to 6
        };

        int[] results = moAlgorithm(arr, queries);

        // Print results
        for (int i = 0; i < queries.length; i++) {
            System.out.println("Sum of elements in query " + (i + 1) + ": " + results[i]);
        }
    }
}

