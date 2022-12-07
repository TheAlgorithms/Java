package com.thealgorithms.searches;

public class OrderAgnosticBinSearch {

    /* Order Agnostic Binary Search is an algorithm in which we do not know whether the given sorted array is
    in ascending or descending order.
     */

    public static int BinSearchAlgo(int[] arr, int target){
        int first = arr[0];      //first element in the array
        int last = arr[arr.length-1];  //last element in the array

        //checking if the array is in ascending order, and it's corresponding algorithm

        if(first < last){
            int start = 0;
            int end = arr.length - 1;
            while(start <= end){
                int mid = start + (end-start)/2;

                if(target < arr[mid]){
                    end = mid - 1;
                }else if(target > arr[mid]){
                    start = mid + 1;
                }else{
                    return mid;       //returns the index of the required element
                }
            }
        }
        //Array is in descending order and it's corresponding code

        else{
            int start = arr.length - 1; // smallest element as start
            int end = 0;                // greatest element as end
            while(end <= start){

                int mid = end + (start - end)/2;

                if(target < arr[mid]){
                    end = mid + 1;
                }else if(target > arr[mid]){
                    start = mid - 1;
                }else{
                    return mid;   //returns the index of the required element
                }
            }
        }
        return -1;           //if no such element exists in the array
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};      //given sorted array
        System.out.println(BinSearchAlgo(arr,8));
    }
}
