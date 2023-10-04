package com.thealgorithms.sorts;

public class ExchangeSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 6};
        exchangeSort(arr); // Call the exchangeSort method to sort the array

        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void exchangeSort(int[] arr) {
        int n = arr.length;

        // Outer loop iterates through each element in the array, except the last one.
        for (int i = 0; i < n - 1; i++) {
            // Inner loop compares the current element with all the elements to its right.
            for (int j = i + 1; j < n; j++) {
                // If the current element is greater than the element to its right, swap them.
                if (arr[i] > arr[j]) {
                    // Swap arr[i] and arr[j]
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}

