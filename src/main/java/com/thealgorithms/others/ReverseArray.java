// Created by Pronay Debnath
// Date:- 1/10/2023
// Updated the whole code with documentations and comments wherever necessary


import java.util.*;

public class Main {
    /**
     * Reverses the elements of an array in-place.
     *
     * @param arr   The array to be reversed.
     * @param start The starting index for the reversal.
     * @param end   The ending index for the reversal.
     */
    public static void reverseArray(int[] arr, int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        System.out.println("Reversed array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the array size");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter array elements");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int start = 0;
        int end = n - 1;
        reverseArray(arr, start, end);
    }
}
