
public class CountSort 
{
	/*
	 * @param arr The array to be sorted
	 */
	public static int[] countSort(int arr[])
	{
		int[] indexer = new int[256];
		
		for(int i = 0; i < arr.length; i++)
		{
			indexer[arr[i]] += 1;
		}
		
		for(int i = 0; i < indexer.length; i++)
		{
			if(i != 0)
			{
				indexer[i] = indexer[i] + indexer[i - 1];
			}
			
		}
		
		int[] returnArray = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++)
		{
			returnArray[indexer[arr[i]] - 1] = arr[i];
			indexer[arr[i]] -= 1;
		}
		
		return returnArray;
	}
	
	//Main method
	public static void main(String args[])
	{
		int[] data = {1, 3, 2, 4, 2, 5, 3, 6, 4, 3, 5, 10, 11, 32, 9, 101};
		data = countSort(data);
		
		for(int i = 0; i < data.length; i++)
			System.out.println(data[i]);
	}

}
