package DataStructures.DynamicArray;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DynamicArray<E> implements Iterable<E> {

    private int capacity = 10;

    private int size = 0;

    private Object[] elements;

    public DynamicArray(final int capacity) {
        this.capacity = capacity;
        this.elements = new Object[this.capacity];
    }

    public DynamicArray() {
        this.elements = new Object[this.capacity];
    }

    public int newCapacity() {
        this.capacity <<= 1;

        return this.capacity;
    }

    public void add(final E element) {
        if (this.size == this.elements.length)
            this.elements = Arrays.copyOf(this.elements, newCapacity());

        this.elements[this.size] = element;
        size++;
    }

    public void put(final int index, E element) {
//        Objects.checkIndex(index, this.size);

        this.elements[index] = element;
    }

    public E get(final int index) {
        return getElement(index);
    }

    public E remove(final int index) {
        final E oldElement = getElement(index);
        fastRemove(this.elements, index);

        return oldElement;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    private void fastRemove(final Object[] elements, final int index) {
        final int newSize = this.size - 1;

        if (newSize > index)
            System.arraycopy(elements, index + 1, elements, index, newSize - index);

        elements[this.size = newSize] = null;
    }

    private E getElement(final int index) {
//        Objects.checkIndex(index, this.size);
        return (E) this.elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(this.elements).filter(Objects::nonNull).toArray());
    }

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

    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>();
        names.add("Peubes");
        names.add("Marley");

        for (String name : names) {
            System.out.println(name);
        }

        names.stream().forEach(System.out::println);

        System.out.println(names);

        System.out.println(names.size());

        names.remove(0);

        for (String name : names) {
            System.out.println(name);
        }
    }
}
