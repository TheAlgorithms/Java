package sort;

import static sort.SortUtils.*;

public class ThreewayQuickSort implements SortAlgorithm {
	public <T extends Comparable<T>> T[] sort(T[] array) {
		doSort(array, 0, array.length);
		return array;
	}
	
	private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
		if (left >= right) 
			return;
		int[] pivot = partition(array, left, right);
		doSort(array, left, pivot[0] - 1);
		doSort(array, pivot[1], right);
	}
	
	private static <T extends Comparable<T>> int[] partition(T[] array, int left, int right) {
		int[] result = new int[2];
		int n = right - left;
		int p = left;
		int i, j;
		
		swap(array, p, p + n / 2);
		for(int k = i = j = p + 1; k < right; k++) {
			if (array[k].compareTo(array[p]) < 0) {
				swap(array, j, k);
				swap(array, i++, j++);
			}
			else if (array[k].compareTo(array[p]) == 0) {
				swap(array, j++, k);
			}
		}
		swap(array, p, i - 1);
		
		result[0] = i;
		result[1] = j;
		
		return result;
	}

    public static void main(String[] args) {

        // For integer input
        Integer[] array =  {3, 4, 1, 32, 0, 1, 5, 12 ,2, 5 ,7 ,8 ,9, 2, 44, 111, 5};

        ThreewayQuickSort threewayQuickSort = new ThreewayQuickSort();
        threewayQuickSort.sort(array);

        //Output => 0 1 1 2 2 3 4 5 5 5 7 8 9 12 32 44 111
        print(array);

        String[] stringArray =  {"c", "a", "e", "b", "d"};
        threewayQuickSort.sort(stringArray);

        //Output => a	b	c	d	e
        print(stringArray);
    }
}
