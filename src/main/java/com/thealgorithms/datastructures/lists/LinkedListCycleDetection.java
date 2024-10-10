package com.thealgorithms.datastructures.lists;

/**
 * This class provides an implementation of cycle detection in a linked list
 * using Floyd's Cycle Detection Algorithm (Tortoise and Hare Algorithm).
 *
 * The algorithm uses two pointers: slow and fast. The slow pointer moves
 * one step at a time, while the fast pointer moves two steps. If there is a
 * cycle in the linked list, the two pointers will meet; otherwise, the fast
 * pointer will reach the end of the list.
 *
 * @author Lochan Paudel
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListCycleDetection {

    /**
     * Function to detect a cycle in the linked list using Floyd's Cycle Detection
     * Algorithm.
     *
     * @param head The head of the linked list
     * @return true if a cycle is detected, otherwise false
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // Edge Case 1: List is empty or contains only one node (no cycle possible)
        }

        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list with two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by one step
            fast = fast.next.next; // Move fast pointer by two steps

            if (slow == fast) { // If the pointers meet, a cycle is detected
                return true;
            }
        }

        return false; // No cycle detected
    }

    /**
     * Main method to run test cases
     *
     * Test cases include:
     * 1. Empty list
     * 2. Single node with no cycle
     * 3. Multiple nodes with no cycle
     * 4. Multiple nodes with a cycle
     */
    public static void main(String[] args) {
        // Test Case 1: Empty List (Edge Case)
        ListNode head1 = null;
        System.out.println("Test Case 1 (Empty List): " + (hasCycle(head1) ? "Cycle Detected" : "No Cycle"));

        // Test Case 2: Single node, no cycle (Edge Case)
        ListNode head2 = new ListNode(1);
        System.out.println("Test Case 2 (Single Node, No Cycle): " + (hasCycle(head2) ? "Cycle Detected" : "No Cycle"));

        // Test Case 3: Multiple nodes, no cycle
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        System.out.println(
                "Test Case 3 (Multiple Nodes, No Cycle): " + (hasCycle(head3) ? "Cycle Detected" : "No Cycle"));

        // Test Case 4: Multiple nodes, with cycle
        ListNode head4 = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        head4.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second; // Creates a cycle
        System.out.println("Test Case 4 (Multiple Nodes, Cycle): " + (hasCycle(head4) ? "Cycle Detected" : "No Cycle"));
    }
}
