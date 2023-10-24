package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MissingNumberTest {
    @Test
    void testMissingNumber() {
        int arr[] = {1, 2, 3, 4, 6};
        assertEquals(5, MissingNumber.missingNumber(arr));
        int arr1[] = {1, 3, 4, 5, 6, 7};
        assertEquals(2, MissingNumber.missingNumber(arr1));
        int arr2[] = {2, 3, 5, 6};
        assertEquals(4, MissingNumber.missingNumber(arr2));
    }
}
