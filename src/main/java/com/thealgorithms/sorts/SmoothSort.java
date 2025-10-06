package com.thealgorithms.sorts;

/**
 * Smooth Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Smoothsort">Smooth Sort Algorithm</a>
 */
public class SmoothSort implements SortAlgorithm {

    private static final int[] LEONARDO = {1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891, 35421};

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        smoothSort(array, array.length);
        return array;
    }

    private <T extends Comparable<T>> void smoothSort(T[] array, int n) {
        int q = 1;
        int r = 0;
        int p = 1;
        int b = 1;
        int c = 1;

        while (q < n) {
            if ((p & 3) == 1) {
                sift(array, r, q);
                q++;
                r++;
            } else {
                if (LEONARDO[p - 1] < n - q) {
                    sift(array, r, q);
                    q += LEONARDO[p - 1];
                    r++;
                    p--;
                } else {
                    sift(array, r, q);
                    r = r + p - 1;
                    p = c;
                    q++;
                }
            }
            p++;
        }

        for (int i = r; i > 0; i--) {
            if (p > 1) {
                p--;
                q = q - LEONARDO[p];
                trinkle(array, p, q, r);
                p--;
                q = q + LEONARDO[p];
                trinkle(array, p, q, r - 1);
                r--;
            }
        }
    }

    private <T extends Comparable<T>> void sift(T[] array, int pshift, int head) {
        T val = array[head];
        while (pshift > 1) {
            int rt = head - 1;
            int lf = head - 1 - LEONARDO[pshift - 2];
            if (SortUtils.less(val, array[lf]) || SortUtils.less(val, array[rt])) {
                if (SortUtils.less(array[lf], array[rt])) {
                    array[head] = array[rt];
                    head = rt;
                    pshift -= 1;
                } else {
                    array[head] = array[lf];
                    head = lf;
                    pshift -= 2;
                }
            } else {
                break;
            }
        }
        array[head] = val;
    }

    private <T extends Comparable<T>> void trinkle(T[] array, int p, int head, int tail) {
        T val = array[head];
        while (p > 0) {
            int stepson = head - LEONARDO[p];
            if (SortUtils.less(val, array[stepson])) {
                array[head] = array[stepson];
                head = stepson;
                p--;
            } else {
                break;
            }
        }
        array[head] = val;
        sift(array, p, head);
    }
}