package com.thealgorithms.sorts;

/**
 * Merge Sort Algorithm Implementation
 *
 * Wiki:https://en.wikipedia.org/wiki/Merge_sort
 */


import java.util.Arrays;
import java.util.List;

public class MergeSortAlgorithm implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        if (unsorted == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        if (unsorted.length <= 1) {
            return unsorted;
        }

        int middle = unsorted.length / 2;
        T[] left = Arrays.copyOfRange(unsorted, 0, middle);
        T[] right = Arrays.copyOfRange(unsorted, middle, unsorted.length);

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }

    protected <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
        int totalLength = left.length + right.length;
        T[] result = Arrays.copyOf(left, totalLength);

        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].compareTo(right[rightIndex]) <= 0) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        return result;
    }
}



class mergeSortInternTest{
    public static void main(String[] args) {
        MergeSortAlgorithm mergeSort = new MergeSortAlgorithm();
        Integer[] arr = {12, 11, 13, 5, 6, 7};
        Integer[] sortedArr = mergeSort.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(sortedArr));
    }
}

