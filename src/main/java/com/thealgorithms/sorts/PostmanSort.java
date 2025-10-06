package com.thealgorithms.sorts;

/**
 * Postman Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Postman_sort">Postman Sort Algorithm</a>
 */
public class PostmanSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        postmanSort(array);
        return array;
    }

    private <T extends Comparable<T>> void postmanSort(T[] array) {
        int n = array.length;
        double gapFactor = 1.3;
        int gap = (int) (n / gapFactor);

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                T temp = array[i];
                int j = i;
                while (j >= gap && SortUtils.greater(array[j - gap], temp)) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
            if (gap == 1) {
                break;
            }
            gap = (int) (gap / gapFactor);
            if (gap < 1) {
                gap = 1;
            }
        }
    }
}
