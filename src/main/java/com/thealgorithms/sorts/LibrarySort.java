package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * Library Sort (also known as Gapped Insertion Sort) maintains a sparse
 * working array with gaps distributed between elements, so that most
 * insertions land directly in an empty gap without shifting anything.
 * Elements are inserted in rounds that double in size (1, 2, 4, 8, ...);
 * after each round the array is rebalanced so gaps are spread out evenly
 * again for the next round.
 * Time Complexity: O(n log n) expected, O(n^2) worst case if gaps collapse
 * Space Complexity: O(n)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Library_sort">
 *     Wikipedia: Library Sort</a>
 * @author Vraj Prajapati (@Rosander0)
 */
public final class LibrarySort {

    private static final int GAP_FACTOR = 2;

    private LibrarySort() {
        // Utility class
    }

    /**
     * Sorts an array using the Library Sort algorithm.
     *
     * @param array the array to sort (must not be null)
     * @return the sorted array
     * @throws IllegalArgumentException if {@code array} is {@code null}
     */
    public static int[] sort(final int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array must not be null.");
        }
        if (array.length <= 1) {
            return array;
        }

        final int n = array.length;
        final int capacity = GAP_FACTOR * n;
        final int[] data = new int[capacity];
        final boolean[] occupied = new boolean[capacity];

        final int mid = capacity / 2;
        data[mid] = array[0];
        occupied[mid] = true;

        int filled = 1;
        int nextToInsert = 1;
        int round = 0;
        while (nextToInsert < n) {
            final int roundSize = Math.min(1 << round, n - nextToInsert);
            for (int i = 0; i < roundSize; i++) {
                insert(data, occupied, array[nextToInsert + i]);
                filled++;
            }
            nextToInsert += roundSize;
            round++;
            if (nextToInsert < n) {
                rebalance(data, occupied, filled);
            }
        }

        int idx = 0;
        for (int i = 0; i < capacity; i++) {
            if (occupied[i]) {
                array[idx++] = data[i];
            }
        }
        return array;
    }

    /**
     * Inserts {@code value} into the gapped array, placing it directly in an
     * empty gap when possible, otherwise shifting toward the nearest gap.
     */
    private static void insert(final int[] data, final boolean[] occupied, final int value) {
        final int pos = findInsertionIndex(data, occupied, value);
        if (pos >= data.length) {
            insertAtEnd(data, occupied, value);
            return;
        }

        if (!occupied[pos]) {
            data[pos] = value;
            occupied[pos] = true;
            return;
        }

        int right = pos;
        while (right < data.length && occupied[right]) {
            right++;
        }
        int left = pos - 1;
        while (left >= 0 && occupied[left]) {
            left--;
        }

        final boolean canGoRight = right < data.length;
        final boolean canGoLeft = left >= 0;

        if (canGoRight && (!canGoLeft || (right - pos) <= (pos - left))) {
            // Shift data[pos, right) one slot to the right, opening a gap at pos.
            // occupied[pos] is untouched by the copy and was already true.
            System.arraycopy(data, pos, data, pos + 1, right - pos);
            occupied[right] = true;
            data[pos] = value;
        } else if (canGoLeft) {
            // Shift data[left + 1, pos) one slot to the left, opening a gap at pos - 1.
            // occupied[pos - 1] is untouched by the copy and was already true.
            System.arraycopy(data, left + 1, data, left, pos - 1 - left);
            occupied[left] = true;
            data[pos - 1] = value;
        } else {
            // Unreachable in practice: canGoRight and canGoLeft can only both be false if
            // every slot in this capacity-2n array is occupied, but at most n elements are
            // ever present at once. Kept as a defensive guard against that invariant breaking.
            throw new IllegalStateException("No gap available for insertion; rebalance too infrequent.");
        }
    }

    /**
     * Handles insertion of a new global maximum, which must land after every
     * currently occupied slot. Since there is no room to its right, this
     * shifts occupied slots left into the nearest gap instead.
     */
    private static void insertAtEnd(final int[] data, final boolean[] occupied, final int value) {
        final int last = data.length - 1;
        // occupied[last] is unreachable as false here: insertAtEnd() is only called when
        // findInsertionIndex() returns data.length, which requires data[last] to already be
        // occupied. Kept as a defensive guard in case that invariant is ever broken.
        if (!occupied[last]) {
            data[last] = value;
            occupied[last] = true;
            return;
        }
        int left = last - 1;
        while (left >= 0 && occupied[left]) {
            left--;
        }
        // left < 0 is unreachable in practice: at most n elements ever occupy this
        // capacity-2n array, so fewer than half the slots left of `last` can be filled,
        // guaranteeing a gap exists before the scan reaches index -1.
        if (left < 0) {
            throw new IllegalStateException("No gap available for insertion; rebalance too infrequent.");
        }
        // Shift data[left + 1, last] one slot to the left, opening a gap at last.
        // occupied[last] is untouched by the copy and was already true.
        System.arraycopy(data, left + 1, data, left, last - left);
        occupied[left] = true;
        data[last] = value;
    }

    /**
     * Finds the leftmost index at which {@code value} can be inserted so
     * that occupied slots remain sorted. Empty slots are compared using the
     * value of the nearest occupied slot at or after them, which is a
     * monotonic function of index and therefore safe to binary search over.
     */
    private static int findInsertionIndex(final int[] data, final boolean[] occupied, final int value) {
        int lo = 0;
        int hi = data.length;
        while (lo < hi) {
            final int mid = lo + (hi - lo) / 2;
            final int probe = nearestOccupiedValueAtOrAfter(data, occupied, mid);
            if (probe != Integer.MAX_VALUE && probe <= value) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private static int nearestOccupiedValueAtOrAfter(final int[] data, final boolean[] occupied, final int index) {
        for (int i = index; i < data.length; i++) {
            if (occupied[i]) {
                return data[i];
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Redistributes the {@code filled} occupied elements evenly across the
     * full capacity of {@code data}, restoring uniform gaps between them.
     */
    private static void rebalance(final int[] data, final boolean[] occupied, final int filled) {
        final int capacity = data.length;
        final int[] temp = new int[filled];
        int idx = 0;
        for (int i = 0; i < capacity; i++) {
            if (occupied[i]) {
                temp[idx++] = data[i];
            }
        }
        Arrays.fill(occupied, false);
        for (int k = 0; k < filled; k++) {
            final int pos = (int) ((long) k * capacity / filled);
            data[pos] = temp[k];
            occupied[pos] = true;
        }
    }
}
