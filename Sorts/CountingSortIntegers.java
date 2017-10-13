/**
 *
 * @author Youssef Ali (https://github.com/youssefAli11997)
 *
 */

class CountingSortIntegers {

    /**
     * This method implements the Counting Sort for integers
     *
     * @param array The array to be sorted 
     * @param last The count of total number of elements in array
     * Sorts the array in increasing order
     * It sorted only integer arrays especially positive integers
     * It uses array elements as indexes in the frequency array
     * Can accept only array elements within the range [0:10^8]
     **/

    public static void CSI(int array[], int last) {
    
    	// The frequency array. It's initialized with zeros
    	int[] frequency = new int[100000001];
    	// The final output array
    	int[] sortedArray = new int[array.length];
    	int index = 0;
    	
    	// Counting the frequency of @param array elements
    	for(int i=0; i<last; i++){
    		frequency[array[i]]++;
    	}
    	
    	// Filling the sortedArray
    	for(int i=0; i<frequency.length; i++){
    		for(int j=0; j<frequency[i]; j++)
    			sortedArray[index++] = i;
    	}
    	
    	for(int i=0; i<array.length; i++){
    		array[i] = sortedArray[i];
    	}
    }

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        int[] arr1 = {4,23,6,78,1,54,231,9,12};
        int last = arr1.length;
        
        System.out.println("Before Sorting:");
        for (int i=0;i<arr1.length;i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        CSI(arr1, last);

        // Output => 1 4 6 9 12 23 54 78 231
        System.out.println("After Sorting:");
        for (int i=0;i<arr1.length;i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

    }
}
