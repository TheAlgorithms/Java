package com.sorts;

public class PigeonholeSort {

    /**
     * This method sorts the array using Pigeonhole sort technique.
     * <p>
     * Pigeonhole sorting is a sorting algorithms that is suitable for sorting lists of elements where the number
     * of elements and the number of possible key values are approximately the same.
     * <p>
     * It requires O(n + Range) time where n is number of elements in input array and ‘Range’ is number of possible
     * values in array.
     *
     * @param arr The array to be sorted
     * @return arr Sorted array
     */
    public Integer[] sort(Integer[] arr) {

        // Find maximum and minimum elements in array
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (Integer integer : arr) {
            min = Math.min(min, integer);
            max = Math.max(max, integer);
        }

        // Range
        int range = max - min + 1;

        // Pigeonhole array
        int[] pigeonholes = new int[range];

        // Put each element of arr in its pigeonhole
        for (Integer integer : arr) {
            // This increment operation will count for the duplicates elements, if present
            pigeonholes[integer - min]++;
        }

        // Index for the original array
        int index = 0;

        // Loop over pigeonhole array
        for (int i = 0; i < range; i++) {
            // This inner loop will execute only for those indexes in
            // pigeonhole which are greater than zero i.e., only for those
            // elements which are present in the original array. This also
            // takes care of the duplicate elements
            while (pigeonholes[i]-- > 0) {
                arr[index++] = i + min;
            }
        }

        return arr;
    }
}
