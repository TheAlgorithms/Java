import java.util.Scanner;

class BinarySearch
{
	public static int BS(int array[], int key, int lb, int ub)
	{
		if (lb>ub) 
		{
			return -1;
		}

		int mid=(lb+ub)/2;

		if (key<array[mid]) 
		{
			return (BS(array, key, lb, mid-1));
		}
		else if (key>array[mid]) 
		{
			return (BS(array, key, mid+1, ub));
		}
		else
		{
			return mid;
		}
	}

	public static void main(String[] args) 
	{
		Scanner input=new Scanner(System.in);
		int array[]=new int[10]	;
		int key;

		//Input 
		System.out.println("Enter an array of 10 numbers : ");
		for (int i=0; i<10 ;i++ ) 
		{
			array[i]=input.nextInt();	
		}
		System.out.println("Enter the number to be searched : ");
		key=input.nextInt();

		int index=BS(array, key, 0, 9);
		if (index!=-1) 
		{
			System.out.println("Number found at index number : " + index);
		}
		else
		{
			System.out.println("Not found");
		}
	}
}