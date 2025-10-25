package com.thealgorithms.datastructures.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test cases for RotateSinglyLinkedLists.
 * Author: Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public class RotateSinglyLinkedListsTest {

    private final RotateSinglyLinkedLists rotator = new RotateSinglyLinkedLists();

    // Helper method to create a linked list from an array of values
    private SinglyLinkedListNode createLinkedList(int[] values) {
        if (values.length == 0) {
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

    // Helper method to convert a linked list to a string for easy comparison
    private String linkedListToString(SinglyLinkedListNode head) {
        StringBuilder sb = new StringBuilder();
        SinglyLinkedListNode current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    @Test
    public void testRotateRightEmptyList() {
        // Rotate an empty list
        assertNull(rotator.rotateRight(null, 2));
    }

    @Test
    public void testRotateRightSingleNodeList() {
        // Rotate a list with a single element
        SinglyLinkedListNode singleNode = new SinglyLinkedListNode(5);
        SinglyLinkedListNode rotatedSingleNode = rotator.rotateRight(singleNode, 3);
        assertEquals("5", linkedListToString(rotatedSingleNode));
    }

    @Test
    public void testRotateRightMultipleElementsList() {
        // Rotate a list with multiple elements (rotate by 2)
        SinglyLinkedListNode head = createLinkedList(new int[] {1, 2, 3, 4, 5});
        SinglyLinkedListNode rotated = rotator.rotateRight(head, 2);
        assertEquals("4 -> 5 -> 1 -> 2 -> 3", linkedListToString(rotated));
    }

    @Test
    public void testRotateRightFullRotation() {
        // Rotate by more than the length of the list
        SinglyLinkedListNode head = createLinkedList(new int[] {1, 2, 3, 4, 5});
        SinglyLinkedListNode rotated = rotator.rotateRight(head, 7);
        assertEquals("4 -> 5 -> 1 -> 2 -> 3", linkedListToString(rotated));
    }

    @Test
    public void testRotateRightZeroRotation() {
        // Rotate a list by k = 0 (no rotation)
        SinglyLinkedListNode head = createLinkedList(new int[] {1, 2, 3, 4, 5});
        SinglyLinkedListNode rotated = rotator.rotateRight(head, 0);
        assertEquals("1 -> 2 -> 3 -> 4 -> 5", linkedListToString(rotated));
    }

    @Test
    public void testRotateRightByListLength() {
        // Rotate a list by k equal to list length (no change)
        SinglyLinkedListNode head = createLinkedList(new int[] {1, 2, 3, 4, 5});
        SinglyLinkedListNode rotated = rotator.rotateRight(head, 5);
        assertEquals("1 -> 2 -> 3 -> 4 -> 5", linkedListToString(rotated));
    }

    @Test
    public void testRotateRightByMultipleOfListLength() {
        SinglyLinkedListNode head = createLinkedList(new int[] {1, 2, 3, 4, 5});
        SinglyLinkedListNode rotated = rotator.rotateRight(head, 10); // k = 2 * list length
        assertEquals("1 -> 2 -> 3 -> 4 -> 5", linkedListToString(rotated));
    }

    @Test
    public void testRotateRightLongerList() {
        // Rotate a longer list by a smaller k
        SinglyLinkedListNode head = createLinkedList(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        SinglyLinkedListNode rotated = rotator.rotateRight(head, 4);
        assertEquals("6 -> 7 -> 8 -> 9 -> 1 -> 2 -> 3 -> 4 -> 5", linkedListToString(rotated));
    }
}
