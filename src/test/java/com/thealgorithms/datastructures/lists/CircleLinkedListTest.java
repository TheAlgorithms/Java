package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircleLinkedListTest {

    private CircleLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CircleLinkedList<>();
    }

    @Test
    public void testInitialSize() {
        assertEquals(0, list.getSize(), "Initial size should be 0.");
    }

    @Test
    public void testAppendAndSize() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.getSize(), "Size after three appends should be 3.");
        assertEquals("[ 1, 2, 3 ]", list.toString(), "List content should match appended values.");
    }

    @Test
    public void testRemove() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        assertEquals(2, list.remove(1), "Removed element at index 1 should be 2.");
        assertEquals(3, list.remove(1), "Removed element at index 1 after update should be 3.");
        assertEquals("[ 1, 4 ]", list.toString(), "List content should reflect removals.");
        assertEquals(2, list.getSize(), "Size after two removals should be 2.");
    }

    @Test
    public void testRemoveInvalidIndex() {
        list.append(1);
        list.append(2);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2), "Should throw on out-of-bounds index.");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1), "Should throw on negative index.");
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("[]", list.toString(), "Empty list should be represented by '[]'.");
    }

    @Test
    public void testToStringAfterRemoval() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(1);

        assertEquals("[ 1, 3 ]", list.toString(), "List content should match remaining elements after removal.");
    }

    @Test
    public void testSingleElement() {
        list.append(1);

        assertEquals(1, list.getSize(), "Size after single append should be 1.");
        assertEquals("[ 1 ]", list.toString(), "Single element list should display properly.");
        assertEquals(1, list.remove(0), "Single element removed should match appended value.");
        assertEquals("[]", list.toString(), "List should be empty after removing the single element.");
    }

    @Test
    public void testNullElement() {
        assertThrows(NullPointerException.class, () -> list.append(null), "Appending null should throw exception.");
    }

    @Test
    public void testCircularReference() {
        list.append(1);
        list.append(2);
        list.append(3);
        CircleLinkedList.Node<Integer> current = list.head;

        // Traverse one full cycle and verify the circular reference
        for (int i = 0; i <= list.getSize(); i++) {
            current = current.next;
        }
        assertEquals(list.head, current, "End of list should point back to the head (circular structure).");
    }

    @Test
    public void testClear() {
        list.append(1);
        list.append(2);
        list.append(3);

        // Remove all elements to simulate clearing the list
        for (int i = list.getSize() - 1; i >= 0; i--) {
            list.remove(i);
        }

        assertEquals(0, list.getSize(), "Size after clearing should be 0.");
        assertEquals("[]", list.toString(), "Empty list should be represented by '[]' after clear.");
        assertSame(list.head.next, list.head, "Head's next should point to itself after clearing.");
    }
}
