package com.thealgorithms.sorts;

/**
 * Smooth Sort is an in-place, comparison-based sorting algorithm proposed by Edsger W. Dijkstra (1981).
 *
 * <p>It can be viewed as a variant of heapsort that maintains a forest of heap-ordered Leonardo trees
 * (trees whose sizes are Leonardo numbers). The algorithm is adaptive: when the input is already
 * sorted or nearly sorted, the heap invariants are often satisfied and the expensive rebalancing
 * operations do little work, yielding near-linear behavior.
 *
 * <p>Time Complexity:
 * <ul>
 *   <li>Best case: O(n) for already sorted input</li>
 *   <li>Average case: O(n log n)</li>
 *   <li>Worst case: O(n log n)</li>
 * </ul>
 *
 * <p>Space Complexity: O(1) auxiliary space (in-place).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Smoothsort">Smoothsort</a>
 * @see <a href="https://en.wikipedia.org/wiki/Leonardo_number">Leonardo numbers</a>
 * @see SortAlgorithm
 */
public class SmoothSort implements SortAlgorithm {

    /**
     * Leonardo numbers (L(0) = L(1) = 1, L(k+2) = L(k+1) + L(k) + 1) up to the largest value that
     * fits into a signed 32-bit integer.
     */
    private static final int[] LEONARDO = {
        1, 1, 3, 5, 9, 15, 25, 41, 67, 109,
        177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529,
        21891, 35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457, 1664079,
        2692537, 4356617, 7049155, 11405773, 18454929, 29860703, 48315633, 78176337, 126491971, 204668309,
        331160281, 535828591, 866988873, 1402817465
    };

    /**
     * Sorts the given array in ascending order using Smooth Sort.
     *
     * @param array the array to sort
     * @param <T>   the element type
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(final T[] array) {
        if (array.length < 2) {
            return array;
        }

        final int last = array.length - 1;

        // The forest shape is encoded as (p, pshift): p is a bit-vector of present tree orders,
        // shifted right by pshift. pshift is the order of the rightmost (current) Leonardo tree.
        long p = 1L;
        int pshift = 1;

        int head = 0;
        while (head < last) {
            if ((p & 3L) == 3L) {
                sift(array, pshift, head);
                p >>>= 2;
                pshift += 2;
            } else {
                // Add a new singleton tree; if it will not be merged anymore, we must fully trinkle.
                if (LEONARDO[pshift - 1] >= last - head) {
                    trinkle(array, p, pshift, head, false);
                } else {
                    // This tree will be merged later, so it is enough to restore its internal heap property.
                    sift(array, pshift, head);
                }

                if (pshift == 1) {
                    // If L(1) is used, the new singleton is L(0).
                    p <<= 1;
                    pshift = 0;
                } else {
                    // Otherwise, shift to order 1 and append a singleton of order 1.
                    p <<= (pshift - 1);
                    pshift = 1;
                }
            }

            p |= 1L;
            head++;
        }

        trinkle(array, p, pshift, head, false);

        // Repeatedly remove the maximum (always at head) by shrinking the heap region.
        while (pshift != 1 || p != 1L) {
            if (pshift <= 1) {
                // Rightmost tree is a singleton (order 0 or 1). Move to the previous tree root.
                final long mask = p & ~1L;
                final int shift = Long.numberOfTrailingZeros(mask);
                p >>>= shift;
                pshift += shift;
            } else {
                // Split a tree of order (pshift) into two children trees of orders (pshift-1) and (pshift-2).
                p <<= 2;
                p ^= 7L;
                pshift -= 2;

                trinkle(array, p >>> 1, pshift + 1, head - LEONARDO[pshift] - 1, true);
                trinkle(array, p, pshift, head - 1, true);
            }

            head--;
        }

        return array;
    }

    private static <T extends Comparable<T>> void sift(final T[] array, int order, int root) {
        final T value = array[root];

        while (order > 1) {
            final int right = root - 1;
            final int left = root - 1 - LEONARDO[order - 2];

            if (!SortUtils.less(value, array[left]) && !SortUtils.less(value, array[right])) {
                break;
            }

            if (!SortUtils.less(array[left], array[right])) {
                array[root] = array[left];
                root = left;
                order -= 1;
            } else {
                array[root] = array[right];
                root = right;
                order -= 2;
            }
        }

        array[root] = value;
    }

    private static <T extends Comparable<T>> void trinkle(final T[] array, long p, int order, int root, boolean trusty) {
        final T value = array[root];

        while (p != 1L) {
            final int stepson = root - LEONARDO[order];

            if (!SortUtils.less(value, array[stepson])) {
                break;
            }

            if (!trusty && order > 1) {
                final int right = root - 1;
                final int left = root - 1 - LEONARDO[order - 2];

                if (!SortUtils.less(array[right], array[stepson]) || !SortUtils.less(array[left], array[stepson])) {
                    break;
                }
            }

            array[root] = array[stepson];
            root = stepson;

            final long mask = p & ~1L;
            final int shift = Long.numberOfTrailingZeros(mask);
            p >>>= shift;
            order += shift;
            trusty = false;
        }

        if (!trusty) {
            array[root] = value;
            sift(array, order, root);
        }
    }
}
