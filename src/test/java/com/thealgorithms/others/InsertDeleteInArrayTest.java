package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link InsertDeleteInArray} class.
 * <p>
 * Tests cover:
 * <ul>
 * <li>Insert operations at various positions</li>
 * <li>Delete operations at various positions</li>
 * <li>Edge cases (empty arrays, single element, boundary positions)</li>
 * <li>Error conditions (null arrays, invalid positions)</li>
 * </ul>
 * </p>
 */
class InsertDeleteInArrayTest {

    // ========== Insert Element Tests ==========

    @Test
    void testInsertAtBeginning() {
        int[] array = {2, 3, 4, 5};
        int[] result = InsertDeleteInArray.insertElement(array, 1, 0);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, result);
    }

    @Test
    void testInsertAtEnd() {
        int[] array = {1, 2, 3, 4};
        int[] result = InsertDeleteInArray.insertElement(array, 5, 4);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, result);
    }

    @Test
    void testInsertInMiddle() {
        int[] array = {1, 2, 4, 5};
        int[] result = InsertDeleteInArray.insertElement(array, 3, 2);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, result);
    }

    @Test
    void testInsertIntoEmptyArray() {
        int[] array = {};
        int[] result = InsertDeleteInArray.insertElement(array, 42, 0);
        assertArrayEquals(new int[] {42}, result);
    }

    @Test
    void testInsertIntoSingleElementArray() {
        int[] array = {5};
        int[] result = InsertDeleteInArray.insertElement(array, 3, 0);
        assertArrayEquals(new int[] {3, 5}, result);
    }

    @Test
    void testInsertNegativeNumber() {
        int[] array = {1, 2, 3};
        int[] result = InsertDeleteInArray.insertElement(array, -10, 1);
        assertArrayEquals(new int[] {1, -10, 2, 3}, result);
    }

    @Test
    void testInsertZero() {
        int[] array = {1, 2, 3};
        int[] result = InsertDeleteInArray.insertElement(array, 0, 1);
        assertArrayEquals(new int[] {1, 0, 2, 3}, result);
    }

    @Test
    void testInsertWithNullArray() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.insertElement(null, 5, 0));
        assertEquals("Array cannot be null", exception.getMessage());
    }

    @Test
    void testInsertWithNegativePosition() {
        int[] array = {1, 2, 3};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.insertElement(array, 5, -1));
        assertEquals("Position must be between 0 and 3", exception.getMessage());
    }

    @Test
    void testInsertWithPositionTooLarge() {
        int[] array = {1, 2, 3};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.insertElement(array, 5, 4));
        assertEquals("Position must be between 0 and 3", exception.getMessage());
    }

    // ========== Delete Element Tests ==========

    @Test
    void testDeleteFromBeginning() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = InsertDeleteInArray.deleteElement(array, 0);
        assertArrayEquals(new int[] {2, 3, 4, 5}, result);
    }

    @Test
    void testDeleteFromEnd() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = InsertDeleteInArray.deleteElement(array, 4);
        assertArrayEquals(new int[] {1, 2, 3, 4}, result);
    }

    @Test
    void testDeleteFromMiddle() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = InsertDeleteInArray.deleteElement(array, 2);
        assertArrayEquals(new int[] {1, 2, 4, 5}, result);
    }

    @Test
    void testDeleteFromSingleElementArray() {
        int[] array = {42};
        int[] result = InsertDeleteInArray.deleteElement(array, 0);
        assertArrayEquals(new int[] {}, result);
    }

    @Test
    void testDeleteFromTwoElementArray() {
        int[] array = {10, 20};
        int[] result = InsertDeleteInArray.deleteElement(array, 1);
        assertArrayEquals(new int[] {10}, result);
    }

    @Test
    void testDeleteWithNullArray() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.deleteElement(null, 0));
        assertEquals("Array cannot be null", exception.getMessage());
    }

    @Test
    void testDeleteFromEmptyArray() {
        int[] array = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.deleteElement(array, 0));
        assertEquals("Array is empty", exception.getMessage());
    }

    @Test
    void testDeleteWithNegativePosition() {
        int[] array = {1, 2, 3};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.deleteElement(array, -1));
        assertEquals("Position must be between 0 and 2", exception.getMessage());
    }

    @Test
    void testDeleteWithPositionTooLarge() {
        int[] array = {1, 2, 3};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.deleteElement(array, 3));
        assertEquals("Position must be between 0 and 2", exception.getMessage());
    }

    @Test
    void testDeleteWithPositionEqualToLength() {
        int[] array = {1, 2, 3, 4, 5};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> InsertDeleteInArray.deleteElement(array, 5));
        assertEquals("Position must be between 0 and 4", exception.getMessage());
    }

    // ========== Combined Operations Tests ==========

    @Test
    void testInsertThenDelete() {
        int[] array = {1, 2, 3};
        int[] afterInsert = InsertDeleteInArray.insertElement(array, 99, 1);
        assertArrayEquals(new int[] {1, 99, 2, 3}, afterInsert);
        int[] afterDelete = InsertDeleteInArray.deleteElement(afterInsert, 1);
        assertArrayEquals(new int[] {1, 2, 3}, afterDelete);
    }

    @Test
    void testMultipleInsertions() {
        int[] array = {1, 3, 5};
        array = InsertDeleteInArray.insertElement(array, 2, 1);
        assertArrayEquals(new int[] {1, 2, 3, 5}, array);
        array = InsertDeleteInArray.insertElement(array, 4, 3);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
    }

    @Test
    void testMultipleDeletions() {
        int[] array = {1, 2, 3, 4, 5};
        array = InsertDeleteInArray.deleteElement(array, 2);
        assertArrayEquals(new int[] {1, 2, 4, 5}, array);
        array = InsertDeleteInArray.deleteElement(array, 0);
        assertArrayEquals(new int[] {2, 4, 5}, array);
    }

    @Test
    void testLargeArray() {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        int[] result = InsertDeleteInArray.insertElement(array, 999, 50);
        assertEquals(101, result.length);
        assertEquals(999, result[50]);
        assertEquals(49, result[49]);
        assertEquals(50, result[51]);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] array = {1, 2, 2, 3, 2};
        int[] result = InsertDeleteInArray.deleteElement(array, 1);
        assertArrayEquals(new int[] {1, 2, 3, 2}, result);
    }

    @Test
    void testNegativeNumbers() {
        int[] array = {-5, -3, -1, 0, 1};
        int[] result = InsertDeleteInArray.insertElement(array, -2, 2);
        assertArrayEquals(new int[] {-5, -3, -2, -1, 0, 1}, result);
    }
}
