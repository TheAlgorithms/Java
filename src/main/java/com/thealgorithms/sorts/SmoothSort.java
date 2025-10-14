package com.thealgorithms.sorts;

/**
 * Implements the SmoothSort algorithm created by Edsger W. Dijkstra.
 *
 * <p>SmoothSort is an adaptive comparison sorting algorithm. It is a variation of
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

    // Leonardo numbers: L(0)=1, L(1)=1, L(k+2)=L(k+1)+L(k)-1
    // We store pre-calculated Leonardo numbers up to a reasonable limit.
    private static final int[] LP = {
        1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891, 35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 1664079, 2692537, 4356617, 7049155, 11405773, 18454929, 29860703, 48315633, 78176337, 126491971, 204668309, 331160281, 535828591, 866988873,
    };

    /**
     * Sorts an array in ascending order using the SmoothSort algorithm.
     *
     * @param <T> the type of elements in the array, which must be comparable.
     * @param arr the array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int n = arr.length;

        // Bitmasks representing the sizes of the Leonardo heaps.
        // q: rightmost heap, r: size of the heap.
        int q = 1;
        int r = 0;
        int p = 1; // Represents the sequence of heap sizes.

        // Build the series of Leonardo heaps.
        while (q < n) {
            r = q;
            q = q + p + 1;
            p = r;
        }

        // Main sorting loop: iteratively place the largest element at the end.
        while (q > 1) {
            q = q - 1;
            int rshift = p;
            p = q - rshift;

            while (rshift > 1) {
                sift(arr, p, rshift);
                rshift = rshift / 2;
                sift(arr, p - rshift, q - rshift + p);
            }
            trinkle(arr, p, q);
        }
        trinkle(arr, 0, 1);
    }

    /**
     * Helper function to restore the heap property by "sifting" the root down.
     * This is used when the root of a heap is smaller than its children.
     */
    private static <T extends Comparable<T>> void sift(T[] arr, int p, int q) {
        int r = q - 1;
        while (r > p) {
            if (arr[p].compareTo(arr[r]) < 0) {
                swap(arr, p, r);
            }
            r--;
        }
    }

    /**
     * Helper function to insert a new element into the heap structure.
     * It "trinkles" the element up to its correct position.
     */
    private static <T extends Comparable<T>> void trinkle(T[] arr, int p, int q) {
        int r = q - 1;
        while (p < r) {
            int maxIndex = p;
            if (arr[maxIndex].compareTo(arr[r]) < 0) {
                maxIndex = r;
            }

            int p1 = p;
            while (p1 < q) {
                int child1 = p1 + 1;
                int child2 = q - (p1 - p);

                if (child1 < q && arr[maxIndex].compareTo(arr[child1]) < 0) {
                    maxIndex = child1;
                }
                if (child2 < q && arr[maxIndex].compareTo(arr[child2]) < 0) {
                    maxIndex = child2;
                }

                int nextP1 = child2;
                if (child1 >= q) {
                    break;
                }
                p1 = nextP1;
            }

            if (maxIndex == p) {
                break;
            }

            swap(arr, p, maxIndex);
            p = maxIndex;
        }
        sift(arr, 0, p);
    }

    /**
     * Swaps two elements in an array.
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}