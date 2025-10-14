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
    // clang-format off

    /** Private constructor to prevent instantiation of this utility class. */
    private SmoothSort() {}

    // Leonardo numbers: L(0)=1, L(1)=1, L(k)=L(k-1)+L(k-2)+1
    private static final int[] LP = {
        1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193,
        5167, 8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785, 392835,
        635621, 1028457, 1664079, 2692537, 4356617, 7049155, 11405773, 18454929,
        29860703, 48315633, 78176337, 126491971, 204668309, 331160281, 535828591,
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

        int q = 0; // Order of the rightmost heap
        long p = 1; // Bitmask representing heap sizes
        int head = 0; // Current index in array

        while (head < n) {
            if ((p & 3) == 3) {
                p >>>= 2;
                q += 2;
            } else {
                if ((p & 1) == 0) {
                    p = (p << 1) | 1;
                    q = 1;
                } else {
                    p <<= 1;
                    q = 0;
                }
            }

            // Prevent out-of-bounds heap building
            while (head + LP[q] > n) {
                p >>>= 1;
                q--;
            }

            trinkle(arr, head, p, q);
            head++;
        }

        // Sort the heaps
        while (q != 0 || p != 1) {
            if (q <= 1) {
                // Step back through the heaps
                while ((p & 1) == 0) {
                    p >>>= 1;
                    q++;
                }
                p >>>= 1;
            } else {
                p &= ~(1L << q);
                q--;
                int right = head - 1;
                int left = head - 1 - LP[q];
                sift(arr, left + LP[q], q);
                sift(arr, right, q);
            }
            head--;
        }
    }

    private static <T extends Comparable<T>> void trinkle(T[] arr, int head, long p, int q) {
        while (q > 1) {
            int parent = head - LP[q];
            if (parent < 0) {
                return; // prevent negative index
            }

            if (arr[head].compareTo(arr[parent]) <= 0) {
                break;
            }

            swap(arr, head, parent);
            head = parent;
            q -= 2;
        }
        sift(arr, head, q);
    }

    private static <T extends Comparable<T>> void sift(T[] arr, int head, int order) {
        while (order >= 2) {
            int rightChild = head - 1;
            int leftChild = head - 1 - LP[order - 2];

            if (leftChild < 0 || rightChild < 0) {
                return; // safety check
            }

            int largerChild =
                    arr[leftChild].compareTo(arr[rightChild]) >= 0 ? leftChild : rightChild;

            if (arr[head].compareTo(arr[largerChild]) >= 0) {
                break;
            }

            swap(arr, head, largerChild);
            head = largerChild;

            if (largerChild == leftChild) {
                order -= 1;
            } else {
                order -= 2;
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // clang-format on
}
