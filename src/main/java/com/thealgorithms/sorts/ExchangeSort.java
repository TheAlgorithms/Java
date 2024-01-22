package com.thealgorithms.sorts;

public class ExchangeSort {
    public static int[] binaryInsertSort(int[] num , int size) {
        
   
        int i, j, temp;
        for (i = 0; i < size - 1; i++) 
        {
           
          // Outer Loop
          for (j = i + 1; j < size; j++)
          {
             
            // Inner Loop
            // Sorting into ascending order if previous
            // element bigger than next element we swap
            // to make it in ascending order
            if (num[i] > num[j])
            {
               
              // Swapping
              temp = num[i];
              num[i] = num[j];
              num[j] = temp;
            }
          }
        }
        return num;
        }
}
