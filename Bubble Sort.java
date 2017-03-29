import java.util.Scanner;

class BubbleSort
{
	public static void main(String[] args)
	{
		int n;
		int array[]=new int[n];
		Scanner input=new Scanner(System.in);
    System.out.println("Enter length of array:");
		n=sc.nextInt();
		//Input
		System.out.println("Enter any n Numbers for Unsorted Array : ");
		for(int i=0; i<n; i++)
		{
			array[i]=input.nextInt();
		}

		//Sorting
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n-1-i; j++)
			{
				if(array[j]>array[j+1])
				{
					int temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}

		//Output
		for(int i=0; i<n; i++)
		{
			System.out.print(array[i]+"\t");
		}

	}
}
