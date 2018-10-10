// Iterative and simple approach for binary search in java
import java.util.*;
import java.lang.*;
import java.io.*;
class Sunil{
    static int BinarySearch(int a[],int n,int k,int l,int h){
        while(l<=h){
            int mid=(l+h)/2;
            if(a[mid]==k)
                return mid;
            else if(a[mid]>k)
                h=mid-1;
            else
                l=mid+1;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception{
    	try{
        Scanner s=new Scanner(System.in);
            int n=s.nextInt();
            int[] a=new int[n];
        for(int i=0;i<n;i++)
            a[i]=s.nextInt();
        int k=s.nextInt();
        int ans=BinarySearch(a,n,k,0,n-1);
        if(ans==-1)
            System.out.println("Element not found in the array");
        else
            System.out.println("Element is found at index "+ans);
    }
    catch(Exception e){
    	e.printStackTrace();
    }
    }
    }
