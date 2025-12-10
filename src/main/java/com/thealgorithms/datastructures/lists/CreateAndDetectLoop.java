package com.thealgorithms.datastructures.lists;

/**
 * CreateAndDetectLoop provides utility methods for creating and detecting loops
 * (cycles) in a singly linked list. Loops in a linked list are created by
 * connecting the "next" pointer of one node to a previous node in the list,
 * forming a cycle.
 */
public final class CreateAndDetectLoop {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CreateAndDetectLoop() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Node represents an individual element in the linked list, containing
     * data and a reference to the next node.
     */
    static final class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Creates a loop in a linked list by connecting the next pointer of a node
     * at a specified starting position (position2) to another node at a specified
     * destination position (position1). If either position is invalid, no loop
     * will be created.
     *
     * @param head the head node of the linked list
     * @param position1 the position in the list where the loop should end
     * @param position2 the position in the list where the loop should start
     */
    static void createLoop(Node head, int position1, int position2) {
        if (position1 == 0 || position2 == 0) {
            return;
        }

        Node node1 = head;
        Node node2 = head;

        int count1 = 1;
        int count2 = 1;
        // Traverse to the node at position1
        while (count1 < position1 && node1 != null) {
            node1 = node1.next;
            count1++;
        }

        // Traverse to the node at position2
        while (count2 < position2 && node2 != null) {
            node2 = node2.next;
            count2++;
        }

        // If both nodes are valid, create the loop
        if (node1 != null && node2 != null) {
            node2.next = node1;
        }
    }

    /**
     * Detects the presence of a loop in the linked list using Floyd's cycle-finding
     * algorithm, also known as the "tortoise and hare" method.
     *
     * @param head the head node of the linked list
     * @return true if a loop is detected, false otherwise
     * @see <a href="https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_tortoise_and_hare">
     *     Floyd's Cycle Detection Algorithm</a>
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
