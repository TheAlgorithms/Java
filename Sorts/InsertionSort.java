/**
 *
 * @author Shubham Singh (https://github.com/ss3681755) 
 *
 */

class InsertionSort {

    /**
     * This method implements the Generic Insertion Sort
     *
     * @param initialArray The array to be sorted 
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void sort(T[] initialArray) {
    	for (int index = 1; index < initialArray.length; index++) {

    		// Picking up the key
    		T key = initialArray[index];
    		int previousIndex = index - 1;

    		// Keep on comparing key with its previous elements until all 
    		// the elements till a particular index are sorted.
    		while(previousIndex >= 0 && key.compareTo(initialArray[previousIndex]) <= 0) {
    			initialArray[previousIndex + 1] = initialArray[previousIndex];
    			previousIndex -= 1;
    		}

    		// Copy the key to index before which all the elements are sorted.
    		initialArray[previousIndex + 1] = key;
    	}
    }

}
