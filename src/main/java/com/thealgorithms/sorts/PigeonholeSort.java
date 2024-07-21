package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PigeonholeSort {
    private PigeonholeSort() {
    }

    /**
     * Sorts the given array using the pigeonhole sort algorithm.
     *
     * @param array the array to be sorted
     * @throws IllegalArgumentException if any negative integers are found
     * @return the sorted array
     */
    public static int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }

        checkForNegativeInput(array);

        final int maxElement = Arrays.stream(array).max().orElseThrow();
        final List<List<Integer>> pigeonHoles = createPigeonHoles(maxElement);

        populatePigeonHoles(array, pigeonHoles);
        collectFromPigeonHoles(array, pigeonHoles);

        return array;
    }

    /**
     * Checks if the array contains any negative integers.
     *
     * @param array the array to be checked
     * @throws IllegalArgumentException if any negative integers are found
     */
    private static void checkForNegativeInput(int[] array) {
        for (int number : array) {
            if (number < 0) {
                throw new IllegalArgumentException("Array contains non-positive integers.");
            }
        }
    }

    /**
     * Creates pigeonholes for sorting using an ArrayList of ArrayLists.
     *
     * @param maxElement the maximum element in the array
     * @return an ArrayList of ArrayLists
     */
    private static List<List<Integer>> createPigeonHoles(int maxElement) {
        List<List<Integer>> pigeonHoles = new ArrayList<>(maxElement + 1);
        for (int i = 0; i <= maxElement; i++) {
            pigeonHoles.add(new ArrayList<>());
        }
        return pigeonHoles;
    }

    /**
     * Populates the pigeonholes with elements from the array.
     *
     * @param array the array to be sorted
     * @param pigeonHoles the pigeonholes to be populated
     */
    private static void populatePigeonHoles(int[] array, List<List<Integer>> pigeonHoles) {
        for (int element : array) {
            pigeonHoles.get(element).add(element);
        }
    }

    /**
     * Collects sorted elements from the pigeonholes back into the array.
     *
     * @param array the array to be sorted
     * @param pigeonHoles the populated pigeonholes
     */
    private static void collectFromPigeonHoles(int[] array, List<List<Integer>> pigeonHoles) {
        int index = 0;
        for (List<Integer> pigeonHole : pigeonHoles) {
            for (int element : pigeonHole) {
                array[index++] = element;
            }
        }
    }
}
