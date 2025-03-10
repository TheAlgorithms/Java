package com.thealgorithms.datastructures.dynamicarray;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class implements a dynamic array, which can grow or shrink in size
 * as elements are added or removed. It provides an array-like interface
 * with methods to add, remove, and access elements, along with iterators
 * to traverse the elements.
 *
 * @param <E> the type of elements that this array can hold
 */
public class DynamicArray<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    private int modCount; // Tracks structural modifications for iterator integrity
    private Object[] elements;

    /**
     * Constructs a new DynamicArray with the specified initial capacity.
     *
     * @param capacity the initial capacity of the array
     * @throws IllegalArgumentException if the specified capacity is negative
     */
    public DynamicArray(final int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.size = 0;
        this.modCount = 0;
        this.elements = new Object[capacity];
    }

    /**
     * Constructs a new DynamicArray with a default initial capacity.
     */
    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Adds an element to the end of the array. If the array is full, it
     * creates a new array with double the size to accommodate the new element.
     *
     * @param element the element to be added to the array
     */
    public void add(final E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
        modCount++; // Increment modification count
    }

    /**
     * Places an element at the specified index, expanding capacity if necessary.
     *
     * @param index   the index at which the element is to be placed
     * @param element the element to be inserted at the specified index
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the number of elements
     */
    public void put(final int index, E element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }
        ensureCapacity(index + 1);
        elements[index] = element;
        if (index >= size) {
            size = index + 1;
        }
        modCount++; // Increment modification count
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the current size
     */
    @SuppressWarnings("unchecked")
    public E get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the array
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the current size
     */
    public E remove(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        @SuppressWarnings("unchecked") E oldElement = (E) elements[index];
        fastRemove(index);
        modCount++; // Increment modification count
        return oldElement;
    }

    /**
     * Returns the current number of elements in the array.
     *
     * @return the number of elements in the array
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks whether the array is empty.
     *
     * @return true if the array contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a sequential stream with this collection as its source.
     *
     * @return a stream of the elements in the array
     */
    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Ensures that the array has enough capacity to hold the specified number of elements.
     *
     * @param minCapacity the minimum capacity required
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Removes the element at the specified index without resizing the array.
     * This method shifts any subsequent elements to the left and clears the last element.
     *
     * @param index the index of the element to remove
     */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Clear to let GC do its work
    }

    /**
     * Returns a string representation of the array, including only the elements that are currently stored.
     *
     * @return a string containing the elements in the array
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

    /**
     * Returns an iterator over the elements in this array in proper sequence.
     *
     * @return an Iterator over the elements in the array
     */
    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator();
    }

    /**
     * Private iterator class for the DynamicArray.
     */
    private final class DynamicArrayIterator implements Iterator<E> {

        private int cursor;
        private int expectedModCount;

        /**
         * Constructs a new iterator for the dynamic array.
         */
        DynamicArrayIterator() {
            this.expectedModCount = modCount;
        }

        /**
         * Checks if there are more elements in the iteration.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            checkForComodification();
            return cursor < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return (E) elements[cursor++];
        }

        /**
         * Removes the last element returned by this iterator.
         *
         * @throws IllegalStateException if the next method has not yet been called, or the remove method has already been called after the last call to the next method
         */
        @Override
        public void remove() {
            if (cursor <= 0) {
                throw new IllegalStateException("Cannot remove element before calling next()");
            }
            checkForComodification();
            DynamicArray.this.remove(--cursor);
            expectedModCount = modCount;
        }

        /**
         * Checks for concurrent modifications to the array during iteration.
         *
         * @throws ConcurrentModificationException if the array has been modified structurally
         */
        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Performs the given action for each remaining element in the iterator until all elements have been processed.
         *
         * @param action the action to be performed for each element
         * @throws NullPointerException if the specified action is null
         */
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (hasNext()) {
                action.accept(next());
            }
        }
    }
}
