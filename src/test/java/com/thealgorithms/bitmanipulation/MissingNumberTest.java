package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MissingNumberTest {
    @Test
    void testIsEven() {
        int arr1[] = {1,3,4,5,6};
        int arr2[] = {34,35,36,38,39};
        int arr3[] = {6, 5, 4, 2, 1};
        assertEquals(2, MissingNumber.missingNumber(arr1));
        assertEquals(37, MissingNumber.missingNumber(arr2));
        assertEquals(3, MissingNumber.missingNumber(arr3));
    }
}
