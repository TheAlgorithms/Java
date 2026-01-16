package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MiddleOfLinkedListTest {

    private static SinglyLinkedListNode listOf(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }

        SinglyLinkedListNode head = new SinglyLinkedListNode(values[0]);
        SinglyLinkedListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new SinglyLinkedListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    @Test
    void middleNodeOddLength() {
        SinglyLinkedListNode head = listOf(1, 2, 3, 4, 5);
        assertEquals(3, MiddleOfLinkedList.middleNode(head).value);
    }

    @Test
    void middleNodeEvenLengthReturnsSecondMiddle() {
        SinglyLinkedListNode head = listOf(1, 2, 3, 4, 5, 6);
        assertEquals(4, MiddleOfLinkedList.middleNode(head).value);
    }

    @Test
    void middleNodeSingleElement() {
        SinglyLinkedListNode head = listOf(42);
        assertEquals(42, MiddleOfLinkedList.middleNode(head).value);
    }

    @Test
    void middleNodeTwoElementsReturnsSecond() {
        SinglyLinkedListNode head = listOf(10, 20);
        assertEquals(20, MiddleOfLinkedList.middleNode(head).value);
    }

    @Test
    void middleNodeNullHead() {
        assertNull(MiddleOfLinkedList.middleNode(null));
    }

    @Test
    void middleNodeDoesNotModifyListStructure() {
        SinglyLinkedListNode first = new SinglyLinkedListNode(1);
        SinglyLinkedListNode second = new SinglyLinkedListNode(2);
        SinglyLinkedListNode third = new SinglyLinkedListNode(3);
        SinglyLinkedListNode fourth = new SinglyLinkedListNode(4);

        first.next = second;
        second.next = third;
        third.next = fourth;

        assertEquals(3, MiddleOfLinkedList.middleNode(first).value);

        assertEquals(second, first.next);
        assertEquals(third, second.next);
        assertEquals(fourth, third.next);
        assertNull(fourth.next);
    }
}
