package com.thealgorithms.others;

/**
 * Provides a method to perform a left rotation on an array.
 * A left rotation operation shifts each element of the array
 * by a specified number of positions to the left.
 *
 * @author sangin-lee
 */
public final class ArrayLeftRotation {
    private ArrayLeftRotation() {
    }

    /**
     * Performs a left rotation on the given array by the specified number of positions.
     *
     * @param arr the array to be rotated
     * @param n the number of positions to rotate the array to the left
     * @return a new array containing the elements of the input array rotated to the left
     */
    public static int[] rotateLeft(int[] arr, int n) {
        int size = arr.length;

        // Handle cases where array is empty or rotation count is zero
        if (size == 0 || n <= 0) {
            return arr.clone();
        }

        // Normalize the number of rotations
        n = n % size;
        if (n == 0) {
            return arr.clone();
        }

        int[] rotated = new int[size];

        // Perform rotation
        for (int i = 0; i < size; i++) {
            rotated[i] = arr[(i + n) % size];
        }

        return rotated;
    }
}
