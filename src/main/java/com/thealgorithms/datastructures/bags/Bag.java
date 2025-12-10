package com.thealgorithms.datastructures.bags;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic collection that allows adding and iterating over elements but does not support
 * element removal. This class implements a simple bag data structure, which can hold duplicate
 * elements and provides operations to check for membership and the size of the collection.
 *
 * <p>Bag is not thread-safe and should not be accessed by multiple threads concurrently.
 *
 * @param <E> the type of elements in this bag
 */
public class Bag<E> implements Iterable<E> {

    private Node<E> firstElement; // Reference to the first element in the bag
    private int size; // Count of elements in the bag

    // Node class representing each element in the bag
    private static final class Node<E> {
        private E content;
        private Node<E> nextElement;
    }

    /**
     * Constructs an empty bag.
     * <p>This initializes the bag with zero elements.
     */
    public Bag() {
        firstElement = null;
        size = 0;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return {@code true} if the bag contains no elements; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @return the number of elements currently in the bag
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element to the bag.
     *
     * <p>This method adds the specified element to the bag. Duplicates are allowed, and the
     * bag will maintain the order in which elements are added.
     *
     * @param element the element to add; must not be {@code null}
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
     * <p>This method uses the {@code equals} method of the element to determine membership.
     *
     * @param element the element to check for; must not be {@code null}
     * @return {@code true} if the bag contains the specified element; {@code false} otherwise
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
     * <p>The iterator provides a way to traverse the elements in the order they were added.
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

        /**
         * Constructs a ListIterator starting from the given first element.
         *
         * @param firstElement the first element of the bag to iterate over
         */
        ListIterator(Node<E> firstElement) {
            this.currentElement = firstElement;
        }

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return {@code true} if there are more elements; {@code false} otherwise
         */
        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the bag
         * @throws NoSuchElementException if there are no more elements to return
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the bag.");
            }
            E element = currentElement.content;
            currentElement = currentElement.nextElement;
            return element;
        }
    }
}
