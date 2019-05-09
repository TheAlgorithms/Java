package Sorts;

import static Sorts.SortUtils.*;


/**
 * @author dpunosevac
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
public class ShellSort implements SortAlgorithm {

    /**
     * This method implements Generic Shell Sort.
     *
     * @param array The array to be sorted
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int N = array.length;
        int h = 1;

        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    swap(array, j, j - h);
                }
            }

            h /= 3;
        }

        return array;
    }

    public static void main(String[] args) {
        Integer[] toSort = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        ShellSort sort = new ShellSort();
        Integer[] sorted = sort.sort(toSort);

        print(sorted);

    }
}
