/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

import java.util.Comparator;

import util.Functions;

public class BinaryHeap<T> {
	
	private T[] data;
	private int count;
	private Comparator<T> cmp;
	
	/**
	 * Store the number of operations on the last push
	 */
	public long pushOperations;
	/**
	 * Store the number of operations on the last pop
	 */
	public long popOperations;
	/**
	 * Store the number of operations on the last heapify
	 */
	public long heapifyOperations;
	/**
	 * <p>Creates a new heap, given the comparator to use.</p>
	 * @param cmp The comparator function. Use FunctionObjects.less to
	 * create a <i>minimum heap</i>, use FunctionObjects.greater to
	 * create a <i>maximum heap</i>.
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(Comparator<T> cmp) {
		this.data = (T[])new Object[16];
		this.count = 0;
		this.cmp = cmp;
	}

	// Used by the static heapify methods.
	private BinaryHeap() {
		this.data = null;
		this.count = 0;
		this.cmp = null;
	}
	
	/**
	 * <p>Creates a binary heap from existing data. The data should be,
	 * after this call, be owned only by the heap.</p>
	 * @param data The data to be used for the heap.
	 * @return The created BinaryHeap.
	 */
	public static <T extends Comparable<T>> BinaryHeap<T> heapify(T[] data) {
		return heapify(data, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
	}
	/**
	 * Creates a binary heap from existing data. The data should be, after
	 * this call, be owned only by the heap.
	 * @param data The data to be used for the heap.
	 * @param cmp The comparator to use to create the heap.
	 * @return The created BinaryHeap.
	 */
	public static <T> BinaryHeap<T> heapify(T[] data, Comparator<T> cmp) {
		BinaryHeap<T> heap = new BinaryHeap<>();
		heap.heapifyOperations = 0;
		heap.data = data;
		heap.count = data.length;
		heap.cmp = cmp;
		// heapify
		for (int n = data.length / 2; n >= 0; n--) {
			heap.siftDown(n);
		}
		return heap;
	}

	private void siftDown(int n) {
		heapifyOperations++;
		int left = 2*n+1;
		int right = 2*n+2;
		int other = n;
		if (left >= count)
			return;
		if (right >= count) {
			other = left;
		} else {
			if (cmp.compare(data[left], data[right]) < 0) {
				other = left;
			} else {
				other = right;
			}
		}
		if (cmp.compare(data[n], data[other]) > 0) {
			heapifyOperations++;
			Functions.swap(data,n,other);
			siftDown(other);
		}
	}
	
	/**
	 * <p>Query if there are elements on the heap.</p>
	 * @return True if there are elements, false otherwise.
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * <p>Return the element at the top of the heap, without removing it.</p>
	 * @return The element at the top of the heap.
	 */
	public T peek() {
		return data[0];
	}
	
	/**
	 * <p>Add a new element to the heap.</p>
	 * @param elem The element to be added to the heap.
	 */
	public void push(T elem) {
		// This is the same as a siftUp operation, and is
		// slower than a siftDown.
		pushOperations = 0;
		ensureEnoughSpace();
		data[count] = elem;
		int e = count;
		count++;
		while (e > 0) {
			int r = (e-1)/2;
			pushOperations++;
			if (cmp.compare(data[e], data[r]) < 0) {
				pushOperations++;
				Functions.swap(data, e, r);
			}
			e = r;
		}
	}
	
	//
	@SuppressWarnings("unchecked")
	private void ensureEnoughSpace() {
		if (count < data.length)
			return;
		int sz = data.length > 1024 ? data.length+1024 : data.length*2;
		Object[] store = new Object[sz];
		System.arraycopy(data,0, store, 0, count);
		data = (T[])store;
	}
	
	/**
	 * <p>Remove the top element of the heap and return it.</p>
	 * @return The top element of the heap, or null, if the heap is empty.
	 */
	public T pop() {
		if (count == 0)
			return null;
		int r = 0;
		T res = data[r];
		count--;
		data[r] = data[count];
		siftDown(r);
		return res;
	}

	/**
	 * <p>Query the number of elements stored on the heap.</p>
	 * @return The current number of elements.
	 */
	public int size() {
		return count;
	}
}
