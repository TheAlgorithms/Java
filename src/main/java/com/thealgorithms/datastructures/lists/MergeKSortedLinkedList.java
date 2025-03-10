package com.thealgorithms.datastructures.lists;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The MergeKSortedLinkedList class provides a method to merge multiple sorted linked lists
 * into a single sorted linked list.
 * This implementation uses a min-heap (priority queue) to efficiently
 * find the smallest node across all lists, thus optimizing the merge process.
 *
 * <p>Example usage:
 * <pre>
 * Node list1 = new Node(1, new Node(4, new Node(5)));
 * Node list2 = new Node(1, new Node(3, new Node(4)));
 * Node list3 = new Node(2, new Node(6));
 * Node[] lists = { list1, list2, list3 };
 *
 * MergeKSortedLinkedList merger = new MergeKSortedLinkedList();
 * Node mergedHead = merger.mergeKList(lists, lists.length);
 * </pre>
 * </p>
 *
 * <p>This class is designed to handle nodes of integer linked lists and can be expanded for additional data types if needed.</p>
 *
 * @author Arun Pandey (https://github.com/pandeyarun709)
 */
public class MergeKSortedLinkedList {

    /**
     * Merges K sorted linked lists into a single sorted linked list.
     *
     * <p>This method uses a priority queue (min-heap) to repeatedly extract the smallest node from the heads of all the lists,
     * then inserts the next node from that list into the heap. The process continues until all nodes have been processed,
     * resulting in a fully merged and sorted linked list.</p>
     *
     * @param a Array of linked list heads to be merged.
     * @param n Number of linked lists.
     * @return Head of the merged sorted linked list.
     */
    Node mergeKList(Node[] a, int n) {
        if (a == null || n == 0) {
            return null;
        }

        // Min Heap to store nodes based on their values for efficient retrieval of the smallest element.
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.data));

        // Initialize the min-heap with the head of each non-null linked list
        for (Node node : a) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // Start merging process
        Node head = minHeap.poll(); // Smallest head is the initial head of the merged list
        if (head != null && head.next != null) {
            minHeap.add(head.next);
        }

        Node curr = head;
        while (!minHeap.isEmpty()) {
            Node temp = minHeap.poll();
            curr.next = temp;
            curr = temp;

            // Add the next node in the current list to the heap if it exists
            if (temp.next != null) {
                minHeap.add(temp.next);
            }
        }

        return head;
    }

    /**
     * Represents a node in the linked list.
     */
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
