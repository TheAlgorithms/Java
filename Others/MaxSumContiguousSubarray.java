/*Kadane's algorithm*/

import java.io.*;
import java.util.*;
public class MaxSumContiguousSubarray
{
    public static void main(String[] args)throws Exception
    {
        Random rand=new Random();
        int n=rand.nextInt(10);
        int[] a=new int[n];
        for(int i=0;i<n;i++)
        {
            a[i]=sc.nextInt();
        }
        MaxSumContiguousSubarray ob=new MaxSumContiguousSubarray();
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
