/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

import interfaces.Iterator;

class VectorIterator<T> implements interfaces.Iterator<T> {

	private Vector<T> container;
	private int current;

	public VectorIterator(Vector<T> vector) {
		this.container = vector;
		this.current = -1;
	}

	@Override
	public void insert(T value) {
		if (current < 0)
			throw new IllegalStateException("Usage before next().");
		container.insert(current, value);
		current++;
	}

	@Override
	public void remove() {
		if (current < 0)
			throw new IllegalStateException("Usage before next().");
		container.remove(this.current);
	}

	@Override
	public T next() {
		current++;
		return container.get(current);
	}

	@Override
	public boolean hasNext() {
		return current < container.size()-1;
	}
}

/**
 * Implements a list data structure where elements are stored in
 * contiguous memory.
 */
public class Vector<T> implements interfaces.Iterable<T> {

	private Object[] data = new Object[16];
	private int count = 0;

	/**
	 * Inserts a new element to the vector at the specified index.
	 * The index must be between 0 and the number of elements already
	 * stored.
	 * @param index The index to insert the new value.
	 * @param value The value to be stored.
	 */
	public void insert(int index, T value) {
		if (index < 0 || index > count)
			throw new ArrayIndexOutOfBoundsException(index);
		ensureSpaceAvailable();
		System.arraycopy(data, index, data, index+1, count-index);
		data[index] = value;
		count++;
	}

	/**
	 * Query the number of elements stored.
	 * @return The current number of elements stored.
	 */
	public int size() {
		return count;
	}

	/**
	 * Remove the element at the given index, if its between 0 and
	 * the number of elements stored.
	 * @param index The index of the element to be removed.
	 */
	public void remove(int index) {
		validateIndex(index);
		ensureSpaceAvailable();
		System.arraycopy(data, index+1, data, index, count-index-1);
		count--;
		data[count] = null;
	}

	/**
	 * Append a value to the end of the data.
	 * @param value The value to be stored.
	 */
	public void append(T value) {
		insert(count,value);
	}

	private void validateIndex(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException(index);
	}
	
	/**
	 * Returns an element stored at a given index.
	 * @param index The index of the element to be retrieved.
	 * @return The element at the given index.
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		validateIndex(index);
		return (T)data[index];
	}
	
	// resize the underlying storage pool if necessary.
	private void ensureSpaceAvailable() {
		if (count < data.length)
			return;
		Object[] novo = new Object[newSize()];
		System.arraycopy(data, 0, novo, 0, count);
		data = novo;
	}

	// compute the new size of the storage pool.
	private int newSize() {
		final int INCREASE_LIMIT = 1024;
		return data.length > INCREASE_LIMIT ?
				    data.length + INCREASE_LIMIT :
					data.length*2;
	}

	/**
	 * Returns the last element of the vector.
	 * @return The last element of the vector.
	 */
	@SuppressWarnings("unchecked")
	public T last() {
		if (count > 0)
			return (T)data[count-1];
		throw new IllegalStateException("Vector is empty.");
	}
	
	/**
	 * Check if there are elements in the Vector.
	 * @return True if there are no elements stored, false otherwise.
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * Return an iterator for the underlying vector.
	 */
	@Override
	public Iterator<T> iterator() {
		return new VectorIterator<>(this);
	}

	/**
	 * Set an element of the vector at a given index to the given value.
	 * @param level
	 * @param node
	 */
	public void set(int index, T value) {
		validateIndex(index);
		data[index] = value;
	}
}
