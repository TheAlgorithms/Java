//User friendly and all edges cases are handled.

import java.util.*;

public class RotateArray {
    public static void main(String[] args)
    {
        //Rotating the array k steps
        int arr[]={1,2,3,4,5,6,7};
        int k=3;
        int n=arr.length;
        rotate(arr,k);
        for (int i=0;i<n;i++)
        {
            System.out.print(arr[i]+" ");
        }



    }

    static   void rotate(int[] arr, int k)
    {
        //the modulo operator helps us handle cases when k  exceeds the array's length
        int rotation = k % arr.length;
        //Rotating the array using recursion 
        rotate(arr, 0, arr.length - 1);
        rotate(arr, 0, rotation-1);
        rotate(arr, rotation, arr.length - 1);
    }
    private static void rotate(int[] arr, int start, int end)
    {
        while (start < end)
        {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
