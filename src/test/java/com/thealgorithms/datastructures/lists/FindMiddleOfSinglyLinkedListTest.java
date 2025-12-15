package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

final class FindMiddleOfSinglyLinkedListTest {

    @Test
    void testFindMiddleEmptyList() {
        FindMiddleOfSinglyLinkedList.Node<Integer> head = null;
        var middle = FindMiddleOfSinglyLinkedList.findMiddle(head);
        assertNull(middle, "Middle of an empty list should be null.");
    }

    @Test
    void testFindMiddleSingleElement() {
        var head = new FindMiddleOfSinglyLinkedList.Node<>(1, null);
        var middle = FindMiddleOfSinglyLinkedList.findMiddle(head);
        assertEquals(1, middle.value,
            "Middle of a single-element list should be the only element.");
    }

    @Test
    void testFindMiddleOddElements() {
        var head = new FindMiddleOfSinglyLinkedList.Node<>(1,
            new FindMiddleOfSinglyLinkedList.Node<>(2,
                new FindMiddleOfSinglyLinkedList.Node<>(3,
                    new FindMiddleOfSinglyLinkedList.Node<>(4,
                        new FindMiddleOfSinglyLinkedList.Node<>(5, null)))));

        var middle = FindMiddleOfSinglyLinkedList.findMiddle(head);
        assertEquals(3, middle.value,
            "Middle of a 5-element list should be the 3rd element.");
    }

    @Test
    void testFindMiddleEvenElements() {
        var head = new FindMiddleOfSinglyLinkedList.Node<>(1,
            new FindMiddleOfSinglyLinkedList.Node<>(2,
                new FindMiddleOfSinglyLinkedList.Node<>(3,
                    new FindMiddleOfSinglyLinkedList.Node<>(4, null))));

        var middle = FindMiddleOfSinglyLinkedList.findMiddle(head);
        assertEquals(3, middle.value,
            "Middle of a 4-element list should be the 3rd element.");
    }
}
