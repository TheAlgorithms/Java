package com.thealgorithms.others;

/**
 * References: https://www.geeksforgeeks.org/find-maximum-average-subarray-of-k-length/
 *
 * This algorithm involves computing the maximum average of sub arrays of a fixed size k from integers.
 * This is a sliding window technique
 * As we go through the array, we sum up the integers upto k.
 * Once we do reach k initially, we average the numbers and compare it to the current maximum
 * To move onto the next number, we subtract the number at the start of the window,
 * and add the number at the end of the window
 *
 * @author S-M-J-I
 */
public class MaximumAverageOfContiguousSubArraysWithK {
    public MaximumAverageOfContiguousSubArraysWithK() {
    }

    /**
     * Finds the maximum average of a contiguous sub array of size K.
     *
     * @param k The size of the sub array.
     * @param numbers The array from which sub arrays will be considered.
     *
     * @return The maximum average of the contiguous sub array of size K. If no such sub array exists, then it returns 0.
     */
    public double maxAverage(int k, int... numbers) {
        // If there are no elements in the array
        if (numbers.length == 0) {
            // Return 0
            return 0;
        }
        // If there is only one element, return that element
        if (numbers.length == 1) {
            return numbers[0];
        }

        double MAX = Integer.MIN_VALUE; // Keep track of the maximum average
        double runningSum = 0; // Keep track of the running sum
        // For all elements in the array
        for (int i = 0; i < numbers.length; ++i) {
            runningSum += numbers[i]; // Add the current element to the running sum
            if (i >= k - 1) { // If we reach a minimum number of k elements
                double div = runningSum / k; // Find the average
                MAX = Math.max(div, MAX); // Compare and set the new maximum
                runningSum -= numbers[i - (k - 1)]; // Subtract the first element in the window
            }
        }

        // Return the maximum average
        return MAX;
    }
}
