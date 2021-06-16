import java.util.*;
public class LinearSearchRecurssion
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Size of the Array");
		int n=sc.nextInt();
		int a[]=new int[n];
		System.out.println("Enter the Elements of the Array");
		for(int i=0;i<n;i++)
			a[i]=sc.nextInt();
		System.out.println("Enter the Element to be searched");
		int x=sc.nextInt();
		linear_search(a,x,0,n-1);
	}
	static void linear_search(int a[], int x, int i, int n)
	{
		if(x==a[i])
		{
			i++;
			System.out.println("Found at= "+i);
			return;
		}
		else if(i==n)
		{
			System.out.println("Not Found");
		}
		else
		{
			linear_search(a,x,++i,n);
		}
	}
}