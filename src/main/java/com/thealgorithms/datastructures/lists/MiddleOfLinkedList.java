package com.thealgorithms.datastructures.lists;

// Definition for singly-linked list node
public class ListNode {
    public int val; // Value stored in the node
    public ListNode next; // Pointer to the next node

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode createList(int[] values) {
        if (values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
