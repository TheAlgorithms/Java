import java.util.Scanner;

/**
 * This class implements Insertion Sort
 * 
 * @author Unknown
 *
 */
class InsertionSort
{
	/**
	 * Main Method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) 
	{
		int array[]=new int[6];
		Scanner input=new Scanner(System.in);

		//Input
		System.out.println("Enter any 6 Numbers for Unsorted Array : ");
		for(int i=0; i<6; i++)
		{
			array[i]=input.nextInt();
		}

		//Sorting
		for(int i=0; i<6; i++)
		{
			int temp=array[i];
			int j=i-1;
			while(j>=0 && temp<array[j] )
			{
				array[j+1]=array[j];
				j--;
			}

			array[j+1]=temp;
		}

		//Output
		for(int i=0; i<6; i++)
		{
			System.out.print(array[i]+"\t");
		}

	}
}