import java.util.Arrays;
package com.thealgorithms.divideandconquer;

//Java Program to Implement Quick Sort Algorithm for Sorting

/*
 * Uses Divide and Conquer Approach for Sorting
 * Time Complexity: O(nlogn) as an average case and O(n^2) for the worst case (Sorted Array)
 * Space Complexity: O(n)
 *
 * Reference:
 * https://www.geeksforgeeks.org/quick-sort-algorithm/
**/
public class QuickSort
{
    //Function to find Partition Index
    public static int partition(int []arr, int start, int end)
    {
        int pivot = arr[end];
        int index = start-1;
        //Dividing Array into 2 parts where left of partition index is all values less than it and right of it all values more than it
        for(int i=start; i<=end-1;i++)
        {
            if(arr[i]<pivot)
            {
                index++;
                swap(arr,index,j);
            }
        }
        swap(arr,index+1,end)
    }
    //Function to swap 2 elements in an array given 2 indices
    public static void swap(int[] arr, int index1, int index2) 
    {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    //Function to sort given array
    public static void quicksort(int []arr, int start, int end)
    {
        if(start<end)
        {
            int partitionindex = partition(arr,start,end);
            quicksort(arr,start,partitionindex-1);
            quicksort(arr,partitionindex+1,end);
        }
    }
}