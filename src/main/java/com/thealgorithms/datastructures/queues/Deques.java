package com.thealgorithms.datastructures.queues;

/**
 * A [deque](https://en.wikipedia.org/wiki/Double-ended_queue) is short for a
 * double ended queue pronounced "deck" and sometimes referred to as a head-tail
 * linked list. A deque is a data structure based on a doubly linked list, but
 * only supports adding and removal of nodes from the beginning and the end of
 * the list.
 *
 * @author [Ian Cowan](https://github.com/iccowan)
 */
public class Deques<T> {

    /**
     * Node for the deque
     */
    class DequeNode<S> {

        /**
         * Value of the node
         */
        S val;

        /**
         * Next node in the deque from this node
         */
        DequeNode<S> next = null;

        /**
         * Previous node in the deque from this node
         */
        DequeNode<S> prev = null;

        /**
         * Constructor
         */
        DequeNode(S val) {
            this.val = val;
        }
    }

    /**
     * Head of the deque
     */
    DequeNode<T> head = null;

    /**
     * Tail of the deque
     */
    DequeNode<T> tail = null;

    /**
     * Size of the deque
     */
    int size = 0;

    /**
     * Adds the specified value to the head of the deque
     *
     * @param val Value to add to the deque
     */
    public void addFirst(T val) {
        // Create a new node with the given value
        DequeNode<T> newNode = new DequeNode<T>(val);

        // Add the node
        if (head == null) {
            // If the deque is empty, add the node as the head and tail
            head = newNode;
            tail = newNode;
        } else {
            // If the deque is not empty, insert the node as the new head
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    /**
     * Adds the specified value to the tail of the deque
     *
     * @param val Value to add to the deque
     */
    public void addLast(T val) {
        // Create a new node with the given value
        DequeNode<T> newNode = new DequeNode<T>(val);

        // Add the node
        if (tail == null) {
            // If the deque is empty, add the node as the head and tail
            head = newNode;
            tail = newNode;
        } else {
            // If the deque is not empty, insert the node as the new tail
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the first (head) value in the deque
     *
     * @return the value of the head of the deque
     */
    public T pollFirst() {
        // If the head is null, return null
        if (head == null) {
            return null;
        }

        // First, let's get the value of the old head
        T oldHeadVal = head.val;

        // Now, let's remove the head
        if (head == tail) {
            // If there is only one node, remove it
            head = null;
            tail = null;
        } else {
            // If there is more than one node, fix the references
            head.next.prev = null;
            DequeNode<T> oldHead = head;
            head = head.next;

            // Can be considered unnecessary...
            // Unlinking the old head to make sure there are no random
            // references possibly affecting garbage collection
            oldHead.next = null;
        }

        size--;
        return oldHeadVal;
    }

    /**
     * Removes and returns the last (tail) value in the deque
     *
     * @return the value of the tail of the deque
     */
    public T pollLast() {
        // If the tail is null, return null
        if (tail == null) {
            return null;
        }

        // Let's get the value of the old tail
        T oldTailVal = tail.val;

        // Now, remove the tail
        if (head == tail) {
            // If there is only one node, remove it
            head = null;
            tail = null;
        } else {
            // If there is more than one node, fix the references
            tail.prev.next = null;
            DequeNode<T> oldTail = tail;
            tail = tail.prev;

            // Similarly to above, can be considered unnecessary
            // See `pollFirst()` for explanation
            oldTail.prev = null;
        }

        size--;
        return oldTailVal;
    }

    /**
     * Returns the first (head) value of the deque WITHOUT removing
     *
     * @return the value of the head of the deque
     */
    public T peekFirst() {
        return head.val;
    }

    /**
     * Returns the last (tail) value of the deque WITHOUT removing
     *
     * @return the value of the tail of the deque
     */
    public T peekLast() {
        return tail.val;
    }

    /**
     * Returns the size of the deque
     *
     * @return the size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether or not the deque is empty
     *
     * @return whether or not the deque is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns a stringified deque in a pretty form:
     *
     * <p>
     * Head -> 1 <-> 2 <-> 3 <- Tail
     *
     * @return the stringified deque
     */
    @Override
    public String toString() {
        String dequeString = "Head -> ";
        DequeNode<T> currNode = head;
        while (currNode != null) {
            dequeString += currNode.val;

            if (currNode.next != null) {
                dequeString += " <-> ";
            }

            currNode = currNode.next;
        }

        dequeString += " <- Tail";

        return dequeString;
    }

    public static void main(String[] args) {
        Deques<Integer> myDeque = new Deques<Integer>();
        for (int i = 0; i < 42; i++) {
            if (i / 42.0 < 0.5) {
                myDeque.addFirst(i);
            } else {
                myDeque.addLast(i);
            }
        }

        System.out.println(myDeque);
        System.out.println("Size: " + myDeque.size());
        System.out.println();

        myDeque.pollFirst();
        myDeque.pollFirst();
        myDeque.pollLast();
        System.out.println(myDeque);
        System.out.println("Size: " + myDeque.size());
        System.out.println();

        int dequeSize = myDeque.size();
        for (int i = 0; i < dequeSize; i++) {
            int removing = -1;
            if (i / 39.0 < 0.5) {
                removing = myDeque.pollFirst();
            } else {
                removing = myDeque.pollLast();
            }

            System.out.println("Removing: " + removing);
        }

        System.out.println(myDeque);
        System.out.println(myDeque.size());
    }
}
