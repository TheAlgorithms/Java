import java.util.Arrays;

/**
*
* @author Orel Damari (https://github.com/Oreldm)
*
*/

public class BucketSort {
    /**
     * This method implements the BucketSort for int
     *
     * @param array The array to be sorted
     * @param maxValue The maximum value in the array
     * Sorts the array in increasing order
     **/
	
	public static void bucketSort(int[] array, int maxValue) {
		
		//creating maxValue+1 empty buckets
		int[] bucket = new int[maxValue + 1];

		//putting values in the right bucket
		for (int i = 0; i < array.length; i++) {
			bucket[array[i]]++;
		}

		//Overriding array[] with the values in the right order
		int arrayPos = 0;
		for (int i = 0; i < bucket.length; i++) {
			for (int j = 0; j < bucket[i]; j++) {
				array[arrayPos++] = i;
			}
		}
	}

	
	/** Driver Program**/
	public static void main(String[] args) {
		int[] array = {8, 2, 2, 11, 3, 1, 2, 6, 8, 10, 11, 8};
		int maxValue = 11;

		System.out.println("Before: " + Arrays.toString(array));
		bucketSort(array, maxValue);
		System.out.println("After:  " + Arrays.toString(array));
	}

}
