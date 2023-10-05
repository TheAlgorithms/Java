package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MajorityElementTest {
    @Test
    public void testMajorityElement() {
        int[] arr1 = { 1, 1, 2, 1, 3, 5, 1 };
        assertEquals(1, MajorityElement.FindMajorityElement(arr1));

        int[] arr2 = { 1, 2, 3, 4, 5 };
        assertEquals(-1, MajorityElement.FindMajorityElement(arr2));

        int[] arr3 = { 3, 3, 4, 2, 4, 4, 2, 4, 4 };
        assertEquals(4, MajorityElement.FindMajorityElement(arr3));
    }
}
