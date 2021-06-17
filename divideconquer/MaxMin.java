/*
This Algorithm finds the Maximum and Minimum elements of an Array.
The method used here is called the Tournament Method which uses divide and conquer algorithmic strategy to solve the problem.
To know visit this Link--> https://en.wikipedia.org/wiki/Divide-and-conquer_algorithm	or	https://www.tutorialspoint.com/design_and_analysis_of_algorithms/design_and_analysis_of_algorithms_max_min_problem.htm
*/
import java.util.*;
public class MaxMin
{
	static int maximum(int a[], int f, int l)
	{
		if(f==l)
			return a[f];
		else
		{
			int mid=(f+l)/2;
			int m1=maximum(a,f,mid);
			int m2=maximum(a,mid+1,l);
			if(m1>m2)
				return m1;
			else
				return m2;
		}
	}
	static int minimum(int a[], int f, int l)
	{
		if(f==l)
			return a[f];
		else
		{
			int mid=(f+l)/2;
			int m1=minimum(a,f,mid);
			int m2=minimum(a,mid+1,l);
			if(m1<m2)
				return m1;
			else
				return m2;
		}
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Size of the Array");
		int n=sc.nextInt();
		int a[]=new int[n];
		System.out.println("Enter the Elements in the Array");
		for(int i=0;i<n;i++)
			a[i]=sc.nextInt();
		int max=maximum(a,0,n-1);
		int min=minimum(a,0,n-1);
		System.out.println("Maximum=> "+max);
		System.out.println("Minimum=> "+min);
	}
}
