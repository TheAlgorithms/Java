package com.thealgorithms.sorts;
import java.util.Random;

/**
 * Implementation of the Randomized QuickSort algorithm.
 * This algorithm sorts an array by choosing a random pivot element,
 * which improves the average performance over traditional QuickSort.
 */
public class RandomizedQuickSort {

    private static final Random rand = new Random();

    /**
     * Sorts the array in-place using Randomized QuickSort algorithm.
     * 
     * @param arr the array to be sorted
     * @param low the starting index of the segment to sort
     * @param high the ending index of the segment to sort
     */
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Chooses a random pivot, swaps it with the last element,
     * then partitions the array around this pivot.
     * 
     * @param arr the array to partition
     * @param low the starting index of the segment to partition
     * @param high the ending index of the segment to partition
     * @return final index position of the pivot element
     */
    private static int randomizedPartition(int[] arr, int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);  // Move pivot to end
        return partition(arr, low, high);
    }

    /**
     * Partitions the array segment such that elements less than or equal
     * to the pivot are to the left, and greater are to the right.
     * 
     * @param arr the array to partition
     * @param low the starting index of the segment to partition
     * @param high the ending index of the segment to partition
     * @return the final position of the pivot element
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;  // index of smaller element
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in the array.
     * 
     * @param arr the array containing elements to swap
     * @param i index of first element
     * @param j index of second element
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Main method for basic demonstration.
     * Sorts a sample array and prints the sorted output.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        randomizedQuickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
