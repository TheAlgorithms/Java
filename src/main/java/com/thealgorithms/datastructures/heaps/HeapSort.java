package com.thealgorithms.datastructures.heaps;

import java.util.Arrays;

public class HeapSort {

    public HeapSort() {

    }

    public static void heapSort(int arr[]){
        for(int i = arr.length / 2 - 1; i >= 0 ; i--){
            adjustHeap(arr, i, arr.length);
        }
        for(int j = arr.length - 1; j > 0; j--){
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void adjustHeap(int arr[], int i, int length){
        int temp = arr[i];
        for(int k = 2 * i + 1; k < length; k = 2 * k + 1){
            if(k + 1 < length && arr[k + 1] > arr[k]){
                k++;
            }
            if(arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] testArr = {1, 5, 4, 7, 9};
        heapSort(testArr);
    }
}
