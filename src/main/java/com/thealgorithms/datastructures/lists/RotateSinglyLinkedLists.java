package com.thealgorithms.datastructures.lists;

/**
 * Rotate a singly linked list
 * 
 */

public class RotateSinglyLinkedLists {

    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = getLength(head);
        k = k % length;
        if (k == 0) {
            return head;
        }

        Node last = getLastNode(head);
        last.next = head;

        int stepsToNewHead = length - k;
        Node newLast = head;
        while (stepsToNewHead > 1) {
            newLast = newLast.next;
            stepsToNewHead--;
        }
        head = newLast.next;
        newLast.next = null;
        return head;
    }

    private int getLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    private Node getLastNode(Node head) {
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }
}
