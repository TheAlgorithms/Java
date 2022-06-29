/**
 * com.thealgorithms.datastructures.lists
 */
package com.thealgorithms.datastructures.lists;
/**
 * TheAlgorithms
 * Class CircleLinkedList
 */
public class CircleLinkedList<E> {

    /**
     * Class Node
     */
    private static class Node<E> {
        /** 
         * class variables <br>
         * components of the composite data object Node */
        Node<E> next;   // acts like a pointer on the next element of a composite data object
        E value;    // acts like the key of a composite data object
        /**
         * Constructor for class Node taking two parameters
         * @param value the E(-type) to assign to a new object of Node
         * @param next the Node to assign to a new object of Node (pointing to the following list element)
         */
        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /** int size: <br>
     * Variable to store the size of a list
     * For better O.O design this should be private and allows for better black box design
     */
    private int size;
    /** Node<E> head: <br>
     * This will point to dummy node <br>
     * Starting point of a list
     */
    private Node<E> head = null;
    /** Node<E> tail: <br>
     * keeping a tail pointer to keep track of the end of list <br>
     * End point of a list
     */
    private Node<E> tail = null;

    /** CircleLinkedList(): <br>
     * Constructor for class.. <br>
     * here we will make a dummy node for circly linked list <br>
     * implementation with reduced error catching as our list will never be empty
     */
    public CircleLinkedList() {
        // creation of the dummy node
    	// E inside diamond nodes is obsolete here
        head = new Node<>(null, head);
        tail = head;
        size = 0;
    }

    /** getSize(): <br>
     * getter for the size... needed because size is private.
     * @return int returns the size of a given list
     */
    public int getSize() {
        return size;
    }

    /** append(E value): <br>
     * For the sake of simplicity this class will only contain the append function <br>
     * or addLast other <br>
     * add functions can be implemented however this is the basses of them all <br>
     * really.
     * @param value a value given of type E
     */
    public void append(E value) {
        if (value == null) {
            // we do not want to add null elements to the list.
            throw new NullPointerException("Cannot add null element to the list");
        }
        // head.next points to the last element;
        if (tail == null) {
            tail = new Node<>(value, head); // E inside diamond nodes is obsolete here
            head.next = tail;
        } else {
            tail.next = new Node<>(value, head); // E inside diamond nodes is obsolete here
            tail = tail.next;
        }
        size++;
    }

    /** toString(): <br>
     * Utility function for traversing the list
     * @return String 
     */
    @Override // toString() method without parameters should usually have an @Override annotation to make sure the custom implementation is used
    public String toString() {
        Node<E> p = head.next;
        String s = "[ ";
        while (p != head) {
            s += p.value;
            s += " , ";
            p = p.next;
        }
        return s + " ]";
    }

    /** main(String args[]): <br>
     * Starting point of the program
     * @param args[] Standard main parameter
     */
    public static void main(String args[]) {
        CircleLinkedList<Integer> cl = new CircleLinkedList<Integer>(); // Parameter for generic type was missing for constructor call
        cl.append(12);
        System.out.println(cl);
        cl.append(23);
        System.out.println(cl);
        cl.append(34);
        System.out.println(cl);
        cl.append(56);
        System.out.println(cl);
        cl.remove(3);
        System.out.println(cl);
    }

    /** remove(int pos): <br>
     * @param pos the given position for the element to delete
     * @return E returns the value of type E
     */
    public E remove(int pos) {
        if (pos > size || pos < 0) {
            // catching errors
            throw new IndexOutOfBoundsException("position cannot be greater than size or negative");
        }
        // we need to keep track of the element before the element we want to remove we
        // can see why
        // bellow.
        Node<E> before = head;
        for (int i = 1; i <= pos; i++) {
            before = before.next;
        }
        Node<E> destroy = before.next;
        E saved = destroy.value;
        // assigning the next reference to the element following the element we want to
        // remove...
        // the last element will be assigned to the head.
        before.next = before.next.next;
        // scrubbing
        if (destroy == tail) {
            tail = before;
        }
        destroy = null;
        size--;
        return saved;
    }
}
