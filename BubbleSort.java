import java.util.Scanner;

/**
 * This class implements BubbleSort
 * 
 * @author Unknown
 *
 */

class BubbleSort
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
		for(int i=0; i<5; i++)
		{
			for(int j=i+1; j<6; j++)
			{
				if(array[j]>array[i])
				{
					int temp=array[j];
					array[j]=array[i];
					array[i]=temp;
				}
			}
		}

		//Output
		for(int i=0; i<6; i++)
		{
			System.out.print(array[i]+"\t");
		}
		input.close();
	}
}
