package com.thealgorithms.datastructures.lists;

/**
 * Rotate a Generic LinkedList
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 */

public class RotateLinkedList<T> {

    // Definition for singly-linked list
    public class ListNode<T> {
        T val;
        ListNode<T> next;
        
        ListNode(T x) { val = x; }
    }

    public ListNode<T> rotateRight(ListNode<T> head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode<T> tail = head;

        // Find the length of the linked list and locate the tail node
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Connect the tail node to the head to form a circular linked list
        tail.next = head;

        // Calculate the new head position after rotation
        int newHeadIndex = length - (k % length);

        // Find the new head node
        ListNode<T> newTail = tail;
        while (newHeadIndex > 0) {
            newTail = newTail.next;
            newHeadIndex--;
        }

        // Set the new head and break the circular link
        ListNode<T> newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    // Test cases
    public static void main(String[] args) {
        RotateLinkedList<Integer> rotateLinkedList = new RotateLinkedList<>();
        
        // Creating and testing linked lists
        
        // Example test case
        RotateLinkedList<Integer>.ListNode<Integer> head = rotateLinkedList.new ListNode<>(1);
        head.next = rotateLinkedList.new ListNode<>(2);
        head.next.next = rotateLinkedList.new ListNode<>(3);
        head.next.next.next = rotateLinkedList.new ListNode<>(4);
        head.next.next.next.next = rotateLinkedList.new ListNode<>(5);
        int k = 2;
        RotateLinkedList<Integer>.ListNode<Integer> newHead = rotateLinkedList.rotateRight(head, k);
        rotateLinkedList.printLinkedList(newHead); 
        // Output: 4 -> 5 -> 1 -> 2 -> 3
        
        // Additional test cases can be added similarly
    }
    
    // Helper function to print the linked list
    private <T> void printLinkedList(ListNode<T> head) {
        ListNode<T> current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

