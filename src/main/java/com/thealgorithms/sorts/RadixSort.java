package com.thealgorithms.sorts;

import java.util.Arrays;
import java.util.*;

//Add comments for better understanding//
class RadixSort {

    private static int getMax(int[] arr, int n) {
//        int mx = arr[0]; //Give Proper name to variables//
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
//        int i; //No need to initialized here loop variable//
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) { //For java Code loop variable initialized in loop//
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        int i = 0;
        while (i < n) {
            arr[i] = output[i];
            i++;
        }
    }

    private static void radixsort(int[] arr, int n) {
        int m = getMax(arr, n);

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, n, exp);
        }
    }

    static void print(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Make this method based on user input not dynamically //
    public static void main(String[] args) {
//        int[] arr = { 170, 45, 75, 90, 802, 24, 2, 66 };
//        int n = arr.length;
//        radixsort(arr, n);
//        print(arr, n);

        //User Input//
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of array");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("After Sorting");
        radixsort(arr, n);
        print(arr, n);
    }
}

// Written by James Mc Dermott(theycallmemac)
