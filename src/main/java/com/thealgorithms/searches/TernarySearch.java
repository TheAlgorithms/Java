package com.thealgorithms.searches;

import static java.lang.String.format;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * A ternary search algorithm is a technique in computer science for finding the
 * minimum or maximum of a unimodal function The algorithm determines either
 * that the minimum or maximum cannot be in the first third of the domain or
 * that it cannot be in the last third of the domain, then repeats on the
 * remaining third.
 *
 * <p>
 * Worst-case performance Θ(log3(N)) Best-case performance O(1) Average
 * performance Θ(log3(N)) Worst-case space complexity O(1)
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */
public class TernarySearch implements SearchAlgorithm {

    /**
     * @param arr The **Sorted** array in which we will search the element.
     * @param value The value that we want to search for.
     * @return The index of the element if found. Else returns -1.
     */
    @Override
    public <T extends Comparable<T>> int find(T[] arr, T value) {
        return ternarySearch(arr, value, 0, arr.length - 1);
    }

    /**
     * @param arr The **Sorted** array in which we will search the element.
     * @param key The value that we want to search for.
     * @param start The starting index from which we will start Searching.
     * @param end The ending index till which we will Search.
     * @return Returns the index of the Element if found. Else returns -1.
     */
    private <T extends Comparable<T>> int ternarySearch(
        T[] arr,
        T key,
        int start,
        int end
    ) {
        if (start > end) {
            return -1;
        }
        /* First boundary: add 1/3 of length to start */
        int mid1 = start + (end - start) / 3;
        /* Second boundary: add 2/3 of length to start */
        int mid2 = start + 2 * (end - start) / 3;

        if (key.compareTo(arr[mid1]) == 0) {
            return mid1;
        } else if (key.compareTo(arr[mid2]) == 0) {
            return mid2;
        } /* Search the first (1/3) rd part of the array.*/else if (
            key.compareTo(arr[mid1]) < 0
        ) {
            return ternarySearch(arr, key, start, --mid1);
        } /* Search 3rd (1/3)rd part of the array */else if (
            key.compareTo(arr[mid2]) > 0
        ) {
            return ternarySearch(arr, key, ++mid2, end);
        } /* Search middle (1/3)rd part of the array */else {
            return ternarySearch(arr, key, mid1, mid2);
        }
    }

    public static void main(String[] args) {
        // just generate data
        Random r = new Random();
        int size = 100;
        int maxElement = 100000;
        Integer[] integers = Stream
            .generate(() -> r.nextInt(maxElement))
            .limit(size)
            .sorted()
            .toArray(Integer[]::new);

        // the element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];

        TernarySearch search = new TernarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(
            format(
                "Should be found: %d. Found %d at index %d. An array length %d",
                shouldBeFound,
                integers[atIndex],
                atIndex,
                size
            )
        );

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(
            format(
                "Found by system method at an index: %d. Is equal: %b",
                toCheck,
                toCheck == atIndex
            )
        );
    }
}
