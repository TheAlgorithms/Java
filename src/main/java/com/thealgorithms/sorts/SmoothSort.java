package com.thealgorithms.sorts;

/**
 * Implements the Smoothsort algorithm created by Edsger W. Dijkstra.
 *
 * <p>Smoothsort is an adaptive comparison sorting algorithm. It is a variation of
 * heapsort that can efficiently sort arrays that are already substantially
 * sorted, approaching an O(n) time complexity in the best case. Its worst-case
 * time complexity is O(n log n).
 *
 * <p>The algorithm works by building a series of "Leonardo heaps", which are
 * heaps that obey a specific size constraint based on Leonardo numbers.
 * The list is first partitioned into this implicit sequence of heaps, and then
 * the largest element is repeatedly extracted and placed in its final sorted
 * position.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Smoothsort">Wikipedia: Smoothsort</a>
 */
public final class SmoothSort {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SmoothSort() {
    }

    // Leonardo numbers: L(0)=1, L(1)=1, L(k)=L(k-1)+L(k-2)+1
    private static final int[] LP = {
        1,
        1,
        3,
        5,
        9,
        15,
        25,
        41,
        67,
        109,
        177,
        287,
        465,
        753,
        1219,
        1973,
        3193,
        5167,
        8361,
        13529,
        21891,
        35421,
        57313,
        92735,
        150049,
        242785,
        392835,
        635621,
        1028457,
        1664079,
        2692537,
        4356617,
        7049155,
        11405773,
        18454929,
        29860703,
        48315633,
        78176337,
        126491971,
        204668309,
        331160281,
        535828591,
        866988873,
    };

    /**
     * Sorts an array in ascending order using the Smoothsort algorithm.
     *
     * @param <T> the type of elements in the array, which must be comparable.
     * @param arr the array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int n = arr.length;

        int head = 0; // The head of the list to be sorted
        long p = 1; // A bitmask representing the sizes of the heaps.
        int q = 1; // Order of the rightmost heap

        // Phase 1: Build the forest of heaps
        for (head = 0; head < n - 1; head++) {
            if ((p & 7) == 3) {
                // Heaps of order k and k-1 merge to form a heap of order k+1
                p >>>= 2;
                q += 2;
            } else {
                p >>>= 1;
                q = (Long.numberOfTrailingZeros(p) + 1);
            }
            p |= 1;
            trinkle(arr, head, p, q);
        }
        trinkle(arr, n - 1, p, q);

        // Phase 2: Deconstruct the forest to sort the array
        for (head = n - 1; head > 0; head--) {
            if (q <= 1) {
                p >>>= q;
                q = (Long.numberOfTrailingZeros(p) + 1);
            } else {
                p >>>= 1;
                q--;
                sift(arr, head - LP[q], q);
                p |= (1L << (q - 1));
                trinkle(arr, head - 1, p, q);
            }
        }
    }

    /**
     * Sifts the root of a heap down to restore the heap property.
     */
    private static <T extends Comparable<T>> void sift(T[] arr, int head, int order) {
        while (order >= 2) {
            int rightChild = head - 1;
            int leftChild = head - LP[order] + LP[order - 1];

            if (arr[head].compareTo(arr[leftChild]) >= 0 && arr[head].compareTo(arr[rightChild]) >= 0) {
                break;
            }

            if (arr[leftChild].compareTo(arr[rightChild]) > 0) {
                swap(arr, head, leftChild);
                head = leftChild;
                order -= 1;
            } else {
                swap(arr, head, rightChild);
                head = rightChild;
                order -= 2;
            }
        }
    }

    /**
     * Restores the heap property by "trinkling" an element up the chain of heaps.
     */
    private static <T extends Comparable<T>> void trinkle(T[] arr, int head, long p, int q) {
        while (p > 1 && (p & 2) != 0) {
            int parent = head - LP[q];
            if (arr[parent].compareTo(arr[head]) >= 0) {
                break;
            }

            swap(arr, head, parent);
            head = parent;
            p >>>= 1;
            q--;
        }
        sift(arr, head, q);
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
