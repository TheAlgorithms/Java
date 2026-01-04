package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Merge Sort using recursion.
 * This class sorts a list of integers by repeatedly dividing it
 * into smaller sublist and merging them in sorted order.
 */
public class MergeSortRecursive {

    // The original list to be sorted
    List<Integer> arr;

    /**
     * Constructor to initialize the list.
     *
     * @param arr the list of integers to be sorted
     */
    public MergeSortRecursive(List<Integer> arr) {
        this.arr = arr;
    }

    /**
     * Public method to start the merge sort process.
     *
     * @return a new sorted list
     */
    public List<Integer> mergeSort() {
        return merge(arr);
    }

    /**
     * Recursively divides the list into two halves
     * until sublist of size 1 are obtained.
     *
     * @param arr the list to be divided
     * @return a sorted list
     */
    private static List<Integer> merge(List<Integer> arr) {
        // Base condition: a list with 0 or 1 element is already sorted
        if (arr.size() <= 1) {
            return arr;
        }
        int arrLength = arr.size();
        int half = arrLength / 2;

        // Split the list into left and right halves
        List<Integer> arrA = arr.subList(0, half);
        List<Integer> arrB = arr.subList(half, arr.size());

        // Recursively sort both halves
        arrA = merge(arrA);
        arrB = merge(arrB);

        // Merge the sorted halves
        return sort(arrA, arrB);
    }

    /**
     * Merges two already sorted lists into a single sorted list.
     *
     * @param unsortedA first sorted list
     * @param unsortedB second sorted list
     * @return merged and sorted list
     */

    private static List<Integer> sort(List<Integer> unsortedA, List<Integer> unsortedB) {
        // If both lists are empty, return an empty list
        if (unsortedA.isEmpty() && unsortedB.isEmpty()) {
            return new ArrayList<>();
        }
        // If one list is empty, return the other list
        if (unsortedA.isEmpty()) {
            return unsortedB;
        }
        if (unsortedB.isEmpty()) {
            return unsortedA;
        }

        // Compare first elements of both lists and pick the smaller one
        if (unsortedA.get(0) <= unsortedB.get(0)) {
            List<Integer> newAl = new ArrayList<Integer>() {
                { add(unsortedA.get(0)); }
            };
            // Recursively merge the remaining elements
            newAl.addAll(sort(unsortedA.subList(1, unsortedA.size()), unsortedB));
            return newAl;
        } else {
            // Create a new list and add the smaller element
            List<Integer> newAl = new ArrayList<Integer>() {
                { add(unsortedB.get(0)); }
            };
            // Recursively merge the remaining elements
            newAl.addAll(sort(unsortedA, unsortedB.subList(1, unsortedB.size())));
            return newAl;
        }
    }
}
