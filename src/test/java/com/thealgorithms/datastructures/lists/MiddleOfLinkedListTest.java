package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public class MiddleOfLinkedListTest {

    private static SinglyLinkedListNode listOf(int firstValue, int... remainingValues) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(firstValue);
        SinglyLinkedListNode current = head;

        for (int i = 0; i < remainingValues.length; i++) {
            current.next = new SinglyLinkedListNode(remainingValues[i]);
            current = current.next;
        }
        return head;
    }

    @Test
    void middleNodeOddLength() {
        SinglyLinkedListNode head = listOf(1, 2, 3, 4, 5);
        SinglyLinkedListNode middle = Objects.requireNonNull(MiddleOfLinkedList.middleNode(head));
        assertEquals(3, middle.value);
    }

    @Test
    void middleNodeEvenLengthReturnsSecondMiddle() {
        SinglyLinkedListNode head = listOf(1, 2, 3, 4, 5, 6);
        SinglyLinkedListNode middle = Objects.requireNonNull(MiddleOfLinkedList.middleNode(head));
        assertEquals(4, middle.value);
    }

    @Test
    void middleNodeSingleElement() {
        SinglyLinkedListNode head = listOf(42);
        SinglyLinkedListNode middle = Objects.requireNonNull(MiddleOfLinkedList.middleNode(head));
        assertEquals(42, middle.value);
    }

    @Test
    void middleNodeTwoElementsReturnsSecond() {
        SinglyLinkedListNode head = listOf(10, 20);
        SinglyLinkedListNode middle = Objects.requireNonNull(MiddleOfLinkedList.middleNode(head));
        assertEquals(20, middle.value);
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

        SinglyLinkedListNode middle = Objects.requireNonNull(MiddleOfLinkedList.middleNode(first));
        assertEquals(3, middle.value);

        assertEquals(second, first.next);
        assertEquals(third, second.next);
        assertEquals(fourth, third.next);
        assertNull(fourth.next);
    }
}
