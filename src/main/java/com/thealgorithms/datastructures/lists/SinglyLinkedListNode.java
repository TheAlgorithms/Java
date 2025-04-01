package com.thealgorithms.datastructures.lists;

/**
 * This class is the nodes of the SinglyLinked List. They consist of a value and
 * a pointer to the node after them.
 */
class SinglyLinkedListNode {

    int value;
    SinglyLinkedListNode next = null;

    SinglyLinkedListNode() {
    }

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     */
    SinglyLinkedListNode(int value) {
        this(value, null);
    }

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     * @param next Reference to the next node
     */
    SinglyLinkedListNode(int value, SinglyLinkedListNode next) {
        this.value = value;
        this.next = next;
    }
}
