package com.sorts;

public class CountingSort {

    /**
     * This method sorts the array using counting sort technique
     *
     * @param arr The array to be sorted
     * @return arr Sorted array
     */
    public Integer[] sort(Integer[] arr) {

        // Finding the maximum element in the array
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        // Finding the minimum element in the array
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        // Creating the count array - This method will store the count of each element in the unsorted array
        int[] count = new int[max - min + 1];

        // This loop will store the count of each element in the array
        for (Integer integer : arr) {
            count[integer - min]++;
        }

        // This loop will replace the ith index of the count array with the sum of values at the ith and (i-1) index
        for (int i = 1; i < count.length; i++) {

            count[i] = count[i] + count[i - 1];
        }

        // This array will store the sorted array
        Integer[] places = new Integer[arr.length];

        // This loop will put the ith element at is correct position in the places array
        for (int i = 0; i < places.length; i++) {

            // Getting the value of the index - the value at the oount array index will be replaced by the value
            // in the original array
            int index = arr[i];
            places[count[index - min] - 1] = index;
            count[index - min]--;
        }

        // Copy the places array back to the original array - which will be sorted
        System.arraycopy(places, 0, arr, 0, arr.length);

        return arr;
    }
}
