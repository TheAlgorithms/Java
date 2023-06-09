package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.less;

/**
 * This is simplified TimSort algorithm implementation. The original one is more complicated.
 * <p>
 * For more details @see <a href="https://en.wikipedia.org/wiki/Timsort">TimSort Algorithm</a>
 */
class TimSort implements SortAlgorithm {
    private static final int SUB_ARRAY_SIZE = 32;
    @SuppressWarnings("rawtypes") private static Comparable[] aux;

    @Override
    public <T extends Comparable<T>> T[] sort(T[] a) {
        int n = a.length;

        InsertionSort insertionSort = new InsertionSort();
        for (int i = 0; i < n; i += SUB_ARRAY_SIZE) {
            insertionSort.sort(a, i, Math.min(i + SUB_ARRAY_SIZE, n));
        }

        aux = new Comparable[n];
        for (int sz = SUB_ARRAY_SIZE; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }

        return a;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (j > hi) {
                a[k] = (T) aux[i++];
            } else if (i > mid) {
                a[k] = (T) aux[j++];
            } else if (less(aux[j], aux[i])) {
                a[k] = (T) aux[j++];
            } else {
                a[k] = (T) aux[i++];
            }
        }
    }
}
