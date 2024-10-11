package com.thealgorithms.searches;

/**
 * LinearSearchThread is a multithreaded implementation of the linear search algorithm.
 * It creates multiple threads to search for a specific number in an array.
 *
 * <p>
 * The class generates an array of random integers, prompts the user to enter a number to search for,
 * and divides the array into four segments, each handled by a separate thread.
 * The threads run concurrently and search for the specified number within their designated segments.
 * Finally, it consolidates the results to determine if the number was found.
 * </p>
 *
 * <p>
 * Example usage:
 * 1. The program will output the generated array.
 * 2. The user will be prompted to input a number to search for.
 * 3. The program will display whether the number was found in the array.
 * </p>
 */
public final class LinearSearchThread {
    private LinearSearchThread() {
    }
}

/**
 * The Searcher class extends Thread and is responsible for searching for a specific
 * number in a segment of an array.
 */
class Searcher extends Thread {
    private final int[] arr; // The array to search in
    private final int left; // Starting index of the segment
    private final int right; // Ending index of the segment
    private final int x; // The number to search for
    private boolean found; // Result flag

    /**
     * Constructor to initialize the Searcher.
     *
     * @param arr The array to search in
     * @param left The starting index of the segment
     * @param right The ending index of the segment
     * @param x The number to search for
     */
    Searcher(int[] arr, int left, int right, int x) {
        this.arr = arr;
        this.left = left;
        this.right = right;
        this.x = x;
    }

    /**
     * The run method for the thread, performing the linear search in its segment.
     */
    @Override
    public void run() {
        int k = left;
        found = false;
        while (k < right && !found) {
            if (arr[k++] == x) {
                found = true;
            }
        }
    }

    /**
     * Returns whether the number was found in the segment.
     *
     * @return true if the number was found, false otherwise
     */
    boolean getResult() {
        return found;
    }
}
