package com.thealgorithms.datastructures.lists;

/**
 * Rotate a list
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class RotateSinglyLinkedLists {
    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        Node curr = head;
        int len = 1;
        while (curr.next != null) {
            curr = curr.next;
            len++;
        }

        curr.next = head;
        k = k % len;
        k = len - k;
        while (k > 0) {
            curr = curr.next;
            k--;
        }

        head = curr.next;
        curr.next = null;
        return head;
    }
}
