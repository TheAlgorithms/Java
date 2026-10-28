package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WaveletTreeTest {

    @Test
    public void testRank() {
        int[] arr = {5, 1, 2, 5, 1};
        WaveletTree wt = new WaveletTree(arr);

        // x = 1
        assertEquals(1, wt.rank(1, 1)); // In [5, 1], '1' appears 1 time
        assertEquals(2, wt.rank(1, 4)); // In [5, 1, 2, 5, 1], '1' appears 2 times
        assertEquals(0, wt.rank(1, 0)); // In [5], '1' appears 0 times

        // x = 5
        assertEquals(1, wt.rank(5, 0)); // In [5], '5' appears 1 time
        assertEquals(1, wt.rank(5, 2)); // In [5, 1, 2], '5' appears 1 time
        assertEquals(2, wt.rank(5, 4)); // In [5, 1, 2, 5, 1], '5' appears 2 times

        // Out of bounds / invalid value
        assertEquals(0, wt.rank(10, 4)); // '10' is not in the array
        assertEquals(0, wt.rank(5, -1)); // Invalid end index
    }

    @Test
    public void testSelect() {
        int[] arr = {5, 1, 2, 5, 1};
        WaveletTree wt = new WaveletTree(arr);

        assertEquals(1, wt.select(1, 1)); // 1st '1' is at index 1
        assertEquals(4, wt.select(1, 2)); // 2nd '1' is at index 4

        assertEquals(0, wt.select(5, 1)); // 1st '5' is at index 0
        assertEquals(3, wt.select(5, 2)); // 2nd '5' is at index 3

        assertEquals(2, wt.select(2, 1)); // 1st '2' is at index 2

        assertEquals(-1, wt.select(5, 3)); // 3rd '5' doesn't exist
        assertEquals(-1, wt.select(10, 1)); // '10' doesn't exist
        assertEquals(-1, wt.select(5, 0)); // invalid k
    }

    @Test
    public void testKthSmallest() {
        int[] arr = {5, 1, 2, 5, 1};
        WaveletTree wt = new WaveletTree(arr);

        // Array: [5, 1, 2, 5, 1] -> Sorted: [1, 1, 2, 5, 5]
        assertEquals(1, wt.kthSmallest(0, 4, 1)); // 1st smallest in [5, 1, 2, 5, 1] is 1
        assertEquals(1, wt.kthSmallest(0, 4, 2)); // 2nd smallest in [5, 1, 2, 5, 1] is 1
        assertEquals(2, wt.kthSmallest(0, 4, 3)); // 3rd smallest in [5, 1, 2, 5, 1] is 2
        assertEquals(5, wt.kthSmallest(0, 4, 4)); // 4th smallest in [5, 1, 2, 5, 1] is 5
        assertEquals(5, wt.kthSmallest(0, 4, 5)); // 5th smallest in [5, 1, 2, 5, 1] is 5

        // Subarray: arr[1..3] = [1, 2, 5] -> Sorted: [1, 2, 5]
        assertEquals(1, wt.kthSmallest(1, 3, 1)); // 1st smallest in [1, 2, 5] is 1
        assertEquals(2, wt.kthSmallest(1, 3, 2)); // 2nd smallest in [1, 2, 5] is 2
        assertEquals(5, wt.kthSmallest(1, 3, 3)); // 3rd smallest in [1, 2, 5] is 5

        // Invalid ranges / arguments
        assertEquals(-1, wt.kthSmallest(4, 2, 1)); // Invalid range (left > right)
        assertEquals(-1, wt.kthSmallest(0, 4, 10)); // k > range length
        assertEquals(-1, wt.kthSmallest(0, 4, 0)); // k < 1
    }

    @Test
    public void testEmptyAndSingleElementArray() {
        WaveletTree wtEmpty = new WaveletTree(new int[] {});
        assertEquals(0, wtEmpty.rank(1, 0));
        assertEquals(-1, wtEmpty.select(1, 1));
        assertEquals(-1, wtEmpty.kthSmallest(0, 0, 1));

        WaveletTree wtSingle = new WaveletTree(new int[] {42});
        assertEquals(1, wtSingle.rank(42, 0));
        assertEquals(0, wtSingle.rank(42, -1));
        assertEquals(0, wtSingle.select(42, 1));
        assertEquals(-1, wtSingle.select(42, 2));
        assertEquals(42, wtSingle.kthSmallest(0, 0, 1));
    }

    @Test
    public void testNullArrayAndCustomBounds() {
        WaveletTree wtNull = new WaveletTree(null);
        assertEquals(0, wtNull.rank(1, 0));

        WaveletTree wtNullCustom = new WaveletTree(null, 1, 5);
        assertEquals(-1, wtNullCustom.select(1, 1));

        int[] arr = {5, 1, 2, 5, 1};
        WaveletTree wtCustom = new WaveletTree(arr, 1, 10);
        assertEquals(2, wtCustom.rank(5, 4));
        assertEquals(0, wtCustom.rank(4, 4)); // Query an element inside bounds but not in array
        assertEquals(0, wtCustom.rank(10, 4)); // Query upper bound
    }

    @Test
    public void testNegativeValues() {
        int[] arr = {-5, 10, -2, 0, -5};
        WaveletTree wt = new WaveletTree(arr);

        assertEquals(2, wt.rank(-5, 4));
        assertEquals(1, wt.rank(0, 3));

        assertEquals(0, wt.select(-5, 1));
        assertEquals(4, wt.select(-5, 2));
        assertEquals(3, wt.select(0, 1));

        // Sorted: [-5, -5, -2, 0, 10]
        assertEquals(-5, wt.kthSmallest(0, 4, 1));
        assertEquals(-2, wt.kthSmallest(0, 4, 3));
        assertEquals(10, wt.kthSmallest(0, 4, 5));
    }
}
