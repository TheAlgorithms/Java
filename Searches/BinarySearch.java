import java.util.Scanner;

/**
 * Implements a Binary Search in Java
 *
 * @author unknown
 */
class BinarySearch
{
	/**
	 * This method implements the Binary Search
	 *
	 * @param array The array to make the binary search
	 * @param key The number you are looking for
	 * @param lb The lower bound
	 * @param up The  upper bound
	 * @return the location of the key
	 **/
	public static int BS(int array[], int key, int lb, int ub)
	{	
		if ( lb > ub) 		
			return -1;
		
		int mid = (lb + ub) / 2;

		if (key < array[mid]) 		
			return (BS(array, key, lb, mid-1));
		
		if (key > array[mid]) 		
			return (BS(array, key, mid + 1, ub));
		
		return mid;	
	}

	
	/**
	 * This is the main method of Binary Search
	 *
	 * @author Unknown
	 * @param args Command line parameters
	 */

	public static void main(String[] args) 
	{
		Scanner input=new Scanner(System.in);
		int array[]=new int[10]	;
		int key;

		//Input 
		System.out.println("Enter an array of 10 numbers : ");
		
		for (int i = 0; i < 10 ; i++ ) 		
			array[i] = input.nextInt();	
		
		System.out.println("Enter the number to be searched : ");

		key = input.nextInt();

		int index=BS(array, key, 0, 9);

		if (index != -1) 	
			System.out.println("Number found at index number : " + index);		
		else		
			System.out.println("Not found");
		
		input.close();
	}
}
