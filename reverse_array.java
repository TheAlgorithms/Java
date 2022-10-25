package array;
//REVERSE THE ARRAY
import java.util.*;

public class Q1 
{
	public static int[] reverse(int a[],int n)
	{
		int start=0,end=n-1;
		while(start<end)
		{
			int temp=a[start];
			a[start]=a[end];
			a[end]=temp;
			start=start+1;
			end=end-1;
		}
		return a;
	}
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("\nENTER SIZE OF ARRAY:");
		int n=sc.nextInt();
		int [] a=new int[n];
		System.out.println("\nENTER ARRAY ELEMENTS:");
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}
		System.out.println("\nARRAY ELEMENTS:");
		for(int i=0;i<n;i++)
		{
			System.out.println(a[i]+"\t");
		}
		/*
		int start=0;
		int end=n-1;
		while(start<end)
		{
			int temp=a[start];
			a[start]=a[end];
			a[end]=temp;
			start=start+1;
			end=end-1;
		}*/
		reverse(a,n);
		System.out.println("\nARRAY ELEMENTS AFTER REVERSE:");
		for(int i=0;i<n;i++)
		{
			System.out.println(a[i]+"\t");
		}
		sc.close();
		
	}

}
