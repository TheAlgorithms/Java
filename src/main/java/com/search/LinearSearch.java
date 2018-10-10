package src.main.java.com.search;

/**
 * Linear search in an array.
 */
public class LinearSearch {
	
	/**
	 * Searches for an element in the array.
	 * 
	 * @param x The value to find
	 * @param array Search array
	 * @return If the element is found it returns its position in the array, otherwise -1
	 */
	public static int search(int x, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (x == array[i]) return i;	// return the position if x is found
		}
		return -1;	// return -1 if x is not found
	}
}
