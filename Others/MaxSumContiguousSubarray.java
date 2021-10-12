/*Kadane's algorithm*/

import java.io.*;
import java.util.*;
public class Max_sum_contiguous_subarray
{
    public static void main(String[] args)throws Exception
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++)
        {
            a[i]=sc.nextInt();
        }
        Max_sum_contiguous_subarray ob=new Max_sum_contiguous_subarray();
        int result=ob.maxSubArray(a);
        System.out.println(result);
    }
    public int maxSubArray(int[] A)
    {
        int l=A.length;
        int max_so_far=Integer.MIN_VALUE,max_ending_here=0;
        for(int i=0;i<l;i++)
        {
            max_ending_here+=A[i];
            if(max_so_far<max_ending_here)
                max_so_far=max_ending_here;
            if(max_ending_here<0)
                max_ending_here=0;
        }
        return max_so_far;
    }
}
