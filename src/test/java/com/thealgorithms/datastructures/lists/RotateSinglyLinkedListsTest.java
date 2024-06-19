package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test cases for RotateSinglyLinkedLists
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public class RotateSinglyLinkedListsTest {

    @Test
    public void testRotateRightEmptyList() {
        RotateSinglyLinkedLists rotator = new RotateSinglyLinkedLists();

        // Test case: Rotate an empty list
        assertNull(rotator.rotateRight(null, 2));
    }

    @Test
    public void testRotateRightSingleNodeList() {
        RotateSinglyLinkedLists rotator = new RotateSinglyLinkedLists();

        // Test case: Rotate a list with one element
        Node singleNode = new Node(5);
        Node rotatedSingleNode = rotator.rotateRight(singleNode, 3);
        assertEquals(5, rotatedSingleNode.value);
        assertNull(rotatedSingleNode.next);
    }

    @Test
    public void testRotateRightMultipleElementsList() {
        RotateSinglyLinkedLists rotator = new RotateSinglyLinkedLists();

        // Test case: Rotate a list with multiple elements (Rotate by 2)
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node rotated1 = rotator.rotateRight(head, 2);
        assertEquals(4, rotated1.value);
        assertEquals(5, rotated1.next.value);
        assertEquals(1, rotated1.next.next.value);
        assertEquals(2, rotated1.next.next.next.value);
        assertEquals(3, rotated1.next.next.next.next.value);
        assertNull(rotated1.next.next.next.next.next);
    }

    @Test
    public void testRotateRightFullRotation() {
        RotateSinglyLinkedLists rotator = new RotateSinglyLinkedLists();

        // Test case: Rotate a list with multiple elements (Full rotation)
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node rotated3 = rotator.rotateRight(head, 7);
        assertEquals(4, rotated3.value);
        assertEquals(5, rotated3.next.value);
        assertEquals(1, rotated3.next.next.value);
        assertEquals(2, rotated3.next.next.next.value);
        assertEquals(3, rotated3.next.next.next.next.value);
        assertNull(rotated3.next.next.next.next.next);
    }
}
