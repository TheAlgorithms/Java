package com.thealgorithms.maths;

/**
 * To Count the Negative Numbers In A Row-wise and Column-wise Sorted Matrix
 * @author Yash Jain (https://github.com/Yashjain1602)
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class CountNegativeNumbersInASortedMatrix {
    // Recursive binary search to get last negative value in a row between a start
    // and an end
    static int getLastNegativeIndex(int array[], int start, int end) {
        // Base case
        if (start == end) {
            return start;
        }
        // Get the mid for binary search
        int mid = start + (end - start) / 2;
        // If current element is negative
        if (array[mid] < 0) {
            // If it is the rightmost negative element in the current row
            if (mid + 1 < array.length && array[mid + 1] >= 0) {
                return mid;
            }
            // Check in the right half of the array
            return getLastNegativeIndex(array, mid + 1, end);
        } else {
            // Check in the left half of the array
            return getLastNegativeIndex(array, start, mid - 1);
        }
    }
    // Function to return the count of negative numbers in the given matrix
    static int countNegative(int M[][], int n, int m) {

        // Initialize result
        int count = 0;

        // To store the index of the rightmost negative element in the row under
        // consideration
        int nextEnd = m - 1;

        // Iterate over all rows of the matrix
        for (int i = 0; i < n; i++) {
            // If the first element of the current row is positive then there will be no
            // negatives
            // in the matrix below or after it
            if (M[i][0] >= 0) {
                break;
            }

            // Run binary search only until the index of last negative Integer in the above
            // row
            nextEnd = getLastNegativeIndex(M[i], 0, nextEnd);
            count += nextEnd + 1;
        }
        return count;
    }
}
