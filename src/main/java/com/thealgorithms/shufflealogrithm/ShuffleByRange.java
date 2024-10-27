package com.thealgorithms.shufflealogrithm;

import java.util.Random;

public class ShuffleByRange {

    private ShuffleByRange() {
        // Prevent instantiation
    }

    /**
     * Shuffles elements within a specified index range, leaving elements outside this range unchanged.
     *
     * @param array the input array to shuffle
     * @param start starting index of the range to shuffle
     * @param end ending index of the range to shuffle
     */
    public static void shuffleRange(int[] array, int start, int end) {
        // Edge case handling
        if (array == null || start < 0 || end >= array.length || start >= end) {
            return;
        }

        Random random = new Random();

        // Shuffle elements only in the specified range
        for (int i = end; i > start; i--) {
            int j = start + random.nextInt(i - start + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 20, 30, 40, 50, 60};
        int start = 1;
        int end = 4;
        shuffleRange(array, start, end);

        System.out.println("Array after shuffling range:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
