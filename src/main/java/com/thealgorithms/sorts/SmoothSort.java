package com.thealgorithms.sorts;

import java.util.ArrayList;
import java.util.List;

public class SmoothSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        //TODO: Pending implementation

        return unsorted;
    }

    private static List<Integer> generateLeonardoNumbers(int maxIndex) {
        List<Integer> leonardoNumbers = new ArrayList<>();
        // First two numbers of the Leonardo Series are 1
        leonardoNumbers.add(1); 
        leonardoNumbers.add(1);

        for (int i = 2; i <= maxIndex; i++) {
            int nextNumber = leonardoNumbers.get(i - 1) + leonardoNumbers.get(i - 2) + 1;
            leonardoNumbers.add(nextNumber);
        }

        return leonardoNumbers;
    }

    private static <T extends Comparable<T>> void heapify(T[] array, int end, int[] heaps, int size, List<Integer> leonardoNumbers) {
        //TODO: Pending implementation
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // SINCE THIS IS ENHANCEMENT OF HEAP SORT THIS COMMIT WILL HELP ME UNDERSTAND HEAPSORT AND THEN IMPROVE IT

    public void buildHeap() {
        // For ith element
        // Left node is present at (2*i)+1
        // Right node is present at (2*i)+2
        // Parent node is at (i-1)/2
    }

    public void buildMaxHeap() {
        // In Max heap parent node is always greater than or equal to child node
        // For everey element at index i
            // Compatre if element at i is greater than element at (i-2)/2 [PARENT NODE]
            // if true
                // then swap
            // if false
                // then increment i
    }

    public void heapsort() {
        // build heap from the input
        // start_index = 0
        // end_index = arr.length

        // do till exd_index = start_index
            // build max heap 
            // swap elements start_index and end_index (as largest element is present at start_index and needs to be moved to end_index)
            // end_index = end_index - 1
    }
    
}
