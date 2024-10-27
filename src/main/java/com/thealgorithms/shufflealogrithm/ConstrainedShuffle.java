package com.thealgorithms.shufflealogrithm;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ConstrainedShuffle {

    /**
     * Shuffles the array so that no element stays in its original position.
     *
     * @param array the input array to shuffle with constraints
     */
    public static void constrainedShuffle(int[] array) {
        // Edge case: Check if array has only one element (no valid shuffle possible)
        if (array == null || array.length <= 1) return;

        List<Integer> shuffledList = new ArrayList<>();
        for (int num : array) shuffledList.add(num);

        do {
            Collections.shuffle(shuffledList);
        } while (!isValidShuffle(array, shuffledList));

        for (int i = 0; i < array.length; i++) array[i] = shuffledList.get(i);
    }

    /**
     * Verifies that no element is in its original position in the shuffled list.
     */
    private static boolean isValidShuffle(int[] original, List<Integer> shuffled) {
        for (int i = 0; i < original.length; i++) {
            if (original[i] == shuffled.get(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        constrainedShuffle(array);

        System.out.println("Constrained Shuffled Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}

