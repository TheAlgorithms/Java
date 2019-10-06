package Sorts;

import java.util.Arrays;

class RadixSort {

    private static int getMax(int[] arr) {
        int mx = arr[0];
        for (int i = 1, n = arr.length; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    private static void radixsort(int[] arr) {

        int m = getMax(arr);


        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, exp);
    }


    static void print(int[] arr) {
        for (int value : arr) System.out.print(value + " ");
    }


    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixsort(arr);
        print(arr);
    }
}
// Written by James Mc Dermott(theycallmemac)
