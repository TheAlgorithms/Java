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

    
    public static void buildHeap() {
        // For ith element
        // Left node is present at (2*i)+1
        // Right node is present at (2*i)+2
        // Parent node is at (i-1)/2
    }

    public static Integer[] buildMaxHeap(Integer[] array, int endIndex) {
        // In Max heap parent node is always greater than or equal to child node
        // For everey element at index i
            // Compatre if element at i is greater than element at (i-2)/2
            // if true
                // then swap and keep on swapping till false or till you reach root node(index 0)
            // if false
                // then increment i
        for(int i = 0; i <= endIndex; i++) {
            int parent_index = (i-2)/2;
            if (parent_index < 0) continue; // no parent for root node, I guess I can change the iteration from 1
            int current_index = i;
            while(array[current_index] > array[parent_index]) {
                int temp = array[current_index];
                array[current_index] = array[parent_index];
                array[parent_index] = temp;

                current_index = parent_index;
                parent_index = (current_index-2)/2;

                if(current_index == 0) break;
            }
        }

        return array;
    }

    public static Integer[] heapsort(Integer[] array) {
        // build heap from the input
        // start_index = 0
        // end_index = arr.length

        // do till exd_index = start_index
            // build max heap 
            // swap elements start_index and end_index (as largest element is present at start_index and needs to be moved to end_index)
            // end_index = end_index - 1
        
        buildHeap();
        int startIndex = 0;
        int endIndex = array.length - 1;

        while(endIndex != startIndex) {
            array = buildMaxHeap(array , endIndex);

            int temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;

            endIndex = endIndex -1;
        } 
        

        return array;
    }

    public static void main(String args[]) {
        // This is an existing test case, remove this after implementation is verified
        Integer[] array = new Integer[] {60, 7, 55, 9, 999, 3};
        Integer[] expected = new Integer[] {3, 7, 9, 55, 60, 999};
        
        Integer[] sorted = heapsort(array);
        System.out.println(myAssertArrayEquals(expected, sorted));
    }

    //Because Why not? Need to remove this
    public static boolean myAssertArrayEquals(Integer[] expected, Integer[] actual) {
        // Check if both arrays are null
        if (expected == null && actual == null) {
            return true;
        }

        // Check if one of the arrays is null
        if (expected == null || actual == null) {
            return false;
        }

        // Check if the lengths of the arrays are different
        if (expected.length != actual.length) {
            return false;
        }

        // Compare each element of the arrays
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(actual[i])) {
                return false;
            }
        }

        // If all elements are equal
        return true;
    }
}
