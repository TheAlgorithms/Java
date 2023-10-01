// Code by Pronay Debnath
// Created:- 1/10/2023
// File Name should be RecursiveBinarySearch.java

import java.util.*;
public class RecursiveBinarySearch {

    // Recursive binary search function
    static int binsear(int a[], int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            // If the element is present at the middle itself
            if (a[mid] == target) return mid;

            // If the element is not in the middle but in the left or right subarray
            if (a[mid] > target) return binsear(a, left, mid - 1, target);

            return binsear(a, mid + 1, right, target);
        }

        // Element is not present in the array
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Added user inputs
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        int a[] = new int[n];

        System.out.println("Enter the elements in sorted order:");

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.print("Enter the target element to search for: ");
        int t = sc.nextInt();

        int res = binsear(a, 0, n - 1, t);

        if (res == -1)
            System.out.println("Element not found in the array.");
        else
            System.out.println("Element found at index " + res);
    }
}

// Explanation:- https://www.tutorialspoint.com/java-program-for-binary-search-recursive
