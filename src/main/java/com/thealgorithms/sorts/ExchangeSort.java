package com.thealgorithms.sorts;

public class ExchangeSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 6};
        exchangeSort(arr); // Call the optimizedExchangeSort method to sort the array

        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void exchangeSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no swaps were made in this iteration, the array is already sorted.
            if (!swapped) {
                break;
            }
        }
    }
}

