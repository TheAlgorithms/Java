package com.thealgorithms.searches;

import java.util.Scanner;

/**
 * @author: Lahu Shahadev Andhale
 * @date: 1-10-2023(Sunday)
 */
/*
 * Recursive Binary Search is an efficient algorithm for finding a search
 * element within a sorted array of data. It works by repeatedly dividing the
 * search
 * space in half until the desired element is found or it is determined that the
 * element
 * is not present in the array.
 */
public class RecursiveBinarySearch {

    public static int BinarySearchRecursively(int n, int arr[], int search, int firstIdx, int lastIdx) {
        if (lastIdx >= firstIdx) {
            //We are finding mid;
            int mid = (firstIdx + lastIdx) / 2;
            //Checking is value at mid is equal to the search ;
            if (arr[mid] == search) {
                return mid;
            } else if (arr[mid] > search) {
                //recursively call the binary search on the left half of the array
                return BinarySearchRecursively(n, arr, search, firstIdx, mid - 1);
            } else {
                //recursively call the binary search on the right half of the array
                return BinarySearchRecursively(n, arr, search, mid + 1, lastIdx);
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of data set: ");
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.print("Enter the elements in sorted order: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.print("Enter the element to be search over data set: ");
        int search = sc.nextInt();
        int idx = BinarySearchRecursively(n, arr, search, 0, n - 1);
        if (idx == -1) {
            System.out.println("Element is not present in the given data set");
        } else {
            System.out.println("The element is present at the index: " + idx + " value is " + arr[idx]);
        }
    }
}
/* input and Output:
 * Enter the size of data set: 10
 * Enter the elements in sorted order: 2 4 5 6 7 8 9 10 22 24
 * Enter the element to be search over data set: 22
 * The element is present at the index: 8 value is 22
 */
/*
 * Complexity Analysis:
 * Best Case: O(1) the element is found at mid int first iteration
 * Average Case: T(N)=T(N/2)+c that is " O(log N) "
 * Worst Case: T(N)=T(N/2)+c that is " O(log N) "
 * Space Complexity: O(logN) that is size of recursion tree.
 */
