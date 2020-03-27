package com.dataStructures.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * Implementation of a Generic Bag Datastructure
 * @param <Element> - generic type of elements in bag
 */
public class Bag<Element> implements Iterable<Element> {

    private Node<Element> firstElement; //first element of the bag
    private int size;                   //size of the bag

    /**
     * Inner static class for elements of bag
     * @param <Element>
     */
    private static class Node<Element> {
        private Element content;
        private Node<Element> nextElement;
    }

    /**
     * Constructor
     * initializes an empty bag
     */
    public Bag() {
        firstElement = null;
        size = 0;
    }

    /**
     * @return true if this bag is empty;
     *         false otherwise
     */
    public boolean isEmpty() {
        return firstElement == null;
    }

    /**
     * @return the size of the bag, i.e. the number of items
     */
    public int size() {
        return size;
    }

    /**
     *Add items to bag
     * @param element - element to be added
     */
    public void addElement(Element element) {
        Node<Element> oldfirst = firstElement;
        firstElement = new Node<>();
        firstElement.content = element;
        firstElement.nextElement = oldfirst;
        size++;
    }

    /**
     * Check whether element is in bag
     * @param element
     * @return true if element is in bag
     *         false otherwise
     */
    public boolean contains(Element element) {
        Iterator<Element> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return iterator over elements in bag
     */
    @Override
    public Iterator<Element> iterator() {
        return new ListIterator<>(firstElement);
    }

    /**
     * private inner class iplements an iterator
     */
    private class ListIterator<Element> implements Iterator<Element> {
        private Node<Element> currentElement;

        public ListIterator(Node<Element> firstElement) {
            currentElement = firstElement;
        }

        @Override
        public boolean hasNext() {
            return currentElement != null;

        }

        @Override
        public Element next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Element element = currentElement.content;
            currentElement = currentElement.nextElement;
            return element;
        }
    }
}
