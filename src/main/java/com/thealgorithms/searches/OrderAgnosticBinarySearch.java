package com.thealgorithms.searches;

//URL: https://www.geeksforgeeks.org/order-agnostic-binary-search/

/* Order Agnostic Binary Search is an algorithm where we do not know whether the given
   sorted array is ascending or descending order.
   We declare a boolean variable to find whether the array is ascending order.
   Insted of  while loop, we use recursion and two pointer method (start and end) to get the middle element.
   when the search space become a single element then start= end in that case of element not equal to target return -1
   if the middle element is equal to our target element, then that is the answer.
   If not, then we check if the array is ascending or descending order.
   Depending upon the condition, respective statements will be executed and we will get our answer.
 */

 public class OrderAgnosticBinarySearch {

     static int BinSearchAlgo(int arr[], int start, int end, int target) {
        int middle = start + (end-middle)/2;
        boolean isAsending=arr[start]<arr[end];
        if(arr[middle]== target){
            return middle;
        }
        if(start == end && arr[middle]!=target){
            return -1;
        }
        if(isAsending){
            if(target<arr[start]){
                end=middle-1;
            }else{
                start=mid+1;
            }
        }else{
            if(target<arr[start]){
                start=middle+1;
            }else{
                end=middle-1;
            }
        }
        return BinSearchAlgo(arr, start, end, target);
     }
} 
