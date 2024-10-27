package com.thealgorithms.sorts;

import static com.thealgorithms.maths.Ceil.ceil;
import static com.thealgorithms.maths.Floor.floor;
import static com.thealgorithms.searches.QuickSelect.select;

import java.util.Arrays;

/**
 * A wiggle sort implementation based on John L.s' answer in
 * https://cs.stackexchange.com/questions/125372/how-to-wiggle-sort-an-array-in-linear-time-complexity
 * Also have a look at:
 * https://cs.stackexchange.com/questions/125372/how-to-wiggle-sort-an-array-in-linear-time-complexity?noredirect=1&lq=1
 * Not all arrays are wiggle-sortable. This algorithm will find some obviously not wiggle-sortable
 * arrays and throw an error, but there are some exceptions that won't be caught, for example [1, 2,
 * 2].
 */
public class WiggleSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return wiggleSort(unsorted);
    }

    private int mapIndex(int index, int n) {
        return ((2 * index + 1) % (n | 1));
    }

    /**
     * Modified Dutch National Flag Sort. See also: sorts/DutchNationalFlagSort
     *
     * @param sortThis array to sort into group "greater", "equal" and "smaller" than median
     * @param median   defines the groups
     * @param <T>      extends interface Comparable
     */
    private <T extends Comparable<T>> void triColorSort(T[] sortThis, T median) {
        int n = sortThis.length;
        int i = 0;
        int j = 0;
        int k = n - 1;
        while (j <= k) {
            if (0 < sortThis[mapIndex(j, n)].compareTo(median)) {
                SortUtils.swap(sortThis, mapIndex(j, n), mapIndex(i, n));
                i++;
                j++;
            } else if (0 > sortThis[mapIndex(j, n)].compareTo(median)) {
                SortUtils.swap(sortThis, mapIndex(j, n), mapIndex(k, n));
                k--;
            } else {
                j++;
            }
        }
    }

    private <T extends Comparable<T>> T[] wiggleSort(T[] sortThis) {
        // find the median using quickSelect (if the result isn't in the array, use the next greater
        // value)
        T median;

        median = select(Arrays.asList(sortThis), (int) floor(sortThis.length / 2.0));

        int numMedians = 0;

        for (T sortThi : sortThis) {
            if (0 == sortThi.compareTo(median)) {
                numMedians++;
            }
        }
        // added condition preventing off-by-one errors for odd arrays.
        // https://cs.stackexchange.com/questions/150886/how-to-find-wiggle-sortable-arrays-did-i-misunderstand-john-l-s-answer?noredirect=1&lq=1
        if (sortThis.length % 2 == 1 && numMedians == ceil(sortThis.length / 2.0)) {
            T smallestValue = select(Arrays.asList(sortThis), 0);
            if (!(0 == smallestValue.compareTo(median))) {
                throw new IllegalArgumentException("For odd Arrays if the median appears ceil(n/2) times, "
                    + "the median has to be the smallest values in the array.");
            }
        }
        if (numMedians > ceil(sortThis.length / 2.0)) {
            throw new IllegalArgumentException("No more than half the number of values may be the same.");
        }

        triColorSort(sortThis, median);
        return sortThis;
    }
}
