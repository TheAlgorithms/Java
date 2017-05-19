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
		int size = 6;
		int array[]=new int[size];
		boolean swap;
		int last = size - 1;
		Scanner input=new Scanner(System.in);
		

		//Input
		System.out.println("Enter any 6 Numbers for Unsorted Array : ");
		for(int i=0; i<6; i++)
		{
			array[i]=input.nextInt();
		}

		//Sorting
		do
     		{
          		swap = false;
          		for (int count = 0; count < last; count++)
          		{
               			if (array[count] > array[count + 1])
               			{
                    			int temp = array[count];
                    			array[count] = array[count + 1];
                    			array[count + 1] = temp;
                    			swap = true;
               			}
          		}
			
          		last--;
     		} while (swap);

		//Output
		for(int i=0; i<6; i++)
		{
			System.out.print(array[i]+"\t");
		}
		input.close();
	}
}
