package com.thealgorithms.datastructures.lists;

/**
 * Rotate a singly linked list to the right
 */
public class RotateSinglyLinkedLists {
    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        Node last = head;
        int length = 1;
        while (last.next != null) {
            last = last.next;
            length++;
        }

        k = k % length;
        if (k == 0) {
            return head;
        }

        Node newLast = head;
        for (int i = 0; i < length - k - 1; i++) {
            newLast = newLast.next;
        }

        Node newHead = newLast.next;
        newLast.next = null;
        last.next = head;
        return newHead;
    }
}