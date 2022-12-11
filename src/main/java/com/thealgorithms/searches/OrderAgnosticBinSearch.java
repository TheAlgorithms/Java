package com.thealgorithms.searches;

//URL: https://www.geeksforgeeks.org/order-agnostic-binary-search/

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import org.junit.jupiter.api.Test;

 class OrderAgnosticBinarySearch {

    static int BinSearchAlgo(int arr[], int start, int end, int target)
    {

        // Checking whether the given array is ascending order
        boolean AscOrd = arr[start] < arr[end];

        while (start <= end) {
            int middle = start + (end - start) / 2;

            // Check if the desired element is present at the middle position
            if (arr[middle] == target)
                return middle;

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
    
     @Test
    //valid Test Case
    public void ElementInMiddle(){
        int[] arr = {10,20,30,40,50};
        int answer = BinSearchAlgo(arr,0,arr.length-1,30);
        System.out.println(answer);
        int expected = 2;
        assertEquals(expected,answer);
    }
     
    @Test
    //valid Test Case
     public void RightHalfDescOrder(){
        int[] arr = {50,40,30,20,10};
        int answer = BinSearchAlgo(arr,0,arr.length-1,10);
        System.out.println(answer);
        int expected = 4;
        assertEquals(expected,answer);
    }

    @Test
     //valid test case
     public void LeftHalfDescOrder(){
        int[] arr = {50,40,30,20,10};
        int answer = BinSearchAlgo(arr,0,arr.length-1,50);
        System.out.println(answer);
        int expected = 0;
        assertEquals(expected,answer);
    }

    @Test
     //valid test case
     public void RightHalfAscOrder(){
        int[] arr = {10,20,30,40,50};
        int answer = BinSearchAlgo(arr,0,arr.length-1,50);
        System.out.println(answer);
        int expected = 4;
        assertEquals(expected,answer);
    }

    @Test
     //valid test case
     public void LeftHalfAscOrder(){
        int[] arr = {10,20,30,40,50};
        int answer = BinSearchAlgo(arr,0,arr.length-1,10);
        System.out.println(answer);
        int expected = 0;
        assertEquals(expected,answer);
    }

    @Test
     //valid test case
     public void ElementNotFound(){
        int[] arr = {10,20,30,40,50};
        int answer = BinSearchAlgo(arr,0,arr.length-1,100);
        System.out.println(answer);
        int expected = -1;
        assertEquals(expected,answer);
    }
}
