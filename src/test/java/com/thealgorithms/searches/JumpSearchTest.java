package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JumpSearchTest {

    @Test
    void testJumpSearchFound() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 5;
        assertEquals(5, JumpSearch.find(array, key));
    }

    @Test
    void testJumpSearchFirstElement() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 0;
        assertEquals(0, JumpSearch.find(array, key));
    }

    @Test
    void testJumpSearchLastElement() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = 10;
        assertEquals(10, JumpSearch.find(array, key));
    }

    @Test
    void testJumpSearchNotFound() {
        Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer key = -1;
        assertEquals(-1, JumpSearch.find(array, key));
    }

    @Test
    void testJumpSearchEmptyArray() {
        Integer[] array = {};
        Integer key = 1;
        assertEquals(-1, JumpSearch.find(array, key));
    }

    @Test
    void testJumpSearchLargeArray() {
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        }
        Integer key = 256;
        assertEquals(128, JumpSearch.find(array, key));
    }

    @Test
    void testJumpSearchLargeArrayNotFound() {
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        }
        Integer key = 999;
        assertEquals(-1, JumpSearch.find(array, key));
    }
}
