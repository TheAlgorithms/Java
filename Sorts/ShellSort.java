package Sorts;

import static Sorts.SortUtils.*;

public class ShellSort implements SortAlgorithm {

    /**
     * This method implements Generic Shell Sort.
     *
     * @param array the array to be sorted
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int length = array.length;
        int gap = 1;

        /* Calculate gap for optimization purpose */
        while (gap < length / 3) {
            gap = 3 * gap + 1;
        }

        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < length; i++) {
                int j;
		T temp = array[i];
                for (j = i; j >= gap && less(temp, array[j - gap]); j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
        return array;
    }

    /* Driver Code */
    public static void main(String[] args) {
        Integer[] toSort = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        ShellSort sort = new ShellSort();
        print(sort.sort(toSort));
    }
}
