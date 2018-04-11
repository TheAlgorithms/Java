package sort;

import static sort.SortUtils.*;

/**
 *
 * @author Mateus Bizzo (https://github.com/MattBizzo)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 *
 */

class CocktailShakerSort implements SortAlgorithm {

	/**
	 * This method implements the Generic Cocktail Shaker Sort
	 *
	 * @param array The array to be sorted
	 * Sorts the array in increasing order
	 **/

	@Override
	public <T extends Comparable<T>> T[] sort(T[] array){

		int last = array.length;
		
		// Sorting
		boolean swap;
		do {
			swap = false;
			
			//front
			for (int count = 0; count <= last - 2; count++) {
				if (less(array[count + 1], array[count])) {
					swap = swap(array, count, count + 1);
				}
			}
			//break if no swap occurred
			if (!swap) {
				break;
			}
			swap = false;
			
			//back
			for (int count = last - 2; count >= 0; count--) {
				if (less(array[count + 1], array[count])) {
					swap = swap(array, count, count + 1);
				}
			}
			last--;
		//end
		} while (swap);
		return array;
	}

	// Driver Program
	public static void main(String[] args) {
		// Integer Input
		Integer[] integers = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
		CocktailShakerSort shakerSort = new CocktailShakerSort();

		// Output => 1 4 6 9 12 23 54 78 231
		print(shakerSort.sort(integers));

		// String Input
		String[] strings = { "c", "a", "e", "b", "d" };
		print(shakerSort.sort(strings));
	}


}
