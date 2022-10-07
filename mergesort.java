package Arrays;

import java.util.Arrays;

public class MergeSort {
    static void sort(int[] arr) {
        if (arr.length < 2) return;

        int mid = arr.length / 2;

        int[] left_half = new int[mid];
        int[] right_half = new int[arr.length - mid];

        // copying the elements of array into left_half
        for (int i = 0; i < mid; i++) {
            left_half[i] = arr[i];
        }
        
        // copying the elements of array into right_half
        for (int i = mid; i < arr.length; i++) {
            right_half[i - mid] = arr[i];
        }

        sort(left_half);
        sort(right_half);
        merge(arr, left_half, right_half);
    }
  
    static void merge(int[] arr, int[] left_half, int[] right_half) {
        int i = 0, j = 0, k = 0;

        while (i < left_half.length && j < right_half.length) {
            if (left_half[i] < right_half[j]) {
                arr[k++] = left_half[i++];
            }
            else {
                arr[k++] = right_half[j++];
            }
        }
        while (i < left_half.length) {
            arr[k++] = left_half[i++];
        }
        while (j < right_half.length) {
            arr[k++] = right_half[j++];
        }
    }
    public static void main(String[] args) {
        int[] arr = {5, 1, 7, 3, 8, 0, 1, 5, 7, 2, 8, 9, -7, 4, -9, -3, 4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
