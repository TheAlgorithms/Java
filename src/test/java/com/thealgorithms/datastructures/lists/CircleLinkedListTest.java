package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CircleLinkedListTest {

    @Test
    public void testAppendAndSize() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.getSize());
        assertEquals("[ 1, 2, 3 ]", list.toString());
    }

    @Test
    public void testRemove() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        assertEquals(2, list.remove(1));
        assertEquals(3, list.remove(1));
        assertEquals("[ 1, 4 ]", list.toString());
        assertEquals(2, list.getSize());
    }

    @Test
    public void testRemoveInvalidIndex() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        list.append(1);
        list.append(2);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    public void testToStringEmpty() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        assertEquals("[]", list.toString());
    }

    @Test
    public void testToStringAfterRemoval() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(1);

        assertEquals("[ 1, 3 ]", list.toString());
    }

    @Test
    public void testSingleElement() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        list.append(1);

        assertEquals(1, list.getSize());
        assertEquals("[ 1 ]", list.toString());
        assertEquals(1, list.remove(0));
        assertEquals("[]", list.toString());
    }

    @Test
    public void testNullElement() {
        CircleLinkedList<String> list = new CircleLinkedList<>();
        assertThrows(NullPointerException.class, () -> list.append(null));
    }
}
