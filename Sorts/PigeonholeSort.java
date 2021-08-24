package Sorts;
//comment the above line incase it throws an error
import java.util.*;
import java.io.*;
public class PigeonholeSort 
{
    public static void pigeonhole(int[] arr)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(min>arr[i])
                min=arr[i];
            if(max<arr[i])
                max=arr[i];
        }
        int size= (max-min)+1;
        
        int[] holes = new int[size];
        
        for(int x : arr)
        {
            holes[x-min]+=1;
        }
        int i=0;
        for(int j=0;j<size;j++)
        {
            while(holes[j]>0)
            {
                holes[j]-=1;
                arr[i]=j+min;
                i+=1;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter array size:");
        int size=sc.nextInt();
        int[] arr=new int[size];
        System.out.println("Enter the array elements:");
        for(int i=0;i<size;i++)
        {
            arr[i]=sc.nextInt();
        }
        pigeonhole(arr);
        System.out.println("Sorted array: ");
        for(int i: arr)
            System.out.print(i+" ");
        System.out.println();
    }
}
