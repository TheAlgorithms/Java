//https://en.wikipedia.org/wiki/Dutch_national_flag_problem

package com.thealgorithms.sorts;
public class DijkstraThreeWayPartition {

    public static void threeWayPartition(int[] arr, int pivot) {
        int low = 0, mid = 0;
        int high = arr.length - 1;
        //this will sort the array in o(n) time complexity
        //also with no extra space i,e o(1) space complexity
        while (mid <= high) {
            if (arr[mid] < pivot) {
                // Swap
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;

                low++;
                mid++;
            } else if (arr[mid] > pivot) {
                // Swap 
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;

                high--;
            } else {
                mid++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 2, 3, 2, 1, 3, 2};
        int pivot = 2;
        threeWayPartition(arr, pivot);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
