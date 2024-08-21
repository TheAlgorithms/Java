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
 * This class implements a dynamic array.
 *
 * @param <E> the type that each index of the array will hold
 */
public class DynamicArray<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    private int modCount; // Tracks structural modifications for the iterator
    private Object[] elements;

    /**
     * Constructor with initial capacity.
     *
     * @param capacity the starting length of the desired array
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
     * No-args constructor with default capacity.
     */
    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Adds an element to the array. If full, creates a new array with double the size.
     *
     * @param element the element to be added to the array
     */
    public void add(final E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
        modCount++; // Increment modification count
    }

    /**
     * Places an element at the desired index, expanding capacity if necessary.
     *
     * @param index   the index for the element to be placed
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if n is less than 0 or greater or equal to the number of elements in the array
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
     * Gets the element at a given index.
     *
     * @param index the desired index of the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if n is less than 0 or greater or equal to the number of elements in the array
     */
    @SuppressWarnings("unchecked")
    public E get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    /**
     * Removes an element from the array.
     *
     * @param index the index of the element to be removed
     * @return the element removed
     * @throws IndexOutOfBoundsException if n is less than 0 or greater or equal to the number of elements in the array
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
     * Gets the size of the array.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the array is empty.
     *
     * @return true if the array contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Clear to let GC do its work
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator();
    }

    private final class DynamicArrayIterator implements Iterator<E> {

        private int cursor;
        private int expectedModCount;

        DynamicArrayIterator() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return (E) elements[cursor++];
        }

        @Override
        public void remove() {
            if (cursor <= 0) {
                throw new IllegalStateException();
            }
            checkForComodification();
            DynamicArray.this.remove(--cursor);
            expectedModCount = ++modCount;
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (hasNext()) {
                action.accept(next());
            }
        }
    }
}
