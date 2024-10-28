package com.thealgorithms.shufflealgo;

import java.util.Random;

public
final class ShuffleByRange {

    private
    ShuffleByRange() {
        // Prevent instantiation
    }

    /**
     * Shuffles elements in the specified range of the array.
     *
     * @param array the input array to shuffle
     * @param start the starting index of the range (inclusive)
     * @param end   the ending index of the range (exclusive)
     */
    public
    static void shuffleByRange(int[] array, int start, int end) {
        // Edge case: Check if the range is valid
        if (array == null || start < 0 || end > array.length || start >= end) {
            return;
        }

        Random random = new Random();
        for (int i = end - 1; i > start; i--) {
            int j = random.nextInt(i - start + 1) + start;

            // Swap the elements at positions i and j
            int temp = array[i]; // Temporarily store the element at i
            array[i] = array[j]; // Move element from j to i
            array[j] = temp;     // Place the stored element in position j
        }
    }

    public
    static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        System.out.println("Original Array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }

        shuffleByRange(array, 1, 5);
        System.out.println("\nShuffled Array (Range 1 to 5): ");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
