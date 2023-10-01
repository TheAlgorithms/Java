package com.thealgorithms.searches;

public class RecursiveBinarySearch {
    public static void main(String[] args) {
        int[] arr={34,65,78,98,670};
        System.out.println(BinarySearch(arr,98,0, arr.length-1));
    }
    static int BinarySearch(int[] arr,int target,int start,int end){
        int middleElementIndex=(start+end)/2;
        // Base Conditions for Recursion
        if(start>end){
            return -1;
        }
        if(arr[middleElementIndex]==target){
            return middleElementIndex;
        }
        if(middleElementIndex>target){
          return  BinarySearch(arr,target,start,middleElementIndex-1);
        }

        return  BinarySearch(arr,target,middleElementIndex+1, end);
    }
}
