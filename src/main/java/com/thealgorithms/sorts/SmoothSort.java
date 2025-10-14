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
    private SmoothSort() {}

    // Leonardo numbers: L(0)=1, L(1)=1, L(k)=L(k-1)+L(k-2)+1
    private static final int[] LP = {
        1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 1664079, 2692537, 4356617, 7049155, 11405773, 18454929, 29860703, 48315633, 78176337, 126491971, 204668309, 331160281, 535828591, 866988873,
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
        int q = 1; // Size of the rightmost heap

        // Phase 1: Build the forest of heaps
        while (head < n) {
            if ((p & 3) == 3) {
                // Combine the last two heaps
                p >>>= 2;
                q = LP[Long.numberOfTrailingZeros(p) + 2] - 1;
            } else if ((p & 1) == 1) {
                p >>>= 1;
                q = 1;
            }

            if (head + q < n) {
                p = (p << 1) | 1;
            } else {
                int order = Long.numberOfTrailingZeros(p);
                while (head + LP[order] >= n) {
                    p &= ~(1L << order);
                    order--;
                }
            }
            head++;
            trinkle(arr, head - 1, p);
        }

        // Phase 2: Deconstruct the forest to sort the array
        while (head > 1) {
            head--;
            long orderMask = p & -p; // lowest set bit
            int order = Long.numberOfTrailingZeros(orderMask);
            if (order > 0) {
                p &= ~orderMask;
                sift(arr, head - LP[order] + 1, order - 1);
            }
        }
    }

    private static <T extends Comparable<T>> void sift(T[] arr, int start, int order) {
        if (order < 2) {
            return;
        }
        int root = start;
        while (order >= 2) {
            int rightChild = root - 1;
            int leftChild = root - LP[order] + LP[order - 1];
            if (arr[root].compareTo(arr[leftChild]) >= 0 && arr[root].compareTo(arr[rightChild]) >= 0) {
                break;
            }
            if (arr[leftChild].compareTo(arr[rightChild]) > 0) {
                swap(arr, root, leftChild);
                root = leftChild;
                order -= 1;
            } else {
                swap(arr, root, rightChild);
                root = rightChild;
                order -= 2;
            }
        }
    }

    private static <T extends Comparable<T>> void trinkle(T[] arr, int head, long p) {
        int root = head;
        while (p > 1) {
            int order = Long.numberOfTrailingZeros(p);
            p &= ~(1L << order);
            int parentOrder = Long.numberOfTrailingZeros(p);
            int parent = root - LP[order];

            if (arr[parent].compareTo(arr[root]) >= 0) {
                break;
            }
            swap(arr, root, parent);
            root = parent;
        }
        sift(arr, root, Long.numberOfTrailingZeros(p & -p));
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}