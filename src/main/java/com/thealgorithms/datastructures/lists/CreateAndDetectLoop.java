package com.thealgorithms.datastructures.lists;

public final class CreateAndDetectLoop {

    // Node class representing a single node in the linked list
    private CreateAndDetectLoop() {
        throw new UnsupportedOperationException("Utility class");
    }
    static final class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    // Method to create a loop between two specific positions in the linked list
    /*
     *  Test case that shows the Cycle(loop) in a LinkedList
     *  Let's take this linked list:
     *  1->2->3->4->5->6
     *     \______/
     *   In this linked list we can see there is a cycle.
     *   we can create loop by calling createLoop function in main after creating LL
     *   createLoop(head,2,5);
     *  to detect there is loop or not we can call detectloop function in main
     *  detectloop(head);
     */

    static void createLoop(Node head, int position1, int position2) {
        if (position1 == 0 || position2 == 0) {
            return;
        }

        Node node1 = head;
        Node node2 = head;

        int count1 = 1;
        int count2 = 1;
        // Traverse to find node at position1
        while (count1 < position1 && node1 != null) {
            node1 = node1.next;
            count1++;
        }

        // Traverse to find node at position2
        while (count2 < position2 && node2 != null) {
            node2 = node2.next;
            count2++;
        }

        // Create a loop by connecting node2's next to node1
        if (node1 != null && node2 != null) {
            node2.next = node1;
        }
    }
    // Method to detect a loop in the linked list
    /**
     * Detects the presence of a loop in the linked list.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_tortoise_and_hare">Floyd's Cycle Detection Algorithm</a>
     * @return true if loop exists else false
     */
    static boolean detectLoop(Node head) {
        Node sptr = head;
        Node fptr = head;

        while (fptr != null && fptr.next != null) {
            sptr = sptr.next;
            fptr = fptr.next.next;
            if (sptr == fptr) {
                return true;
            }
        }
        return false;
    }
}
