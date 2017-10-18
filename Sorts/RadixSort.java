package Sorts;

import java.util.Arrays;

class RadixSort {

    static int getMax(int arr[], int n) {
        int maxNumber = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > maxNumber) {
                maxNumber = arr[i];
            }
        }
        return maxNumber;
    }

    static void countSort(int arr[], int length, int exp) {
        int output[] = new int[length];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < length; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < length; i++) {
            arr[i] = output[i];
        }
    }

    static void radixSort(int arr[], int length) {
        int max = getMax(arr, length);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, length, exp);
        }
    }

    static void print(int arr[]) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        int length = arr.length;
        radixSort(arr, length);
        print(arr);
    }
}
// Written by James Mc Dermott(theycallmemac)
