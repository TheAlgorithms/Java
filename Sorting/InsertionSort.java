/**
 * InsertionSort.java
 * This program implements the Insertion Sort algorithm.
 * Time Complexity: O(n^2) in worst case, O(n) in best case (already sorted).
 */

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        // Loop from the second element to the end
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];  // Store the current element to be inserted
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // Shift element to the right
                j = j - 1;
            }
            arr[j + 1] = key;  // Insert the key into its correct position
        }
    }

    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 13};
        insertionSort(arr);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}