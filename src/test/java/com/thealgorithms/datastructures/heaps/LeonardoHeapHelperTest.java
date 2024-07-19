package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

// TODO: CHeck file name. Check if the file needs to be here or in bitwose operations.
public class LeonardoHeapHelperTest {

    @Test
    public void testConsecutiveTreeIndicesForZero() {
        ArrayList<Integer> arrayList = LeonardoHeapHelper.findConsecutiveTreeIndices(0);
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(-1, -1));

        assertEquals(expectedList, arrayList);
    }

    @Test
    public void testConsecutiveTreeIndicesForEleven() {
        ArrayList<Integer> arrayList = LeonardoHeapHelper.findConsecutiveTreeIndices(11);
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(0, 1));

        assertEquals(expectedList, arrayList);
    }

    @Test
    public void testConsecutiveTreeIndicesForSixteen() {
        ArrayList<Integer> arrayList = LeonardoHeapHelper.findConsecutiveTreeIndices(16);
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(-1, -1));

        assertEquals(expectedList, arrayList);
    }

    @Test
    public void testConsecutiveTreeIndicesForTwentyFour() {
        ArrayList<Integer> arrayList = LeonardoHeapHelper.findConsecutiveTreeIndices(24);
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(3, 4));

        assertEquals(expectedList, arrayList);
    }

    @Test
    public void testfindAllTreeIndicesForZero() {
        Integer[] array = LeonardoHeapHelper.findAllTreeIndices(0);
        assertEquals(0, array.length);
    }

    @Test
    public void testfindAllTreeIndicesForEleven() {
        Integer[] array = LeonardoHeapHelper.findAllTreeIndices(11);
        Integer[] expectedArray = new Integer[] {3, 1, 0};

        assertTrue(Arrays.equals(expectedArray, array));
    }

    @Test
    public void testfindAllTreeIndicesForSixteen() {
        Integer[] array = LeonardoHeapHelper.findAllTreeIndices(16);
        Integer[] expectedArray = new Integer[] {4};

        assertTrue(Arrays.equals(expectedArray, array));
    }

    @Test
    public void testfindAllTreeIndicesForTwentyFour() {
        Integer[] array = LeonardoHeapHelper.findAllTreeIndices(24);
        Integer[] expectedArray = new Integer[] {4, 3};

        assertTrue(Arrays.equals(expectedArray, array));
    }
}
