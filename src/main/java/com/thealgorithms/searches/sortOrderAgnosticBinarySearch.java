package com.thealgorithms.searches;
import java.util.*;
public class sortOrderAgnosticBinarySearch {
    public static int find(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        boolean arrDescending = arr[start] > arr[end]; // checking for Array is in ascending order or descending order.
        while (start <= end) {
            int mid = end - start / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arrDescending) { // boolean is true then our array is in descending order
                if (key < arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else { // otherwise our array is in ascending order
                if (key > arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
