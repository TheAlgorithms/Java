package com.thealgorithms.sorts;

import com.thealgorithms.searches.QuickSelect;

import java.util.Arrays;

import static com.thealgorithms.maths.Ceil.ceil;
import static com.thealgorithms.maths.Floor.floor;
import static com.thealgorithms.searches.QuickSelect.select;

/**
 * A wriggle sort implementation based on John L.s' answer in
 * https://cs.stackexchange.com/questions/125372/how-to-wiggle-sort-an-array-in-linear-time-complexity
 */
public class WiggleSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return wiggleSort(unsorted);
    }

    private int mapIndex(int index, int n){
        return (2 * index) % (n-1 | 1);
    }

    /**
     * Modified Dutch National Flag Sort. See also: sorts/DutchNationalFlagSort
     * @param sortThis array to sort into group "greater", "equal" and "smaller" than median
     * @param median defines the groups
     * @param <T> extends interface Comparable
     */
    private <T extends Comparable<T>> void triColorSort(T[] sortThis, T median){
        int n = sortThis.length;
        int i = 0;
        int j = 0;
        int k = n - 1;
        while (j <= k) {
            if (0 > sortThis[mapIndex(j, n)].compareTo(median)) {
                SortUtils.swap(sortThis, mapIndex(j, n), mapIndex(i, n));
                i++;
                j++;
            } else if (0 < sortThis[mapIndex(j, n)].compareTo(median)) {
                SortUtils.swap(sortThis, mapIndex(j, n), mapIndex(k, n));
                k--;
            } else {
                j++;
            }
        }
    }

    private <T extends Comparable<T>> T[] wiggleSort(T[] sortThis) {
        // find the median using quickSelect (if the result isn't in the array, use the next greater value)
        T median;

        if(sortThis.length % 2 == 0) {
            median = select(Arrays.<T>asList(sortThis), (int) (sortThis.length / 2.0));
        } else {
            median = select(Arrays.<T>asList(sortThis), (int) floor(sortThis.length / 2.0));
        }

        System.out.println(median);

        for(int i = 0; i < sortThis.length; i++){
            int numMedians = 0;
            if(0 == sortThis[i].compareTo(median)){
                numMedians++;
            }
            if( numMedians > ceil(sortThis.length/ 2.0)){
                throw new IllegalArgumentException("No more than half the number of values may be the same.");
            }
        }

        triColorSort(sortThis, median);
        return sortThis;
    }
    public static void main(String[] args) {
        WiggleSort wiggleSort = new WiggleSort();

        Integer[] integers0 = new Integer[]{1, 2, 3, 4, 5};
        Integer[] integers1 = new Integer[]{1, 2, 1, 1, 5};
        String[] strings = new String[]{"a", "b", "e", "d"};

        wiggleSort.sort(integers0);
        wiggleSort.sort(integers1);
        wiggleSort.sort(strings);

        System.out.println(Arrays.toString(integers0));
        System.out.println(Arrays.toString(integers1));
        System.out.println(Arrays.toString(strings));
    }
}
