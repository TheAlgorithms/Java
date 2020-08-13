package DataStructures.DynamicArray;

import java.util.*;

/**
 * This class implements a dynamic array
 * @param <E> the type that each index of the array will hold
 */
public class DynamicArray<E> implements Iterable<E> {

    private int capacity;
    private int size;
    private Object[] elements;

    /**
     * constructor
     * @param capacity the starting length of the desired array
     */
    public DynamicArray(final int capacity) {
    	this.size = 0;
        this.capacity = capacity;
        this.elements = new Object[this.capacity];
    }

    /**
     * No-args constructor
     */
    public DynamicArray() {
    	this.size = 0;
    	this.capacity = 10;
        this.elements = new Object[this.capacity];
    }

    /**
     * Doubles the capacity of the array
     * @return int the new capacity of the array
     */
    public int newCapacity() {
        this.capacity *= 2; 
        //changed from this.capacity <<= 1; now much easier to understand
        return this.capacity;
    }

    /**
     * Adds an element to the array
     * If full, creates a copy array twice the size of the current one
     * @param element the element of type <E> to be added to the array
     */
    public void add(final E element) {
        if (this.size == this.elements.length) {
        	this.elements = Arrays.copyOf(this.elements, newCapacity());
        }

        this.elements[this.size] = element;
        size++;
    }
    
    /**
     * Places element of type <E> at the desired index
     * @param index the index for the element to be placed
     * @param element the element to be inserted
     */
    public void put(final int index, E element) {
        this.elements[index] = element;
    }

    /**
     * get method for element at a given index
     * returns null if the index is empty
     * @param index the desired index of the element
     * @return <E> the element at the specified index
     */
    public E get(final int index) {
        return getElement(index);
    }
    
    /**
     * Removes an element from the array
     * @param index the index of the element to be removed
     * @return <E> the element removed
     */
    public E remove(final int index) {
        final E oldElement = getElement(index);
        fastRemove(this.elements, index);

        return oldElement;
    }
    
    /**
     * get method for size field
     * @return int size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * isEmpty helper method
     * @return boolean true if the array contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    private void fastRemove(final Object[] elements, final int index) {
        final int newSize = this.size - 1;

        if (newSize > index) {
        	System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }

        elements[this.size = newSize] = null;
    }

    private E getElement(final int index) {
        return (E) this.elements[index];
    }

    /**
     * returns a String representation of this object
     * @return String a String representing the array
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(this.elements).filter(Objects::nonNull).toArray());
    }

    /**
     * Creates and returns a new Dynamic Array Iterator
     * @return Iterator a Dynamic Array Iterator
     */
    @Override
    public Iterator iterator() {
        return new DynamicArrayIterator();
    }

    private class DynamicArrayIterator implements Iterator<E> {

        private int cursor;

        @Override
        public boolean hasNext() {
            return this.cursor != size;
        }

        @Override
        public E next() {
            if (this.cursor > DynamicArray.this.size) throw new NoSuchElementException();

            if (this.cursor > DynamicArray.this.elements.length) throw new ConcurrentModificationException();

            final E element = DynamicArray.this.getElement(this.cursor);
            this.cursor++;

            return element;
        }

        @Override
        public void remove() {
            if (this.cursor < 0) throw new IllegalStateException();

            DynamicArray.this.remove(this.cursor);
            this.cursor--;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);

            for (int i = 0; i < DynamicArray.this.size; i++) {
                action.accept(DynamicArray.this.getElement(i));
            }
        }
    }

    /**
     * This class is the driver for the DynamicArray<E> class
     * it tests a variety of methods and prints the output
     */
    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>();
        names.add("Peubes");
        names.add("Marley");

        for (String name : names) {
            System.out.println(name);
        }

        names.stream().forEach(System.out::println);

        System.out.println(names);

        System.out.println(names.getSize());

        names.remove(0);

        for (String name : names) {
            System.out.println(name);
        }
    }
}
