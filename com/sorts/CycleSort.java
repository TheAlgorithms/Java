package com.sorts;

public class CycleSort {

    /**
     * @param arr Array to be sorted
     * @param <T> Generic type
     * @return arr Sorted array
     */
    public <T extends Comparable<T>> T[] sort(T[] arr) {

        int n = arr.length;

        // Counter for the number of memory writes
        int count = 0;

        // Traverse array and put the elements on their respective right places
        for (int i = 0; i < n - 2; i++) {

            // Initialize item as the starting point
            T item = arr[i];

            // Find the position where we want to put the item
            // Basically we count all the smaller elements to the right of the item
            int position = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(item) < 0) {
                    position++;
                }
            }

            // Check if the element is already at the correct position...
            if (position == i) {

                // .. then we do not have to do anything
                continue;
            }

            // Ignore duplicate elements
            while (item == arr[position]) {
                position++;
            }

            // Put the elements at its right position
            if (position != i) {

                // Swap
                T temp = item;
                item = arr[position];
                arr[position] = temp;

                count++;
            }

            // Rotate remaining cycle
            while (position != i) {
                position = i;

                // Find the position where we put the element
                for (int j = i + 1; j < n; j++) {
                    if (arr[j].compareTo(item) < 0) {
                        position++;
                    }
                }

                // Ignore duplicate elements
                while (item == arr[position]) {
                    position++;
                }

                // Put the element to its correct position
                if (item != arr[position]) {
                    T temp = arr[position];
                    arr[position] = item;
                    item = temp;

                    count++;
                }
            }
        }

        System.out.println("Number of memory writes :: " + count);

        return arr;
    }
}
