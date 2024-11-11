package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A generic implementation of a max heap data structure.
 *
 * @param <T> the type of elements in this heap, must extend Comparable.
 */
public class GenericHeap<T extends Comparable<T>> {

    private final ArrayList<T> data = new ArrayList<>();
    private final HashMap<T, Integer> map = new HashMap<>();

    /**
     * Adds an item to the heap, maintaining the heap property.
     *
     * @param item the item to be added
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot insert null into the heap.");
        }

        this.data.add(item);
        map.put(item, this.data.size() - 1);
        upHeapify(this.data.size() - 1);
    }

    /**
     * Restores the heap property by moving the item at the given index upwards.
     *
     * @param ci the index of the current item
     */
    private void upHeapify(int ci) {
        int pi = (ci - 1) / 2;
        if (ci > 0 && isLarger(this.data.get(ci), this.data.get(pi)) > 0) {
            swap(pi, ci);
            upHeapify(pi);
        }
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the size of the heap
     */
    public int size() {
        return this.data.size();
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes and returns the maximum item from the heap.
     *
     * @return the maximum item
     */
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        this.swap(0, this.size() - 1);
        T rv = this.data.remove(this.size() - 1);
        map.remove(rv);
        downHeapify(0);
        return rv;
    }

    /**
     * Restores the heap property by moving the item at the given index downwards.
     *
     * @param pi the index of the current item
     */
    private void downHeapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int mini = pi;
        if (lci < this.size() && isLarger(this.data.get(lci), this.data.get(mini)) > 0) {
            mini = lci;
        }
        if (rci < this.size() && isLarger(this.data.get(rci), this.data.get(mini)) > 0) {
            mini = rci;
        }
        if (mini != pi) {
            this.swap(pi, mini);
            downHeapify(mini);
        }
    }

    /**
     * Retrieves the maximum item from the heap without removing it.
     *
     * @return the maximum item
     */
    public T get() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return this.data.getFirst();
    }

    /**
     * Compares two items to determine their order.
     *
     * @param t the first item
     * @param o the second item
     * @return a positive integer if t is greater than o, negative if t is less, and zero if they are equal
     */
    private int isLarger(T t, T o) {
        return t.compareTo(o);
    }

    /**
     * Swaps two items in the heap and updates their indices in the map.
     *
     * @param i index of the first item
     * @param j index of the second item
     */
    private void swap(int i, int j) {
        T ith = this.data.get(i);
        T jth = this.data.get(j);
        this.data.set(i, jth);
        this.data.set(j, ith);
        map.put(ith, j);
        map.put(jth, i);
    }

    /**
     * Updates the priority of the specified item by restoring the heap property.
     *
     * @param item the item whose priority is to be updated
     */
    public void updatePriority(T item) {
        if (!map.containsKey(item)) {
            throw new IllegalArgumentException("Item not found in the heap");
        }
        int index = map.get(item);
        upHeapify(index);
    }
}
