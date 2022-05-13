/*
To find the maximum of an array using divide and conquer method we are going to divide the array in two halves and compare the maximum of each half
Author: abirbhattacharya82
*/
import java.util.*;
class Solution
{
    int n;
    int a[];
    Solution(int x)
    {
        n=x;
        a=new int [n];
    }
    int maximum(int f, int l)
    {
        if(f==l)
        {
            return(a[f]);
        }
        else
        {
            int mid=(f+l)/2;
            int a=maximum(f,mid);
            int b=maximum(mid+1,l);
            if(a>b)
            {
                return a;
            }
            else
            {
                return b;
            }
        }
    }
    void mainFunc()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Datas in the array");
        for(int i=0;i<n;i++)
        {
            a[i]=sc.nextInt();
        }
        int max=maximum(0,n-1);
        System.out.println("Maximum Element=> "+max);
    }
}
public class MaximumOfArray
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner (System.in);
        System.out.println("Enter the Size of the Array");
        int n=sc.nextInt();
        Solution obj=new Solution(n);
        obj.mainFunc();
    }
}