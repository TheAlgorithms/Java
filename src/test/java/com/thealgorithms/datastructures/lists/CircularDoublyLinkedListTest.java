package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircularDoublyLinkedListTest {

    private CircularDoublyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CircularDoublyLinkedList<>();
    }

    @Test
    public void testInitialSize() {
        assertEquals(0, list.getSize(), "Initial size should be 0.");
    }

    @Test
    public void testAppendAndSize() {
        list.append(10);
        list.append(20);
        list.append(30);

        assertEquals(3, list.getSize(), "Size after appends should be 3.");
        assertEquals("[ 10, 20, 30 ]", list.toString(), "List content should match appended values.");
    }

    @Test
    public void testRemove() {
        list.append(10);
        list.append(20);
        list.append(30);

        int removed = list.remove(1);
        assertEquals(20, removed, "Removed element at index 1 should be 20.");

        assertEquals("[ 10, 30 ]", list.toString(), "List content should reflect removal.");
        assertEquals(2, list.getSize(), "Size after removal should be 2.");

        removed = list.remove(0);
        assertEquals(10, removed, "Removed element at index 0 should be 10.");
        assertEquals("[ 30 ]", list.toString(), "List content should reflect second removal.");
        assertEquals(1, list.getSize(), "Size after second removal should be 1.");
    }

    @Test
    public void testRemoveInvalidIndex() {
        list.append(10);
        list.append(20);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2), "Removing at invalid index 2 should throw exception.");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1), "Removing at negative index should throw exception.");
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", list.toString(), "Empty list should display as [].");
    }

    @Test
    public void testSingleElement() {
        list.append(10);

        assertEquals(1, list.getSize(), "Size after adding single element should be 1.");
        assertEquals("[ 10 ]", list.toString(), "Single element list string should be formatted correctly.");
        int removed = list.remove(0);
        assertEquals(10, removed, "Removed element should be the one appended.");
        assertEquals("[]", list.toString(), "List should be empty after removing last element.");
        assertEquals(0, list.getSize(), "Size after removing last element should be 0.");
    }

    @Test
    public void testNullAppend() {
        assertThrows(NullPointerException.class, () -> list.append(null), "Appending null should throw NullPointerException.");
    }

    @Test
    public void testRemoveLastPosition() {
        list.append(10);
        list.append(20);
        list.append(30);
        int removed = list.remove(list.getSize() - 1);
        assertEquals(30, removed, "Last element removed should be 30.");
        assertEquals(2, list.getSize(), "Size should decrease after removing last element.");
    }

    @Test
    public void testRemoveFromEmptyThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0), "Remove from empty list should throw.");
    }

    @Test
    public void testRepeatedAppendAndRemove() {
        for (int i = 0; i < 100; i++) {
            list.append(i);
        }
        assertEquals(100, list.getSize());

        for (int i = 99; i >= 0; i--) {
            int removed = list.remove(i);
            assertEquals(i, removed, "Removed element should match appended value.");
        }
        assertEquals(0, list.getSize(), "List should be empty after all removes.");
    }

    @Test
    public void testToStringAfterMultipleRemoves() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(2);
        list.remove(0);
        assertEquals("[ 2 ]", list.toString(), "ToString should correctly represent remaining elements.");
    }
}
