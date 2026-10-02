package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class HowManyTimesRotatedTest {

    @Test
    public void testHowManyTimesRotated() {
        int[] arr1 = {5, 1, 2, 3, 4};
        assertEquals(1, HowManyTimesRotated.rotated(arr1));
        int[] arr2 = {15, 17, 2, 3, 5};
        assertEquals(2, HowManyTimesRotated.rotated(arr2));
    }

    /** An unrotated (already sorted) array should resolve to 0 rotations without hanging. */
    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testHowManyTimesRotatedOnUnrotatedArray() {
        int[] arr = {2, 5, 6, 8, 11, 12, 15, 18};
        assertEquals(0, HowManyTimesRotated.rotated(arr));
    }

    /** Arrays of size 1 and 2 should not throw ArrayIndexOutOfBoundsException. */
    @Test
    public void testHowManyTimesRotatedOnSmallArrays() {
        assertEquals(0, HowManyTimesRotated.rotated(new int[] {5}));
        assertEquals(0, HowManyTimesRotated.rotated(new int[] {1, 2}));
        assertEquals(1, HowManyTimesRotated.rotated(new int[] {2, 1}));
    }
}
