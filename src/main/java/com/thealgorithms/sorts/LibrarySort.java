package com.thealgorithms.sorts;

public class LibrarySort {

    public static void librarySort(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        int gap = 1;
        while (gap < n) {
            gap = gap * 2;
        }

        gap = gap / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap = gap / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 1};

        System.out.println("Original Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        librarySort(arr);

        System.out.println("\nSorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
