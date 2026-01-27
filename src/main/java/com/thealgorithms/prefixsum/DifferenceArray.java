package com.thealgorithms.prefixsum;

/**
 * Implements the Difference Array algorithm.
 *
 * <p>
 * The Difference Array is an auxiliary data structure that enables efficient range update operations.
 * It is based on the mathematical concept of Finite Differences.
 * </p>
 *
 * <p>
 * <strong>Key Operations:</strong>
 * <ul>
 * <li>Range Update (Add value to [L, R]): O(1)</li>
 * <li>Reconstruction (Prefix Sum): O(N)</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Finite_difference">Finite Difference (Wikipedia)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Prefix_sum">Prefix Sum (Wikipedia)</a>
 * @author Chahat Sandhu, <a href="https://github.com/singhc7">singhc7</a>
 */
public class DifferenceArray {

    private final long[] differenceArray;
    private final int n;

    /**
     * Initializes the Difference Array from a given integer array.
     *
     * @param inputArray The initial array. Cannot be null or empty.
     * @throws IllegalArgumentException if the input array is null or empty.
     */
    public DifferenceArray(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }
        this.n = inputArray.length;
        // Size n + 1 allows for branchless updates at the right boundary (r + 1).
        this.differenceArray = new long[n + 1];
        initializeDifferenceArray(inputArray);
    }

    private void initializeDifferenceArray(int[] inputArray) {
        differenceArray[0] = inputArray[0];
        for (int i = 1; i < n; i++) {
            differenceArray[i] = inputArray[i] - inputArray[i - 1];
        }
    }

    /**
     * Adds a value to all elements in the range [l, r].
     *
     * <p>
     * This method uses a branchless approach by allocating an extra element at the end
     * of the array, avoiding the conditional check for the right boundary.
     * </p>
     *
     * @param l   The starting index (inclusive).
     * @param r   The ending index (inclusive).
     * @param val The value to add.
     * @throws IllegalArgumentException if the range is invalid.
     */
    public void update(int l, int r, int val) {
        if (l < 0 || r >= n || l > r) {
            throw new IllegalArgumentException(String.format("Invalid range: [%d, %d] for array of size %d", l, r, n));
        }

        differenceArray[l] += val;
        differenceArray[r + 1] -= val;
    }

    /**
     * Reconstructs the final array using prefix sums.
     *
     * @return The resulting array after all updates. Returns long[] to handle potential overflows.
     */
    public long[] getResultArray() {
        long[] result = new long[n];
        result[0] = differenceArray[0];

        for (int i = 1; i < n; i++) {
            result[i] = differenceArray[i] + result[i - 1];
        }
        return result;
    }
}
