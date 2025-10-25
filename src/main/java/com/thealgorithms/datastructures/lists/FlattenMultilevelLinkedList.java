package com.thealgorithms.datastructures.lists;
/**
 * Implements an algorithm to flatten a multilevel linked list.
 *
 * In this specific problem structure, each node has a `next` pointer (to the
 * next node at the same level) and a `child` pointer (which points to the head
 * of another sorted linked list). The goal is to merge all these lists into a
 * single, vertically sorted linked list using the `child` pointer.
 *
 * The approach is a recursive one that leverages a merge utility, similar to
 * the merge step in Merge Sort. It recursively flattens the list starting from
 * the rightmost node and merges each node's child list with the already
 * flattened list to its right.
 * @see <a href="https://www.geeksforgeeks.org/flattening-a-linked-list/">GeeksforGeeks: Flattening a Linked List</a>
 */
public final class FlattenMultilevelLinkedList {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private FlattenMultilevelLinkedList() {
    }
    /**
     * Node represents an element in the multilevel linked list. It contains the
     * integer data, a reference to the next node at the same level, and a
     * reference to the head of a child list.
     */
    static class Node {
        int data;
        Node next;
        Node child;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.child = null;
        }
    }

    /**
     * Merges two sorted linked lists (connected via the `child` pointer).
     * This is a helper function for the main flatten algorithm.
     *
     * @param a The head of the first sorted list.
     * @param b The head of the second sorted list.
     * @return The head of the merged sorted list.
     */
    private static Node merge(Node a, Node b) {
        // If one of the lists is empty, return the other.
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        Node result;

        // Choose the smaller value as the new head.
        if (a.data < b.data) {
            result = a;
            result.child = merge(a.child, b);
        } else {
            result = b;
            result.child = merge(a, b.child);
        }
        result.next = null; // Ensure the merged list has no `next` pointers.
        return result;
    }

    /**
     * Flattens a multilevel linked list into a single sorted list.
     * The flattened list is connected using the `child` pointers.
     *
     * @param head The head of the top-level list (connected via `next` pointers).
     * @return The head of the fully flattened and sorted list.
     */
    public static Node flatten(Node head) {
        // Base case: if the list is empty or has only one node, it's already flattened.
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively flatten the list starting from the next node.
        head.next = flatten(head.next);

        // Now, merge the current list (head's child list) with the flattened rest of the list.
        head = merge(head, head.next);

        // Return the head of the fully merged list.
        return head;
    }
}
