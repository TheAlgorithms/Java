package com.thealgorithms.LinkedList;

/**
 * Detects a cycle in a singly linked list using Tortoise and Hare algorithm
 */
public class TortoiseAndHare {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }
}
