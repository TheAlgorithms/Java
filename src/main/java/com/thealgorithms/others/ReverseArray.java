// Created by Pronay Debnath
// Date:- 1/10/2023
// Updated the existing code with documentations and some comments

import java.util.*;

public class ReverseArray {
    /**
     * Reverses the elements of an array in-place.
     *
     * @param a   The array to be reversed.
     * @param start The starting index for the reversal.
     * @param end   The ending index for the reversal.
     */
    public static void revarr(int[] a, int start, int end) {
        int temp;
        while (start < end) {
            temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
        System.out.println("Reversed array:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // Adding user input functionality
        System.out.print("Enter the array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter array elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int start = 0;
        int end = n - 1;
        revarr(arr, start, end);
    }
}
