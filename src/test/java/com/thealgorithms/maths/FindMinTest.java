package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FindMinTest {

    @Test
    public void findMinTest() {
        testFindMinValue(1, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        test1(5, new int[] {5, 5, 5, 5, 5});
        test2(0, new int[] {0, 192, 384, 576});
        test3(-1, new int[] {-1, 2, 5, 10});
        test4(-10, new int[] {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1});
        testFindMinThrowsExceptionForEmptyInput();
    }

    public void testFindMinValue(int result, int[] array) {
        assertEquals(result, FindMin.findMin(array));
    }

    public void test1(int result, int[] array) {
        assertEquals(result, FindMin.findMin(array));
    }

    public void test2(int result, int[] array) {
        assertEquals(result, FindMin.findMin(array));
    }

    public void test3(int result, int[] array) {
        assertEquals(result, FindMin.findMin(array));
    }

    public void test4(int result, int[] array) {
        assertEquals(result, FindMin.findMin(array));
    }

    public void testFindMinThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> FindMin.findMin(new int[] {}));
    }
}
