package com.thealgorithms.datastructures.lists;

/**
 * Reverse K Group LinkedList (<a href="https://www.topcoder.com/thrive/articles/reverse-node-in-k-group">...</a>)
 * Author: Bama Charan Chhandogi (<a href="https://github.com/BamaCharanChhandogi">...</a>)
 */

public class ReverseKGroup {
    public int length(Node head) {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }
    // reverse function
    public Node reverse(Node head, int count, int k) {
        if (count < k) {
            return head;
        }
        Node prev = null;
        int count1 = 0;
        Node curr = head;
        Node Next = null;
        while (curr != null && count1 < k) {
            Next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = Next;
            count1++;
        }

        if (Next != null) {
            head.next = reverse(Next, count - k, k);
        }
        return prev;
    }
    public Node reverseKGroup(Node head, int k) {
        int count = length(head);
        return reverse(head, count, k);
    }
}
