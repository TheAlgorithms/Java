package com.thealgorithms.datastructures.lists;

/**
 * CountSinglyLinkedListRecursion extends a singly linked list to include a
 * recursive count method, which calculates the number of nodes in the list.
 */
public class CountSinglyLinkedListRecursion extends SinglyLinkedList {

    /**
     * Recursively calculates the number of nodes in the list.
     *
     * @param head the head node of the list segment being counted.
     * @return the count of nodes from the given head node onward.
     */
    private int countRecursion(SinglyLinkedListNode head) {
        return head == null ? 0 : 1 + countRecursion(head.next);
    }

    /**
     * Returns the total number of nodes in the list by invoking the recursive
     * count helper method.
     *
     * @return the total node count in the list.
     */
    @Override
    public int count() {
        return countRecursion(getHead());
    }
}
