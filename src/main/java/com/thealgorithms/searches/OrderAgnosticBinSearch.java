package com.thealgorithms.searches;

//URL: https://www.geeksforgeeks.org/order-agnostic-binary-search/

/* Order Agnostic Binary Search is an algorithm where we do not know whether the given
   sorted array is ascending or descending order.
   We declare a boolean variable to find whether the array is ascending order.
   In the while loop, we use the two pointer method (start and end) to get the middle element.
   if the middle element is equal to our target element, then that is the answer.
   If not, then we check if the array is ascending or descending order.
   Depending upon the condition, respective statements will be executed and we will get our answer.
 */

 public class OrderAgnosticBinarySearch {

     static int BinSearchAlgo(int arr[], int start, int end, int target) {

         // Checking whether the given array is ascending order
         boolean AscOrd = arr[start] < arr[end];

         while (start <= end) {
             int middle = start + (end - start) / 2;

             // Check if the desired element is present at the middle position
             if (arr[middle] == target)
                 return middle;       // returns the index of the middle element

             // Ascending order
             if (AscOrd) {
                 if (arr[middle] < target)
                     start = middle + 1;
                 else
                     end = middle - 1;
             }

             // Descending order
             else {
                 if (arr[middle] > target)
                     start = middle + 1;
                 else
                     end = middle - 1;
             }
         }
         // Element is not present
         return -1;
     }
 }
