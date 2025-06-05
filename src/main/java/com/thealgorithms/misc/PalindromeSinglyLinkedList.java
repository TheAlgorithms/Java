package com.thealgorithms.misc;

import java.util.Stack;

/**
 * A simple way of knowing if a singly linked list is palindrome is to push all
 * the values into a Stack and then compare the list to popped vales from the
 * Stack.
 *
 * See more:
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
@SuppressWarnings("rawtypes")
public final class PalindromeSinglyLinkedList {
    private PalindromeSinglyLinkedList() {
    }

    public static boolean isPalindrome(final Iterable linkedList) {
        var linkedListValues = new Stack<>();

        for (final var x : linkedList) {
            linkedListValues.push(x);
        }

        for (final var x : linkedList) {
            if (x != linkedListValues.pop()) {
                return false;
            }
        }

        return true;
    }

    // Optimised approach with O(n) time complexity and O(1) space complexity

    public static boolean isPalindromeOptimised(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node midNode = slow;

        Node prevNode = null;
        Node currNode = midNode;
        Node nextNode;
        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        Node left = head;
        Node right = prevNode;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            right = right.next;
            left = left.next;
        }
        return true;
    }
    static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
