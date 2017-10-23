
/**
 *
 * @author Mateus Bizzo (https://github.com/MattBizzo)
 *
 */

class CocktailShakerSort {
	/**
	 * This method implements the Generic Cocktail Shaker Sort
	 *
	 * @param array
	 *            The array to be sorted
	 * @param last
	 *            The count of total number of elements in array Sorts the array in
	 *            increasing order
	 **/

	public static <T extends Comparable<T>> void CS(T array[], int last) {
		
		// Sorting
		boolean swap;
		do {
			swap = false;
			
			//front
			for (int count = 0; count <= last - 2; count++) {
				int comp = array[count].compareTo(array[count + 1]);
				if (comp > 0) {
					T aux = array[count];
					array[count] = array[count + 1];
					array[count + 1] = aux;
					swap = true;
				}
			}
			//break if no swap occurred
			if (!swap) {
				break;
			}
			swap = false;
			
			//back
			for (int count = last - 2; count >= 0; count--) {
				int comp = array[count].compareTo(array[count + 1]);
				if (comp > 0) {
					T aux = array[count];
					array[count] = array[count + 1];
					array[count + 1] = aux;
					swap = true;
				}
			}
			last--;
		//end
		} while (swap);
	}

	// Driver Program
	public static void main(String[] args) {
		// Integer Input
		int[] arr1 = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
		int last = arr1.length;
		Integer[] array = new Integer[last];
		for (int i = 0; i < last; i++) {
			array[i] = arr1[i];
		}

		CS(array, last);

		// Output => 1 4 6 9 12 23 54 78 231
		for (int i = 0; i < last; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();

		// String Input
		String[] array1 = { "c", "a", "e", "b", "d" };
		last = array1.length;

		CS(array1, last);

		// Output => a b c d e
		for (int i = 0; i < last; i++) {
			System.out.print(array1[i] + "\t");
		}
	}
}
