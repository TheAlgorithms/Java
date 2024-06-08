package com.thealgorithms.datastructures.lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Arun Pandey (https://github.com/pandeyarun709)
 */
public class MergeKSortedLinkedlist {

    /**
     * This function merge K sorted LinkedList
     *
     * @param a array of LinkedList
     * @param n size of array
     * @return node
     */
    Node mergeKList(Node[] a, int n) {
        // Min Heap
        PriorityQueue<Node> min = new PriorityQueue<>(Comparator.comparingInt(x -> x.data));

        // adding head of all linkedList in min heap
        min.addAll(Arrays.asList(a).subList(0, n));

        // Make new head among smallest heads in K linkedList
        Node head = min.poll();
        min.add(head.next);
        Node curr = head;

        // merging LinkedList
        while (!min.isEmpty()) {
            Node temp = min.poll();
            curr.next = temp;
            curr = temp;

            // Add Node in min Heap only if temp.next is not null
            if (temp.next != null) {
                min.add(temp.next);
            }
        }

        return head;
    }

    private final class Node {

        private int data;
        private Node next;
    }
}
