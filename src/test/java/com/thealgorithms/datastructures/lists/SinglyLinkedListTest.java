package com.thealgorithms.datastructures.lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void shouldRotateList() {
        Node node = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null)))));

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        Node actual = singlyLinkedList.rotateRight(node, 2);

        assertEquals(4, actual.value);
        assertEquals(5, actual.next.value);
        assertEquals(1, actual.next.next.value);
        assertEquals(2, actual.next.next.next.value);
        assertEquals(3, actual.next.next.next.next.value);
    }
}