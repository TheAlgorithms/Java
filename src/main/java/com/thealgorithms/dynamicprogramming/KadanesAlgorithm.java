import java.util.*;
import java.lang.*;
import java.io.*;

class Kadane
{
	public static void main (String[] args) throws java.lang.Exception
{
        Scanner sc=new Scanner(System.in);  
        System.out.print("Enter the size of the array : ");  
        int n=sc.nextInt();  
        int[] arr=new int[n];  
        System.out.println("Enter the elements of the array : ");  
        for(int i=0;i<n;i++){  
            arr[i]=sc.nextInt();  
        }  
        int result=Integer.MIN_VALUE,max_ending_here=0;  
        for(int i=0;i<n;i++)  
        {  
            max_ending_here+=arr[i];  

            if(result<max_ending_here){  
            result=max_ending_here;  
            }  
            if(max_ending_here<0){  
            max_ending_here=0;  
            }  
        }  
    System.out.print("The Maximum contiguous sum in the array is : "+result);  

	}
}
