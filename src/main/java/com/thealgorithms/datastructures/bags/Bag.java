package com.thealgorithms.datastructures.bags;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection that allows adding and iterating over elements but does not support element removal.
 *
 * @param <E> the type of elements in this bag
 */
public class Bag<E> implements Iterable<E> {

    private Node<E> firstElement; // First element in the bag
    private int size; // Number of elements in the bag

    // Node class representing each element in the bag
    private static final class Node<E> {
        private E content;
        private Node<E> nextElement;
    }

    /**
     * Constructs an empty bag.
     */
    public Bag() {
        firstElement = null;
        size = 0;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the bag.
     *
     * @param element the element to add
     */
    public void add(E element) {
        Node<E> newNode = new Node<>();
        newNode.content = element;
        newNode.nextElement = firstElement;
        firstElement = newNode;
        size++;
    }

    /**
     * Checks if the bag contains a specific element.
     *
     * @param element the element to check for
     * @return true if the bag contains the element, false otherwise
     */
    public boolean contains(E element) {
        for (E value : this) {
            if (value.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this bag.
     *
     * @return an iterator that iterates over the elements in the bag
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(firstElement);
    }

    // Private class for iterating over elements
    private static class ListIterator<E> implements Iterator<E> {

        private Node<E> currentElement;

        ListIterator(Node<E> firstElement) {
            this.currentElement = firstElement;
        }

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = currentElement.content;
            currentElement = currentElement.nextElement;
            return element;
        }
    }
}
